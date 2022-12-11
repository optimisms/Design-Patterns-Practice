package org.example;

import org.example.states.*;

public class GumballMachine {
    GumballMachineState state;

    public GumballMachine(boolean hasGumballs, boolean hasQuarter) {
        if (hasGumballs) {
            if (hasQuarter) {
                state = new YesGumballsYesQuarterState(this, 5, 0);
            } else {
                state = new YesGumballsNoQuarterState(this, 5, 0);
            }
        } else {
            if (hasQuarter) {
                state = new NoGumballsYesQuarterState(this, 0);
            } else {
                state = new NoGumballsNoQuarterState(this, 0);
            }
        }
    }

    public void addGumballs(int count) {
        state.addGumballs(count);
    }
    public void insertQuarter() {
        state.insertQuarter();
    }
    public void removeQuarter() {
        state.removeQuarter();
    }
    public void turnHandle() {
        state.turnHandle();
    }
    public void setState(GumballMachineState newState) {
        state = newState;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
