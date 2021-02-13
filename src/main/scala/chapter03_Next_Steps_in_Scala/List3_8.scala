package chapter3

object List3_8 {
  def main(args: Array[String]) = {
    val romanNumeral = Map( // immutable map
      1 -> "I",
      2 -> "II",
      3 -> "III",
      4 -> "IV",
      5 -> "V"
    )
    println(romanNumeral(4))
  }
}
