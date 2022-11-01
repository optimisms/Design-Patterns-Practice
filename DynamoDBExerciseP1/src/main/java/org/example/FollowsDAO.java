package org.example;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class FollowsDAO {
    private static final String TABLE_NAME = "follows";
    private static final String FOLLOWER_HANDLE_ATTR = "follower_handle";
    private static final String FOLLOWER_NAME_ATTR = "follower_name";
    private static final String FOLLOWEE_HANDLE_ATTR = "followee_handle";
    private static final String FOLLOWEE_NAME_ATTR = "followee_name";

    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public void addFollower(String follower_handle, String follower_name, String followee_handle, String followee_name) {
        Table table = dynamoDB.getTable(TABLE_NAME);

        Item item = new Item().withPrimaryKey(FOLLOWER_HANDLE_ATTR, follower_handle,
                        FOLLOWEE_HANDLE_ATTR, followee_handle)
                .withString(FOLLOWER_NAME_ATTR, follower_name)
                .withString(FOLLOWEE_NAME_ATTR, followee_name);

        table.putItem(item);
    }
}
