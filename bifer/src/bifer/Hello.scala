package bifer

import unsafeExceptions.canThrowAny

import scala.language.experimental.namedTuples
import sqala.dsl.Extension.given
import sqala.jdbc.Extension.given
import sqala.dsl.*
import sqala.dsl.sum as sum
import sqala.jdbc.*
import sqala.printer.PostgresqlDialect
import bifer.DB.db
import java.sql.SQLException
import java.time.LocalDateTime
import bifer.DB.ds
import sqala.jdbc.JdbcContext
import org.postgresql.Driver

case class User(
    name: String,
    category: String,
    `type`: String,
    amount: BigDecimal
)
def fetch() =
  val q = queryContext {
    val subQuery = query[User].filter(_.name == "Dove").map(_.name).take(1)
    query[User].filter(_.name == subQuery)
  }
  println(q.sql(PostgresqlDialect)(0))
  db.fetch(q)

def fetch3() =
  db.fetch(
    query[User]
      .groupBy(_.category)
      .map((category, user) => (category, sum(user.amount)))
  )

@main def main: Unit =
  fetch()
