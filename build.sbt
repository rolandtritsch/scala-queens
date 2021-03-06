lazy val commonSettings = Seq(
  name := "scala-queens",
  version := "0.3.0",
  scalaVersion := "3.0.0-RC1",
  scalacOptions ++= Seq(
    "-Xfatal-warnings"
  )
)

lazy val libsLogging = Seq(
  libraryDependencies += "org.codehaus.janino" % "janino" % "3.1.3",
  libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
  libraryDependencies += "org.log4s" %% "log4s" % "1.10.0-M5"
)

lazy val libsTesting = Seq(
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.5",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.5" % "test"
)

lazy val root = project
  .in(file("."))
  .settings(commonSettings)
  .settings(libsLogging)
  .settings(libsTesting)
