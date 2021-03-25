package chapter28_Working_with_XML

// need scala-xml library, https://github.com/scala/scala-xml.
// see how to at https://github.com/scala/scala-xml/wiki/Getting-started
object XMLsMain extends App {
  val a = <a>{"hello" + ", world"}</a>
  println(a) // <a>hello, world</a>

  val yearMade = 1955
  val a2 = <a>{
    if (yearMade < 2000) <old>{yearMade}</old> else xml.NodeSeq.Empty
  }</a>
  println(a2) // <a><old>1955</old></a>

  val a3 = <a>{3 + 4}</a>
  println(a3) // <a>7</a>

  val a4 = <a>{"</a>potential security hole<a>"}</a>
  println(a4)

  val a5 = <a>{{{{brace yourself!}}}}</a>
  println(a5) // <a>{{brace yourself!}}</a>
}
