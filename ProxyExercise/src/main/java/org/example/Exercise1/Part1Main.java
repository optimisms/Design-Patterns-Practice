package org.example.Exercise1;

public class Part1Main {
    public static void main(String[] args) {
        Part1MyProxyClass proxy = new Part1MyProxyClass(true);
        proxy.setDay(3);
        proxy.setTime(10);
        System.out.println("The current date int is " + proxy.getDay() + " and the time is " + proxy.getTime());
        System.out.println("My student is awake: " + proxy.isAwake());
        System.out.println("My student is not tired: " + proxy.isTired());

        System.out.println("My student is in their first class: " + proxy.getCurrClass());
        proxy.goToNextClass();
        System.out.println("My student is in their second class: " + proxy.getCurrClass());
        proxy.goToNextClass();
        System.out.println("My student is in their third class: " + proxy.getCurrClass());
        proxy.goToNextClass();
        System.out.println("My student is in their fourth class: " + proxy.getCurrClass());
        System.out.println("My student is tired: " + proxy.isTired());

        proxy.setTime(18);
        System.out.println("My student has " + proxy.doHomework() + " hours of homework left.");
        proxy.doHomework();
        proxy.doHomework();
        System.out.println("My student has no homework left.");

        proxy.setTime(24);
        System.out.println("It is now the " + proxy.getTime() + "th hour of the day.");

        proxy.goToBed();
        System.out.println("My student is asleep: " + !proxy.isAwake());
    }
}