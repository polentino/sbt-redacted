package io.github.polentino.sbt

import io.github.polentino.redacted.redacted

package object redacted {
  case class Username(id: Int, @redacted name: String)
}
