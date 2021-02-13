package chapter3

import scala.collection.mutable
import scala.collection.immutable.HashSet

object List3_6 {
  def main(args: Array[String]) = {
    val movieSet = mutable.Set("Hitch", "Poltergeist")
    movieSet += "Shrek"
    println(movieSet)

    val hashSet = HashSet("Tomatoes", "Chilies")
    println(hashSet + "Coriander")
  }
}
