package chapter17_Working_with_Other_Collections

import scala.collection.immutable.{TreeSet, TreeMap}

object SortedSetsAndMaps extends App {
  val ts = TreeSet(9, 3, 1, 8, 0, 2, 7, 4, 6, 5)
  println(ts) // TreeSet(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

  val cs = TreeSet('f', 'u', 'n')
  println(cs) // TreeSet(f, n, u)

  var tm = TreeMap(3 -> 'x', 1 -> 'x', 4 -> 'x')
  println(tm) // Map(1 -> x, 3 -> x, 4 -> x)

  tm += (2 -> 'x') // tm is assigned a new immutable TreeMap updated with (2 -> 'x')
  println(tm) // Map(1 -> x, 2 -> x, 3 -> x, 4 -> x)
}
