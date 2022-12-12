package org.example.texteditor;

import org.example.texteditor.commands.Command;

import java.util.Stack;

public class UndoRedoManager {
    Stack<Command> undoStack;
    Stack<Command> redoStack;

    public UndoRedoManager() {
        this.undoStack= new Stack<>();
        this.redoStack= new Stack<>();
    }

    public void execute(Command command) {
        command.execute();
        undoStack.push(command);
    }

    public void undo() {
        if (canUndo()) {
            Command last = undoStack.pop();
            last.undo();
            redoStack.push(last);
        }
    }

    public void redo() {
        if (canRedo()) {
            Command next = redoStack.pop();
            next.redo();
            undoStack.push(next);
        }
    }

    private boolean canUndo() {
        return undoStack.size() != 0;
    }

    private boolean canRedo() {
        return redoStack.size() != 0;
    }
}
