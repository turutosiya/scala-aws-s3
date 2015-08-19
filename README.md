# scala-aws-s3

[![Join the chat at https://gitter.im/turutosiya/scala-aws-s3](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/turutosiya/scala-aws-s3?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

A simple AWS/S3 wrapper

# code example

## `build.sbt`

    resolvers += "scala-aws-s3" at "http://turutosiya.github.io/scala-aws-s3/"

    libraryDependencies += "com.turutosiya" % "scala-aws-s3_2.11" % "0.1.2"

## list objects

    import com.turutosiya.aws.S3
    ...
    S3("accessKey", "secretKey", "bucket").list.map { keys =>
        // do things
    }

## get object

    import com.turutosiya.aws.S3
    ...
    val file: File = S3("accessKey", "secretKey", "bucket").get("key/of/the/object")

## put object

    import com.turutosiya.aws.S3
    ...
    S3("accessKey", "secretKey", "bucket").put(new File("/path/to/the/file/to/be/uploaded"), "key/of/the/object")