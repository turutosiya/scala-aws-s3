organization := "com.turutosiya"

name := """scala-aws-s3"""

version := "1.10.37"

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
  "-target:jvm-1.8",
  "-Ybackend:GenBCode",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-language:reflectiveCalls",
  "-language:postfixOps"
)

javacOptions ++= Seq(
  "-source", "1.8",
  "-target", "1.8"
)

ideaExcludeFolders += ".idea"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// aws sdk
libraryDependencies += "com.amazonaws" % "aws-java-sdk-s3" % "1.10.37"

//
publishTo := Some(Resolver.file("file",file("target/releases")))