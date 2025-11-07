package org.example.model;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Observable;

public class Model extends Observable {
    private MyShape currentShape;

    public void setMyShape(MyShape myShape) {
        this.currentShape = myShape;
    }

    public void changeShape(Point2D x, Point2D y) {
        if (currentShape != null) {
            currentShape.setFrame(x, y);
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void draw(Graphics2D g) {
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }

    public void createCurrentShape(MyShape shape) {
        this.currentShape = shape;
        this.setChanged();
        this.notifyObservers();
    }
}