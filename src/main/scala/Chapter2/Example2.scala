package Chapter2

object Example2 {
  def max(x: Int, y: Int): Int = {
    if (x > y) x
    else y
  }
  def max2(x: Int, y: Int) = if (x > y) x else y
  def greet() = println("Hello world!")
  def main(args: Array[String]): Unit = {}
}
