package chapter29_Modular_Programming_Using_Objects

class Recipe(
    val name: String,
    val ingredients: List[Food],
    val instructions: String
) {
  override def toString = name
}
