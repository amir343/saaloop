import sbt._

object Dependencies {

  object Compile {
    val hadoop =          "org.apache.hadoop"                 % "hadoop-core"               % "0.20.205.0"       % "compile"
  }

  object Test {
    val spec2 =           "org.specs2"                        %% "specs2"                   % "1.8.2"            % "test"
  }
}
