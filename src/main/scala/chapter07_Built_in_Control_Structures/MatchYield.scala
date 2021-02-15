package chapter07_Built_in_Control_Structures

object MatchYield {
  def main(args: Array[String]) = {
    val firstArg = if (!args.isEmpty) args(0) else ""

    val friend = firstArg match {
      case "salt"  => "pepper"
      case "chips" => "salsa"
      case "eggs"  => "bacon"
      case _       => "huh?"
    }
    println(friend)
  }
}
