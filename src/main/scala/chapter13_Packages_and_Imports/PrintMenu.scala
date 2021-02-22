package chapter13_Packages_and_Imports

package printmenu
import bobsdelights.Fruits
import bobsdelights.showFruit

object PrintMenu {
  def main(args: Array[String]) = {
    for (fruit <- Fruits.menu)
      showFruit(fruit)
  }
}