package chapter08_Functions_and_Closures

object Partially {
  def sum(a: Int, b: Int, c: Int) = a + b + c

  def main(args: Array[String]) = {
    val a = sum _
    println(a(1, 2, 3)) // a.apply(1, 2, 3)

    val b = sum(10, _, 30)
    println(b(20))
  }
}
