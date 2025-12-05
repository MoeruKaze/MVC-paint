package org.example.controller.state;

import org.example.controller.AppAction;
import org.example.view.menu.CommandActionListener;
import javax.swing.*;
import java.util.Stack;

public class UndoMachine {
    private Stack<AppAction> undoStack = new Stack<>();
    private Stack<AppAction> redoStack = new Stack<>();

    private CommandActionListener undoActionListener;
    private CommandActionListener redoActionListener;

    public UndoMachine(CommandActionListener undoActionListener, CommandActionListener redoActionListener) {
        this.undoActionListener = undoActionListener;
        this.redoActionListener = redoActionListener;
        updateButtons();
    }

    public void add(AppAction action) {
        undoStack.push(action);
        redoStack.clear();
        updateButtons();
    }

    public void executeUndo() {
        if (!undoStack.isEmpty()) {
            AppAction action = undoStack.pop();
            action.unexecute();
            redoStack.push(action);
            updateButtons();
        }
    }

    public void executeRedo() {
        if (!redoStack.isEmpty()) {
            AppAction action = redoStack.pop();
            action.execute();
            undoStack.push(action);
            updateButtons();
        }
    }

    public void updateButtons() {
        if (undoActionListener != null) {
            undoActionListener.setEnabled(isEnabledUndo());
        }
        if (redoActionListener != null) {
            redoActionListener.setEnabled(isEnabledRedo());
        }
    }

    private boolean isEnabledUndo() {
        return !undoStack.isEmpty();
    }

    private boolean isEnabledRedo() {
        return !redoStack.isEmpty();
    }

    public void setUndoActionListener(CommandActionListener undoActionListener) {
        this.undoActionListener = undoActionListener;
        updateButtons();
    }

    public void setRedoActionListener(CommandActionListener redoActionListener) {
        this.redoActionListener = redoActionListener;
        updateButtons();
    }
}