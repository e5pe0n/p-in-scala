package chapter25_The_Architecture_of_Scala_Collections

import scala.collection._

class Capped1[A] private (
    val capacity: Int,
    val length: Int,
    offset: Int,
    elems: Array[Any]
) extends immutable.Iterable[A] {
  self =>

  def this(capacity: Int) =
    this(capacity, length = 0, offset = 0, elems = Array.ofDim(capacity))

  def appended[B >: A](elem: B): Capped1[B] = {
    val newElems = Array.ofDim[Any](capacity)
    Array.copy(elems, 0, newElems, 0, capacity)
    val (newOffset, newLength) =
      if (length == capacity) {
        newElems(offset) = elem
        ((offset + 1) % capacity, length)
      } else {
        newElems(length) = elem
        (offset, length + 1)
      }
    new Capped1[B](capacity, newLength, newOffset, newElems)
  }

  @inline def :+[B >: A](elem: B): Capped1[B] = appended(elem)

  def apply(i: Int): A = elems((i + offset) % capacity).asInstanceOf[A]

  def iterator: Iterator[A] = new AbstractIterator[A] {
    private var current = 0
    def hasNext = current < self.length
    def next(): A = {
      val elem = self(current)
      current += 1
      elem
    }
  }

  override def className = "Capped1"
}

object Capped1Main extends App {
  val c = new Capped1(capacity = 4)
  val c1 = c :+ 1 :+ 2 :+ 3
  println(c1) // Capped1(1, 2, 3)
  println(c1.length) // 3
  println(c1.lastOption) // Some(3)

  val c2 = c1 :+ 4 :+ 5 :+ 6
  println(c2) // Capped1 (3, 4, 5, 6)

  val c3 = c2.take(3)
  println(c3) // List(3, 4, 5)
}
