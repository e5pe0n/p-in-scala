package chapter29_Modular_Programming_Using_Objects

object Apple extends Food("Apple")
object Orange extends Food("Orange")
object Cream extends Food("Cream")
object Sugar extends Food("Sugar")

object FruitSalada
    extends Recipe(
      "fruit salad",
      List(Apple, Orange, Cream, Sugar),
      "Stir it all together."
    )
