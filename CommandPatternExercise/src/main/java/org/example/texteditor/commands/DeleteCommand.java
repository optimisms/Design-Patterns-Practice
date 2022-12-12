package org.example.texteditor.commands;

import org.example.texteditor.IDocument;

public class DeleteCommand implements Command {
    int deletionIndex;
    int deletionDistance;
    IDocument document;
    String deletedString;

    public DeleteCommand(IDocument document, int deletionIndex, int deletionDistance) {
        this.deletionIndex=deletionIndex;
        this.deletionDistance=deletionDistance;
        this.document=document;
    }

    @Override
    public void execute() {
        deletedString = document.delete(deletionIndex, deletionDistance);
    }

    @Override
    public void undo() {
        document.insert(deletionIndex, deletedString);
    }

    @Override
    public void redo() {
        execute();
    }

    public String getDeletedString() { return deletedString; }
}