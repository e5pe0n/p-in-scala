package chapter07_Built_in_Control_Structures

object MidStreamAssignment {
  def main(args: Array[String]) = {
    val filesHere = (new java.io.File(".")).listFiles

    def fileLines(file: java.io.File) =
      scala.io.Source.fromFile(file).getLines().toArray

    def grep(pattern: String) = for {
      file <- filesHere
      if file.getName.endsWith(".scala")
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches(pattern)
    } println(s"$file: $trimmed")

    grep(".*gcd.*")
  }
}
