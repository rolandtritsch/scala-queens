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

import com.typesafe.scalalogging.slf4j.Logging

object Queens extends Logging {
  case class Pos(row: Int, column: Int) {
    def sameRow(p: Pos) = row == p.row
    def sameColumn(p: Pos) = column == p.column
    def sameDiag(p: Pos) = (p.column - column).abs == (p.row - row).abs
    def illegal(p: Pos) = sameRow(p) || sameColumn(p) || sameDiag(p)
    def legal(p: Pos) = !illegal(p)
  }

  def rowSet(size: Int, row: Int) = (Iterator.tabulate(size)(column => Pos(row, column))).toList

  def expand(solutions: List[List[Pos]], size: Int, row: Int) =
    for {
      solution <- solutions
      pos <- rowSet(size, row)
      if(solution.forall(_.legal(pos)))
    } yield pos :: solution

  def seed(size: Int) = rowSet(size, 0) map (sol => List(sol))

  def solve(size: Int) = (1 until size).foldLeft(seed(size)) (expand(_, size, _))

  def mkString(solutions: List[List[Pos]]): String = {
    solutions.map(s => {
      val board = Array.fill(s.size)(Array.fill(s.size)("."))
      s.map(p => p match {case Pos(r, c) => board(r)(c) = "Q"})
      board.map(_.mkString(" ")).mkString("\n")
    }).mkString("\n\n")
  }

  def main(args: Array[String]): Unit = {
    require(args.size == 1, "Usage: Queens <n>")
    val n = args(0).toInt

    logger.info("Calculating solutions for boards of size >" + n + "< ...")
    val solutions = solve(n)

    logger.info("Found >" + solutions.size + "< solutions ...")
    logger.info("\n" + mkString(solutions))
    println(solutions.size)
  }
}
