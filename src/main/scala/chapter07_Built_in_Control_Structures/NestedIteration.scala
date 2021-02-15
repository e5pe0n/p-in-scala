package chapter07_Built_in_Control_Structures

object NestedIteration {
  def main(args: Array[String]) = {
    val filesHere = (new java.io.File(".")).listFiles

    def fileLines(file: java.io.File) =
      scala.io.Source.fromFile(file).getLines().toArray

    def grep(pattern: String) = for (
      file <- filesHere
      if file.getName.endsWith(".scala");
      line <- fileLines(file)
      if line.trim.matches(pattern)
    ) println(s"$file: ${line.trim}")

    grep(".*gcd.*")
  }
}
