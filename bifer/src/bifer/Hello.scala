package bifer

import unsafeExceptions.canThrowAny

import sqala.dynamic.sum as dysum
import sqala.dsl.*
import sqala.dsl.sum as sum
import sqala.dynamic.*
import sqala.jdbc.*
import sqala.printer.PostgresqlDialect
import bifer.DB.db
import java.sql.SQLException
import java.time.LocalDateTime
import bifer.DB.ds
import sqala.jdbc.JdbcContext
import org.postgresql.Driver

case class User(name: String, category: String, `type`: String, amount: BigDecimal)
case class Result(name: String, amount: BigDecimal)
def fetch(name: String) =
  val q = select(List(column(name) as "name", dysum(column("amount")) as "amount")) from
          table("user") groupBy List(column(name))
  val sql = q.sql(PostgresqlDialect)
  db.fetchTo[Result](sql)

def fetch2() =
  db.fetch(query[User])

def fetch3() =
  db.fetch(query[User].groupBy(_.category)
    .map((category, user) => (category, sum(user.amount))))

@main def main: Unit =
  for name <- List("name", "category", "type")
      r <- fetch(name)
  do println(s"${name} => ${r.name}: ${r.amount}")
  for user <- fetch2() do print(s"name: ${user.name};")
  println()
  for r <- fetch3() do println(s"cat: ${r(0)}, amount: ${r(1)}")
