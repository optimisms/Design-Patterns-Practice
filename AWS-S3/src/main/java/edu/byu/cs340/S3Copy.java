package edu.byu.cs340;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;


public class S3Copy {

    public static void main(String[] args) {

        // Create AmazonS3 object for doing S3 operations
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withRegion("us-west-2")
                .build();

        // Write code to do the following:
        String targetBucket = args[4];
        if (args[4].contains("s3://")) { targetBucket = args[4].substring(5); }

        File sourceFile = new File(args[3]);

        String fileName = args[3];
        while (fileName.contains("/")) { fileName = fileName.substring(fileName.indexOf('/') + 1);}

        AmazonS3 client = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        PutObjectRequest req = new PutObjectRequest(targetBucket, fileName, sourceFile);
        client.putObject(req);
    }
}