# scala-aws-s3

A Simple Amazon S3 Wrapper for Scala

[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/turutosiya/scala-aws-s3?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=body_badge)

# Usage

## `build.sbt`

    resolvers += "scala-aws-s3" at "http://turutosiya.github.io/scala-aws-s3/"

    libraryDependencies += "com.turutosiya" % "scala-aws-s3_2.11" % "1.10.32"

## list objects

    com.turutosiya.aws.S3("accessKey", "secretKey", "bucket")
        .list.map { key =>
            // do things
        }

## get object

    val file: File =
        com.turutosiya.aws.S3("accessKey", "secretKey", "bucket")
            .get("key/of/the/object")

## put object

    com.turutosiya.aws.S3("accessKey", "secretKey", "bucket")
        .put("/path/to/the/file/to/be/uploaded", "key/of/the/object")
