import sbt.Keys.publish

ThisBuild / scalaVersion := "2.12.21"

// interesting; bumping scalatest / scalacheck, makes 3.1.x and 3.2.x compilation to fail
val scalaTestVersion = "3.2.19"
val scalaCheckVersion = "3.2.17.0"

lazy val root = (project in file("."))
  .enablePlugins(RedactedPlugin)
  .settings(
    redactedVersion := "0.9.9",
    version := "0.1",
    publish / skip := true,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
      "org.scalatestplus" %% "scalacheck-1-17" % scalaCheckVersion % Test
    )
  )
