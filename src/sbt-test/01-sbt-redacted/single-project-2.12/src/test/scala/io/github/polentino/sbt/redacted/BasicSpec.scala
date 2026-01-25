package io.github.polentino.sbt.redacted

import org.scalatest.Checkpoints._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks


class RedactedSpec extends AnyFlatSpec with ScalaCheckPropertyChecks {
  behavior of "sbt-reacted"

  it should "add the compiler plugin to the project and kick the redaction process, automatically" in {

    forAll { (id: Int, name: String) =>
      val expected = s"Username($id,***)"
      val testing = Username(id, name)
      val implicitToString = s"$testing"
      val explicitToString = testing.toString

      val cp = new Checkpoint
      cp {
        assert(implicitToString == expected)
      }
      cp {
        assert(explicitToString == expected)
      }
      cp {
        assert(testing.id == id)
        assert(testing.name == name)
      }
      cp.reportAll()
    }
  }
}