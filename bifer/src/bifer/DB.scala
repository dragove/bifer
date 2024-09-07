package bifer

import sqala.jdbc.*
import javax.sql.DataSource
import sqala.printer.PostgresqlDialect
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object DB {
  given Logger = Logger(_ => ())
  private val config = HikariConfig()
  config.setJdbcUrl("jdbc:postgresql://localhost:5432/dove")
  config.setUsername("dove")
  config.setPassword("1234")
  config.setSchema("demo")
  config.addDataSourceProperty("cachePrepStmts", "true")
  config.addDataSourceProperty("prepStmtCacheSize", "250")
  config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
  private val ds = HikariDataSource(config)
  val db = JdbcContext(ds, PostgresqlDialect)
}
