package bifer

import unsafeExceptions.canThrowAny

import sqala.dsl.*
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
    email: String,
    category: String,
    `type`: String,
    amount: BigDecimal
)
def fetchUser(email: String) =
  val q = query[User].filter(_.email == email).take(1)
  println(q.sql(PostgresqlDialect)(0))
  db.fetch(q).headOption

@main def main: Unit =
  val r = fetchUser("dragove@qq.com")
