package org.example.texteditor.commands;

import org.example.texteditor.IDocument;

public class ReplaceCommand implements Command {
    IDocument doc;
    int replaceIndex;
    int replaceDistance;
    String replacementString;
    String oldString;

    public ReplaceCommand(IDocument doc, int replaceIndex, int replaceDistance, String replacementString) {
        this.doc=doc;
        this.replaceIndex=replaceIndex;
        this.replaceDistance=replaceDistance;
        this.replacementString=replacementString;
    }

    @Override
    public void execute() {
        oldString = doc.delete(replaceIndex, replaceDistance);
        doc.insert(replaceIndex, replacementString);
    }

    @Override
    public void undo() {
        doc.delete(replaceIndex, replaceDistance);
        doc.insert(replaceIndex, oldString);
    }

    @Override
    public void redo() {
        execute();
    }
}
