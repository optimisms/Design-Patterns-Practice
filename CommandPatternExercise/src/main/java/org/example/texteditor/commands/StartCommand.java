package org.example.texteditor.commands;

import org.example.texteditor.IDocument;

public class StartCommand implements Command {
    IDocument doc;
    String oldDocString;

    public StartCommand(IDocument document) {
        doc = document;
    }

    @Override
    public void execute() {
        oldDocString = doc.sequence().toString();
        doc.clear();
    }

    @Override
    public void undo() {
        //TODO:
    }

    @Override
    public void redo() {
        //TODO:
    }
}