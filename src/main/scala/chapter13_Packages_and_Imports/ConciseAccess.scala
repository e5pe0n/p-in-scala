package bobsrockets {
  package navigation {
    class Navigator {
      val map = new StarMap // bobsrockets.navigation.StarMap
    }
    class StarMap
  }
  class Ship {
    val nav = new navigation.Navigator // bobsrockets.navigation.Navigator
  }
  package fleets {
    class Fleet {
      def addShip() = new Ship // bobsrockets.Ship
    }
  }
}

// package encupsules and hide the name of classes in it.
// thus if we want to access to a class inside a package, the package name is necessary such as line9, `new navigation.Navigator`.
// by contrast, classes outside the package are visible from inside of the package so we can access the class without the package name such as line 13, `new Ship`
