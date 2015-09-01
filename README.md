# scala-aws-s3

A Simple Amazon S3 Wrapper for Scala

# Usage

## `build.sbt`

    resolvers += "scala-aws-s3" at "http://turutosiya.github.io/scala-aws-s3/"

    libraryDependencies += "com.turutosiya" % "scala-aws-s3_2.11" % "0.1.5"

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
