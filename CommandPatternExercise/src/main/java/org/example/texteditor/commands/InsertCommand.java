package org.example.texteditor.commands;

import org.example.texteditor.IDocument;

public class InsertCommand implements Command {
    IDocument doc;
    int insertionIndex;
    String sequenceInput;

    public InsertCommand(IDocument doc, int insertionIndex, String sequenceInput) {
        this.doc = doc;
        this.insertionIndex = insertionIndex;
        this.sequenceInput = sequenceInput;
    }

    @Override
    public void execute() {
        doc.insert(insertionIndex, sequenceInput);
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