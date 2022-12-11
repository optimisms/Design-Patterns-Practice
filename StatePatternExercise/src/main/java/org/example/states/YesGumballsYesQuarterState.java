package org.example.states;

import org.example.GumballMachine;

public class YesGumballsYesQuarterState extends GumballMachineState {
    public YesGumballsYesQuarterState(GumballMachine machine, int initialCount, double profit) {
        super(machine, initialCount, profit);
    }
    public YesGumballsYesQuarterState(GumballMachineState state) {
        super(state);
    }

    @Override
    public void insertQuarter() {
        System.out.println("There is already a quarter in the slot.");
    }

    @Override
    public void removeQuarter() {
        machine.setState(new YesGumballsNoQuarterState(this));
        System.out.println("Quarter returned.");
    }

    @Override
    public void turnHandle() {
        profit += .25;
        gumballCount--;
        if (gumballCount == 0) {
            machine.setState(new NoGumballsNoQuarterState(this));
        } else {
            machine.setState(new YesGumballsNoQuarterState(this));
        }
        System.out.println("Thanks for your business! Enjoy your gumball.");
    }

    @Override
    public boolean hasQuarter() {
        return true;
    }
}
