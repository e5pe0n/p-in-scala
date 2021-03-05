package chapter20_Abstract_Members

trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int

  lazy val numer = numerArg / g
  lazy val denom = denomArg / g

  override def toString = s"$numer/$denom"

  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}

object LazyRationalTraitRunner extends App {
  val x = 2

  val lrt = new LazyRationalTrait {
    val numerArg = 1 * x
    val denomArg = 2 * x
  }
  println(lrt) // 1 / 2
}
