
package org.example.controller.state;

import org.example.controller.AppAction;

import java.util.LinkedList;

public class StateEnableUndoEnableRedo extends UndoRedoState {

    protected StateEnableUndoEnableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        //TODO: Определить
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();
        AppAction action = undoActivityList.pollLast();
        if (action != null) {
            redoActivityList.add(action);
            action.unexecute();
        }
        if (undoActivityList.size() == 0) {
            return new StateDisableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        } else return this;
    }

    @Override
    public UndoRedoState redo() {
        //TODO: Определить
        return this;
    }
}

