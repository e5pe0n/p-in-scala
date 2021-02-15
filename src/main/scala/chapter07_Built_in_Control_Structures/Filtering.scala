package chapter07_Built_in_Control_Structures

object Filtering {
  def main(args: Array[String]) = {
    val filesHere = (new java.io.File(".")).listFiles
    for (
      file <- filesHere
      if file.isFile
      if file.getName.endsWith(".scala")
    )
      println(file)
  }
}
