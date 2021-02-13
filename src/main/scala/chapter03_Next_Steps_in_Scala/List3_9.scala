package chapter3

object List3_9 {
  def formatArgs(args: Array[String]) = args.mkString("\n")
  def main(args: Array[String]) = {
    val res = formatArgs(Array("zero", "one", "two"))
    assert(res == "zero\none\ntwo")
  }
}
