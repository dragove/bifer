package bifer

import sqala.jdbc.*
import sqala.metadata.*
import sqala.static.dsl.*
import sqala.data.json.toJson
import java.time.LocalDate

import bifer.DB.db

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
  val u = fetchUser("Dove")
  println(u.toJson)
