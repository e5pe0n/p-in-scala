package chapter16_Working_with_Lists

object ListExample extends App {
  val fruit = List("apples", "oranges", "pears")
  val nums = List(1, 2, 3, 4)
  val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
  val empty = List() // List[Nothing]
  val xs: List[String] =
    List() // ok because Nothing is subtype of any other type.

  val fruit2 = "apples" :: ("oranges" :: ("pears" :: Nil))
  val nums2 = 1 :: (2 :: 3 :: 4 :: Nil)
  val diag3_2 =
    (1 :: (0 :: (0 :: Nil))) :: (0 :: (1 :: (0 :: Nil))) :: (0 :: (0 :: (1 :: Nil))) :: Nil
  val empty_2 = Nil // Nil is an empty list

  def isort(xs: List[Int]): List[Int] =
    if (xs.isEmpty) Nil else insert(xs.head, isort(xs.tail))

  def insert(x: Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs else xs.head :: insert(x, xs.tail)

  // List patterns
  val List(a, b, c) = fruit
  val p :: q :: rest = fruit //

  def isort2(xs: List[Int]): List[Int] = xs match {
    // with pattern matching
    case List()   => List()
    case x :: xs1 => insert(x, isort2(xs1))
  }

  def insert2(x: Int, xs: List[Int]): List[Int] = xs match {
    // with pattern matching
    case List()  => List(x)
    case y :: ys => if (x <= y) x :: xs else y :: insert2(x, ys)
  }

  // concatenating two lists
  val list1 = List(1, 2) ::: List(3, 4, 5) // List(1, 2, 3, 4, 5)
  val List2 = List() ::: List(1, 2, 3) // List(1, 2, 3)
  val List3 = List(1, 2, 3) ::: List(4) // List(1, 2, 3, 4)

  println(List(1, 2, 3).length) // 3

  val abcde = List('a', 'b', 'c', 'd', 'e')
  // these methods throw an exception when applied to an empty list
  println(abcde.head) // 'a'
  println(abcde.tail) // List(b, c, d, e)
  println(abcde.last) // 'e'
  println(abcde.init) // List(a, b, c, d)

  val edcba = abcde.reverse // List('e', 'd', 'c', 'b', 'a')

  // A take n = [0, n) in 0 based index
  println(abcde take 2) // List(a, b)
  // A drop n = [n, A.length) in 0 based index
  println(abcde drop 2) // List(c, d, e)
  // A splitAt n = [0, n) and [n, A.length) in 0 based index
  println(abcde splitAt 2) // (List(a, b), List(c, d, e))

  // `xs apply n` equals `(xs drop n).head`
  println(
    abcde apply 2 // slow because of taking time proportional to the index n
  )
  println(abcde(2)) // the same as above

  val flattened = List(List(1, 2), List(3), List(), List(4, 5)).flatten
  // List(1, 2, 3, 4, 5)
  val flattened2 = fruit.map(_.toCharArray).flatten
  // List(a, p, p, l, e, s, o, r, a, n, g, e, s, p, e, a, r, s)

  val zipped = abcde.indices zip abcde
  // Vector((0, a), (1, b), (2, c), (3, d), (4, e))
  val zipped2 = abcde zip List(1, 2, 3) // List((a, 1), (b, 2), (c, 3))
  val zipped3 =
    abcde.zipWithIndex // List((a, 0), (b, 1), (c, 2), (d, 3), (e, 4))

  val unzipped2 = zipped2.unzip // (List(a, b, c), List(1, 2, 3))

  val s = abcde.toString // String = List(a, b, c, d, e)
  val s2 = abcde.mkString("[", ",", "]") // String = [a, b, c, d, e]
  val s3 = abcde mkString "" // String = abcde
  val s4 = abcde.mkString // String = abcde

  val buf = new StringBuilder
  val sb = abcde.addString(buf, "(", ";", ")") // StringBuilder = (a;b;c;d;e)

  val arr = abcde.toArray // Array(a, b, c, d, e)
  val lst = arr.toList // List(a, b, c, d, e)

  val arr2 = new Array[Int](10)
  List(1, 2, 3).copyToArray(arr2, 3) // Array(0, 0, 0, 1, 2, 3, 0, 0, 0, 0)

  val it = abcde.iterator
  println(it.next) // a
  println(it.next) // b

  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, _) => ys
      case (_, Nil) => xs
      case (x :: xs1, y :: ys1) =>
        if (less(x, y)) x :: merge(xs1, ys) else y :: merge(xs, ys1)
    }

    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }

  val msorted =
    msort((x: Int, y: Int) => x < y)(List(5, 7, 1, 3)) // List(1, 3, 5, 7)
  val intSort = msort((x: Int, y: Int) => x < y) _
  val reverseIntSort = msort((x: Int, y: Int) => x > y) _

  val plusOne = List(1, 2, 3) map (_ + 1) // List(2, 3, 4)
  val words =
    List("the", "quick", "brown", "fox") // List(the, quick, brown, fox)
  val lengths = words map (_.length) // List(3, 5, 5, 3)
  val reversed =
    words map (_.toList.reverse.mkString) // List(eht, kciuq, nworb, xof)

  val mappedWords = words map (_.toList)
  // List(List(t, h, e), List(q, u, i, c, k), List(b, r, o, w, n), List(f, o, x))
  val flatMappedWords = words flatMap (_.toList)
  // List(t, h, e, q, u, i, c, k, b, r, o, w, n, f, o, x)

  val ps = List.range(1, 5) flatMap (i => List.range(1, i) map (j => (i, j)))
  val ps2 = for (i <- List.range(1, 5); j <- List.range(1, i)) yield (i, j)
  // List((2,1), (3,1), (3,2), (4,1), (4,2), (4,3))

  var sum = 0
  List(1, 2, 3, 4, 5) foreach (sum += _)
  println(sum) // 15

  val evens = List(1, 2, 3, 4, 5) filter (_ % 2 == 0) // List(2, 4)
  val threes = words filter (_.length == 3) // List(the, fox)

  val partitioned = List(1, 2, 3, 4, 5) partition (_ % 2 == 0)
  // (List(2, 4), List(1, 3, 5))

  val fstEven = List(1, 2, 3, 4, 5) find (_ % 2 == 0) // Some(2)
  val fstLower0 = List(1, 2, 3, 4, 5) find (_ <= 0) // None

  val fstGreater0s = List(1, 2, 3, -4, 5) takeWhile (_ > 0)
  val dropWords = words dropWhile (_ startsWith "t") // List(quick, brown, fox)

  val spanedBy0 =
    List(1, 2, 3, -4, 5) span (_ > 0) // (List(1, 2, 3), List(-4, 5))

  def hasZeroRow(m: List[List[Int]]) = m exists (row => row forall (_ == 0))

  println(hasZeroRow(List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 0)))) // true

  def sum(xs: List[Int]): Int = xs.foldLeft(0)(_ + _)

  def product(xs: List[Int]): Int = xs.foldLeft(1)(_ * _)

  val sentence =
    words.tail.foldLeft(words.head)(_ + " " + _) // the quick brown fox

  def flattenLeft[T](xss: List[List[T]]) = xss.foldLeft(List[T]())(_ ::: _)
  // less efficient than flattenRight.
  // `x :: xs` is invoked O(n) times each `_ ::: _` because `_ ::: _` is formed `List(x1, x2, ...) ::: List(y)`
  // see implementation of `append` function below.

  def flattenRight[T](xss: List[List[T]]) = xss.foldRight(List[T]())(_ ::: _)
  // more efficient than flattenLeft.
  // `x :: xs` is invoked once each `_ ::: _` becasuse `_ ::: _` is formed `List(x) ::: List(y1, y2, ...)`

  def append[T](xs: List[T], ys: List[T]): List[T] = xs match {
    // implementation of `:::`
    case List()   => ys
    case x :: xs1 => x :: append(xs1, ys)
  }

  def reverseLeft[T](xs: List[T]) = xs.foldLeft(List[T]()) { (ys, y) =>
    y :: ys // snoc operation (cons reversed)
  }

  val sorted = List(1, -3, 4, 2, 6) sortWith (_ < _)
  val sortedWithLengthByDesc = words sortWith (_.length > _.length)

  val rng1 = List.range(1, 5) // List(1, 2, 3, 4)
  val rng2 = List.range(1, 9, 2) // List(1, 3, 5, 7)
  val rng3 = List.range(9, 1, -3) // List(9, 6, 3)

  val filledWitha = List.fill(5)('a') // List(a, a, a, a, a)
  val filledWithHello = List.fill(3)("hello") // List(hello, hello, hello)
  val filledWithb = List.fill(2, 3)('b') // List(List(b, b, b), List(b, b, b))

  val squares = List.tabulate(5)(n => n * n)
  val multiplication = List.tabulate(5, 5)(_ * _)

  val con1 = List.concat(List('a', 'b'), List('c')) // List(a, b, c)
  val con2 = List.concat(List(), List('b'), List('c')) // List(b, c)
  val con3 = List.concat() // List[Nothing] = List()

  val pros = (List(10, 20) zip List(3, 4, 5))
    .map { case (x, y) => x * y } // List(30, 80)
}
