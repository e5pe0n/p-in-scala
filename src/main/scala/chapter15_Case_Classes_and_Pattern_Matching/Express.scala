package chapter15_Case_Classes_and_Pattern_Matching

import expr._

object Express extends App {
  val f = new ExprFormatter

  val e1 = BinOp(
    "*",
    BinOp("/", Number(1), Number(2)),
    BinOp("+", Var("x"), Number(1))
  )

  val e2 = BinOp(
    "+",
    BinOp("/", Var("x"), Number(2)),
    BinOp("/", Number(1.5), Var("x"))
  )

  val e3 = BinOp("/", e1, e2)

  def show(e: Expr) = s"${println(f.format(e))}\n\n"

  for (e <- Array(e1, e2, e3)) show(e)
}
