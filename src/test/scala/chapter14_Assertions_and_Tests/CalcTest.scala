package chapter14_Assertions_and_Tests

class CalcTest extends org.scalatest.funsuite.AnyFunSuite {
  test("Calc.add") {
    assert(Calc.add(1, 2) == 3)
  }
}
