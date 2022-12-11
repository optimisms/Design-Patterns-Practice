package org.example.states;

import org.example.GumballMachine;

public class NoGumballsYesQuarterState extends GumballMachineState {
    public NoGumballsYesQuarterState(GumballMachine machine, double profit) {
        super(machine, profit);
    }
    public NoGumballsYesQuarterState(GumballMachineState state) {
        super(state);
    }

    @Override
    public void addGumballs(int count) {
        super.addGumballs(count);
        machine.setState(new YesGumballsYesQuarterState(this));
    }

    @Override
    public void insertQuarter() {
        System.out.println("There is already a quarter in the slot.");
    }

    @Override
    public void removeQuarter() {
        machine.setState(new NoGumballsNoQuarterState(this));
        System.out.println("Quarter returned.");
    }

    @Override
    public void turnHandle() {
        System.out.println("Sorry, this machine is out of gumballs.");
    }

    @Override
    public boolean hasQuarter() {
        return true;
    }
}
