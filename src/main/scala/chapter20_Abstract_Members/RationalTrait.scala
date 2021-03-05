package chapter20_Abstract_Members

trait RationalTrait {
  val numerArg: Int
  val denomArg: Int

  require(denomArg != 0)

  private val g = gcd(numerArg, denomArg)

  val numer = numerArg / g
  val denom = denomArg / g

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  override def toString = s"$numer/$denom"
}

object RationalTraitRunner extends App {
  val x = 1

  // val rt = new RationalTrait {
  //   val numerArg = 1 * x
  //   val denomArg = 2 * x
  // } //Exception in thread "main" java.lang.IllegalArgumentException: requirement failed

  val rt = new {
    val numerArg = 1 * x
    val denomArg = 2 * x
  } with RationalTrait
  println(rt) // 1 / 2

  object twoThirds extends {
    val numerArg = 2
    val denomArg = 3
  } with RationalTrait
  println(twoThirds) // 2 / 3

  class RationalClass(n: Int, d: Int) extends {
    val numerArg = n
    val denomArg = d
  } with RationalTrait {
    def +(that: RationalClass) = new RationalClass(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  }

  val rc = new RationalClass(2, 6)
  println(rc) // 1 / 3

  // val rt2 = new {
  //   val numerArg = 1
  //   val denomArg =
  //     this.numerArg * 2 // ERROR: value numerArg is not a member of object
  // } with RationalTrait

  object Demo {
    lazy val x = { println("initializing x"); "done" }
  }
  println(Demo.x)
}
