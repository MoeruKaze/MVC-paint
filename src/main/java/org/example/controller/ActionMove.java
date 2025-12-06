package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.Point;
import java.awt.geom.Point2D;

public class ActionMove implements AppAction {
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private Model model;

    public ActionMove(Model model) {
        this.model = model;
    }

    @Override
    public void mousePressed(Point point) {
        firstPoint = point;
        shape = model.getShapeList().stream()
                .filter(myShape -> myShape.getShape().contains(point))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void mouseDragged(Point point) {
        if (shape == null) {
            return;
        }

        double deltaX = point.getX() - firstPoint.getX();
        double deltaY = point.getY() - firstPoint.getY();

        Point2D newShapeFirstPoint = new Point2D.Double();
        newShapeFirstPoint.setLocation(shape.getShape().getMaxX() + deltaX,
                shape.getShape().getMaxY() + deltaY);
        Point2D newShapeSecondPoint = new Point2D.Double();
        newShapeSecondPoint.setLocation(shape.getShape().getMinX() + deltaX,
                shape.getShape().getMinY() + deltaY);

        shape.getShape().setFrameFromDiagonal(newShapeFirstPoint, newShapeSecondPoint);
        firstPoint = point;
        model.update();
    }

    public Point2D getFirstPoint() {
        return firstPoint;
    }

    public Point2D getSecondPoint() {
        return secondPoint;
    }

    public MyShape getShape() {
        return shape;
    }

    @Override
    public void execute() {
        model.createCurrentShape(shape);
        model.update();
    }

    @Override
    public void unexecute() {
        shape = model.getLastShape();
        model.removeLastShape();
        model.update();
    }

    @Override
    public AppAction cloneAction() {
        ActionMove actionMove = new ActionMove(model);
        actionMove.shape = shape.clone();
//        actionMove.movebleShape = movebleShape;
        return actionMove;
    }
}