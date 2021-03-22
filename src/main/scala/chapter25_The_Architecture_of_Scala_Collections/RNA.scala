package chapter25_The_Architecture_of_Scala_Collections

import scala.collection.{
  AbstractIterator,
  SpecificIterableFactory,
  StrictOptimizedSeqOps,
  View,
  mutable
}
import scala.collection.immutable.{IndexedSeq, IndexedSeqOps}

final class RNA private (
    val groups: Array[Int],
    val length: Int
) extends IndexedSeq[Base]
    with IndexedSeqOps[Base, IndexedSeq, RNA]
    with StrictOptimizedSeqOps[Base, IndexedSeq, RNA] {
  rna =>

  import RNA._

  def apply(idx: Int): Base = {
    if (idx < 0 || length <= idx) throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx / N) >> ((idx % N) * S) & M)
  }

  override def className = "RNA"

  override protected def fromSpecific(source: IterableOnce[Base]): RNA =
    RNA.fromSpecific(source)

  override protected def newSpecificBuilder: mutable.Builder[Base, RNA] =
    RNA.newBuilder

  override def empty: RNA = RNA.empty

  def appended(base: Base): RNA =
    (newSpecificBuilder ++= this += base).result()

  def appendedAll(suffix: IterableOnce[Base]): RNA =
    strictOptimizedConcat(suffix, newSpecificBuilder)

  def concat(suffix: IterableOnce[Base]): RNA =
    strictOptimizedConcat(suffix, newSpecificBuilder)

  def flatMap(f: Base => IterableOnce[Base]): RNA =
    strictOptimizedFlatMap(newSpecificBuilder, f)

  def map(f: Base => Base): RNA =
    strictOptimizedMap(newSpecificBuilder, f)

  def prepended(base: Base): RNA =
    (newSpecificBuilder += base ++= this).result()

  def prependedAll(prefix: IterableOnce[Base]): RNA =
    (newSpecificBuilder ++= prefix ++= this).result()

  @inline final def ++(suffix: IterableOnce[Base]): RNA =
    concat(suffix)

  override def iterator: Iterator[Base] =
    new AbstractIterator[Base] {
      private var i = 0
      private var b = 0
      def hasNext: Boolean = i < rna.length
      def next(): Base = {
        b = if (i % N == 0) groups(i / N) else b >>> S
        i += 1
        Base.fromInt(b & M)
      }
    }
}

// inheriting from SpecificIterableFactory enable to apply `to` method.
// SpecificIterableFactory difines abstract methods: `empty`, `newBuilder`, `fromSpecific`
object RNA extends SpecificIterableFactory[Base, RNA] {
  private val S = 2
  private val M = (1 << S) - 1
  private val N = 32 / S

  def fromSeq(buf: collection.Seq[Base]): RNA = {
    val groups = new Array[Int]((buf.length + N - 1) / N)
    for (i <- 0 until buf.length)
      groups(i / N) |= Base.toInt(buf(i)) << ((i % N) * S)
    new RNA(groups, buf.length)
  }

  def empty: RNA = fromSeq(Seq.empty)

  def newBuilder: mutable.Builder[Base, RNA] =
    mutable.ArrayBuffer.newBuilder[Base].mapResult(fromSeq)

  def fromSpecific(it: IterableOnce[Base]): RNA = it match {
    case seq: collection.Seq[Base] => fromSeq(seq)
    case _                         => fromSeq(mutable.ArrayBuffer.from(it))
  }
}

object RNAMain extends App {
  // inheriting from SpecificIterableFactory enable to apply `to` method.
  val rna = List(U, A, G, C).to(RNA)
  println(rna) // RNA(U, A, G, C)
}
