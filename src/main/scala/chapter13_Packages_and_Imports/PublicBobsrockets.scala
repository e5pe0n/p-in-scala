package bobsrockets

package navigation {
  private[bobsrockets] class Navigator3 {
    protected[navigation] def useStarChart() = {}
    class LegOfJourney {
      private[Navigator3] val distance = 100
    }
    private[this] var speed = 200
  }
}

package launch {
  import navigation._
  object Vehicle {
    private[launch] val guide = new Navigator3
  }
}
