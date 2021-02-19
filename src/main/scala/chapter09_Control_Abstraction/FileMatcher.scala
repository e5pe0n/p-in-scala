package chapter09_Control_Abstraction

object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file

  def filesEnding(query: String) = filesMatching(_.endsWith(query)) // closure

  def filesContaining(query: String) = filesMatching(
    _.contains(query)
  ) // closure

  def filesRegex(query: String) = filesMatching(_.matches(query)) // closure
}
