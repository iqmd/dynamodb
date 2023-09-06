package com.db;

import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import java.util.Scanner;


public class PutItem {
    DynamoDB dynamoDB = DynamoDBInstance.getInstance();

    Scanner read = new Scanner(System.in);

    public void putItem(){
        String table_name;
        System.out.print("Table Name : ");
        table_name = read.nextLine();
        Table table = dynamoDB.getTable(table_name);
        Item item = new Item();
        String answer="y";
        String key;
        String value;

        System.out.print("Primary Key : ");
        key = read.nextLine();
        System.out.print("Value : ");
        value = read.nextLine();

        item.withPrimaryKey(key,value);


        System.out.println("Do you want to add more attributes ?");
        answer = read.nextLine();


        while(answer.equals("y")){
            System.out.print("Key : ");
            key = read.nextLine();
            System.out.print("Value :");
            value = read.nextLine();

            item.withString(key, value);


            System.out.print("Do you want to add more attributes ?");
            answer = read.nextLine();


        }

        table.putItem(item);
    }
}
