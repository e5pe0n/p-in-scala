package chapter29_Modular_Programming_Using_Objects

object StudentDatabase extends Database {
  object FrozenFood extends Food("FrozenFood")

  object HeatItUp
      extends Recipe(
        "heat it up",
        List(FrozenFood),
        "Microwave the 'food' for 10 minutes."
      )

  def allFoods: List[Food] = List(FrozenFood)
  def allRecipes: List[Recipe] = List(HeatItUp)
  def allCategories: List[FoodCategory] = List(
    FoodCategory("edible", List(FrozenFood))
  )
}
