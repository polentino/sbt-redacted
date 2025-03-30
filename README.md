todo insert badges

# sbt-redacted

[sbt](https://www.scala-sbt.org/) plugin to add and configure [redacted](https://github.com/polentino/redacted) compiler
plugin and annotation library
in your sbt build.

## How to install

In your `project/plugins.sbt` add the following line

> addSbtPlugin("io.github.polentino" % "sbt-redacted" % "0.1.0")

and then enable it in your specific (sub)project in `build.sbt` like so

> lazy val root = (project in file("."))  
> &nbsp;&nbsp;&nbsp;&nbsp;.enablePlugins(RedactedPlugin)  
> &nbsp;&nbsp;&nbsp;&nbsp;.setting(...)

## Local development

According to the
sbt [plugin development guidelines](https://www.scala-sbt.org/1.x/docs/Testing-sbt-plugins.html#step+1%3A+snapshot),
local development of a sbt plugin must have a version with `-SNAPSHOT` suffix; on the other hand, the CI needs a proper
release version to be published. For this reason, there's a little task called `checkStatus` to ensure the proper
version is used locally and in the CI pipeline.

| CI_BUILD | -SNAPSHOT | Status Check |
|:--------:|:---------:|:------------:|
|    T     |     T     |      F       |
|    T     |     F     |      T       |
|    F     |     V     |      T       |
|    F     |     F     |      F       |
