package chapter25_The_Architecture_of_Scala_Collections

import scala.collection._
import scala.language.implicitConversions

class PrefixMap[A]
    extends mutable.Map[String, A]
    with mutable.MapOps[String, A, mutable.Map, PrefixMap[A]]
    with StrictOptimizedIterableOps[
      (String, A),
      mutable.Iterable,
      PrefixMap[A]
    ] {
  private var suffixes: immutable.Map[Char, PrefixMap[A]] =
    immutable.Map.empty

  private var value: Option[A] = None

  def get(s: String): Option[A] =
    if (s.isEmpty) value
    else suffixes get (s(0)) flatMap (_.get(s substring 1))

  def addOne(kv: (String, A)): this.type = {
    withPrefix(kv._1).value = Some(kv._2)
    this
  }

  def subtractOne(s: String): this.type = {
    if (s.isEmpty) {
      val prev = value
      value = None
      prev
    } else suffixes get (s(0)) flatMap (_.remove(s substring 1))
    this
  }

  def withPrefix(s: String): PrefixMap[A] =
    if (s.isEmpty) this
    else {
      val leading = s(0)
      suffixes get leading match {
        case None => suffixes = suffixes + (leading -> empty)
        case _    =>
      }
      suffixes(leading) withPrefix (s substring 1)
    }

  def iterator: Iterator[(String, A)] =
    (for (v <- value.iterator) yield ("", v)) ++
      (for ((chr, m) <- suffixes.iterator; (s, v) <- m.iterator)
        yield (chr +: s, v))

  override def className = "PrefixMap"

  def map[B](f: ((String, A)) => (String, B)): PrefixMap[B] =
    strictOptimizedMap(PrefixMap.newBuilder[B], f)

  def flatMap[B](f: ((String, A)) => IterableOnce[(String, B)]): PrefixMap[B] =
    strictOptimizedFlatMap(PrefixMap.newBuilder[B], f)

  def concat[B >: A](suffix: Iterable[(String, B)]): PrefixMap[B] =
    strictOptimizedConcat(suffix, PrefixMap.newBuilder[B])

  override def clear(): Unit = suffixes = immutable.Map.empty

  // specify PrefixMap[A] as the return type instead of mutable.Map
  override protected def fromSpecific(
      source: IterableOnce[(String, A)]
  ): PrefixMap[A] = PrefixMap.from(coll)

  // specify PrefixMap[A] as the return type instead of mutable.Map
  override protected def newSpecificBuilder
      : mutable.Builder[(String, A), PrefixMap[A]] = PrefixMap.newBuilder

  override def empty: PrefixMap[A] = new PrefixMap
}

object PrefixMap {
  def empty[A] = new PrefixMap[A]

  def from[A](source: IterableOnce[(String, A)]): PrefixMap[A] =
    source match {
      case pm: PrefixMap[A] => pm
      case _                => (newBuilder[A] ++= source).result()
    }

  def apply[A](kvs: (String, A)*): PrefixMap[A] = from(kvs)

  def newBuilder[A]: mutable.Builder[(String, A), PrefixMap[A]] =
    new mutable.GrowableBuilder[(String, A), PrefixMap[A]](empty)

  // triggered by List("foo" -> 3).to(PrefixMap)
  // `to` operation takes a `Factory` but the `PrefixMap` companion object does not extend `Factory` and it can not because a `Factory` fixes the type of collection elements, whereas `PrefixMap` has a polymorphic type of values.
  implicit def toFactory[A](
      self: this.type
  ): Factory[(String, A), PrefixMap[A]] =
    new Factory[(String, A), PrefixMap[A]] {
      def fromSpecific(source: IterableOnce[(String, A)]): PrefixMap[A] =
        self.from(source)
      def newBuilder: mutable.Builder[(String, A), PrefixMap[A]] =
        self.newBuilder
    }
}

object PrefixMapMain extends App {
  val m = PrefixMap("abc" -> 0, "abd" -> 1, "al" -> 2, "all" -> 3, "xy" -> 4)
  println(m) // PrefixMap(abc -> 0, abd -> 1, al -> 2, all -> 3, xy -> 4)

  val m2 = m withPrefix "a"
  println(m2) // PrefixMap(bc -> 0, bd -> 1, l -> 2, ll -> 3)

  m2 += "pple" -> 5
  println(m)
  // PrefixMap(abc -> 0, abd -> 1, al -> 2, all -> 3, apple -> 5, xy -> 4)
}
