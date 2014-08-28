name := """persistence-odin"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.akka" %% "akka-persistence-experimental" % "2.3.4" % "compile",
  "com.github.krasserm" %% "akka-persistence-testkit"    % "0.3.3"    % "test",
  "com.github.krasserm" %% "streamz-akka-camel" % "0.0.1",
  "com.github.krasserm" %% "streamz-akka-persistence" % "0.0.1"
)

resolvers += "scalaz at bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += "krasserm at bintray" at "http://dl.bintray.com/krasserm/maven"