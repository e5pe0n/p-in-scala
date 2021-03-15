package chapter23_For_Experessions_Revisited

object Filters extends App {
  case class Person(name: String, isMale: Boolean, children: Person*)

  val lara = Person("Lara", false)
  val bob = Person("Bob", true)
  val julie = Person("Julie", false, lara, bob)
  val persons = List(lara, bob, julie)

  val motherAndChilds = persons filter (p => !p.isMale) flatMap (p =>
    (p.children map (c => (p.name, c.name)))
  )
  println(motherAndChilds) // List((Julie,Lara), (Julie,Bob))

  // withFilter does not make an intermediate data
  val motherAndChilds2 = persons withFilter (p => !p.isMale) flatMap (p =>
    (p.children map (c => (p.name, c.name)))
  )
  println(motherAndChilds2) // List((Julie,Lara), (Julie,Bob))

  // translated to the above withFilter version
  val motherAndChilds3 =
    for (p <- persons; if !p.isMale; c <- p.children) yield (p.name, c.name)
  println(motherAndChilds3)
}
