import sbt._

object Dependencies {
  val Ver = new {
    val `scala2.13`   = "2.13.0-M4"
    val `scala2.12`   = "2.12.6"
    val `scala2.11`   = "2.11.12"
    val scalafmt      = "1.3.0"
    val shapeless     = "2.3.3"
    val exportHook    = "1.2.0"
    val scalacheck    = "1.14.0"
    val scalatest     = "3.0.6-SNAP2"
    val msgpackJava   = "0.8.16"
    val kindProjector = "0.9.7"
    val macroParadise = "2.1.1"
  }

  val Pkg = new {
    lazy val shapeless     = "com.chuusai"     %% "shapeless"      % Ver.shapeless
    lazy val exportHook    = "org.typelevel"   %% "export-hook"    % Ver.exportHook
    lazy val scalatest     = "org.scalatest"   %% "scalatest"      % Ver.scalatest
    lazy val scalacheck    = "org.scalacheck"  %% "scalacheck"     % Ver.scalacheck
    lazy val msgpackJava   = "org.msgpack"     % "msgpack-core"    % Ver.msgpackJava
    lazy val kindProjector = "org.spire-math"  %% "kind-projector" % Ver.kindProjector
    lazy val macroParadise = "org.scalamacros" % "paradise"        % Ver.macroParadise cross CrossVersion.patch

    def scalaReflect(v: String) = "org.scala-lang" % "scala-reflect" % v % "provided"

    lazy val forTest = Seq(scalatest, scalacheck).map(_ % "test")
  }
}
