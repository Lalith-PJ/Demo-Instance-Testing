package com.testing;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;

import java.util.Map;
import java.util.UUID;

import static com.testing.Constants.*;

public class DBHelper {

    private static DynamoDB dynamoDB;
    private static AmazonDynamoDB amazonDynamoDB;

    static {
        initializeDbClient();
    }

    /**
     * Private method to initialize Database client object. Will be invoked during init phase
     */
    private static void initializeDbClient() {
        amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withRegion(DB_REGION).build();
        dynamoDB = new DynamoDB(amazonDynamoDB);
        System.out.println("Database client successfully instantiated");
    }

    /**
     * Persists a given event map as a row in DynamoDB table
     * @param stringObjectMap
     */
    protected static void persistItem(Map<String, Object> stringObjectMap) {
        try {
            dynamoDB.getTable(TABLE_NAME)
                    .putItem(new Item().withPrimaryKey("demo", UUID.randomUUID().toString())
                            .withString("event", stringObjectMap.toString()));
        } catch (Exception ex) {
            System.err.println("Exception caught - " + ex);
            ex.printStackTrace();
        }
    }
}
