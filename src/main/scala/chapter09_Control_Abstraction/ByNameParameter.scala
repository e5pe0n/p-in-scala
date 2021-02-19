package chapter09_Control_Abstraction

object ByNameParameter {
  var assertionsEnabled = true

  def myAssert(predicate: () => Boolean) =
    if (assertionsEnabled && !predicate())
      throw new AssertionError

  def byNameAssert(
      predicate: => Boolean
  ) = // by-name type enable lazy evaluation
    if (assertionsEnabled && !predicate)
      throw new AssertionError

  def boolAssert(predicate: Boolean) =
    if (assertionsEnabled && !predicate)
      throw new AssertionError

  def main(args: Array[String]) = {
    myAssert(() => 5 > 3)
    byNameAssert(5 > 3)

    val x = 5
    assertionsEnabled = false
    boolAssert(x / 0 == 0) // throw exception though assertionsEnabled = False
    byNameAssert(
      x / 0 == 0
    ) // not throw exception because x / 0 == 0 is not evaluated immediately when passed to byNameAssert
  }
}
