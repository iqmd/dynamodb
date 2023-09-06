package com.db;

import java.util.*;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

public class CreateTable{
    private DynamoDB dynmdb = null;
    List<AttributeDefinition> attributeDefinition = new ArrayList<>();
    List<KeySchemaElement> keySchema = new ArrayList<>();

    Scanner read = new Scanner(System.in);


    public void setAttributes(){


        String answer="y";
        int flag = 0;
        String input;

        while(answer.equals("y")){
            //Define the attribute and keySchemaElement
            AttributeDefinition attribute = new AttributeDefinition();
            KeySchemaElement ks = new KeySchemaElement();


            System.out.println("Add attribute name : ");
            input = read.nextLine();

            //Add the attribureName
            attribute.withAttributeName(input);
            //Add the keySchemaElement Name
            ks.withAttributeName(input);

            System.out.println("Type : ");
            input = read.nextLine();

            //Add the type of the attribute
            attribute.withAttributeType(input);


            if(flag == 0){
                //If its a primary key then keytype will be HASH
                ks.withKeyType(KeyType.HASH);
            }else{
                //If its a sort key then keytype will be RANGE
                ks.withKeyType(KeyType.RANGE);
            }
            //Adding the keySchemaElements to keySchema
            keySchema.add(ks);
            attributeDefinition.add(attribute);

            if(flag == 0){
                System.out.println("Do you want to add a Sort Key ?");
                answer = read.nextLine();
                flag = 1;
            }else{
                break;
            }

        }


    }

    public ProvisionedThroughput setProvisionedThroughput(){
        ProvisionedThroughput ps = new ProvisionedThroughput();
        long c = 5;
        ps.withReadCapacityUnits(c);
        ps.withWriteCapacityUnits(c);
        return ps;

    }

    public void generateTable(){
        System.out.println("Table Name : ");
        String table_name = read.nextLine();

        dynmdb = DynamoDBInstance.getInstance();

        setAttributes();


        CreateTableRequest request = new CreateTableRequest();

        request.withTableName(table_name);
        request.withAttributeDefinitions(attributeDefinition);
        request.withKeySchema(keySchema);
        request.withProvisionedThroughput(setProvisionedThroughput());

        Table table = dynmdb.createTable(request);

        try{
            table.waitForActive();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

}
