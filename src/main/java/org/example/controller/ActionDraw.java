package org.example.controller;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;


public class ActionDraw {
    public MyShape sampleShape;
    private MyShape shape;
    private Point2D point1;
    private Point2D point2;
    private Model model;

    public ActionDraw(MyShape shape) {
        this.shape = shape;
    }
}
