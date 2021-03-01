/*
 __________       .__                     .___
 \______   \ ____ |  | _____    ____    __| _/
 |       _//  _ \|  | \__  \  /    \  / __ |
 |    |   (  <_> )  |__/ __ \|   |  \/ /_/ |
 |____|_  /\____/|____(____  /___|  /\____ |
 \/                 \/     \/      \/
 Copyright (c), 2013, roland@tritsch.org
 http://www.tritsch.org
 */

package org.tritsch.scala.queens

import de.sciss.log._

/** Main class/object to solve the N Queens problem. */
object Queens {
  val logger = new Logger("scala-queens", Level.Info, Console.err)

  /** Describe a position on the board. Also checks, if putting another Queens into position p is legal. */
  case class Pos(row: Int, column: Int) {
    def sameRow(p: Pos) = row == p.row
    def sameColumn(p: Pos) = column == p.column
    def sameDiag(p: Pos) = (p.column - column).abs == (p.row - row).abs
    def illegal(p: Pos) = sameRow(p) || sameColumn(p) || sameDiag(p)
    def legal(p: Pos) = !illegal(p)
  }

  /** @return a row as a list of positions. */
  def rowList(size: Int, row: Int) =
    (0 until size).toList.map(column => Pos(row, column))

  /** @return a list of possible solutions with the first queen placed on each. */
  def seed(size: Int) = rowList(size, 0).map(sol => List(sol))

  /** @return expand the list of existing solutions with one more queen and look for legal positions. */
  def expand(solutions: List[List[Pos]], size: Int, row: Int) = {
    logger.debug(
      s"${size}/${row}/${solutions.size}/${solutions(0).size}"
    )
    logger.debug(solutions.toString)
    (for {
      solution <- solutions
      pos <- rowList(size, row)
      if (solution.forall(_.legal(pos)))
    } yield pos :: solution).toList
  }

  /** @return expand the first set of solutions with N queens. */
  def solve(size: Int) = (1 until size).foldLeft(seed(size))(expand(_, size, _))

  /** @return a string that shows/dumps a board/solution (for debugging). */
  def dump(s: List[Pos]): String = {
    val board = Array.fill(s.size)(Array.fill(s.size)("."))
    s.map(p => p match { case Pos(r, c) => board(r)(c) = "Q" })
    board.map(_.mkString(" ")).mkString("\n")
  }

  /** Main method to solve the N Queens problem. */
  def main(args: Array[String]): Unit = {
    require(args.size == 1, "Usage: Queens <n>")
    val n = args(0).toInt

    logger.info(s"Calculating solutions for boards of size >${n}< ...")
    val solutions = solve(n)

    logger.info(s"Found >${solutions.size}< solutions ...")
    logger.info("\n" + solutions.map(s => dump(s)).mkString("\n\n"))
    println(solutions.size)
  }
}
