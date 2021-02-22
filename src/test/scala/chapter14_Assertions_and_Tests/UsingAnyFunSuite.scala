package chapter14_Assertions_and_Tests

import org.scalatest.featurespec.AnyFeatureSpec
import Element.elem

class ElementSuite extends AnyFunSuite {
  test("elem result should have passed width") {
    val ele = elem('x', 2, 3)
    assert(ele.width == 4)
  }
}
