package chapter3

object List3_5 {
  def main(args: Array[String]) = {
    var jetSet = Set("Boeing", "Airbus") // immutable set
    jetSet += "Lear" // jetSet = jetSet + "Lear". new immutable set is created and assigned to jetSet of left side
    println(jetSet.contains("Cessna"))
  }
}
