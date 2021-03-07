package chapter20_Abstract_Members

class Food
abstract class Animal {
  type SuitableFood <: Food
  def eat(food: SuitableFood): Unit
}

class Grass extends Food
class Cow extends Animal {
  type SuitableFood = Grass
  override def eat(food: Grass) = {}
}

class Pasture {
  // structural subtyping
  var animals: List[Animal { type SuitableFood = Grass }] = Nil
}
