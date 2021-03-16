package chapter24_Collections_in_Depth

object MutableCollections extends App {
  // ArrayBuffer
  import scala.collection.mutable.ArrayBuffer

  val buf = ArrayBuffer.empty[Int]
  buf += 1
  buf += 10
  println(buf) // ArrayBuffer(1, 10)
  val arr = buf.toArray
  println(arr.mkString("Array(", ", ", ")")) // Array(1, 10)

  // ListBuffer
  import scala.collection.mutable.ListBuffer

  val listBuf = ListBuffer.empty[Int]
  listBuf += 1
  listBuf += 10
  println(listBuf) // ListBuffer(1, 10)
  println(listBuf.toList) // List(1, 10)

  // StringBuilder
  val strBuf = new StringBuilder
  strBuf += 'a'
  strBuf ++= "bcdef"
  println(strBuf) // abcdef
  val str = strBuf.toString

  // Queue
  import scala.collection.mutable.Queue

  val q = Queue[String]()
  q += "a"; println(q) // Queue(a)
  q ++= List("b", "c"); println(q) // Queue(a, b, c)
  q.dequeue; println(q) // Queue(b, c)

  // Stack
  import scala.collection.mutable.Stack

  val s = Stack[Int]()

  s.push(1)
  println(s) // Stack(1)

  s.push(2)
  println(s) //Stack(2, 1)

  println(s.top) // 2
  println(s.pop) // 2
  println(s) // Stack(1)

  // HashMap
  import scala.collection.mutable.HashMap

  val m = HashMap.empty[Int, String]

  m += (1 -> "make a web site")
  println(m) // HashMap(1 -> make a web site)

  m += (3 -> "profit!")
  println(m) // HashMap(1 -> make a web site, 3 -> profit!)

  println(m(1)) // make a web site
  println(m contains 2) // false

  // BitSet
  import scala.collection.mutable.BitSet

  val b = BitSet.empty
  b += 1
  b += 3
  println(b) // BitSet(1, 3)

  // Array
  val a1 = Array(1, 2, 3)

  val a2 = a1 map (_ * 3)
  println(a2.mkString("Array(", ", ", ")")) // Array(3, 6, 9)

  val a3 = a2 filter (_ % 2 != 0)
  println(a3.mkString("Array(", ", ", ")")) // Array(3, 9)

  println(a3.reverse.mkString("Array(", ", ", ")")) // Array(9, 3)

  import scala.collection.Seq
  val seq: Seq[Int] = a1
  val a4: Array[Int] = seq.toArray // toArray makes new Array
  println(a1 eq a4) // false

  // Views
  val v = Vector(1 to 10: _*)
  println(v)

  val v2 =
    v map (_ + 1) map (_ * 2) // creates intermediate vectors, which is waste
  println(v2) // Vector(4, 6, 8, 10, 12, 14, 16, 18, 20, 22)

  val v3 = (v.view map (_ + 1) map (_ * 2))
    .to(Vector) // not creates intermediate vectors
  println(v3) // Vector(4, 6, 8, 10, 12, 14, 16, 18, 20, 22)
}
