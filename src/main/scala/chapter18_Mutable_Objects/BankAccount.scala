package chapter18_Mutable_Objects

class BankAccount {
  private var bal: Int = 0

  def balance: Int = bal

  def deposite(amount: Int) = {
    require(amount > 0)
    bal += amount
  }

  def withdraw(amount: Int): Boolean =
    if (amount > bal) false
    else {
      bal -= amount
      true
    }
}

object BankAccount extends App {
  val acc = new BankAccount
  // acc deposite 0  // Exception in thread "main" java.lang.IllegalArgumentException: requirement failed
  acc deposite 100
  println(acc withdraw 80) // true
  println(acc withdraw 80) // false
}
