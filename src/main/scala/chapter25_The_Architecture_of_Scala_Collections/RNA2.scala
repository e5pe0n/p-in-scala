package chapter25_The_Architecture_of_Scala_Collections

import scala.collection.{View, mutable}
import scala.collection.immutable.{IndexedSeq, IndexedSeqOps}

final class RNA2 private (
    val groups: Array[Int],
    val length: Int
) extends IndexedSeq[Base]
    with IndexedSeqOps[Base, IndexedSeq, RNA2] {
  import RNA2._

  def apply(idx: Int): Base = {
    if (idx < 0 || length <= idx) throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx / N) >> ((idx % N) * S) & M)
  }

  override def className = "RNA2"

  override protected def fromSpecific(source: IterableOnce[Base]): RNA2 =
    fromSeq(source.iterator.toSeq)

  override protected def newSpecificBuilder: mutable.Builder[Base, RNA2] =
    iterableFactory.newBuilder[Base].mapResult(fromSeq)

  override def empty: RNA2 = fromSeq(Seq.empty)

  def appended(base: Base): RNA2 = fromSpecific((new View.Appended(this, base)))

  def appendedAll(suffix: IterableOnce[Base]): RNA2 = concat(suffix)

  def concat(suffix: IterableOnce[Base]): RNA2 =
    fromSpecific(this.iterator ++ suffix.iterator)

  def flatMap(f: Base => IterableOnce[Base]): RNA2 =
    fromSpecific(new View.FlatMap(this, f))

  def map(f: Base => Base): RNA2 = fromSpecific((new View.Map(this, f)))

  def prepended(base: Base): RNA2 = fromSpecific(new View.Prepended(base, this))

  def prependedAll(prefix: IterableOnce[Base]): RNA2 =
    fromSpecific(prefix.iterator ++ this.iterator)

  @inline final def ++(suffix: IterableOnce[Base]): RNA2 = concat(suffix)
}

object RNA2 {
  private val S = 2
  private val N = 32 / S
  private val M = (1 << S) - 1

  def fromSeq(buf: collection.Seq[Base]): RNA2 = {
    val groups = new Array[Int]((buf.length + N - 1) / N)
    for (i <- 0 until buf.length)
      groups(i / N) |= Base.toInt(buf(i)) << ((i % N) * S)
    new RNA2(groups, buf.length)
  }

  def apply(bases: Base*) = fromSeq(bases)
}

object RNA2Main extends App {
  val rna = RNA2(A, U, G, G, C)
  println(rna) // RNA2(A, U, G, G, C)

  val rna2 = rna.map(base => base)
  println(rna2) //RNA2(A, U, G, G, C)

  val rna3 = rna ++ rna
  println(rna3) // RNA2(A, U, G, G, C, A, U, G, G, C)
}
