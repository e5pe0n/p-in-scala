package chapter17_Working_with_Other_Collections

import scala.collection.mutable

object UsingSets extends App {
  val text = "See Spot run. Run, Spot. Run!"
  val wordsArray = text.split("[ !,.]+")
  println(
    wordsArray mkString ("[", ", ", "]")
  ) // [See, Spot, run, Run, Spot, Run]

  val words = mutable.Set.empty[String]
  for (word <- wordsArray) words += word.toLowerCase
  println(words) // Set(see, run, spot)
}
