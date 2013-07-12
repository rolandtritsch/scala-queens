package org.tritsch.scala.queens

import org.scalatest._

class QueensSpec extends FlatSpec {
  val solutions = List(
    (-1,0), (0,0),
    (1,1), (2,0), (3,0), (4,2), (5,10), (6,4), (7,4), (8,92),
    (9,352), (10, 724)
  )

  "Finding solutions" should "return the right number of solutions" in {
    solutions.map(s => s match {case (n, numberOfSolutions) => assert(Queens.findSolutions(n) === numberOfSolutions)})
  }
}
