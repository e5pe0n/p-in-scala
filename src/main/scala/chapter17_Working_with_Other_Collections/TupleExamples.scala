package chapter17_Working_with_Other_Collections

object TupleExamples extends App {
  def longestWord(words: Array[String]): (String, Int) = {
    var word = words(0)
    var idx = 0
    for (i <- 1 until words.length)
      if (words(i).length > word.length) {
        word = words(i)
        idx = i
      }
    (word, idx)
  }

  val longest = longestWord("The quick brown fox".split(" "))
  println(longest) // (quick,1)

  println(longest._1) // quick
  println(longest._2) // 1
  val (word, idx) = longest
  println(s"word=$word, idx=$idx")
}
