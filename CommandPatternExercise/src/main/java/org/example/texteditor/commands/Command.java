package org.example.texteditor.commands;

public interface Command {
    void execute();
    void undo();
    void redo();
}