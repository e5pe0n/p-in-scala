package chapter13_Packages_and_Imports

import bobsdelights.Fruit

object bobsdelightsUser {
  def showFruit(fruit: Fruit) = {
    import fruit._
    println(name + "s are " + color)
  }
}
