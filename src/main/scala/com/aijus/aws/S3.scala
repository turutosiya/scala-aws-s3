package com.aijus.aws

import java.io.File
import java.net.URL

import com.amazonaws.AmazonServiceException
import com.amazonaws.auth._
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model._
import com.amazonaws.services.s3.transfer.TransferManager

import scala.collection.mutable.ListBuffer

object S3 {

}

/**
 * S3 class
 * 
 * @param accessKey
 * @param secretKey
 * @param bucket
 */
case class S3(
  accessKey: String,
  secretKey: String,
  bucket: String){

  /**
   *
   */
  lazy val client =
    new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey))

  /**
   * exists
   *
   * @param key
   * @return Boolean
   */
  def exists(key: String): Boolean =
    try {
      client.getObject(bucket, key)
      true
    } catch {
      case e: AmazonServiceException =>
        false
      case t: Throwable =>
        throw t
    }

  /**
   * get
   *
   * @param key
   */
  def get(key: String): File =
    get(key, File.createTempFile("com_aijus_aws_s3", "1"))

  /**
   * get
   *
   * @param key
   */
  def get(key: String, local: File): File = {
    // get
    new TransferManager(client)
      .download(new GetObjectRequest(bucket, key), local)
      .waitForCompletion()
    //
    local
  }

  /**
   * put
   *
   * @param key
   * @return
   */
  def put(local: String, key: String): S3 =
    put(new File(local), key)

  /**
   * put
   *
   * @param key
   * @return
   */
  def put(file: File, key: String): S3 = {
    client.putObject(new PutObjectRequest(bucket, key, file))
    this
  }

  /**
   *
   * @param key
   * @return
   */
  def delete(key: String): S3 = {
    //
    client.deleteObject(bucket, key)
    // return this for method chain
    this
  }

  /**
   *
   * @param src
   * @param dst
   */
  def copy(src: String, dst: String): S3 = {
    list(src) map { key =>
      client.copyObject(bucket, key, bucket, key.replace(src, dst))
    }
    this
  }

  /**
   * rename
   *
   * @param src
   * @param dst
   */
  def rename(src: String, dst: String): S3 = {
    // copy then delete
    this.copy(src, dst).delete(src)
  }

  /**
   * url
   *
   * @param key
   * @return URL
   */
  def url(key: String): URL =
    client.getUrl(bucket, key)
    /* client.generatePresignedUrl(
      new GeneratePresignedUrlRequest(
        bucket,
        key)) */

  /**
   * list
   *
   * @param prefix
   * @return List[S3ObjectSummary]
   */
  def list(prefix: String): List[String] = {
    //
    var keys: ListBuffer[String] = ListBuffer[String]()
    //
    val request = new ListObjectsRequest()
    request.setBucketName(bucket)
    request.setPrefix(prefix)
    //
    var objectListing: ObjectListing = null
    //
    try {
      //
      do {
        objectListing = client.listObjects(request)
        val summaries = objectListing.getObjectSummaries
        (0 to summaries.size - 1).foreach { i => keys += summaries.get(i).getKey() }
        request.setMarker(objectListing.getNextMarker())
      } while (objectListing.isTruncated())
    } catch {
      case e: AmazonServiceException =>

      case t: Throwable =>
        throw t
    }
    //
    keys.toList
  }

  /**
   * subdirs
   *
   * @param prefix
   * @return List[String]
   */
  def subdirs(prefix: String): List[String] = {
    //
    var keys: ListBuffer[String] = ListBuffer[String]()
    //
    val request = new ListObjectsRequest()
    request.setBucketName(bucket)
    request.setPrefix(prefix)
    request.setDelimiter("/")
    //
    var objectListing: ObjectListing = null
    //
    try {
      //
      do {
        objectListing = client.listObjects(request)
        val prefixes = objectListing.getCommonPrefixes()
        (0 to prefixes.size - 1).foreach { i => keys += prefixes.get(i) }
        request.setMarker(objectListing.getNextMarker())
      } while (objectListing.isTruncated())
    } catch {
      case e: AmazonServiceException =>
        false
      case t: Throwable =>
        throw t
    }
    //
    keys.toList
  }
}
