package bifer

import sqala.metadata.*
import sqala.static.dsl.*
import sqala.jdbc.*
import bifer.DB.db
import sqala.data.json.toJson
import java.time.LocalDate
import sqala.data.json.JsonDateFormat

@table("users")
case class User(
    @autoInc
    id: Long,
    name: String,
    sex: String,
    birthday: LocalDate
)

def fetchUser(name: String) =
  db.fetch(from[User]).headOption

@main def main: Unit =
  given JsonDateFormat("yyyy-MM-dd")
  val u = fetchUser("dragon")
  println(u.toJson)
