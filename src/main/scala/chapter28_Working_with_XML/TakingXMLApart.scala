package chapter28_Working_with_XML

object TakingXMLApart extends App {
  // Extracting text
  val txt = <a>Sounds <tag/> good</a>.text
  println(txt) // Sounds  good

  val txt2 = <a>input ---&gt; output </a>.text
  println(txt2) // input ---> output

  // Extracting sub-elements
  val b = <a><b><c>hello</c></b></a> \ "b"
  println(b) // <b><c>hello</c></b>

  // Extracting sub-sub-element
  val c = <a><b><c>hello</c></b></a> \ "c"
  println(c) // NodeSeq()

  val c1 = <a><b><c>hello</c></b></a> \\ "c"
  println(c1) // <c>hello</c>

  // Extracting attributes
  val joe = <employee
    name="Joe"
    rank="code monkey"
    serial="123"
  />
  val name = joe \ "@name"
  val serial = joe \ "@serial"
  println(s"name=$name, serial=$serial") // name=Joe, serial=123
}
