package chapter30_Object_Equality

object EqualsWithWrongSignature3Main extends App {
  private class Point(val x: Int, val y: Int) {
    override def hashCode(): Int = (x, y).##
    override def equals(other: Any): Boolean = other match {
      case that: Point =>
        (that canEqual this) && (this.x == that.x) && (this.y == that.y)
      case _ => false
    }

    def canEqual(other: Any) = other.isInstanceOf[Point]
  }

  private object Color extends Enumeration {
    val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
  }

  private class ColoredPoint(x: Int, y: Int, val color: Color.Value)
      extends Point(x, y) {
    override def hashCode = (super.hashCode, color).##
    override def equals(other: Any) = other match {
      case that: ColoredPoint =>
        (that canEqual this) && super.equals(that) && this.color == that.color
      case _ => false
    }
    override def canEqual(other: Any): Boolean =
      other.isInstanceOf[ColoredPoint]
  }

  private val p = new Point(1, 2)
  private val cp = new ColoredPoint(1, 2, Color.Indigo)
  private val pAnon = new Point(1, 1) { override val y = 2 }
  private val coll = List(p)
  println(coll contains p) // true
  println(coll contains cp) // false
  println(coll contains pAnon) // true
}
