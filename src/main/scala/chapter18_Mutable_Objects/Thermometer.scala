package chapter18_Mutable_Objects

class Thermometer {
  var celsius: Float = _

  def fahrenheit = celsius * 9 / 5 + 32
  def fahrenheit_=(f: Float) = celsius = (f - 32) * 5 / 9

  override def toString = s"${fahrenheit}F/${celsius}C"
}

object Thermometer extends App {
  val t = new Thermometer
  println(t)
  t.celsius = 100
  println(t)
  t.fahrenheit = -40
  println(t)
}
