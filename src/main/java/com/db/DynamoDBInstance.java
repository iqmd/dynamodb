package com.db;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public class DynamoDBInstance {

    private static DynamoDB ddb = null;

    private DynamoDBInstance(){}

    public static DynamoDB getInstance(){
        if(ddb == null){
            AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
            // AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            // .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
            // .build();
            return new DynamoDB(client);
        }else{
            return ddb;
        }
    }
}
