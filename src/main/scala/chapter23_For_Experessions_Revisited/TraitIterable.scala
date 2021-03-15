package chapter23_For_Experessions_Revisited

object TraitIterable extends App {
  val xs = (1 to 5).toList
  val git: Iterator[List[Int]] = xs grouped 3
  println(git.next()) // List(1, 2, 3)
  println(git.next()) //List(4, 5)

  val sit = xs sliding 3
  println(sit.next()) // List(1, 2, 3)
  println(sit.next()) // List(2, 3, 4)
  println(sit.next()) // List(3, 4, 5)
}
