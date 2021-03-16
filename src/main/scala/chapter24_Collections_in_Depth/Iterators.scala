package chapter24_Collections_in_Depth

object Iterators extends App {
  def mkIt = Iterator("a", "number", "of", "words")

  val it = mkIt
  for (i <- it) println(i)
  // println(it.next()) // Exception in thread "main" java.util.NoSuchElementException: next on empty iterator

  val it2 = mkIt
  val it2WithMap = it2.map(_.length)
  println(it2WithMap.hasNext) // true
  it2WithMap foreach println // 1 6 2 5
  println(it2WithMap.hasNext) // false

  val it3 = mkIt
  val it3WithDropWhile = it3 dropWhile (_.length < 2)
  println(it3WithDropWhile.next()) // number

  val (it4_1, it4_2) =
    mkIt.duplicate // it4_1 and it4_2 are independent each otehr
}
