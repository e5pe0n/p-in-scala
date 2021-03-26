package chapter29_Modular_Programming_Using_Objects

trait SimpleRecipes {
  this: SimpleFoods => // self type

  object FruitSalada
      extends Recipe(
        "fruit salada",
        List(
          Apple,
          Pear // Pear, defined in SimpleFoods, is in scope by self type.
        ),
        "Mix it all together"
      )

  def allRecipes = List(FruitSalada)
}
