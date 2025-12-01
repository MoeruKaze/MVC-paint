package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;

import javax.swing.*;
import java.awt.*;

public class MenuCreator {
    private final MenuState menuState;

    private final Model model;
    private final MyShape sampleShape;

    public MenuCreator(MyShape sampleShape, Model model, MenuState menuState) {
        this.sampleShape = sampleShape;
        this.model = model;
        this.menuState = menuState;
    }

    public JMenuBar createMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu actionMenu = createShapeMenu();
        menu.add(actionMenu);
        return menu;
    }

    private JMenu createShapeMenu() {
        JMenu shapeMenu = new JMenu("Действие");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem draw = new JRadioButtonMenuItem("Рисовать");
        draw.addActionListener(e -> menuState.setAppAction(new ActionDraw(sampleShape, model)));
        shapeMenu.add(draw);
        group.add(draw);

        JRadioButtonMenuItem move = new JRadioButtonMenuItem("Двигать");
        move.addActionListener(e -> menuState.setAppAction(new ActionMove(model)));
        shapeMenu.add(move);
        group.add(move);

        return shapeMenu;

    }

}
