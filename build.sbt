name := """scala-aws-s3"""

version := "20150811"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// 
lazy val s3 = project in file(".")

