package chapter25_The_Architecture_of_Scala_Collections

import scala.collection._

class Capped2[A] private (
    val capacity: Int,
    val length: Int,
    offset: Int,
    elems: Array[Any]
) extends immutable.Iterable[A]
    with IterableOps[A, Capped2, Capped2[A]] {
  self =>

  def this(capacity: Int) =
    this(capacity, length = 0, offset = 0, elems = Array.ofDim(capacity))

  def appended[B >: A](elem: B): Capped2[B] = {
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
    new Capped2[B](capacity, newLength, newOffset, newElems)
  }

  @inline def :+[B >: A](elem: B): Capped2[B] = appended(elem)

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

  override def className = "Capped2"

  override val iterableFactory: IterableFactory[Capped2] = new Capped2Factory(
    capacity
  )

  override protected def fromSpecific(coll: IterableOnce[A]): Capped2[A] =
    iterableFactory.from(coll)

  override protected def newSpecificBuilder: mutable.Builder[A, Capped2[A]] =
    iterableFactory.newBuilder

  override def empty: Capped2[A] = iterableFactory.empty
}

class Capped2Factory(capacity: Int) extends IterableFactory[Capped2] {
  def from[A](source: IterableOnce[A]): Capped2[A] =
    (newBuilder[A] ++= source).result()

  def empty[A]: Capped2[A] = new Capped2[A](capacity)

  def newBuilder[A]: mutable.Builder[A, Capped2[A]] =
    new mutable.ImmutableBuilder[A, Capped2[A]](empty) {
      def addOne(elem: A): this.type = {
        elems = elems :+ elem
        this
      }
    }
}

object Capped2Main extends App {
  object Capped extends Capped2Factory(capacity = 4)

  val c = Capped(1, 2, 3)
  println(c) // Capped2(1, 2, 3)

  val res = c.take(2)
  println(res) // Capped2(1, 2)

  val res2 = c.filter(x => x % 2 == 1)
  println(res2) // Capped2(1, 3)

  val res3 = c.map(x => x * x)
  println(res3) // Capped2(1, 4, 9)

  val res4 = List(1, 2, 3, 4, 5).to(Capped)
  println(res4) // Capped2(2, 3, 4, 5)
}
