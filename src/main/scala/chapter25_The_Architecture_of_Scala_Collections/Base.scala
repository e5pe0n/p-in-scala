package chapter25_The_Architecture_of_Scala_Collections

abstract class Base
case object A extends Base
case object U extends Base
case object G extends Base
case object C extends Base

object Base {
  val fromInt: Int => Base = Array(A, U, G, C)
  val toInt: Base => Int = Map(A -> 0, U -> 1, G -> 2, C -> 3)
}
