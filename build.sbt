ThisBuild / crossScalaVersions := Seq("2.12.20", "3.6.4")
ThisBuild / sbtPlugin := true
ThisBuild / scalaVersion := crossScalaVersions.value.head

inThisBuild(
  List(
    organization := "io.github.polentino",
    homepage := Some(url("https://github.com/polentino/sbt-redacted")),
    licenses := List(
      "WTFPL" -> url("http://www.wtfpl.net/")
    ),
    developers := List(
      Developer(
        "polentino",
        "Diego Casella",
        "polentino911@gmail.com",
        url("https://linkedin.com/in/diegocasella")
      )
    )
  )
)

lazy val checkStatus = taskKey[Unit]("Fail build if CI_BUILD is not set & version suffix contains `-SNAPSHOT`")

checkStatus := {
  val isCiBuild = sys.env.contains("CI_BUILD")
  val isSnapshot = version.value.endsWith("-SNAPSHOT")

  // xor would be more concise, but having expressive log message is better
  val errorMessage = (isCiBuild, isSnapshot) match {
    case (true, true) => Some("when running from a CI build pipeline, version cannot be `xyz-SNAPSHOT`.")
    case (false, false) => Some("when running from locally, the version must be `xyz-SNAPSHOT`.")
    case _ => None
  }

  errorMessage.fold(Unit){ msg =>
    sys.error(s"""⚠️ Invalid status: $msg""".stripMargin)
  }
}

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-redacted",
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalacOptions := {
      scalaBinaryVersion.value match {
        case "2.12" => Seq("-Xsource:3", "-Xfatal-warnings", "-unchecked", "-deprecation", "-feature", "-language:implicitConversions")
        case _      => Seq("-Vdebug")
      }
    },
    (pluginCrossBuild / sbtVersion) := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.5.8"
        case _      => "2.0.0-RC4"
      }
    },
    scriptedSbt := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.10.7"
        case _      => "2.0.0-RC4"
      }
    },
    scripted := scripted.dependsOn(checkStatus).evaluated,
    scriptedLaunchOpts := scriptedLaunchOpts.value ++ Seq("-Xmx1024M", "-Dplugin.version=" + version.value),
    scriptedBufferLog := false
  )

addCommandAlias("fmt", "; checkStatus; scalafix; scalafmtAll; scalafmtSbt")
addCommandAlias("fmtCheck", "; checkStatus; scalafmtCheckAll ; scalafmtSbtCheck")
addCommandAlias("crossReleaseAll", "; checkStatus; clean; +publishSigned; sonatypeBundleRelease")