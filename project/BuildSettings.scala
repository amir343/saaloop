import sbt._
import Keys._

object BuildSettings {
  lazy val basicSettings = Seq[Setting[_]](
    version       := "1.0-SNAPSHOT",
    homepage      := Some(new URL("https://github.com/amir343/saaloop")),
    organization  := "com.jayway",
    organizationHomepage := Some(new URL("http://jayway.com")),
    description   := "A Scala DSL for Hadoop",
    startYear     := Some(2012),
    licenses      := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    scalaVersion  := "2.9.1",
    scalacOptions := Seq("-deprecation", "-encoding", "utf8")
  )

  lazy val noPublishing = Seq(
    publish := (),
    publishLocal := ()
  )

  lazy val exampleSettings = basicSettings ++ noPublishing

}

