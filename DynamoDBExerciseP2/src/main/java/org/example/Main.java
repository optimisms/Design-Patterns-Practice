package org.example;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        try {
            FollowsDAO myDAO = new FollowsDAO();

            //Add items to DynamoDB
            Relationship[] sameFollower = getTestData(1);
            for (Relationship relationship : sameFollower) {
                myDAO.addFollowerRelationship(relationship.follower_handle, relationship.follower_name, relationship.followee_handle, relationship.followee_name);
            }

            Relationship[] sameFollowee = getTestData(2);
            for (Relationship relationship : sameFollowee) {
                myDAO.addFollowerRelationship(relationship.follower_handle, relationship.follower_name, relationship.followee_handle, relationship.followee_name);
            }

            //Get relationship from DB
            Relationship relationship = sameFollowee[0];
            Relationship retrievedRelationship = myDAO.getRelationship(relationship.follower_handle, relationship.followee_handle);

            System.out.println(relationship);
            System.out.println(retrievedRelationship);

            //Update relationship in DB
            myDAO.updateFollowerRelationship(relationship.follower_handle, relationship.followee_handle, "just11blocks", "goldie031");

            //Delete 1 relationship from DB
            myDAO.deleteFollowerRelationship(relationship.follower_handle, relationship.followee_handle);

            List<Relationship> first10followers = myDAO.getFollowers("@optimisms", 10, null);
            System.out.println(first10followers);
            System.out.println(first10followers.size());
            List<Relationship> second10followers = myDAO.getFollowers("@optimisms", 10, first10followers.get(9).follower_handle);
            System.out.println(second10followers);
            System.out.println(second10followers.size());

            List<Relationship> first10following = myDAO.getFollowing("@optimisms", 10, null);
            System.out.println(first10following);
            System.out.println(first10following.size());
            List<Relationship> second10following = myDAO.getFollowing("@optimisms", 10, first10following.get(9).followee_handle);
            System.out.println(second10following);
            System.out.println(second10following.size());

            TimeUnit.SECONDS.sleep(10);

            //Delete all items from DB for cleanup
            for (Relationship testCase : sameFollower) {
                myDAO.deleteFollowerRelationship(testCase.follower_handle, testCase.followee_handle);
            }

            for (Relationship testCase : sameFollowee) {
                if (testCase == relationship) { continue; } //to avoid errors from deleting the same object twice
                myDAO.deleteFollowerRelationship(testCase.follower_handle, testCase.followee_handle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Relationship[] getTestData(int caseNum) {
        Relationship[] relationships= new Relationship[25];
        for (int i=0; i < relationships.length; i++) {
            relationships[i] = makeTestCase(caseNum);
        }
        return relationships;
    }

    private static Relationship makeTestCase(int caseNum) {
        String fixedHandle = "@optimisms";
        String fixedName = "Haile Diana";
        String varHandle = "@" + UUID.randomUUID().toString().substring(0, 8);
        String varName = UUID.randomUUID().toString().substring(0, 8);
        if (caseNum == 1) {
            Relationship relationship = new Relationship();
            relationship.setFollower_handle(fixedHandle);
            relationship.setFollower_name(fixedName);
            relationship.setFollowee_handle(varHandle);
            relationship.setFollowee_name(varName);
            return relationship;//new Relationship(fixedHandle, fixedName, varHandle, varName);

        }
        else {
            Relationship relationship = new Relationship();
            relationship.setFollower_handle(varHandle);
            relationship.setFollower_name(varName);
            relationship.setFollowee_handle(fixedHandle);
            relationship.setFollowee_name(fixedName);
            return relationship;//new Relationship(varHandle, varName, fixedHandle, fixedName);
        }
    }
}