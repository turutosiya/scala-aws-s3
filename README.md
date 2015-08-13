# scala-aws-s3

A simple AWS/S3 wrapper

# example

## `build.sbt`

	resolvers += "Scala AWS S3 Repository" at "http://turutosiya.github.io/scala-aws-s3/"
	libraryDependencies += "com.aijus" % "scala-aws-s3_2.11" % "0.1.0"

## list objects

    import com.aijus.aws.S3
    ...
    S3("accessKey", "secretKey", "bucket").list.map { summary =>
        // do things
    }

## get object

    import com.aijus.aws.S3
    ...
    val file: File = S3("accessKey", "secretKey", "bucket").get("key/of/the/object")

## put object

    import com.aijus.aws.S3
    ...
    S3("accessKey", "secretKey", "bucket").put("/path/to/the/file/to/be/uploaded", "key/of/the/object")