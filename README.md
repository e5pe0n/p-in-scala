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

## Metals

you can run a `main` function in a class or object with the feature of Metals extension.
click `Run | Debug` on the name of class or object.  

## SBT

run `sbt` command in **root of the project** for resolving classpath.  

```
$ sbt
sbt:p-in-scala> run

Multiple main classes detected. Select one to run:
 [1] chapter12_Traits.MainIntQueue
 [2] chapter13_Packages_and_Imports.printmenu.PrintMenu
 [3] chapter14_Assertions_and_Tests.Calc
 [4] chapter15_Case_Classes_and_Pattern_Matching.Express

Enter number: 1
```
