package org.example.Exercise1;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class MyProxyClass implements MyInterface {
    private final int[] allowedDaysForSchool = {2, 4, 6};
    private final int[] allowedDaysForSleep = {1, 2, 3, 4, 5, 6, 7};
    private final int[] allowedHoursForClass = {10, 11, 12, 13, 14, 15, 16};
    private final int[] allowedHoursForHomework = {17, 18, 21, 22};
    private final int[] allowedHoursForSleep= {24, 1, 2, 3, 4, 5, 6, 7};
    private DayOfWeek currentDay;
    private int currentHour;
    private final MyRealClass realClass;

    public MyProxyClass() {
        realClass = new MyRealClass();
    }

    private void getDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        currentDay = dateTime.getDayOfWeek();
        currentHour = dateTime.getHour();
    }

    private boolean isAllowed(int currVal, int[] searchArray) {
        for (int searchVal : searchArray) {
            if (searchVal == currVal) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void wakeUp() {
        getDateTime();
        if (isAllowed(currentDay.getValue(), allowedDaysForSleep) && !isAllowed(currentHour, allowedHoursForSleep)) {
            realClass.wakeUp();
        }
    }

    @Override
    public int doHomework() {
        getDateTime();
        if (isAllowed(currentDay.getValue(), allowedDaysForSchool) && isAllowed(currentHour, allowedHoursForHomework)) {
            return realClass.doHomework();
        }
        else return -1;
    }

    @Override
    public void goToNextClass() {
        getDateTime();
        if (isAllowed(currentDay.getValue(), allowedDaysForSchool) && isAllowed(currentHour, allowedHoursForClass)) {
            realClass.goToNextClass();
        }
    }

    @Override
    public String getCurrClass() {
        getDateTime();
        if (isAllowed(currentDay.getValue(), allowedDaysForSchool) && isAllowed(currentHour, allowedHoursForClass)) {
            return realClass.getCurrClass();
        }
        else return null;
    }

    @Override
    public void goToBed() {
        getDateTime();
        if (isAllowed(currentDay.getValue(), allowedDaysForSleep) && isAllowed(currentHour, allowedHoursForSleep)) {
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
