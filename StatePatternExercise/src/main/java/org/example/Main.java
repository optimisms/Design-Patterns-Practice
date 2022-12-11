package org.example;

public class Main {
    public static void main(String[] args) {
        //Test initial state
        GumballMachine machine = new GumballMachine(true, true);
        System.out.println(machine + "\n");

        //Test adding quarter to full slot
        machine.insertQuarter();
        machine.removeQuarter();
        System.out.println(machine + "\n");

        //Test turning handle with gumballs and no quarter
        machine.turnHandle();

        //Test removing quarter from empty slot
        machine.removeQuarter();

        //Test turning handle with gumballs and quarter
        machine.insertQuarter();
        machine.turnHandle();
        System.out.println(machine + "\n");

        //Now start with empty machine
        machine = new GumballMachine(false, false);
        System.out.println(machine + "\n");

        //Test removing quarter from empty slot
        machine.removeQuarter();
        machine.insertQuarter();
        System.out.println(machine + "\n");

        //Test turning handle with quarter and no gumballs
        machine.turnHandle();

        //Test adding gumballs
        machine.addGumballs(1);
        System.out.println(machine + "\n");

        //Test turning handle with quarter and one gumball
        //Profit should go up and gumballs should be 0 again
        machine.turnHandle();
        System.out.println(machine + "\n");

        //Test turning handle with no quarter and no gumballs
        machine.turnHandle();

        //Test turning handle with quarter and no gumballs
        machine.insertQuarter();
        machine.turnHandle();

        //Test turning handle with quarter and gumballs
        //Ensure that profit stays the same between first and second gumball returned
        //Profit should be 0.5 in the following print statement for two gumballs sold
        machine.addGumballs(10);
        machine.turnHandle();
        System.out.println(machine + "\n");
    }
}