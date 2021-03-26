package chapter29_Modular_Programming_Using_Objects

abstract class Database2 extends FoodCategories {
  def allFoods: List[Food]
  def allRecipes: List[Recipe]
  def foodNamed(name: String) = allFoods.find(f => f.name == name)
}
