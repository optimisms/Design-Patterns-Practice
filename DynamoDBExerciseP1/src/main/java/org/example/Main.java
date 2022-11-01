package org.example;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        try {
            FollowsDAO myDAO = new FollowsDAO();

            //Add items to DynamoDB
            TestCase[] sameFollower = getTestData(1);
            for (TestCase testCase : sameFollower) {
                myDAO.addFollowerRelationship(testCase.follower_handle, testCase.follower_name, testCase.followee_handle, testCase.followee_name);
            }

            TestCase[] sameFollowee = getTestData(2);
            for (TestCase testCase : sameFollowee) {
                myDAO.addFollowerRelationship(testCase.follower_handle, testCase.follower_name, testCase.followee_handle, testCase.followee_name);
            }

            //Get relationship from DB
            TestCase relationship = sameFollowee[0];
            TestCase retrievedRelationship = myDAO.getRelationship(relationship.follower_handle, relationship.followee_handle);

            System.out.println(relationship);
            System.out.println(retrievedRelationship);

            //Update relationship in DB
            myDAO.updateFollowerRelationship(relationship.follower_handle, relationship.followee_handle, "just11blocks", "goldie031");

            TimeUnit.SECONDS.sleep(15);

            //Delete all items from DB
            for (TestCase testCase : sameFollower) {
                myDAO.deleteFollowerRelationship(testCase.follower_handle, testCase.followee_handle);
            }

            for (TestCase testCase : sameFollowee) {
                myDAO.deleteFollowerRelationship(testCase.follower_handle, testCase.followee_handle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TestCase[] getTestData(int caseNum) {
        TestCase[] testCases = new TestCase[25];
        for (int i = 0; i < testCases.length; i++) {
            testCases[i] = makeTestCase(caseNum);
        }
        return testCases;
    }

    private static TestCase makeTestCase(int caseNum) {
        String fixedHandle = "@optimisms";
        String fixedName = "Haile Diana";
        String varHandle = "@" + UUID.randomUUID().toString().substring(0, 8);
        String varName = UUID.randomUUID().toString().substring(0, 8);
        if (caseNum == 1) {
            return new TestCase(fixedHandle, fixedName, varHandle, varName);
        }
        else {
            return new TestCase(varHandle, varName, fixedHandle, fixedName);
        }
    }
}