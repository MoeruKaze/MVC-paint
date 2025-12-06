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
    private MyShape originalShape; // Для хранения исходного состояния
    private MyShape movedShape; // Для хранения перемещенного состояния

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

        if (shape != null) {
            originalShape = shape.clone(); // Сохраняем исходное состояние
        }
    }

    @Override
    public void mouseDragged(Point point) {
        if (shape == null) {
            return;
        }

        double deltaX = point.getX() - firstPoint.getX();
        double deltaY = point.getY() - firstPoint.getY();

        // Сохраняем перемещенное состояние
        movedShape = shape.clone();

        Point2D newShapeFirstPoint = new Point2D.Double();
        newShapeFirstPoint.setLocation(shape.getShape().getMinX() + deltaX,
                shape.getShape().getMinY() + deltaY);
        Point2D newShapeSecondPoint = new Point2D.Double();
        newShapeSecondPoint.setLocation(shape.getShape().getMaxX() + deltaX,
                shape.getShape().getMaxY() + deltaY);

        shape.getShape().setFrameFromDiagonal(newShapeFirstPoint, newShapeSecondPoint);
        firstPoint = point;
        model.update();
    }

    @Override
    public void execute() {
        if (movedShape != null && shape != null) {
            // Восстанавливаем перемещенное состояние
            shape.setShape(movedShape.getShape());
            model.update();
        }
    }

    @Override
    public void unexecute() {
        if (originalShape != null && shape != null) {
            // Восстанавливаем исходное состояние
            shape.setShape(originalShape.getShape());
            model.update();
        }
    }

    @Override
    public AppAction cloneAction() {
        ActionMove actionMove = new ActionMove(model);
        actionMove.shape = this.shape;
        actionMove.originalShape = this.originalShape != null ? this.originalShape.clone() : null;
        actionMove.movedShape = this.movedShape != null ? this.movedShape.clone() : null;
        return actionMove;
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
}