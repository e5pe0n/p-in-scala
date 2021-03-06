package chapter16_Working_with_Lists

object DivideAndConquer extends App {
  def append[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List()   => ys
    case x :: xs1 => x :: append(xs1, ys)
  }
}
