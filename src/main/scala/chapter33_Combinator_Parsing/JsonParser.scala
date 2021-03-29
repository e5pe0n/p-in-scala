package chapter33_Combinator_Parsing

import scala.util.parsing.combinator._

private class JSON extends JavaTokenParsers {
  def value: Parser[Any] =
    obj | arr | stringLiteral | floatingPointNumber | "null" | "true" | "false"

  def obj: Parser[Any] = "{" ~ repsep(member, ",") ~ "}"

  def arr: Parser[Any] = "[" ~ repsep(value, ",") ~ "]"

  def member: Parser[Any] = stringLiteral ~ ":" ~ value
}

import java.io.FileReader

private object ParserJSON extends JSON with App {
  val r = new FileReader("resources/sample1.json")
  println(parseAll(value, r))
}
// [12.1] parsed: (({~List((("address book"~:)~(({~List((("name"~:)~"John Smith"), (("address"~:)~(({~List((("street"~:)~"10 Market Street"), (("city"~:)~"San Francisco, CA"), (("zip"~:)~94111)))~})), (("phone numbers"~:)~(([~List("408 338-4238", "408 111-6892"))~]))))~}))))~})
