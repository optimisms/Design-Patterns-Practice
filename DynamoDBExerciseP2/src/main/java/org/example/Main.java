package org.example;

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

    /**
     * Fetch the next page of locations visited by visitor
     *
     * @param visitor The visitor of interest
     * @param pageSize The maximum number of locations to include in the result
     * @param lastLocation The last location returned in the previous page of results
     * @return The next page of locations visited by visitor
     */
    public List<Visit> getVisitedLocations(String visitor, int pageSize, String lastLocation) {
        DynamoDbTable<Visit> table = enhancedClient.table(TableName, TableSchema.fromBean(Visit.class));
        Key key = Key.builder()
                .partitionValue(visitor)
                .build();

        QueryEnhancedRequest.Builder requestBuilder = QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(key));
        // If you use iterators, it auto-fetches next page always, so instead limit the stream below
        //.limit(5);

        if(isNonEmptyString(lastLocation)) {
            // Build up the Exclusive Start Key (telling DynamoDB where you left off reading items)
            Map<String, AttributeValue> startKey = new HashMap<>();
            startKey.put(VisitorAttr, AttributeValue.builder().s(visitor).build());
            startKey.put(LocationAttr, AttributeValue.builder().s(lastLocation).build());

            requestBuilder.exclusiveStartKey(startKey);
        }

        QueryEnhancedRequest request = requestBuilder.build();

        return table.query(request)
                .items()
                .stream()
                .limit(pageSize)
                .collect(Collectors.toList());

        /*
         * Alternative Implementation 1 of the line above, using forEach
         */
        //List<Visit> visits = new ArrayList<>();
        //table.query(request).items().stream().limit(pageSize).forEach(v -> visits.add(v));
        //return visits;

        /*
         * Alternative Implementation 2 of the line above, using a for loop
         */
        //List<Visit> visits = new ArrayList<>();
        //for(Visit visit : table.query(request).items()) {
        //    // stop iteration if we've reached the number of items asked for
        //    if(visits.size() >= pageSize)
        //        break;
        //    visits.add(visit);
        //}
        //return visits;

        /*
         * Alternative Implementation 3 of the line above, using a while loop
         */
        //List<Visit> visits = new ArrayList<>();
        //Iterator<Visit> it = table.query(request).items().iterator();
        //// while there are elements to iterate over, and we haven't reached the number of items asked for
        //while(it.hasNext() && visits.size() < pageSize){
        //    Visit visit = it.next();
        //    visits.add(visit);
        //}
        //return visits;
    }

    /**
     * Fetch the next page of visitors who have visited location
     *
     * @param location The location of interest
     * @param pageSize The maximum number of visitors to include in the result
     * @param lastVisitor The last visitor returned in the previous page of results
     * @return The next page of visitors who have visited location
     */
    public List<Visit>  getVisitors(String location, int pageSize, String lastVisitor) {
        DynamoDbIndex<Visit> index = enhancedClient.table(TableName, TableSchema.fromBean(Visit.class)).index(IndexName);
        Key key = Key.builder()
                .partitionValue(location)
                .build();

        QueryEnhancedRequest.Builder requestBuilder = QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(key))
                // Unlike Tables, querying from an Index returns a PageIterable, so we want to just ask for
                // 1 page with pageSize items
                .limit(pageSize);

        if(isNonEmptyString(lastVisitor)) {
            Map<String, AttributeValue> startKey = new HashMap<>();
            startKey.put(LocationAttr, AttributeValue.builder().s(location).build());
            startKey.put(VisitorAttr, AttributeValue.builder().s(lastVisitor).build());

            requestBuilder.exclusiveStartKey(startKey);
        }

        QueryEnhancedRequest request = requestBuilder.build();

        List<Visit> visits = new ArrayList<>();

        SdkIterable<Page<Visit>> results2 = index.query(request);
        PageIterable<Visit> pages = PageIterable.create(results2);
        // limit 1 page, with pageSize items
        pages.stream()
                .limit(1)
                .forEach(visitsPage -> visitsPage.items().forEach(v -> visits.add(v)));

        return visits;
    }
}