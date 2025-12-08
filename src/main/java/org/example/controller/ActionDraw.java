package org.example.controller;

import lombok.Getter;
import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.Point;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {
    public MyShape sampleShape;
    private MyShape drawableShape;
    @Getter
    private Point2D firstPoint;
    @Getter
    private Point2D secondPoint;
    private Model model;
    private MyShape originalShape;

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
        originalShape = drawableShape.clone();
        model.createCurrentShape(drawableShape);
    }

    @Override
    public void mouseDragged(Point point) {
        secondPoint = point;
        drawableShape.setFrame(firstPoint, secondPoint);
        model.changeShape(this);
    }

    @Override
    public void execute() {
        if (drawableShape == null && originalShape != null) {
            drawableShape = originalShape.clone();
        }
        if (drawableShape == null) {
            return;
        }
//        drawableShape.setFrame(firstPoint, secondPoint);
        model.changeShape(this);
        model.createCurrentShape(drawableShape);

        model.update();
    }

    @Override
    public void unexecute() {
        model.removeLastShape();
        model.update();
    }

    @Override
    public AppAction cloneAction() {
        ActionDraw actionDraw = new ActionDraw(drawableShape, model);
        actionDraw.sampleShape = sampleShape.clone();
        actionDraw.drawableShape = drawableShape != null ? drawableShape.clone() : null;
        actionDraw.originalShape = originalShape != null ? originalShape.clone() : null;
//        actionDraw.firstPoint = firstPoint;
//        actionDraw.secondPoint = secondPoint;
        return actionDraw;
    }
}