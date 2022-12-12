package org.example.texteditor.commands;

import org.example.texteditor.IDocument;

public class StartCommand implements Command {
    IDocument doc;
    String oldDocString;

    public StartCommand(IDocument doc) {
        this.doc=doc;
    }

    @Override
    public void execute() {
        oldDocString = doc.sequence().toString();
        doc.clear();
    }

    @Override
    public void undo() {
        doc.insert(0, oldDocString);
    }

    @Override
    public void redo() {
        execute();
    }
}