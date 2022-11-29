package org.example;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class FollowsDAO {
    private static final String TABLE_NAME = "follows";
    public static final String INDEX_NAME = "follows_index";
    private static final String FOLLOWER_HANDLE_ATTR = "follower_handle";
    private static final String FOLLOWER_NAME_ATTR = "follower_name";
    private static final String FOLLOWEE_HANDLE_ATTR = "followee_handle";
    private static final String FOLLOWEE_NAME_ATTR = "followee_name";

    private static DynamoDbClient dynamoDbClient = DynamoDbClient.builder().region(Region.US_WEST_2).build();
    private static DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();

    public Relationship getRelationship(String follower_handle, String followee_handle) throws DataAccessException {
        DynamoDbTable<Relationship> table = enhancedClient.table(TABLE_NAME, TableSchema.fromBean(Relationship.class));
        Key key = Key.builder().partitionValue(follower_handle).sortValue(followee_handle).build();

        Relationship relationship = table.getItem(key);

        if (relationship == null) {
            throw new DataAccessException("Item not found at PrimaryKey (" + FOLLOWER_HANDLE_ATTR + ":" + follower_handle + ") with SortKey (" + FOLLOWEE_HANDLE_ATTR + ":" + followee_handle + ")");
        } else {
            return relationship;
        }
    }

    public void addFollowerRelationship(String follower_handle, String follower_name, String followee_handle, String followee_name) {
        DynamoDbTable<Relationship> table = enhancedClient.table(TABLE_NAME, TableSchema.fromBean(Relationship.class));

        Relationship newRelationship = new Relationship();
        newRelationship.setFollower_handle(follower_handle);
        newRelationship.setFollowee_handle(followee_handle);
        newRelationship.setFollower_name(follower_name);
        newRelationship.setFollowee_name(followee_name);
        table.putItem(newRelationship);
    }

    public void updateFollowerRelationship(String follower_handle, String followee_handle, String new_follower_name, String new_followee_name) {
        DynamoDbTable<Relationship> table = enhancedClient.table(TABLE_NAME, TableSchema.fromBean(Relationship.class));
        Key key = Key.builder().partitionValue(follower_handle).sortValue(followee_handle).build();

        Relationship relationship = table.getItem(key);
        relationship.setFollower_name(new_follower_name);
        relationship.setFollowee_name(new_followee_name);
        table.updateItem(relationship);
    }

    public void deleteFollowerRelationship(String follower_handle, String followee_handle) {
        DynamoDbTable<Relationship> table = enhancedClient.table(TABLE_NAME, TableSchema.fromBean(Relationship.class));
        Key key = Key.builder().partitionValue(follower_handle).sortValue(followee_handle).build();
        table.deleteItem(key);
    }
}
