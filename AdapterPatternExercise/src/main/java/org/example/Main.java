package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class Main {
    public static void main(String[] args) throws IOException {
        ContactManager manager = new ContactManager();
        manager.addContact(new Contact("Harry", "Potter", "777-777-7777", "boywholived@hogwarts.edu"));
        manager.addContact(new Contact("Hermione", "Granger", "333-333-3333", "spew@hogwarts.edu"));
        manager.addContact(new Contact("Ron", "Weasley", "123-456-7890", "chessmaster@hogwarts.edu"));
        manager.addContact(new Contact("Draco", "Malfoy", "987-654-3210", "ferrets@hogwarts.edu"));

        Writer output = new PrintWriter(System.out);
        Table table = new Table(output, new ContactTableAdapter(manager));

        table.display();
        output.flush();
    }
}