package chapter19_Type_Parametarization

class LowerBoundQueue[+T](
    private val leading: List[T],
    private val trailing: List[T]
) {
  // T is lower bound for U; U must be supertype of T (T itself is included)
  def enqueue[U >: T](x: U) = new LowerBoundQueue[U](leading, x :: leading)
}
