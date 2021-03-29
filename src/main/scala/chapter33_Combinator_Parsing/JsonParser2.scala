package chapter33_Combinator_Parsing

import scala.util.parsing.combinator._
import java.io.FileReader

class JSON2 extends JavaTokenParsers {
  def value: Parser[Any] = (
    obj
      | arr
      | stringLiteral
      | floatingPointNumber
      | "null" ^^ (x => null)
      | "true" ^^ (x => true)
      | "false" ^^ (x => false)
  )

  def obj: Parser[Map[String, Any]] =
    "{" ~> repsep(member, ",") <~ "}" ^^ (Map() ++ _)

  def arr: Parser[List[Any]] = "[" ~> repsep(value, ",") <~ "]"

  def member: Parser[(String, Any)] = stringLiteral ~ ":" ~ value ^^ {
    case name ~ ":" ~ value => (name, value)
  }
}

object ParserJSON2 extends JSON2 with App {
  val r = new FileReader("resources/sample1.json")
  println(parseAll(value, r))
  // [12.1] parsed: Map("address book" -> Map("name" -> "John Smith", "address" -> Map("street" -> "10 Market Street", "city" -> "San Francisco, CA", "zip" -> 94111), "phone numbers" -> List("408 338-4238", "408 111-6892")))
}
