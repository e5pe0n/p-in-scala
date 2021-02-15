package chapter07_Built_in_Control_Structures

object TransformingArrayWithFor {
  def main(args: Array[String]) = {
    val filesHere = (new java.io.File(".")).listFiles

    def fileLines(file: java.io.File) =
      scala.io.Source.fromFile(file).getLines().toArray

    val forLineLengths = for {
      file <- filesHere
      if file.getName.endsWith(".scala")
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches((".*for.*"))
    } yield trimmed.length
  }
}
