package chapter21_Implicit_Conversions_and_Parameters

import chapter06_Functional_Objects.Rational

object ImplicitConversionsRunner extends App {
  implicit def doubleToInt(x: Double) = x.toInt

  val i: Int = 3.5
  println(i) // 3

  // simulating new syntax
  implicit def intToRational(x: Int) = new Rational(x, 1)

  val oneHalf = new Rational(1, 2)
  println(1 + oneHalf) // 3/2

  // implicit classes
  case class Rectangle(width: Int, height: Int) {
    override def toString = s"${width}x${height}"
  }

  implicit class RectangleMaker(width: Int) {
    def x(height: Int) = Rectangle(width, height)
  }

  val rect = 3 x 4
  println(rect) // 3x4

  // implicit parameters
  class PreferredPrompt(val preference: String)
  class PreferredDrink(val preference: String)

  object Greeter {
    def greet(
        name: String
    )(implicit prompt: PreferredPrompt, drink: PreferredDrink) = {
      println("Welcome, " + name + ". The system is ready.")
      println("But while you work,")
      println("why not enjoy a cup of " + drink.preference + "?")
      println(prompt.preference)
    }
  }

  object JoesPrefs {
    implicit val prompt = new PreferredPrompt("Yes, master> ")
    implicit val drink = new PreferredDrink("tea")
  }

  import JoesPrefs._ // prompt must be as single identifier
  Greeter.greet("Joe")
  // Welcome, Joe. The system is ready.
  // But while you work,
  // why not enjoy a cup of tea?
  // Yes, master>

  def maxListImpParm[T](elements: List[T])(implicit ordering: Ordering[T]): T =
    elements match {
      case List()  => throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxListImpParm(rest)(ordering)
        if (ordering.gt(x, maxRest)) x else maxRest
    }

  println(maxListImpParm(List(1, 5, 10, 3))) // 10
  println(maxListImpParm(List(1.5, 5.2, 10.7, 3.14159))) // 10.7
  println(maxListImpParm(List("one", "two", "three"))) // two
}
