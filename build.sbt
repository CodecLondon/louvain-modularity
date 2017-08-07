import sbt.Keys.libraryDependencies

name := "Louvain Modularity"

version := "0.0.1"

scalaVersion := "2.11.6"
val sparkVersion = "2.2.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-graphx" % sparkVersion,

  "net.sf.opencsv" % "opencsv" % "2.3",

  "org.specs2" %% "specs2-core" % "3.8.3-20160524134053-8145e17" % "test",
  "org.specs2" %% "specs2-matcher-extra" % "3.8.3-20160524134053-8145e17" % "test",
  "org.specs2" %% "specs2-mock" % "3.8.3-20160524134053-8145e17" % "test",
  "org.mockito" % "mockito-all" % "1.10.19" % "test"
)

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

// http://stackoverflow.com/questions/28612837/spark-classnotfoundexception-when-running-hello-world-example-in-scala-2-11
fork := true
