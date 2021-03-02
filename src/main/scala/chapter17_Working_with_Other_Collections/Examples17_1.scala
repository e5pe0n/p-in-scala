package chapter17_Working_with_Other_Collections

import scala.collection.mutable.{ListBuffer, ArrayBuffer}

object Examples17_1 extends App {
  val colors = List("red", "blue", "green")
  println(colors) // List(red, blue, green)

  val fiveInts = new Array[Int](5) // Array(0, 0, 0, 0, 0)
  val fiveToOne = Array(5, 4, 3, 2, 1)
  fiveInts(0) = fiveToOne(4)
  println(fiveInts) //[I@34b7bfc0
  println(fiveInts mkString ("[", ", ", "]")) // [1, 0, 0, 0, 0]

  val buf = new ListBuffer[Int]
  buf += 1
  println(buf) // ListBuffer(1)
  buf += 2
  println(buf) // ListBuffer(1, 2)

  3 +=: buf
  println(buf) // ListBuffer(3, 1, 2)

  val listFromBuf = buf.toList
  println(listFromBuf) // List(3, 1, 2)

  val bufArray = new ArrayBuffer[Int]()
  bufArray += 12
  println(bufArray) // ArrayBuffer(12)
  bufArray += 15
  println(bufArray) // ArrayBuffer(12, 15)
  println(bufArray.length) // 2
  println(bufArray(0)) // 12

  def hasUpperCase(s: String) = s.exists(_.isUpper)
  // Scala converts String to StringOps implicitly because String does not have isUpper method

  println(hasUpperCase("Robert Frost")) // true
  println(hasUpperCase("e e commings")) // false
}
