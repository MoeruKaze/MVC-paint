package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.Point;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {
    private MyShape sampleShape;
    private MyShape drawableShape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private Model model;

    public ActionDraw(MyShape shape, Model model) {
        this.sampleShape = shape;
        this.model = model;
    }

    public ActionDraw(Model model) {
        this.model = model;
    }

    @Override
    public void mousePressed(Point point) {
        secondPoint = point;
        sampleShape = sampleShape.clone();
        drawableShape = sampleShape;
        model.createCurrentShape(sampleShape);
        model.update();
    }

    @Override
    public void mouseDragged(Point point) {
        firstPoint = point;
        sampleShape.setFrame(firstPoint, secondPoint);
        if (drawableShape != null) {
            drawableShape.setFrame(firstPoint, secondPoint);
        }
        model.update();
    }

    @Override
    public void execute() {
        if (drawableShape != null) {
            model.createCurrentShape(drawableShape);
            model.update();
        }
    }

    @Override
    public void unexecute() {
        drawableShape = model.getLastShape();
        model.removeLastShape();
        model.update();
    }

    @Override
    public AppAction cloneAction() {
        ActionDraw actionDraw = new ActionDraw(model);
        if (sampleShape != null) {
            actionDraw.sampleShape = sampleShape.clone();
        }
        actionDraw.drawableShape = drawableShape;
        return actionDraw;
    }

    public Point2D getFirstPoint() {
        return firstPoint;
    }

    public Point2D getSecondPoint() {
        return secondPoint;
    }

    public MyShape getSampleShape() {
        return sampleShape;
    }

    public MyShape getDrawableShape() {
        return drawableShape;
    }
}