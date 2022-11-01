package org.example;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import java.util.HashMap;
import java.util.Map;

public class FollowsDAO {
    private static final String TABLE_NAME = "follows";
    private static final String FOLLOWER_HANDLE_ATTR = "follower_handle";
    private static final String FOLLOWER_NAME_ATTR = "follower_name";
    private static final String FOLLOWEE_HANDLE_ATTR = "followee_handle";
    private static final String FOLLOWEE_NAME_ATTR = "followee_name";

    private static final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
    private static final DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public void addFollowerRelationship(String follower_handle, String follower_name, String followee_handle, String followee_name) {
        Table table = dynamoDB.getTable(TABLE_NAME);

        Item item = new Item().withPrimaryKey(FOLLOWER_HANDLE_ATTR, follower_handle,
                        FOLLOWEE_HANDLE_ATTR, followee_handle)
                .withString(FOLLOWEE_NAME_ATTR, followee_name)
                .withString(FOLLOWER_NAME_ATTR, follower_name);

        table.putItem(item);
    }

    public TestCase getRelationship(String follower_handle, String followee_handle) throws DataAccessException {
        Table table = dynamoDB.getTable(TABLE_NAME);

        Item item = table.getItem(FOLLOWER_HANDLE_ATTR, follower_handle, FOLLOWEE_HANDLE_ATTR, followee_handle);
        if (item == null) {
            throw new DataAccessException("Item not found at PrimaryKey (" + FOLLOWER_HANDLE_ATTR + ":" + follower_handle + ") with SortKey (" + FOLLOWEE_HANDLE_ATTR + ":" + followee_handle + ")");
        } else {
            return new TestCase(item.getString(FOLLOWER_HANDLE_ATTR), item.getString(FOLLOWER_NAME_ATTR), item.getString(FOLLOWEE_HANDLE_ATTR), item.getString(FOLLOWEE_NAME_ATTR));
        }
    }

    public void updateFollowerRelationship(String follower_handle, String followee_handle, String new_follower_name, String new_followee_name) {
        Table table = dynamoDB.getTable(TABLE_NAME);

        Map<String, String> attrNames = new HashMap<>();
        attrNames.put("#ER_name", FOLLOWER_NAME_ATTR);

        Map<String, Object> attrValues = new HashMap<>();
        attrValues.put(":ER_val", new_follower_name);

        table.updateItem(FOLLOWER_HANDLE_ATTR, follower_handle, FOLLOWEE_HANDLE_ATTR, followee_handle,
                "set #ER_name = :ER_val", attrNames, attrValues);

        attrNames = new HashMap<>();
        attrNames.put("#EE_name", FOLLOWEE_NAME_ATTR);

        attrValues = new HashMap<>();
        attrValues.put(":EE_val", new_followee_name);

        table.updateItem(FOLLOWER_HANDLE_ATTR, follower_handle, FOLLOWEE_HANDLE_ATTR, followee_handle,
                "set #EE_name = :EE_val", attrNames, attrValues);
    }

    public void deleteFollowerRelationship(String follower_handle, String followee_handle) {
        Table table = dynamoDB.getTable(TABLE_NAME);
        table.deleteItem(FOLLOWER_HANDLE_ATTR, follower_handle, FOLLOWEE_HANDLE_ATTR, followee_handle);
    }
}
