todo insert badges

# sbt-redacted

[sbt](https://www.scala-sbt.org/) plugin to add and configure [redacted](https://github.com/polentino/redacted) compiler plugin and annotation library
in your sbt build.

## How to install

In your `project/plugins.sbt` add the following line

> addSbtPlugin("io.github.polentino" % "sbt-redacted" % "0.1.0")

and then enable it in your specific (sub)project in `build.sbt` like so

> lazy val root = (project in file("."))  
> &nbsp;&nbsp;&nbsp;&nbsp;.enablePlugins(RedactedPlugin)  
> &nbsp;&nbsp;&nbsp;&nbsp;.setting(...)