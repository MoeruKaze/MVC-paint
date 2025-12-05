package org.example.view.menu;

import org.example.controller.MenuState;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

public class SwitchFill implements AppCommand {
    private MenuState menuState;
    private boolean fill;

    public SwitchFill(MenuState menuState, boolean fill) {
        this.menuState = menuState;
        this.fill = fill;
    }

    @Override
    public void execute() {
        menuState.setFill(fill);
        updateSampleShapeFill();
    }

    private void updateSampleShapeFill() {
        FillBehavior fb;
        if (menuState.isFill()) {
            fb = new Fill();
        } else {
            fb = new NoFill();
        }
        fb.setColor(menuState.getColor());
        if (menuState.getSampleShape() != null) {
            menuState.getSampleShape().setFb(fb);
        }
    }
}