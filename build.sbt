name := """scala-aws-s3"""

version := "0.1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// aws sdk
libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.10.10"