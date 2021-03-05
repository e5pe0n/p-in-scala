package chapter19_Type_Parametarization

class OptimizedFunctionalQueue[+T] private (
    private[this] var leading: List[T],
    private[this] var trailing: List[T]
) {
  private def mirror() =
    if (leading.isEmpty) {
      while (!trailing.isEmpty) {
        leading = trailing.head :: leading
        trailing = trailing.tail
      }
    }

  def head: T = {
    mirror()
    leading.head
  }

  def tail: OptimizedFunctionalQueue[T] = {
    mirror()
    new OptimizedFunctionalQueue(leading.tail, trailing)
  }

  // T is lower bound for U; U must be supertype of T (T itself is included)
  def enqueue[U >: T](x: U) =
    new OptimizedFunctionalQueue[U](leading, x :: trailing)
}
