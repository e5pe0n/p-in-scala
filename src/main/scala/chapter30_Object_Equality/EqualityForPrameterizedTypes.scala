package chapter30_Object_Equality

object EqualityForPrameterizedTypesMain extends App {
  private trait Tree[+T] {
    def elem: T
    def left: Tree[T]
    def right: Tree[T]
  }

  private object EmptyTree extends Tree[Nothing] {
    def elem = throw new NoSuchElementException("EmptyTree.elem")
    def left = throw new NoSuchElementException("EmptyTree.left")
    def right = throw new NoSuchElementException("EmptyTree.right")
  }

  private class Branch[+T](
      val elem: T,
      val left: Tree[T],
      val right: Tree[T]
  ) extends Tree[T] {
    override def equals(other: Any): Boolean = other match {
      // element type is unchecked because type-parameters are erased at run-time.
      case that: Branch[_] =>
        this.elem == that.elem && this.left == that.left && this.right == that.right
      case _ => false
    }

    override def hashCode(): Int = (elem, left, right).##

    def canEqual(other: Any) = other match {
      case that: Branch[_] => true
      case _               => false
    }
  }

  private val b1 = new Branch[List[String]](Nil, EmptyTree, EmptyTree)
  private val b2 = new Branch[List[Int]](Nil, EmptyTree, EmptyTree)
  println(b1 == b2) // true
}
