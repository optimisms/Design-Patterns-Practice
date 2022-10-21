package org.example.Exercise1;

public class MyRealClass implements MyInterface {
    private boolean awake;
    private boolean tired;
    private final String[] classes = {"Arabic", "Software Engineering", "International Health", "Social Impact"};
    private int currClass;
    private int hwHours;

    public MyRealClass() {
        wakeUp();
    }

    @Override
    public void wakeUp() {
        awake = true;
        tired = false;
        currClass = 0;
        hwHours = 0;
    }

    @Override
    public void goToNextClass() {
        if (currClass < 3) {
            currClass++;
            hwHours++;
        }
        if (currClass == 3) {
            tired = true;
        }
    }

    @Override
    public String getCurrClass() {
        return classes[currClass];
    }


    @Override
    public int doHomework() {
        hwHours--;
        return hwHours;
    }

    @Override
    public void goToBed() {
        awake = false;
    }

    @Override
    public boolean isTired() {
        return tired;
    }

    @Override
    public boolean isAwake() {
        return awake;
    }
}
