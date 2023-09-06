package com.db;

import java.util.Scanner;

public class App {
    public static void main(String args[]){

       Scanner read = new Scanner(System.in);
       String answer = "y";
       int choice;

       System.out.println("Choose any :");
       System.out.println("1.Create Table");
       System.out.println("2.Delete Table");
       System.out.println("3.Put Item");
       System.out.println("4.Scan Table");
       System.out.println("5.Query Table");
       System.out.println("6.Describe Table");
       choice = Integer.parseInt(read.nextLine());

       switch(choice){
            case 1:
                CreateTable cre = new CreateTable();
                cre.generateTable();
                break;
            case 2:
                DeleteTable dlt = new DeleteTable();
                dlt.delete();
                break;
            case 3:
                PutItem newItem = new PutItem();
                while(answer.equals("y")){
                    newItem.putItem();
                    System.out.println("Do you want to add more item ?");
                    answer = read.nextLine();
                }
                break;

            case 4:
                ReadTable scan = new ReadTable();
                scan.scan();
                break;

            case 5:
                ReadTable query = new ReadTable();
                query.query();
                break;
            case 6:
                DescribeTable desc = new DescribeTable();
                desc.describe();
                break;
       }



       read.close();
    }
}
