import sbt.Keys.publish

ThisBuild / scalaVersion := "3.7.4"

val scalaTestVersion = "3.2.19"
val scalaCheckVersion = "3.2.19.0"

lazy val root = (project in file("."))
  .enablePlugins(RedactedPlugin, ScalaNativePlugin)
  .settings(
    redactedVersion := "0.10.0-SNAPSHOT",
    version := "0.1",
    publish / skip := true,
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "org.scalatestplus" %%% "scalacheck-1-18" % scalaCheckVersion % Test
    )
  )
