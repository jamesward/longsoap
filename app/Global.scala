import java.util.concurrent.TimeUnit

import org.springframework.context.support.ClassPathXmlApplicationContext
import play.api.libs.concurrent.Promise
import play.api.mvc.{Result, RequestHeader, WithFilters, Filter}
import play.api.{Application, GlobalSettings}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random

object RandomDelayFilter extends Filter {
  def apply(nextFilter: (RequestHeader) => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val randomDelay = Random.nextInt(30) + 15
    Promise.timeout(Unit, randomDelay, TimeUnit.SECONDS).flatMap(_ => nextFilter(requestHeader))
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
