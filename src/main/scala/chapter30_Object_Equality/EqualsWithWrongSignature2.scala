package chapter30_Object_Equality

object EqualsWithWrongSignature2 extends App {
  private class Point(val x: Int, val y: Int) {
    override def equals(other: Any) = other match {
      case that: Point =>
        this.x == that.x && this.y == that.y && this.getClass == that.getClass
      case _ => false
    }
  }

  private object Color extends Enumeration {
    val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
  }

  private class ColoredPoint(x: Int, y: Int, val color: Color.Value)
      extends Point(x, y) {
    override def equals(other: Any) = other match {
      case that: ColoredPoint =>
        (this.color == that.color) && super.equals(that)
      case _ => false
    }
  }
}
