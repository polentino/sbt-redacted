addSbtPlugin("org.scalameta"  % "sbt-scalafmt" % "2.5.4")
addSbtPlugin("ch.epfl.scala"  % "sbt-scalafix" % "0.14.2")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.12.2")
addSbtPlugin("com.github.sbt" % "sbt-pgp"      % "2.3.1")

libraryDependencies += "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value
