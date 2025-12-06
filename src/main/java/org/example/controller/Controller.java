package org.example.controller;

import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.view.menu.CommandActionListener;
import org.example.view.menu.MenuCreator;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private final UndoMachine undoMachine;

    private MenuState menuState;
    private MyShape sampleShape;

    private Controller() {
        model = new Model();
        sampleShape = new MyShape(new Rectangle2D.Double());
        panel = new MyPanel(this);
        model.addObserver(panel);
        frame = new MyFrame();
        frame.setPanel(panel);

        // Создаем UndoMachine
        // TODO: 06.12.2025 ПЕРЕНЕСТИ
        CommandActionListener undoAction = new CommandActionListener("Undo", null, null);
        CommandActionListener redoAction = new CommandActionListener("Redo", null, null);
        undoMachine = new UndoMachine();

        menuState = new MenuState();
        menuState.setFill(false);
        menuState.setColor(Color.BLACK);
        menuState.setSelectedShape(new Rectangle2D.Double());
        menuState.setSampleShape(sampleShape);
        menuState.setAppAction(new ActionDraw(sampleShape, model));

        MenuCreator menuCreator = MenuCreator.getInstance();
        menuCreator.setState(menuState);
        menuCreator.setModel(model);
        menuCreator.setSampleShape(sampleShape);


        frame.setJMenuBar(menuCreator.createMenuBar());
        frame.revalidate();
        frame.add(menuCreator.createToolBar(), BorderLayout.NORTH);
    }

    public static Controller getInstance() {
        synchronized (Controller.class) {
            if (instance == null) instance = new Controller();
        }
        return instance;
    }

    public void mousePressed(Point p) {
        AppAction action = menuState.getAppAction();
        if (action != null) {
            action.mousePressed(p);
        }
    }

    public void mouseDragged(Point p) {
        AppAction action = menuState.getAppAction();
        if (action != null) {
            action.mouseDragged(p);
        }
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public Model getModel() {
        return model;
    }
}