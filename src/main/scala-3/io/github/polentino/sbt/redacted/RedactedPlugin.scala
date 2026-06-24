package io.github.polentino.sbt.redacted

import sbt.Keys.libraryDependencies
import sbt.librarymanagement.Platform
import sbt._

object RedactedPlugin extends AutoPlugin {
  override def trigger: PluginTrigger = allRequirements

  object autoImport {
    val redactedVersion = settingKey[String]("The version of redacted library & compiler plugin to use.")
  }

  import autoImport._

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    libraryDependencies ++= Seq(
      ("io.github.polentino" %% "redacted" % redactedVersion.value)
        .cross(CrossVersion.binary)
        .platform(Platform.jvm),
      (compilerPlugin("io.github.polentino" %% "redacted-plugin" % redactedVersion.value))
        .cross(CrossVersion.full)
        .platform(Platform.jvm)
    )
  )
}
