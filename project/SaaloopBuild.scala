import sbt._
import Keys._


object SaaloopBuild extends Build {
  import Dependencies._
  import BuildSettings._

  override lazy val settings = super.settings :+ {
    shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Root Project
  // -------------------------------------------------------------------------------------------------------------------

  lazy val saaloop = Project("saaloop", file("."))
    .settings(basicSettings: _*)
    .aggregate(core, examples)


  // -------------------------------------------------------------------------------------------------------------------
  // Modules
  // -------------------------------------------------------------------------------------------------------------------

  lazy val core = Project("saaloop-core", file("saaloop-core"))
    .settings(basicSettings: _*)
    .settings(
    libraryDependencies ++= Seq(
      Compile.hadoop,
      Test.spec2
    )
  )

  // -------------------------------------------------------------------------------------------------------------------
  // Examples
  // -------------------------------------------------------------------------------------------------------------------

  lazy val examples = Project("saaloop-examples", file("saaloop-examples"))
    .settings(exampleSettings: _*)
    .settings(
    libraryDependencies ++= Seq(
      Compile.hadoop
    )
  ).dependsOn(core)

}