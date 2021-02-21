package chapter10_Composition_and_Inheritance

abstract class ElementDemo {
  def demo() = println("Element's implementation invoked")
}

class ArrayElementDemo extends ElementDemo {
  override def demo() = println("ArrayElement's implementation invoked")
}

class LineElementDemo extends ArrayElementDemo {
  override def demo() = println("LineElement's implementation invoked")
}

class UniformElementDemo extends ElementDemo

object ElementDemo {
  def invokeDemo(e: ElementDemo) = e.demo()
  def main(args: Array[String]) = {
    invokeDemo(new ArrayElementDemo)
    invokeDemo(new LineElementDemo)
    invokeDemo(new UniformElementDemo)
  }
}
