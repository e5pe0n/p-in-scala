package chapter24_Collections_in_Depth

object BufferedIterators extends App {
  def skipEmptyWordsNOT(it: Iterator[String]) = while (it.next().isEmpty) {}
  // skip the first string even if it is not empty.

  def skipEmptyWords(it: BufferedIterator[String]) =
    while (it.head.isEmpty) { it.next() }

  val it = Iterator(1, 2, 3, 4)
  val bit: BufferedIterator[Int] = it.buffered
  println(bit.head) // 1
  println(bit.next()) // 1
  println(bit.next()) // 2
}
