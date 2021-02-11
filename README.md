# p-in-scala

Learning about *Scala* by "Programming in Scala (Fourth Edition)"  


# Install Scala

see `.devcontainer/Dockerfile`


# SBT

first install the `Scala (Metals)` VSCode extension.  
next exec `touch build.sbt` on a directory that will be root of project.  
then a modal pop up in VSCode and click `import build`.  
Metals creates template files for a scala project.    

# Run a Scala Program

a package name does not include `src/main/scala`.  
for instance, if you want to run `src/main/scala/Chapter2/Example1.scala`, you should exec `sbt "runMain Chapter2.Example1.scala"`.  

```
$ sbt "runMain <package>.<class>"

e.g.  anywhere in project
$ sbt "runMain Chapter2.Example1.scala"
```

you can pass arguments by appending arguments.  
for instance, suppose that you want to run the following program with a argument: `planet`.  

```scala
// src/main/scala/Chapter2/Example2.scala
package Chapter2

object Example2 {
  def main(args: Array[String]): Unit = {
    println("Hello, " + args(0) + "!")
  }
}
```

```
$ sbt "runMain Chapter2.Example2 planet"
"Hello, planet!"
```
