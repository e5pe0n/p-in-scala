package chapter26_Extractors

import scala.util.matching.Regex

object Regexes extends App {
  val Decimal = new Regex("(-)?(\\d+)(\\.\\d*)?")
  val Decimal2 = new Regex("""(-)?(\d+)(\.\d*)?""")
  val Decimal3 = """(-)?(\d+)(\.\d*)?""".r

  val input = "for -1.0 to 99 by 3"
  for (s <- Decimal findAllIn input)
    println(s) // -1.0, 99, 3

  val fst = Decimal findFirstIn input
  println(fst) // Some(-1.0)

  val pre = Decimal findPrefixOf input
  println(pre) // None

  val Decimal(sign, integerpart, decimalpart) = "-1.23"
  println(sign) // -
  println(integerpart) // 1
  println(decimalpart) // .23

  val Decimal(sign2, integerpart2, decimalpart2) = "1.0"
  println(sign2) // null
  println(integerpart2) // 1
  println(decimalpart2) // 0

  for (Decimal(s, i, d) <- Decimal findAllIn input)
    println("sign: " + s + ", integer: " + i + ", decimal: " + d)
// sign: -, integer: 1, decimal: .0
// sign: null, integer: 99, decimal: null
// sign: null, integer: 3, decimal: null
}
