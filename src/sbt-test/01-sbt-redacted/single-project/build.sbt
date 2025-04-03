import sbt.Keys.publish

val supportedScalaVersions = List(
  "2.12.20",
  "2.13.16",
  "3.1.3",
  "3.2.2",
  "3.3.0",
  "3.3.1",
  "3.3.3",
  "3.3.4",
  "3.3.5",
  "3.4.3",
  "3.5.2",
  "3.6.4"
)

// interesting; bumping scalatest / scalacheck, makes 3.1.x and 3.2.x compilation to fail
val scalaTestVersion = "3.2.19"
val scalaCheckVersion = "3.2.17.0"

lazy val root = (project in file("."))
  .enablePlugins(RedactedPlugin)
  .settings(
    redactedVersion := "0.7.1",
    version := "0.1",
    scalaVersion := "2.12.20",
    publish / skip := true,
    crossScalaVersions := supportedScalaVersions,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
      "org.scalatestplus" %% "scalacheck-1-17" % scalaCheckVersion % Test
    )
  )