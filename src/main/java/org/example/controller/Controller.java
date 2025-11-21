package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;

    private MenuState menuState;
    private Controller() {
        model = new Model();

        MyShape shape = new MyShape(new Rectangle2D.Double());
        FillBehavior fb = new NoFill(); //на фабрику поменять
        fb.setColor(Color.BLUE);
        shape.setFb(new NoFill());

        panel = new MyPanel(this);
        // TODO: Поменять наблюдатель на более современную реализацию
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
        ActionDraw actionDraw = new ActionDraw(shape, model);
        menuState = new MenuState();

    }

    public static Controller getInstance() {
        synchronized (Controller.class) {
            if (instance == null) instance = new Controller();
        }
        return instance;
    }

    public void mousePressed(Point p){
        ActionDraw actionDraw = menuState.getActionDraw();
        actionDraw.createShape(p);

    }
    public void mouseDragged(Point p){
        ActionDraw actionDraw = menuState.getActionDraw();
        actionDraw.stretchShape(p);
        model.changeShape(actionDraw);

    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

}
