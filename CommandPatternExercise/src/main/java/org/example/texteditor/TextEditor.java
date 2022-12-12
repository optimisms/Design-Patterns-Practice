package org.example.texteditor;

import org.example.texteditor.commands.*;

import java.util.Scanner;

public class TextEditor {
    private static TextEditor _instance = new TextEditor(new Document(new Sequence()));

    public static void start() { _instance._start(); }
    public static void insert() {
        _instance._insert();
    }
    public static void delete() {
        _instance._delete();
    }
    public static void replace() {
        _instance._replace();
    }
    public static void open() {
        _instance._open();
    }
    public static IDocument getDoc() { return _instance._document; }
    public static void setDoc(IDocument doc) { _instance._document = doc; }

    private IDocument _document;
    private final UndoRedoManager manager;

    //TODO: make private?
    TextEditor(IDocument document) {
        _document = document;
        manager = new UndoRedoManager();
    }

    void run() {
        while (true) {
            printOptions();

            Scanner scanner = new Scanner(System.in);
            String optionInput = scanner.next();
            int option = validateNumberInput(optionInput);

            if (option != -1) {
                switch (option) {
                    case 1:
                        _insert();
                        break;
                    case 2:
                        _delete();
                        break;
                    case 3:
                        _replace();
                        break;
                    case 4:
                        _document.display();
                        break;
                    case 5:
                        _save();
                        break;
                    case 6:
                        _open();
                        break;
                    case 7:
                        _start();
                        break;
                    case 8:
                        manager.undo();
                        break;
                    case 9:
                        manager.redo();
                        break;
                    case 10:
                        return;
                }
            }

            System.out.println();
        }
    }

    private void printOptions() {
        System.out.println("SELECT AN OPTION (1 - 10):");
        System.out.println("1. Insert a string at a specified index in the document");
        System.out.println("2. Delete a sequence of characters at a specified index");
        System.out.println("3. Replace a sequence of characters at a specified index with a new string");
        System.out.println("4. Display the current contents of the document");
        System.out.println("5. Save the document to a file");
        System.out.println("6. Open a document from a file");
        System.out.println("7. Start a new, empty document");
        System.out.println("8. Undo");
        System.out.println("9. Redo");
        System.out.println("10. Quit");

        System.out.println();
        System.out.print("Your selection: ");
    }

    private void _start() {
        manager.execute(new StartCommand(_document));
    }

    private void _insert() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Start index: ");
        String insertionIndexInput = scanner.next();
        int insertionIndex = validateNumberInput(insertionIndexInput);
        if (insertionIndex != -1) {
            System.out.print("Sequence to insert: ");
            String sequenceInput = scanner.next();
//            _document.insert(insertionIndex, sequenceInput);
            manager.execute(new InsertCommand(_document, insertionIndex, sequenceInput));
        }
    }

    private void _delete() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Start index: ");
        String deletionIndexInput = scanner.next();
        int deletionIndex = validateNumberInput(deletionIndexInput);
        if (deletionIndex != -1) {
            System.out.print("Number of characters to delete: ");
            String deletionDistanceInput = scanner.next();
            int deletionDistance = validateNumberInput(deletionDistanceInput);
            if (deletionDistance != -1) {
                manager.execute(new DeleteCommand(_document, deletionIndex, deletionDistance));
//                if (_document.delete(deletionIndex, deletionDistance) == null) {
//                    System.out.println("Deletion unsuccessful");
//                }
            }
        }
    }

    private void _replace() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Start index: ");
        String replaceIndexInput = scanner.next();
        int replaceIndex = validateNumberInput(replaceIndexInput);
        if (replaceIndex != -1) {
            System.out.print("Number of characters to replace: ");
            String replaceDistanceInput = scanner.next();
            int replaceDistance = validateNumberInput(replaceDistanceInput);
            if (replaceDistance != -1) {
                System.out.print("Replacement string: ");
                String replacementString = scanner.next();
                manager.execute(new ReplaceCommand(_document, replaceIndex, replaceDistance, replacementString));
//                _document.delete(replaceIndex, replaceDistance);
//                _document.insert(replaceIndex, replacementString);
            }
        }
    }

    private void _save() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Name of file: ");
        String saveFileName = scanner.next();
        _document.save(saveFileName);
    }

    private void _open() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Name of file to open: ");
        String openFileName = scanner.next();
//        _document.open(openFileName);

        manager.execute(new OpenCommand(_document, openFileName));
    }

    private int validateNumberInput(String input) {
        int selection = -1;
        try {
            selection = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }

        return selection;
    }
}
