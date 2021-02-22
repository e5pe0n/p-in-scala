package chapter12_Traits

trait Philosophical {
  def philosophize() = println("I consume memory, therefore I am!")
}

class Animal
trait HasLegs

class Frog extends Animal with Philosophical with HasLegs {
  override def toString = "green"
  override def philosophize() = println("It ain't easy being " + toString + "!")
}

object Main {
  def main(args: Array[String]) = {
    val frog = new Frog
    println(frog)
    frog.philosophize()

    val phil: Philosophical = frog
    println(frog)
    phil.philosophize()
  }
}
