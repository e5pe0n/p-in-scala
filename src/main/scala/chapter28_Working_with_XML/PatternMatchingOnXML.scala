package chapter28_Working_with_XML

object PatternMatchingOnXML extends App {
  def proc(node: scala.xml.Node): String = node match {
    case <a>{contents}</a> => "It's an a: " + contents
    case <b>{contents}</b> => "It's a b: " + contents
    case _                 => "It's something else."
  }

  val p1_1 = proc(<a>apple</a>)
  println(p1_1) // It's an a: apple

  val p1_2 = proc(<b>banana</b>)
  println(p1_2) // It's a b: banana

  val p1_3 = proc(<c>cherry</c>)
  println(p1_3) // It's something else.

  val p1_4 = proc(<a>a <em>red</em> apple</a>)
  println(p1_4) // It's something else.

  val p1_5 = proc(<a/>)
  println(p1_5) // It's something else.

  def proc2(node: scala.xml.Node): String = node match {
    case <a>{contents @ _*}</a> => "It's an a: " + contents
    case <b>{contents @ _*}</b> => "It's a b: " + contents
    case _                      => "It's something else."
  }

  val p2_1 = proc2(<a>a <em>red</em> apple</a>)
  println(p2_1) // It's an a: a <em>red</em> apple

  val p2_2 = proc2(<a/>)
  println(p2_2) // It's an a: List()

  val catalog = scala.xml.XML.load("resources/catalog.xml")
  catalog match {
    case <catalog>{therms @ _*}</catalog> =>
      for (therm @ <cctherm>{_*}</cctherm> <- therms)
        println("processing: " + (therm \ "description").text)
  }
// processing: hot dog #5
// processing: Sprite Boy
}
