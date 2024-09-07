package bifer

import unsafeExceptions.canThrowAny

import sqala.dsl.*
import sqala.printer.PostgresqlDialect
import bifer.DB.db
import java.sql.SQLException
import java.time.LocalDateTime

case class User(name: String, age: Int, time: LocalDateTime)

@main def main: Unit =
  val q = query[User].filter(_.age > 1).take(1)
  try {
    val r = db.find(q)
    val p = r match {
      case Some(x) => x.name
      case None => "no"
    }
    println(p)
  } catch {
    case e: SQLException => {
      e.printStackTrace()
    }
    case _ => None
  }


