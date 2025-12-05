package org.example.view.menu;

import org.example.controller.MenuState;
import org.example.model.MyShape;
import java.awt.geom.RectangularShape;

public class SwitchShape implements AppCommand {
    private MenuState state;
    private MyShape sampleShape;
    private RectangularShape shape;

    public SwitchShape(MenuState state, MyShape sampleShape, RectangularShape shape) {
        this.state = state;
        this.sampleShape = sampleShape;
        this.shape = shape;
    }

    @Override
    public void execute() {
        sampleShape.setShape(shape);
        state.setSelectedShape(shape);
    }
}