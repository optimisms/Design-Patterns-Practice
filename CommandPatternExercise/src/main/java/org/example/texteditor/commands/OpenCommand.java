package org.example.texteditor.commands;

import org.example.texteditor.IDocument;

public class OpenCommand implements Command {
    IDocument doc;
    String openFileName;
    String oldDocString;

    public OpenCommand(IDocument doc, String openFileName) {
        this.doc=doc;
        this.openFileName=openFileName;
    }

    @Override
    public void execute() {
        oldDocString = doc.sequence().toString();
        doc.open(openFileName);
    }

    @Override
    public void undo() {
        doc.clear();
        doc.insert(0, oldDocString);
    }

    @Override
    public void redo() {
        execute();
    }
}