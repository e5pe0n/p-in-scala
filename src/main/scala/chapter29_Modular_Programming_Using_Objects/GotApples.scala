package chapter29_Modular_Programming_Using_Objects

object GotApples {
  def main(args: Array[String]) = {
    val db: Database =
      if (args(0) == "student") StudentDatabase else SimpleDatabase3

    object browser extends Browser {
      val database = db
    }

    val apple = SimpleDatabase.foodNamed("Apple").get
    for (recipe <- browser.recipesUsing(apple))
      println(recipe)
  }
}
