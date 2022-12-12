package org.example.texteditor.commands;

import org.example.texteditor.IDocument;

public class OpenCommand implements Command {
    IDocument doc;
    String openFileName;

    public OpenCommand(IDocument doc, String openFileName) {
        this.doc=doc;
        this.openFileName=openFileName;
    }

    @Override
    public void execute() {
        doc.open(openFileName);
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