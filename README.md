# scala-aws-s3

A simple AWS/S3 wrapper

# Usage

## `build.sbt`

    resolvers += "scala-aws-s3" at "http://turutosiya.github.io/scala-aws-s3/"

    libraryDependencies += "com.turutosiya" % "scala-aws-s3_2.11" % "0.1.4"

## list objects

    import com.turutosiya.aws.S3
    ...
    S3("accessKey", "secretKey", "bucket").list.map { key =>
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
