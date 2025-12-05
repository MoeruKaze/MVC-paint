package org.example.view.menu;

import org.example.controller.AppAction;
import org.example.controller.MenuState;

public class SwitchAction implements AppCommand {
    private AppAction action;
    private MenuState state;

    public SwitchAction(AppAction action, MenuState state) {
        this.action = action;
        this.state = state;
    }

    @Override
    public void execute() {
        state.setAppAction(action);
    }
}