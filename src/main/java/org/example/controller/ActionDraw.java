package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.Point;
import java.awt.geom.Point2D;

public class ActionDraw {
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

    public void stretchShape(Point point) {
        firstPoint = point;
        shape.setFrame(point);
    }

    public void createShape(Point point) {
        secondPoint = point;
        shape = sampleShape.clone();
        model.createCurrentShape(shape);
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