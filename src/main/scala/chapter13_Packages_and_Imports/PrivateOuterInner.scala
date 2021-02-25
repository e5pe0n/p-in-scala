// package chapter13_Packages_and_Imports

// class Outer {
//   class Inner {
//     private def f() = println("f")
//     class InnerMost {
//       f()
//     }
//   }
//   (new Inner).f() // error! f is not accessible
// }
