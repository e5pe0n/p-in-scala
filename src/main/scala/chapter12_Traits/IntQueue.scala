package chapter12_Traits

import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def get(): Int
  def put(x: Int): Unit
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) = buf += x
}

trait Doubling extends IntQueue {
  abstract override def put(x: Int) = super.put(2 * x)
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) = super.put(x + 1)
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) = if (x >= 0) super.put(x)
}

class MyQueue extends BasicIntQueue with Doubling

object MainIntQueue {
  def main(args: Array[String]) = {
    val queue = new MyQueue
    queue.put(10)
    println(queue.get()) // v20

    val queue2 = new BasicIntQueue with Doubling
    queue2.put(10)
    println(queue2.get()) // 20

    // traits further to the right take effect first
    val queue3 = new BasicIntQueue with Incrementing with Filtering
    queue3.put(-1)
    queue3.put(0)
    queue3.put(1)
    println(queue3.get()) // 1
    println(queue3.get()) // 2

    val queue4 = new BasicIntQueue with Filtering with Incrementing
    queue4.put(-1)
    queue4.put(0)
    queue4.put(1)
    println(queue4.get()) // 0
    println(queue4.get()) // 1
    println(queue4.get()) // 2
  }
}
