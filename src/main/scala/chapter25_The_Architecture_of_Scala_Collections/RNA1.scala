package chapter25_The_Architecture_of_Scala_Collections

import scala.collection.mutable
import scala.collection.immutable.{IndexedSeq, IndexedSeqOps}

final class RNA1 private (
    val groups: Array[Int],
    val length: Int
) extends IndexedSeq[Base]
    with IndexedSeqOps[Base, IndexedSeq, RNA1] {
  import RNA1._

  def apply(idx: Int): Base = {
    if (idx < 0 || length <= idx) throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx / N) >> ((idx % N) * S) & M)
  }

  override def className = "RNA1"

  override protected def fromSpecific(source: IterableOnce[Base]): RNA1 =
    fromSeq(source.iterator.toSeq)

  override protected def newSpecificBuilder: mutable.Builder[Base, RNA1] =
    iterableFactory.newBuilder[Base].mapResult(fromSeq)

  override def empty: RNA1 = fromSeq(Seq.empty)
}

object RNA1 {
  private val S = 2
  private val N = 32 / S
  private val M = (1 << S) - 1

  def fromSeq(buf: collection.Seq[Base]): RNA1 = {
    val groups = new Array[Int]((buf.length + N - 1) / N)
    for (i <- 0 until buf.length)
      groups(i / N) |= Base.toInt(buf(i)) << ((i % N) * S)
    new RNA1(groups, buf.length)
  }

  def apply(bases: Base*) = fromSeq(bases)
}

object RNA1Main extends App {
  val rna1 = RNA1.fromSeq(List(A, G, U, A))
  println(rna1) // RNA1(A, G, U, A)

  val rna2 = RNA1(A, U, G, G, C)
  println(rna2) //RNA1(A, U, G, G, C)

  // expected
  val rna3 = rna1.take(3)
  println(rna3) // RNA1(A, G, U)

  val rna4 = rna1.filter(_ != U)
  println(rna4) // RNA1(A, G, A)

  // not desired result types
  val rna5 = rna1.map(base => base)
  println(rna5) // Vector(A, G, U, A)

  val rna6 = rna1 ++ rna1
  println(rna6) // Vector(A, G, U, A, A, G, U, A)
}
