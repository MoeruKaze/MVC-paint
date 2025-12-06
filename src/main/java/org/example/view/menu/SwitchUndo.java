package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.controller.state.UndoMachine;

@AllArgsConstructor
public class SwitchUndo implements AppCommand {
    private UndoMachine undoMachine;
    @Override
    public void execute() {
        undoMachine.executeUndo();
        undoMachine.updateButtons();
    }
}
