package org.example;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class Relationship {
    public String follower_handle;
    public String follower_name;
    public String followee_handle;
    public String followee_name;

//    public Relationship(String follower_handle, String follower_name, String followee_handle, String followee_name) {
//        this.follower_handle = follower_handle;
//        this.follower_name = follower_name;
//        this.followee_handle = followee_handle;
//        this.followee_name = followee_name;
//    }

    @DynamoDbPartitionKey
    @DynamoDbSecondarySortKey(indexNames=FollowsDAO.INDEX_NAME)
    public String getFollower_handle() {
        return follower_handle;
    }

    public void setFollower_handle(String follower_handle) {
        this.follower_handle=follower_handle;
    }

    public String getFollower_name() {
        return follower_name;
    }

    public void setFollower_name(String follower_name) {
        this.follower_name=follower_name;
    }

    @DynamoDbSortKey
    @DynamoDbSecondaryPartitionKey(indexNames=FollowsDAO.INDEX_NAME)
    public String getFollowee_handle() {
        return followee_handle;
    }

    public void setFollowee_handle(String followee_handle) {
        this.followee_handle=followee_handle;
    }

    public String getFollowee_name() {
        return followee_name;
    }

    public void setFollowee_name(String followee_name) {
        this.followee_name=followee_name;
    }

    @Override
    public String toString() { return String.format("Relationship[%s, %s, %s, %s]", follower_handle, follower_name, followee_handle, followee_name); }
}