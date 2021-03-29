package chapter30_Object_Equality

object EqualsWithWrongSignatureMain extends App {
  private class Point(val x: Int, val y: Int) {
    override def hashCode = (x, y).##
    override def equals(other: Any): Boolean = other match {
      case that: Point => this.x == that.x && this.y == that.y
      case _           => false
    }
  }

  private object Color extends Enumeration {
    val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
  }

  private class ColoredPoint(x: Int, y: Int, val color: Color.Value)
      extends Point(x, y) {
    override def equals(other: Any): Boolean = other match {
      case that: ColoredPoint =>
        this.color == that.color && super.equals(that)
      case _ => false
    }
  }

  private val p = new Point(1, 2)
  private val cp = new ColoredPoint(1, 2, Color.Red)
  println(p equals cp) // true
  println(cp equals p) // false

  import scala.collection.mutable.HashSet
  println(HashSet[Point](p) contains cp) // false
  println(HashSet[Point](cp) contains p) // true
}
