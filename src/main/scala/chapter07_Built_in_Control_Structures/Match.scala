package chapter07_Built_in_Control_Structures

object Match {
  def main(args: Array[String]) = {
    val firstArg = if (args.length > 0) args(0) else ""

    firstArg match {
      case "salt"  => println("pepper")
      case "chips" => println("salsa")
      case "eggs"  => println("bacon")
      case _       => println("huh?")
    }
  }
}
