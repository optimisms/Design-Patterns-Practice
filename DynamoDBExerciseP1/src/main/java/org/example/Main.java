package org.example;

import java.util.Arrays;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
//        try {
        TestCase[] testCases = getTestData(1);
        System.out.println(Arrays.toString(testCases));
        testCases = getTestData(2);
        System.out.println(Arrays.toString(testCases));

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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