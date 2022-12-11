package org.example.states;

import org.example.GumballMachine;

public class NoGumballsNoQuarterState extends GumballMachineState {
    public NoGumballsNoQuarterState(GumballMachine machine, double profit) {
        super(machine, profit);
    }
    public NoGumballsNoQuarterState(GumballMachineState state) {
        super(state);
    }

    @Override
    public void addGumballs(int count) {
        super.addGumballs(count);
        machine.setState(new YesGumballsNoQuarterState(this));
    }

    @Override
    public void insertQuarter() {
        machine.setState(new NoGumballsYesQuarterState(this));
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
