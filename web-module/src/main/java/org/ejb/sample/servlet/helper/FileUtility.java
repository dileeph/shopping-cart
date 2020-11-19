package org.ejb.sample.servlet.helper;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;

public class FileUtility {

    void generateReport(String content) throws Exception {
        FileWriter fileWriter = new FileWriter("~/Downloads/report.csv");
        fileWriter.write(content);
        fileWriter.close();
    }

    public URL uploadS3ObjectAndGetUrl(String bucketName, String uploadContentPath, String keyName) {
        AmazonS3 s3client = getS3Client();
        s3client.putObject(new PutObjectRequest(bucketName, keyName, new File(uploadContentPath)));
        return s3client.getUrl(bucketName, keyName);
    }

    public AmazonS3 getS3Client() {
        AmazonS3 s3client;
        AWSCredentials credentials = new BasicAWSCredentials(System.getenv("AWS_S3_ACCESS_KEY"), System.getenv("AWS_S3_SECRET_KEY"));
            s3client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(System.getenv("AWS_S3_CLIENT_REGION"))
                    .build();
        return s3client;
    }
}
