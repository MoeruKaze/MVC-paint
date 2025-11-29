package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.Point;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {
    public MyShape sampleShape;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private Model model;

    public ActionDraw(MyShape shape, Model model) {
        this.shape = shape;
        this.model = model;
        this.sampleShape = shape;
    }

    @Override
    public void mousePressed(Point point) {
        firstPoint = point;
        // Создаем новую фигуру только при нажатии
        shape = sampleShape.clone();
        shape.setFrame(point);
        model.createCurrentShape(shape);
    }

    @Override
    public void mouseDragged(Point point) {
        secondPoint = point;
        // Растягиваем существующую фигуру, не создавая новую
        shape.setFrame(firstPoint, secondPoint);
        model.changeShape(this);
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
}