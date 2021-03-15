package chapter24_Collections_in_Depth

object ImmutableCollections extends App {
  // LazyList
  val str = 1 #:: 2 #:: 3 #:: LazyList.empty
  println(str) // LazyList(<not computed>)
  println(str.toList) //List(1, 2, 3)

  def fibFrom(a: Int, b: Int): LazyList[Int] = a #:: fibFrom(b, a + b)
  val fibs = fibFrom(0, 1).take(10)
  println(fibs) // LazyList(<not computed>)
  println(fibs.toList) // List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)

  // Vector
  val vec = Vector.empty
  val vec2 = vec :+ 1 :+ 2
  println(vec2) // Vector(1, 2)
  val vec3 = 100 +: vec2
  println(vec3) // Vector(100, 1, 2)
  println(vec3(0)) // 100

  val vec10 = Vector(10, 20, 30)
  val vec11 = vec10 updated (2, 0)
  println(vec11) // Vector(10, 20, 0)

  // Immutable Queue
  val emp = scala.collection.immutable.Queue[Int]()
  val has1 = emp.enqueue(1)
  println(has1) // Queue(1)
  val has123 = has1.enqueueAll(List(2, 3))
  println(has123) // Queue(1, 2, 3)

  // Ranges
  val rng1_3 = 1 to 3
  println(rng1_3) // Range 1 to 3

  val rng5_14_3 = 5 to 14 by 3
  println(rng5_14_3) //Range 5 to 14 by 3

  val rng1_2 = 1 until 3 // 3 is not included
  println(rng1_2) // Range 1 until 3

  // Red-Black Trees
  val set = scala.collection.immutable.TreeSet.empty[Int]
  val set2 = set + 3 + 1 + 3
  println(set2) // TreeSet(1, 3)

  // Immutable bit sets
  val bits = scala.collection.immutable.BitSet.empty
  val moreBits = bits + 3 + 4 + 4
  println(moreBits) // BitSet(3, 4)
  println(moreBits(3)) // true
  println(moreBits(0)) // false

  // VectorMap
  val vm = scala.collection.immutable.VectorMap.empty[Int, String]
  val vm1 = vm + (1 -> "one")
  val vm3 = vm1 + (3 -> "three")
  val vm2 = vm3 + (2 -> "two")
  println(vm2) // VectorMap(1 -> one, 3 -> three, 2 -> two)
  println(vm2 == Map(2 -> "two", 1 -> "one", 3 -> "three")) // true
}
