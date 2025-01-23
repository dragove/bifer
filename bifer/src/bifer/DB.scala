package bifer

import sqala.jdbc.*
import sqala.printer.PostgresqlDialect
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object DB {
  given Logger = Logger(_ => ())
  private val config = HikariConfig()
  config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres")
  config.setUsername("postgres")
  config.setPassword("1234")
  config.setSchema("public")
  config.addDataSourceProperty("cachePrepStmts", "true")
  config.addDataSourceProperty("prepStmtCacheSize", "250")
  config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
  val ds = HikariDataSource(config)
  val db = JdbcContext(ds, PostgresqlDialect)
}
