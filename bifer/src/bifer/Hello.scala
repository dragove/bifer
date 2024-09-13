package bifer

import unsafeExceptions.canThrowAny

import sqala.dsl.*
import sqala.dynamic.*
import sqala.printer.PostgresqlDialect
import bifer.DB.db
import java.sql.SQLException
import java.time.LocalDateTime
import bifer.DB.ds
import sqala.jdbc.JdbcContext

case class User(name: String, age: Int, time: LocalDateTime)
case class Result(name: String, amount: BigDecimal)
def fetch(name: String) =
  val q = select(
    List(
      column(name) as "name",
      sqala.dynamic.sum(column("amount")) as "amount"
    )
  ) from table("user") groupBy List(column(name))
  val sql = q.sql(PostgresqlDialect)
  db.fetchTo[Result](sql)

@main def main: Unit =
  for
    name <- List("name", "category", "type")
    r <- fetch(name)
  do println(s"${name} => ${r.name}: ${r.amount}")
