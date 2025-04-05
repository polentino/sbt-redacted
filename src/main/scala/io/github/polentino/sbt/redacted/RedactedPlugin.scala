package io.github.polentino.sbt.redacted

import sbt.Keys.libraryDependencies
import sbt._

object RedactedPlugin extends AutoPlugin {
  object autoImport {
    val redactedVersion = settingKey[String]("The version of redacted library & compiler plugin to use.")
  }

  import autoImport._

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    redactedVersion := "0.7.1",
    libraryDependencies ++= Seq(
      "io.github.polentino" %% "redacted" % redactedVersion.value cross CrossVersion.full,
      compilerPlugin("io.github.polentino" %% "redacted-plugin" % redactedVersion.value cross CrossVersion.full)
    )
  )
}
