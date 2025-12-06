package org.example.controller.state;

import org.example.controller.AppAction;
import org.example.view.menu.CommandActionListener;

import java.util.LinkedList;

public class UndoMachine {
    private UndoRedoState undoRedoState;
    private CommandActionListener undoActionListener;
    private CommandActionListener redoActionListener;

    public UndoMachine() {
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
    }

    public void setUndoActionListener(CommandActionListener undoActionListener) {
        this.undoActionListener = undoActionListener;
        updateButtons();
    }

    public void setRedoActionListener(CommandActionListener redoActionListener) {
        this.redoActionListener = redoActionListener;
        updateButtons();
    }

    public void updateButtons() {
        if (undoActionListener != null) {
            undoActionListener.setEnabled(isEnableUndo());
        }
        if (redoActionListener != null) {
            redoActionListener.setEnabled(isEnableRedo());
        }
    }

    public void executeRedo() {
        undoRedoState = undoRedoState.redo();
        updateButtons();
    }

    public void executeUndo() {
        undoRedoState = undoRedoState.undo();
        updateButtons();
    }

    public boolean isEnableUndo() {
        return !undoRedoState.getUndoActivityList().isEmpty();
    }

    public boolean isEnableRedo() {
        return !undoRedoState.getRedoActivityList().isEmpty();
    }

    public void add(AppAction action) {
        undoRedoState.clearHistory();
        undoRedoState.addAction(action.cloneAction());

        if (undoRedoState.getUndoActivityList().size() > 0 &&
                undoRedoState.getRedoActivityList().size() == 0) {
            undoRedoState = new StateEnableUndoDisableRedo(
                    undoRedoState.getUndoActivityList(),
                    undoRedoState.getRedoActivityList()
            );
        }
        updateButtons();
    }
}