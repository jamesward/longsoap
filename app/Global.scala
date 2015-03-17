import java.util.concurrent.TimeUnit

import org.springframework.context.support.ClassPathXmlApplicationContext
import play.api.Application
import play.api.http.MimeTypes
import play.api.libs.concurrent.Promise
import play.api.libs.iteratee.Enumerator
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random

object RandomDelayFilter extends Filter {

  override def apply(nextFilter: (RequestHeader) => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val randomDelay = Random.nextInt(30) + 15

    val timeoutFuture = Promise.timeout(Unit, randomDelay, TimeUnit.SECONDS)
    val nextBodyFuture = nextFilter(requestHeader).map(_.body)

    val responseAfterDelay = for {
      _    <- timeoutFuture
      body <- nextBodyFuture
    } yield body

    val tick = Enumerator.generateM[Array[Byte]] {
      def maybeTick = if (responseAfterDelay.isCompleted) {
        None
      } else {
        Some(" ".getBytes)
      }
      Promise.timeout(maybeTick, 1, TimeUnit.SECONDS)
    }

    val e = tick.interleave(Enumerator.flatten(responseAfterDelay) &> Results.dechunk)

    Future.successful(Results.Ok.feed(e).as(MimeTypes.XML))
  }

}

object Global extends WithFilters(RandomDelayFilter) {

  val ctx = new ClassPathXmlApplicationContext("applicationContext.xml")

  override def onStart(app: Application) {
    super.onStart(app)
    ctx.start()
  }

  override def onStop(app: Application) {
    ctx.stop()
    super.onStop(app)
  }

}