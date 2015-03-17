name := "longsoap"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  "org.springframework" % "spring-expression" % "4.0.3.RELEASE",
  "org.springframework" % "spring-aop" % "4.0.3.RELEASE",
  "eu.imind.play" %% "play-cxf_play23" % "1.1.0",
  "org.apache.cxf" % "cxf-rt-bindings-soap" % "2.7.7",
  "org.apache.cxf" % "cxf-rt-frontend-jaxws" % "2.7.7"
)
