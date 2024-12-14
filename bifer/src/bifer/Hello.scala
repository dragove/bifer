package bifer

import sqala.static.dsl.*
import sqala.jdbc.*
import bifer.DB.db
import sqala.printer.PostgresqlDialect
import java.util.Arrays
import sqala.static.annotation.*
import sqala.data.json.toJson

case class User(
    @primaryKey
    @autoInc
    id: Long,
    name: String,
    email: String,
    category: String,
    `type`: String,
    amount: BigDecimal,
    deleted: Boolean
)

def fetchUser(email: String) =
  val q = queryContext:
    query[User]
      .filter(_.email == email)
  val sql = q.sql(PostgresqlDialect)
  println(sql(0))
  println(Arrays.toString(sql(1)))
  db.fetch(q).head

@main def main: Unit =
  val x = (name = "hi", age = 10)
  println(x.age)
  val u = fetchUser("dove@qq.com")
  println(u.toJson)

