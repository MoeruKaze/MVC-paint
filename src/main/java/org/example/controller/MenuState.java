package org.example.controller;

import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class MenuState {
    private boolean fill;
    private Color color;
    private RectangularShape selectedShape;

    public MenuState(boolean fill, Color color, RectangularShape shapeType, ActionDraw actionDraw) {
        this.fill = fill;
        this.color = color;
        this.selectedShape = shapeType;
        this.actionDraw = actionDraw;
    }

    public MenuState() {
        this.fill = false;
        this.color = Color.blue;
        this.selectedShape = new Rectangle2D.Double();
        this.actionDraw = actionDraw;
    }

    public ActionDraw getActionDraw() {
        return actionDraw;
    }

    public void setActionDraw(ActionDraw actionDraw) {
        this.actionDraw = actionDraw;
    }

    private ActionDraw actionDraw;

    public RectangularShape createShape(){
                return new Rectangle2D.Double();
        }

public FillBehavior createFillBehavior() {
        if (fill) {
            Fill fill = new Fill();
            fill.setColor(color);
            return fill;}
        else{
            NoFill noFill = new NoFill();
            noFill.setColor(color);
            return noFill;

        }
}

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(Shape selectedShape) {
        this.selectedShape = new Rectangle2D.Double();
    }

}

