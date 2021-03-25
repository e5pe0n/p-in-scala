package chapter28_Working_with_XML

abstract class CCTherm {
  val description: String
  val yearMade: String
  val dateObtained: String
  val bookPrice: Int // in US cents
  val purchasePrice: Int // in US cents
  val condition: Int // 1 to 10

  override def toString = description

  def toXML =
    <cctherm>
      <description>{description}</description>
      <yearMade>{yearMade}</yearMade>
      <dateObtained>{dateObtained}</dateObtained>
      <bookPrice>{bookPrice}</bookPrice>
      <purchasePrice>{purchasePrice}</purchasePrice>
      <condition>{condition}</condition>
    </cctherm>

}

object CCThermMain extends App {
  val therm = new CCTherm {
    val description = "hot dog #5"
    val yearMade = "1952"
    val dateObtained = "March 14, 2006"
    val bookPrice = 2199
    val purchasePrice = 500
    val condition = 9
  }

  val node = therm.toXML
  println(node)
// <cctherm>
//   <description>hot dog #5</description>
//   <yearMade>1952</yearMade>
//   <dateObtained>March 14, 2006</dateObtained>
//   <bookPrice>2199</bookPrice>
//   <purchasePrice>500</purchasePrice>
//   <condition>9</condition>
// </cctherm>

  def fromXML(node: scala.xml.Node): CCTherm = new CCTherm {
    val description = (node \ "description").text
    val yearMade = (node \ "yearMade").text
    val dateObtained = (node \ "dateObtained").text
    val bookPrice = (node \ "bookPrice").text.toInt
    val purchasePrice = (node \ "purchasePrice").text.toInt
    val condition = (node \ "condition").text.toInt
  }

  val therm2 = fromXML(node)
  println(therm2) // hot dog #5

  scala.xml.XML.save("resources/therm1.xml", node)
  val loadnode = xml.XML.load("resources/therm1.xml")
  val therm3 = fromXML(node)
  println(therm3)
}
