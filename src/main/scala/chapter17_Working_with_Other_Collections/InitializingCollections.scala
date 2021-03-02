package chapter17_Working_with_Other_Collections

import scala.collection.immutable.TreeSet
import scala.collection.mutable

object InitializingCollections extends App {
  val colors = List("blue", "yellow", "red", "green")
  val ts = colors to TreeSet // List -> TreeSet
  println(ts) // TreeSet(blue, green, red, yellow)

  val list = ts.toList
  println(list) // List(blue, green, red, yellow)

  val arr = ts.toArray
  println(arr mkString ("Array(", ",", ")")) // Array(blue,green,red,yellow)

  val mutaSet = ts to mutable.Set // TreeSet -> mutable Set
  val immutaSet = mutaSet to Set // mutable Set -> immutable Set
  val muta = mutable.Map("i" -> 1, "ii" -> 2) // mutable Map
  Map(1 -> "a", 2 -> "b") to Map // mutable map -> immutable Map
}
