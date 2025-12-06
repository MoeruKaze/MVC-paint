package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.Point;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {
    public MyShape sampleShape;
    private MyShape drawableShape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private Model model;

    public ActionDraw(MyShape drawableShape, Model model) {
        this.drawableShape = drawableShape;
        this.model = model;
        this.sampleShape = drawableShape;
    }

    @Override
    public void mousePressed(Point point) {
        firstPoint = point;
        drawableShape = sampleShape.clone();
        drawableShape.setFrame(point);
        model.createCurrentShape(drawableShape);
    }

    @Override
    public void mouseDragged(Point point) {
        secondPoint = point;
        drawableShape.setFrame(firstPoint, secondPoint);
        model.changeShape(this);
    }

    public Point2D getFirstPoint() {
        return firstPoint;
    }

    public Point2D getSecondPoint() {
        return secondPoint;
    }

    @Override
    public void execute() {
        model.createCurrentShape(drawableShape);
        model.update();
    }
    @Override
    public void unexecute() {
        drawableShape = model.getLastShape();
        model.removeLastShape();
        model.update();
    }
    @Override
    public AppAction cloneAction() {
        ActionDraw actionDraw = new ActionDraw(drawableShape, model);
        actionDraw.sampleShape = sampleShape.clone();
        actionDraw.drawableShape = drawableShape;
        return actionDraw;
    }
}