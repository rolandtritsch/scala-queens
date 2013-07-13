# Solution for the [Queens-Problem](http://en.wikipedia.org/wiki/Eight_queens_puzzle)

Solves the queens problem (how to place the max number of queens on a chess board of size N).

    * First you need to install `sbt` (e.g. with `port install sbt` (on the mac))
    * Then you can compile (`sbt compile`) and test (`sbt test`) it
    * And last you can run (`sbt "run 3"`) it on a board of size N (N >= 3)

The algorithm looks for the max number of queens to put on a chess board of size N and then calculates the number of solutions.

The solution is based on the [Rosetta Code](http://rosettacode.org/wiki/N-queens_problem#Scala).

To see the solutions you need to set the log-level to INFO.
