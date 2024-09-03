package bifer
import sqala.dsl.*
import sqala.printer.PostgresqlDialect

case class A(x: Int)

@main def main: Unit =
  val q = query[A].filter(_.x > 2)
  val r = queryContext(q)
  println(r.sql(PostgresqlDialect))
