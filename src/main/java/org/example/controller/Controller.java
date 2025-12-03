package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;

    private MenuState menuState;

    private MyShape sampleShape;

    private Controller() {
        model = new Model();

        // Инициализация фигуры по умолчанию
        sampleShape = new MyShape(new Rectangle2D.Double());
        // Уже установлен черный цвет по умолчанию в конструкторе MyShape

        panel = new MyPanel(this);
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);

        var actionDraw = new ActionDraw(sampleShape, model);
        var actionMove = new ActionMove(model);

        // Инициализация состояния меню
        menuState = new MenuState();
        menuState.setFill(false); // По умолчанию не закрашивать
        menuState.setColor(Color.BLACK); // По умолчанию черный цвет
        menuState.setSelectedShape(new Rectangle2D.Double()); // По умолчанию прямоугольник
        menuState.setAppAction(actionDraw);

        MenuCreator menuCreator = new MenuCreator(sampleShape, model, menuState);
        frame.setJMenuBar(menuCreator.createMenu());

        frame.revalidate();
    }

    public static Controller getInstance() {
        synchronized (Controller.class) {
            if (instance == null) instance = new Controller();
        }
        return instance;
    }

    public void mousePressed(Point p) {
        AppAction action = menuState.getAppAction();
        action.mousePressed(p);
    }

    public void mouseDragged(Point p) {
        AppAction action = menuState.getAppAction();
        action.mouseDragged(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

}