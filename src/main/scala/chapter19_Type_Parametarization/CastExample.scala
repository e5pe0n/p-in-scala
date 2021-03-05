package chapter19_Type_Parametarization

object CastExample extends App {
  val a1 = Array("abc")
  val a2: Array[Object] = a1.asInstanceOf[Array[Object]] // ok
  val a4: Array[Any] = a1.asInstanceOf[Array[Any]] // ok
}
