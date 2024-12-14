import mill._, scalalib._

trait CommonModule extends ScalaModule {
  def scalaVersion = "3.6.2"
  def scalacOptions = Seq(
    "-Werror",
    "-feature",
    "-Wsafe-init",
    "-Wunused:all",
    "-deprecation",
    "-Yexplicit-nulls",
    "-language:strictEquality",
    "-language:experimental.namedTuples",
  )
}

object bifer extends CommonModule {
  val sqalaVersion = "0.2.3"
  def ivyDeps = Agg(
    ivy"com.wz7982::sqala-jdbc:$sqalaVersion",
    ivy"com.wz7982::sqala-dynamic:$sqalaVersion",
    ivy"com.softwaremill.sttp.tapir::tapir-core:1.11.10",
    ivy"com.zaxxer:HikariCP:5.1.0",
    ivy"org.postgresql:postgresql:42.7.4",
    ivy"org.apache.logging.log4j:log4j-slf4j2-impl:2.23.1"
  )

  object test extends ScalaTests with TestModule.Munit {
    def ivyDeps = Agg(
      ivy"org.scalameta::munit:1.0.3"
    )
  }
}
