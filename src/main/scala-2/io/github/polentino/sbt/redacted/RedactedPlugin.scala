package io.github.polentino.sbt.redacted

import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbt.Keys.libraryDependencies
import sbt._

object RedactedPlugin extends AutoPlugin {
  override def trigger: PluginTrigger = allRequirements
  override def requires: Plugins = org.portablescala.sbtplatformdeps.PlatformDepsPlugin

  object autoImport {
    val redactedVersion = settingKey[String]("The version of redacted library & compiler plugin to use.")
  }

  import autoImport._

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    libraryDependencies ++= Seq(
      "io.github.polentino" %%% "redacted" % redactedVersion.value cross CrossVersion.binary,
      compilerPlugin("io.github.polentino" %% "redacted-plugin" % redactedVersion.value cross CrossVersion.full)
    )
  )
}
