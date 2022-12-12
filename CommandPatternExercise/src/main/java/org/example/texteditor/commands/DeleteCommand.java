package org.example.texteditor.commands;

import org.example.texteditor.IDocument;

public class DeleteCommand implements Command {
    int deletionIndex;
    int deletionDistance;
    IDocument document;

    public DeleteCommand(IDocument document, int deletionIndex, int deletionDistance) {
        this.deletionIndex=deletionIndex;
        this.deletionDistance=deletionDistance;
        this.document=document;
    }

    @Override
    public void execute() {
        document.delete(deletionIndex, deletionDistance);
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