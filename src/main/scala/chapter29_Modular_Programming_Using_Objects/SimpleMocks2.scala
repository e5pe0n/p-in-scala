package chapter29_Modular_Programming_Using_Objects

import chapter13_Packages_and_Imports.bobsdelights.Fruits

object SimpleDatabase extends Database {
  def allFoods = List(Apple, Orange, Cream, Sugar)

  def allRecipes: List[Recipe] = List(FruitSalada)

  private var categories = List(
    FoodCategory("fruits", List(Apple, Orange)),
    FoodCategory("misc", List(Cream, Sugar))
  )

  def allCategories: List[FoodCategory] = categories
}

object SimpleBrowser extends Browser {
  val database = SimpleDatabase
}

object SimpleMocksMain extends App {
  val apple = SimpleDatabase.foodNamed("Apple").get
  println(apple) // Apple

  val recipeUsingApple = SimpleBrowser.recipesUsing(apple)
  println(recipeUsingApple) // List(fruit salad)
}
