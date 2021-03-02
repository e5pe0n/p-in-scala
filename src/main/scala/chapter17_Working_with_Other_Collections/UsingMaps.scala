package chapter17_Working_with_Other_Collections

import scala.collection.mutable

object UsingMaps extends App {
  val map = mutable.Map.empty[String, Int]
  map("hello") = 1
  map("there") = 2
  println(map) // Map(hello -> 1, there -> 2)
  // println(map("those")) // Exception in thread "main" java.util.NoSuchElementException: key not found: those

  def countWords(text: String) = {
    val counts = mutable.Map.empty[String, Int]
    for (rawWord <- text.split("[ !.,]+")) {
      val word = rawWord.toLowerCase
      val oldCount = if (counts.contains(word)) counts(word) else 0
      counts += (word -> (oldCount + 1))
    }
    counts
  }
}
