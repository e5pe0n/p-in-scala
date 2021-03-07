package chapter20_Abstract_Members

object Color extends Enumeration {
  val Red, Green, Blue = Value
}

object Direction extends Enumeration {
  val North = Value("North")
  val East = Value("East")
  val South = Value("South")
  val West = Value("West")
}

object EnumerationsRunner extends App {
  for (d <- Direction.values) print(d + " ")
  println()

  println(Direction.North.id) // 0
  println(Direction(0)) // North
}
