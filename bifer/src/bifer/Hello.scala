package bifer

import sqala.dsl.*
import sqala.jdbc.*
import bifer.DB.db
import sqala.dsl.statement.query.SelectQuery
import sqala.printer.PostgresqlDialect
import java.util.Arrays

extension (exprS: Expr[String])
  def len = length(exprS)
  def toUp = upper(exprS)

case class User(
    name: String,
    email: String,
    category: String,
    `type`: String,
    amount: BigDecimal,
    deleted: Boolean
)

def fetchUser(email: String) =
  val q = query[User]
    .filter(_.email == email)
    .leftJoin[User]((a, b) => a.name == b.name)
    .groupBy((a, b) => (name = a.name))
    .map((g, a, b) => (name = g.name))
  val sql = q.sql(PostgresqlDialect)
  println(sql(0))
  println(Arrays.toString(sql(1)))
  db.fetch(q).head

@main def main: Unit =
  fetchUser("dove@qq.com")
