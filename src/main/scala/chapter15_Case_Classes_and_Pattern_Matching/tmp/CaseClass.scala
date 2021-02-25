// package chapter15_Case_Classes_and_Pattern_Matching

// import math.{E, Pi}

// sealed abstract class Expr
// case class Var(name: String) extends Expr
// case class Number(num: Double) extends Expr
// case class UnOp(operator: String, arg: Expr) extends Expr
// case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

// object ExprCaseClass {
//   def simplifyTop(expr: Expr): Expr = expr match {
//     case UnOp("-", UnOp("-", e))  => e
//     case BinOp("+", e, Number(0)) => e
//     case BinOp("*", e, Number(1)) => e
//     case _                        => expr
//   }

//   def wildcardPattern(expr: Expr) = expr match {
//     case BinOp(_, _, _) => println(s"$expr is a binary operation")
//     case _              => println("It's something else")
//   }

//   def constantPattern(x: Any) = x match {
//     case 5       => "five"
//     case true    => "truth"
//     case "hello" => "hi!"
//     case Nil     => "the empty list"
//     case E       => "Napier's constant"
//     case Pi      => "Pi"
//     case _       => "something else"
//   }

//   def variablePattern(expr: Any) = expr match {
//     case 0             => "zero"
//     case somethingElse => "not zero: " + somethingElse
//   }

//   def backTickForConstants(x: Any) = x match {
//     case `pi` => "this is a constant, not a variable."
//     case pi   => "this is a variable so any object matches this."
//   }

//   def sequencePattern(expr: Any) = expr match {
//     case List(0, _, _) => println("found it")
//     case _             =>
//   }

//   def anyListStartingWithZero(expr: Any) = expr match {
//     case List(0, _*) => println("found it")
//     case _           =>
//   }

//   def tupplePattern(expr: Any) = expr match {
//     case (a, b, c) => println("matched " + a + b + c)
//     case _         =>
//   }

//   def typedPattern(x: Any) = x match {
//     case s: String    => s.length
//     case m: Map[_, _] => m.size
//     case _            => -1
//   }

//   def isIntIntMap(x: Any) = x match {
//     case m: Map[Int, Int] =>
//       true // does not work because types of map are erased at runtime
//     case _ => false
//   }

//   def isStringArray(x: Array) = x match {
//     case a: Array[String] => "yes"
//     case _                => "no"
//   }

//   def variableBinding(expr: Expr) = expr match {
//     case UnOp("abs", e @ UnOp("abs", _)) => e // e = UnOp("abs", _)
//     case _                               =>
//   }

//   def simplifyAdd(e: Expr) = e match {
//     case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2)) // pattern guard
//     case _                          => e
//   }

//   def simplifyAll(expr: Expr): Expr = expr match {
//     case UnOp("-", UnOp("-", e))  => simplifyAll(e)
//     case BinOp("+", e, Number(0)) => simplifyAll(e)
//     case BinOp("*", e, Number(1)) => simplifyAll((e))
//     case UnOp(op, e)              => UnOp(op, simplifyAll((e)))
//     case BinOp(op, l, r)          => BinOp(op, simplifyAll(l), simplifyAll(r))
//     case _                        => expr
//   }

//   def sealedWarning(e: Expr): String = e match {
//     case Number(_) => "a number"
//     case Var(_)    => "a variable"
//   }

//   def SuppressSealedWarning(e: Expr): String = (e: @unchecked) match {
//     case Number(_) => "a number"
//     case Var(_)    => "a variable"
//   }

//   def show(x: Option[String]) = x match {
//     case Some(s) => s
//     case None    => "?"
//   }

//   def main(args: Array[String]) = {
//     val myTuple = (123, "abc")
//     val (number, string) = myTuple

//     val exp = new BinOp("*", Number(5), Number(1))
//     val BinOp(op, left, right) = exp

//     val withDefault: Option[Int] => Int = {
//       case Some(x) => x
//       case None    => 0
//     }

//     val second: PartialFunction[List[Int], Int] = { case x :: y :: _ =>
//       y
//     }

//     second.isDefinedAt(List(5, 6, 7)) // true
//     second.isDefinedAt(List()) // false

//     val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo");
//     println(capitals get "France") // Option[String] = Some(Paris)
//     println(capitals get "North Pole") // Option[String] = None

//     for ((country, city) <- capitals)
//       println("The capital of " + country + "is" + city)

//     val results = List(Some("apple"), None, Some("orange"))
//     for (Some(fruit) <- results)
//       println(fruit)
//   }
// }
