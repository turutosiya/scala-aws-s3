# scala-aws-s3

A simple AWS/S3 wrapper

# example

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