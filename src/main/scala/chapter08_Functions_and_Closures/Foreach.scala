package chapter08_Functions_and_Closures

object Foreach {
  def main(args: Array[String]) = {
    val someNumbers = List(-11, -10, -5, 0, 5, 10)
    someNumbers.foreach(x => println(x))
    println(someNumbers.filter(x => x > 0))
  }
}
