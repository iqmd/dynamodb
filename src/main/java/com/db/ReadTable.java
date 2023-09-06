package com.db;

import java.util.*;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;

/**
 * ScanTable
 */
public class ReadTable {
    private DynamoDB dynamodb = DynamoDBInstance.getInstance();
    private Scanner read = new Scanner(System.in);
    private String table_name ;

    public void scan(){
        System.out.print("Table Name : ");
        table_name = read.nextLine();

        Table table = dynamodb.getTable(table_name);


        ItemCollection<ScanOutcome> items = table.scan();


        for(Item item : items){
            System.out.println(item.toJSONPretty());
        }
        read.close();

    }

    public void query(){
        System.out.print("Table Name : ");
        table_name = read.nextLine();

        Table table = dynamodb.getTable(table_name);
        Index index = table.getIndex("PriceIndex");

        String price = "15000";

        // QuerySpec spec = new QuerySpec().withKeyConditionExpression("Price = :price")
        //     .withValueMap(new ValueMap().withString(":price", price));
        HashMap<String, String> nameMap = new HashMap<>();
        //the #keyName is a placeholder for an attibute Name
        nameMap.put("#keyName", "Price");

        HashMap<String,Object> valueMap = new HashMap<>();
        //the :val is a placeholder for values
        valueMap.put(":val",price);

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("#keyName = :val")
                .withNameMap(nameMap)
                .withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = index.query(spec);

        for(Item item:items){
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf("%10s %10s %10s %10s", "ProductID", "Name", "Brand", "Price");
            System.out.println("\n-----------------------------------------------------------------------------");
            System.out.format("\n%10s %10s %10s %10s",item.getString("ProductID"),item.getString("Name"),item.getString("Brand"),item.getString("Price"));
            System.out.println("\n-----------------------------------------------------------------------------");
        }
        read.close();

    }

}
