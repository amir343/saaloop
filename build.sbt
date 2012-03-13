

name := "saaloop"

organization := "com.jayway"

version := "1.0-SNAPSHOT"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
	"org.apache.hadoop" % "hadoop-core" % "0.20.205.0",
	"org.specs2" %% "specs2" % "1.8.2" % "test"
)
