package org.example.states;

import org.example.GumballMachine;

public class YesGumballsNoQuarterState extends GumballMachineState {
    public YesGumballsNoQuarterState(GumballMachine machine, int initialCount, double profit) {
        super(machine, initialCount, profit);
    }
    public YesGumballsNoQuarterState(GumballMachineState state) {
        super(state);
    }

    @Override
    public void insertQuarter() {
        machine.setState(new YesGumballsYesQuarterState(this));
        System.out.println("Quarter inserted.");
    }

    @Override
    public void removeQuarter() {
        System.out.println("There is no quarter in the slot.");
    }

    @Override
    public void turnHandle() {
        System.out.println("Please insert a quarter to use the gumball machine.");
    }

    @Override
    public boolean hasQuarter() {
        return false;
    }
}
