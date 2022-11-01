package org.example;

public class TestCase {
    public final String follower_handle;
    public final String follower_name;
    public final String followee_handle;
    public final String followee_name;

    public TestCase(String follower_handle, String follower_name, String followee_handle, String followee_name) {
        this.follower_handle = follower_handle;
        this.follower_name = follower_name;
        this.followee_handle = followee_handle;
        this.followee_name = followee_name;
    }

    @Override
    public String toString() { return String.format("TestCase[%s, %s, %s, %s]", follower_handle, follower_name, followee_handle, followee_name); }
}