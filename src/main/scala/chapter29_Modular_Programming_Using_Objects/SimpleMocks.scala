package chapter29_Modular_Programming_Using_Objects

object SimpleDatabase1 {
  def allFoods = List(Apple, Orange, Cream, Sugar)

  def foodNamed(name: String): Option[Food] = allFoods.find(_.name == name)

  def allRecipes: List[Recipe] = List(FruitSalada)

  case class FoodCategory(name: String, foods: List[Food])

  private var categories = List(
    FoodCategory("fruits", List(Apple, Orange)),
    FoodCategory("misc", List(Cream, Sugar))
  )

  def allCategories = categories
}

object SimpleBrowser1 {
  def recipesUsing(food: Food) = SimpleDatabase1.allRecipes.filter(recipe =>
    recipe.ingredients.contains(food)
  )

  def displayCategory(category: SimpleDatabase1.FoodCategory) = {
    println(category)
  }
}

object SimpleMocksMain1 extends App {
  val apple = SimpleDatabase1.foodNamed("Apple").get
  println(apple) // Apple

  val recipesUsingApple = SimpleBrowser1.recipesUsing(apple)
  println(recipesUsingApple) //  List(fruit salad)
}
