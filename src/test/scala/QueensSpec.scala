package org.tritsch.scala.queens

import org.scalatest._

class QueensSpec extends FlatSpec {
  "Positions" should "be legal" in {
    assert(Queens.Pos(1,1).legal(Queens.Pos(2,3)), "(1,1)(2,3) should be legal")
    assert(!Queens.Pos(1,1).legal(Queens.Pos(1,3)), "(1,1)(2,3) should be illegal")
    assert(!Queens.Pos(1,1).legal(Queens.Pos(3,1)), "(1,1)(2,3) should be illegal")
    assert(!Queens.Pos(1,1).legal(Queens.Pos(3,3)), "(1,1)(2,3) should be illegal")
  }

  "A row" should "return positions in that row" in {
    assert(Queens.rowList(4,0).mkString(",") === "Pos(0,0),Pos(0,1),Pos(0,2),Pos(0,3)")
  }

  "Seeding" should "return the list of possible start points for solutions" in {
    assert(Queens.seed(4).mkString(",") === "List(Pos(0,0)),List(Pos(0,1)),List(Pos(0,2)),List(Pos(0,3))")
  }

  "Expanding" should "return a full board" in {
    val possibleSolutions = Queens.expand(Queens.seed(4), 4, 4-1)
    assert(possibleSolutions.size === 10, "There should be 10 possible combinations")
    assert(possibleSolutions.mkString(",") === "List(Pos(3,1), Pos(0,0)),List(Pos(3,2), Pos(0,0)),List(Pos(3,0), Pos(0,1)),List(Pos(3,2), Pos(0,1)),List(Pos(3,3), Pos(0,1)),List(Pos(3,0), Pos(0,2)),List(Pos(3,1), Pos(0,2)),List(Pos(3,3), Pos(0,2)),List(Pos(3,1), Pos(0,3)),List(Pos(3,2), Pos(0,3))")
  }

  "Solving the problem" should "return valid solutions" in {
    val solutions = List(
      (-1,0), (0,0),
      (1,1), (2,0), (3,0), (4,2), (5,10), (6,4), (7,40), (8,92),
      (9,352), (10,724)
    )

    solutions.map(s => s match {case (n, numberOfSolutions) => assert(Queens.solve(n).size === numberOfSolutions)})
  }

  "Solving the problem for a board of size 4" should "give 2 solutions" in {
    val solution = Queens.solve(4).toList
    assert(solution.size === 2)
    assert(solution(0).mkString(",") === "Pos(3,2),Pos(2,0),Pos(1,3),Pos(0,1)")
    assert(solution(1).mkString(",") === "Pos(3,1),Pos(2,3),Pos(1,0),Pos(0,2)")
  }
}
