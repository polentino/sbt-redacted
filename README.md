todo insert badges

# sbt-redacted

[sbt](https://www.scala-sbt.org/) plugin to add and configure [redacted](https://github.com/polentino/redacted) compiler
plugin and annotation library
in your sbt build.

## How to install

In your `project/plugins.sbt` add the following line

```scala
addSbtPlugin("io.github.polentino" % "sbt-redacted" % "0.1.0")
```

and then enable it in your specific (sub)project in `build.sbt` like so

```scala
lazy val root = (project in file("."))
  .enablePlugins(RedactedPlugin)
  .setting(
    redactedVersion := "0.7.1"
    // your usual config goes here
)
```

That's it! You can now start using [redacted](https://github.com/polentino/redacted) in your project :tada:

## Local development

According to the
sbt [plugin development guidelines](https://www.scala-sbt.org/1.x/docs/Testing-sbt-plugins.html#step+1%3A+snapshot),
local development must have `-SNAPSHOT` version:

> Before you start, set your version to a -SNAPSHOT one because scripted-plugin will publish your plugin locally. If you
> don’t use SNAPSHOT, you could get into a horrible inconsistent state of you and the rest of the world seeing different
> artifacts.

On the other hand, the CI needs a proper release version to be published. For this reason, there's a little task called
`checkStatus` to ensure the proper version is used locally and in the CI pipeline: its purpose is to force usage of
`-SNAPSHOT` version on a local repo, while ensuring the CI runs with a proper release version of the project.

Once you modified `version.sbt` to include a `-SNAPSHOT` suffix, you can execute the command

> sbt scripted

to execute the local installation & tests execution of the plugin.