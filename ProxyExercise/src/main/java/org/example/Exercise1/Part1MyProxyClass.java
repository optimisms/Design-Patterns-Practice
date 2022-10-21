package org.example.Exercise1;

import java.time.LocalDateTime;

public class Part1MyProxyClass implements Part1MyInterface {
    private final int[] allowedDaysForSchool = {1, 3, 5};
    private final int[] allowedDaysForSleep = {1, 2, 3, 4, 5, 6, 7};
    private final int[] allowedHoursForClass = {10, 11, 12, 13, 14, 15, 16};
    private final int[] allowedHoursForHomework = {17, 18, 21, 22};
    private final int[] allowedHoursForSleep= {24, 1, 2, 3, 4, 5, 6, 7};
    private int currentDay;
    private int currentHour;
    private final Part1MyRealClass realClass;
    private boolean test;

    public Part1MyProxyClass(boolean test) {
        realClass = new Part1MyRealClass();
        setDateTime();
        this.test = test;
    }

    private void setDateTime() {
        if (!test) {
            LocalDateTime dateTime = LocalDateTime.now();
            currentDay = dateTime.getDayOfWeek().getValue();
            currentHour = dateTime.getHour();
        }
    }

    private boolean isAllowed(int currVal, int[] searchArray) {
        for (int searchVal : searchArray) {
            if (searchVal == currVal) {
                return true;
            }
        }
        return false;
    }

    public int getTime() { return currentHour;}
    public int getDay() { return currentDay;}
    public void setTime(int time) { currentHour = time;}
    public void setDay(int day) { currentDay = day;}

    @Override
    public void wakeUp() {
        setDateTime();
        if (isAllowed(currentDay, allowedDaysForSleep) && !isAllowed(currentHour, allowedHoursForSleep)) {
            realClass.wakeUp();
        }
    }

    @Override
    public int doHomework() {
        setDateTime();
        if (isAllowed(currentDay, allowedDaysForSchool) && isAllowed(currentHour, allowedHoursForHomework)) {
            return realClass.doHomework();
        }
        else return -1;
    }

    @Override
    public void goToNextClass() {
        setDateTime();
        if (isAllowed(currentDay, allowedDaysForSchool) && isAllowed(currentHour, allowedHoursForClass)) {
            realClass.goToNextClass();
        }
    }

    @Override
    public String getCurrClass() {
        setDateTime();
        if (isAllowed(currentDay, allowedDaysForSchool) && isAllowed(currentHour, allowedHoursForClass)) {
            return realClass.getCurrClass();
        }
        else return null;
    }

    @Override
    public void goToBed() {
        setDateTime();
        if (isAllowed(currentDay, allowedDaysForSleep) && isAllowed(currentHour, allowedHoursForSleep)) {
            realClass.goToBed();
        }
    }

    @Override
    public boolean isTired() {
        return realClass.isTired();
    }

    @Override
    public boolean isAwake() {
        return realClass.isAwake();
    }
}
