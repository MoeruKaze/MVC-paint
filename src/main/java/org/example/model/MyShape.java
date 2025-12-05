package org.example.model;

import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class MyShape implements Cloneable {
    private Color color;
    private RectangularShape shape;
    private FillBehavior fb;

    public MyShape(RectangularShape shape) {
        this.shape = shape;
        color = Color.BLACK;
        fb = new NoFill();
        fb.setColor(color);
        fb.setShape(shape);
    }

    public MyShape() {
        color = Color.BLACK;
        shape = new Rectangle2D.Double();
        fb = new NoFill();
        fb.setColor(color);
        fb.setShape(shape);
    }

    public MyShape(Color color, RectangularShape shape, FillBehavior fb) {
        this.color = color;
        this.shape = shape;
        this.fb = fb;
        this.fb.setShape(shape);
        this.fb.setColor(color);
    }

    public MyShape(MyShape other) {
        this.color = other.color;
        this.shape = (RectangularShape) other.shape.clone();
        this.fb = other.fb.clone();
    }

    public void setFb(FillBehavior fb) {
        this.fb = fb;
        fb.setShape(shape);
        fb.setColor(color);
    }

    public void setColor(Color color) {
        this.color = color;
        if (fb != null) {
            fb.setColor(color);
        }
    }

    public Color getColor() {
        return color;
    }

    public RectangularShape getShape() {
        return shape;
    }

    public void setShape(RectangularShape shape) {
        this.shape = shape;
        if (fb != null) {
            fb.setShape(shape);
        }
    }

    public void setFrame(Point2D x, Point2D y) {
        shape.setFrameFromDiagonal(x, y);
        if (fb != null) {
            fb.setShape(shape);
        }
    }

    public void setFrame(Point2D point) {
        shape.setFrame(point.getX(), point.getY(), 0, 0);
        if (fb != null) {
            fb.setShape(shape);
        }
    }

    void draw(Graphics2D g) {
        fb.draw(g);
    }

    @Override
    public MyShape clone() {
        return new MyShape(this);
    }
}