package chapter26_Extractors

object Domain {
  def apply(parts: String*): String = parts.reverse.mkString(".")

  def unapplySeq(whole: String): Option[Seq[String]] =
    Some(whole.split("\\.").reverse.toSeq)
}

object ExpandedEMail {
  def unapplySeq(email: String): Option[(String, Seq[String])] = {
    val parts = email split "@"
    if (parts.length == 2) Some(parts(0), parts(1).split("\\.").reverse.toSeq)
    else None
  }
}

object DomainMain extends App {
  def isTomInDotCom(s: String): Boolean = s match {
    case EMail("tom", Domain("com", _*)) => true
    case _                               => false
  }

  println(isTomInDotCom("tom@sun.com")) // true
  println(isTomInDotCom("peter@sun.com")) // false
  println(isTomInDotCom("tom@acm.org")) // false

  val s = "tom@support.epfl.ch"
  val ExpandedEMail(name, topdom, subdoms @ _*) = s
  println(name) // tom
  println(topdom) // ch
  println(subdoms) // ArraySeq(epfl, support)
}
