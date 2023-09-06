package com.db;

import java.util.List;
import java.util.Scanner;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

public class DescribeTable{


    private AmazonDynamoDB dynamodb = AmazonDynamoDBClientBuilder.defaultClient();

    private Scanner read = new Scanner(System.in);
    private String table_name ;

    public void describe(){
        System.out.print("Table Name : ");
        table_name = read.nextLine();


        TableDescription table_info = dynamodb.describeTable(table_name).getTable();

        List<AttributeDefinition> attribute = table_info.getAttributeDefinitions();

        System.out.println("Attribute : ");
        for (AttributeDefinition a : attribute) {
            System.out.format("  %s (%s)\n",
                    a.getAttributeName(), a.getAttributeType());
        }





    }


}
