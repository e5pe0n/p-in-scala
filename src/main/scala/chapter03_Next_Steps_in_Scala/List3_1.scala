package chapter3

object List3_1 {
  def main(args: Array[String]) = {
    val bit = new java.math.BigInteger("12345")
    val greetStrings = new Array[String](3)
    greetStrings(0) = "Hello"
    greetStrings(1) = ", "
    greetStrings(2) = "world!\n"
    for (i <- 0 to 2)
      print(greetStrings(i))
  }
}
