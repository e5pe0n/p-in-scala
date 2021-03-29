package chapter33_Combinator_Parsing

import scala.util.parsing.combinator._

private class Arith extends JavaTokenParsers {
  def expr: Parser[Any] = term ~ rep("+" ~ term | "-" ~ term)
  def term: Parser[Any] = factor ~ rep("*" ~ factor | "/" ~ factor)
  def factor: Parser[Any] = floatingPointNumber | "(" ~ expr ~ ")"
}

private object MyParsers extends RegexParsers {
  val ident: Parser[String] = """[a-zA-Z_]\w*""".r
}

private object ParseExpr extends Arith with App {
  val e1 = "2 * 3 + 7"
  val p1 = parseAll(expr, e1)
  println(p1) // [1.10] parsed: ((2~List((*~3)))~List((+~(7~List()))))

  val e2 = "2 * (3 + 7)"
  val p2 = parseAll(expr, e2)
  println(p2)
  // [1.12] parsed: ((2~List((*~(((~((3~List())~List((+~(7~List())))))~)))))~List())
}
