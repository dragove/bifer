import mill._, scalalib._

trait CommonModule extends ScalaModule {
  def scalaVersion = "3.5.0"
  def scalacOptions = Seq("-language:experimental.erasedDefinitions")
}

object scoref extends CommonModule {
  def ivyDeps = Agg(
    ivy"com.wz7982::sqala-dsl:0.0.2",
    ivy"com.softwaremill.sttp.tapir::tapir-core:1.11.2"
  )

  object test extends ScalaTests with TestModule.Munit {
    def ivyDeps = Agg(
      ivy"org.scalameta::munit:1.0.1"
    )
  }
}
