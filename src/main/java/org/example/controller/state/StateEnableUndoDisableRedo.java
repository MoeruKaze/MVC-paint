package org.example.controller.state;

import org.example.controller.AppAction;

import java.util.LinkedList;

public class StateEnableUndoDisableRedo extends UndoRedoState {

    protected StateEnableUndoDisableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();
        AppAction action = undoActivityList.pollLast();
        if (action != null) {
            redoActivityList.add(action.cloneAction());
            action.unexecute();
        }
        if (undoActivityList.size() == 0) {
            return new StateDisableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        } else {
            return new StateEnableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        }
    }

    @Override
    public UndoRedoState redo() {
        return this;
    }
}