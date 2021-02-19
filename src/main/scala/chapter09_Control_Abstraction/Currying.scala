package chapter09_Control_Abstraction

object Currying {
  def plainOldSum(x: Int, y: Int) = x + y

  def curriedSum(x: Int)(y: Int) = x + y

  def main(args: Array[String]) = {
    println(plainOldSum(1, 2))
    println(curriedSum(1)(2))
  }
}
