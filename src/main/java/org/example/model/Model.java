package org.example.model;

import org.example.controller.ActionDraw;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private MyShape currentShape;
    private List<MyShape> shapeList = new ArrayList<>();

    public void setMyShape(MyShape myShape) {
        this.currentShape = myShape;
    }

    public void changeShape(ActionDraw actionDraw) {
        if (currentShape != null) {
            currentShape.setFrame(actionDraw.getFirstPoint(), actionDraw.getSecondPoint());
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void draw(Graphics2D g) {
        for (MyShape s : shapeList) {
            s.draw(g);
        }
    }

    public void createCurrentShape(MyShape shape) {
        this.currentShape = shape;
        this.setChanged();
        this.notifyObservers();
        shapeList.add(shape);
    }

    public List<MyShape> getShapeList() {
        return shapeList;
    }

    public void update() {
        this.setChanged();
        this.notifyObservers();
    }
}