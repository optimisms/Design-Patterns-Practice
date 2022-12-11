package org.example.states;

import org.example.GumballMachine;

public abstract class GumballMachineState {
    protected GumballMachine machine;
    protected int gumballCount;
    protected double profit;

    public GumballMachineState(GumballMachineState state) {
        machine = state.machine;
        gumballCount = state.gumballCount;
        profit = state.profit;
    }
    public GumballMachineState(GumballMachine machine, int initialCount, double profit) {
        this.machine = machine;
        this.gumballCount = initialCount;
        this.profit = profit;
    }
    public GumballMachineState(GumballMachine machine, double profit) {
        this.machine=machine;
        this.profit=profit;
    }
    public GumballMachineState(GumballMachine machine, int initialCount) {
        this.machine = machine;
        gumballCount = initialCount;
        profit = 0;
    }
    public GumballMachineState(GumballMachine machine) {
        this.machine=machine;
        gumballCount = 0;
        profit = 0;
    }

    public void addGumballs(int count) {
        gumballCount += count;
        System.out.println("Gumball count is now " + gumballCount + ".");
    }
    public abstract void insertQuarter();
    public abstract void removeQuarter();
    public abstract void turnHandle();
    public abstract boolean hasQuarter();

    @Override
    public String toString() {
        return "GumballMachineState{" +
                "gumballCount=" + gumballCount +
                ", profit=" + profit +
                ", hasQuarter=" + hasQuarter() +
                '}';
    }
}
