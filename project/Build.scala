package org.tritsch.scala.queens.build

import sbt._
import sbt.Keys._

object Build extends sbt.Build {
  lazy val root = Project(
    id = "queens",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "Solution for the Queens Problem",
      version := "0.1",
      scalaVersion := "2.10.1",
      resolvers += Opts.resolver.sonatypeSnapshots,
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "1.9.1" % "test",
        "com.typesafe" %% "scalalogging-slf4j" % "1.1.0-SNAPSHOT",
        "com.typesafe" %% "scalalogging-log4j" % "1.1.0-SNAPSHOT",
        "org.slf4j" % "slf4j-log4j12" % "1.7.5"
      )
    )
  )
}
