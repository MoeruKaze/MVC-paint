package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.controller.state.UndoMachine;

@AllArgsConstructor
public class SwitchRedo implements AppCommand {
    private UndoMachine undoMachine;
    @Override
    public void execute() {
        undoMachine.executeRedo();
        undoMachine.updateButtons();
    }
}
