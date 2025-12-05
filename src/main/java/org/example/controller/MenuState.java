package org.example.controller;

import org.example.model.MyShape;
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
    private AppAction appAction;
    private MyShape sampleShape;

    public MenuState() {
        this.fill = false;
        this.color = Color.BLACK;
        this.selectedShape = new Rectangle2D.Double();
    }

    public AppAction getAppAction() {
        return appAction;
    }

    public void setAppAction(AppAction appAction) {
        this.appAction = appAction;
    }

    public RectangularShape createShape(){
        return new Rectangle2D.Double();
    }

    public FillBehavior createFillBehavior() {
        if (fill) {
            Fill fill = new Fill();
            fill.setColor(color);
            return fill;
        } else {
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
        if (sampleShape != null) {
            sampleShape.setColor(color);
        }
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(Shape selectedShape) {
        this.selectedShape = new Rectangle2D.Double();
    }

    public MyShape getSampleShape() {
        return sampleShape;
    }

    public void setSampleShape(MyShape sampleShape) {
        this.sampleShape = sampleShape;
    }
}