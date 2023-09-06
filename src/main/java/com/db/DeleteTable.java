package com.db;

import java.util.Scanner;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

/**
 * DeleteTable
 */
public class DeleteTable {
    private DynamoDB dynmdb = DynamoDBInstance.getInstance();
    String table_name;
    public void delete(){
        System.out.print("Table Name : ");
        Scanner read = new Scanner(System.in);
        table_name = read.nextLine();

        Table table = dynmdb.getTable(table_name);

        try{
            System.out.println("Deleting "+ table_name+" ...");
            table.delete();
            table.waitForDelete();
            System.out.println("Table deleted successfully ...");
        }catch(Exception e){
            System.out.println("Unable to delte table ...");
            System.out.println(e.getMessage());
        }
    }
}
