package chapter24_Collections_in_Depth

import scala.collection.mutable

object Maps extends App {
  val ms = mutable.Map(("a" -> 1))
  ms("b") = 2;
  println(ms) // HashMap(a -> 1, b -> 2)

  def f(x: String) = {
    println("taking my time."); Thread.sleep(3000)
    x.reverse
  }
  val cache = mutable.Map[String, String]()
  def cachedF(s: String) = cache.getOrElseUpdate(s, f(s))
  println(cachedF("abc"))
  println(cachedF("abc"))
}
