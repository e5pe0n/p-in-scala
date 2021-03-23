package chapter26_Extractors

object EMail {
  // optional
  def apply(user: String, domain: String) = user + "@" + domain

  // mandatory
  def unapply(str: String): Option[(String, String)] = {
    var parts = str split "@"
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}

// 1-variable extractor
object Twice {
  def apply(s: String): String = s + s
  def unapply(s: String): Option[String] = {
    val length = s.length / 2
    val half = s.substring(0, length)
    if (half == s.substring(length)) Some(half) else None
  }
}

// 0-variable extractor
object UpperCase {
  def unapply(s: String): Boolean = s.toUpperCase == s
}

object EMailMain extends App {
  def say(s: Any) =
    s match {
      case EMail(user, domain) => println(user + " AT " + domain)
      case _                   => println("not an email address")
    }
  say("abc@example.com") // abc AT example.com
  say("abc@example@com") // not an email address
  say(123) // not an email address

  def userTwiceUpper(s: String) = s match {
    case EMail(Twice(x @ UpperCase()), domain) =>
      "match: " + x + " in domain " + domain
    case _ => "no match"
  }
  println(userTwiceUpper("DIDI@hotmail.com")) // match: DI in domain hotmail.com
  println(userTwiceUpper("DIDO@hotmail.com")) // no match
  println(userTwiceUpper("didi@hotmail.com")) // no match
}
