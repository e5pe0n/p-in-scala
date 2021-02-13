package chapter3

import scala.io.Source

object List3_10 {
  def main(args: Array[String]) = {
    if (args.length > 0) {
      for (line <- Source.fromFile(args(0)).getLines())
        println(line.length.toString + " " + line)
    } else
      Console.err.println("Please enter filename")
  }
}
