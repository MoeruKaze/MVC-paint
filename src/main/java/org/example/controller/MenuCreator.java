package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

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

        JMenu figureMenu = createFigureMenu();
        menu.add(figureMenu);

        JMenu colorMenu = createColorMenu();
        menu.add(colorMenu);

        JMenu fillMenu = createFillMenu();
        menu.add(fillMenu);

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

    private JMenu createFigureMenu() {
        JMenu figureMenu = new JMenu("Фигура");
        ButtonGroup figureGroup = new ButtonGroup();

        JRadioButtonMenuItem rectangle = new JRadioButtonMenuItem("Прямоугольник");
        rectangle.addActionListener(e -> {
            sampleShape.setShape(new Rectangle2D.Double());
            menuState.setSelectedShape(new Rectangle2D.Double());
        });
        figureMenu.add(rectangle);
        figureGroup.add(rectangle);
        rectangle.setSelected(true);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> {
            sampleShape.setShape(new Ellipse2D.Double());
            menuState.setSelectedShape(new Ellipse2D.Double());
        });
        figureMenu.add(ellipse);
        figureGroup.add(ellipse);

        JRadioButtonMenuItem roundedRect = new JRadioButtonMenuItem("Закругленный прямоугольник");
        roundedRect.addActionListener(e -> {
            sampleShape.setShape(new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20));
            menuState.setSelectedShape(new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20));
        });
        figureMenu.add(roundedRect);
        figureGroup.add(roundedRect);

        return figureMenu;
    }

    private JMenu createColorMenu() {
        JMenu colorMenu = new JMenu("Цвет");
        ButtonGroup colorGroup = new ButtonGroup();

        JRadioButtonMenuItem black = new JRadioButtonMenuItem("Черный");
        black.addActionListener(e -> {
            sampleShape.setColor(Color.BLACK);
            menuState.setColor(Color.BLACK);
            updateFillBehavior();
        });
        colorMenu.add(black);
        colorGroup.add(black);
        black.setSelected(true);

        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Синий");
        blue.addActionListener(e -> {
            sampleShape.setColor(Color.BLUE);
            menuState.setColor(Color.BLUE);
            updateFillBehavior();
        });
        colorMenu.add(blue);
        colorGroup.add(blue);

        JRadioButtonMenuItem red = new JRadioButtonMenuItem("Красный");
        red.addActionListener(e -> {
            sampleShape.setColor(Color.RED);
            menuState.setColor(Color.RED);
            updateFillBehavior();
        });
        colorMenu.add(red);
        colorGroup.add(red);

        JRadioButtonMenuItem green = new JRadioButtonMenuItem("Зеленый");
        green.addActionListener(e -> {
            sampleShape.setColor(Color.GREEN);
            menuState.setColor(Color.GREEN);
            updateFillBehavior();
        });
        colorMenu.add(green);
        colorGroup.add(green);

        return colorMenu;
    }

    private JMenu createFillMenu() {
        JMenu fillMenu = new JMenu("Заливка");
        ButtonGroup fillGroup = new ButtonGroup();

        JRadioButtonMenuItem fill = new JRadioButtonMenuItem("Закрашивать");
        fill.addActionListener(e -> {
            menuState.setFill(true);
            updateFillBehavior();
        });
        fillMenu.add(fill);
        fillGroup.add(fill);

        JRadioButtonMenuItem noFill = new JRadioButtonMenuItem("Не закрашивать");
        noFill.addActionListener(e -> {
            menuState.setFill(false);
            updateFillBehavior();
        });
        fillMenu.add(noFill);
        fillGroup.add(noFill);
        noFill.setSelected(true);

        return fillMenu;
    }
    private void updateFillBehavior() {
        FillBehavior fb;
        if (menuState.isFill()) {
            fb = new Fill();
        } else {
            fb = new NoFill();
        }
        fb.setColor(sampleShape.getColor());
        fb.setShape(sampleShape.getShape());
        sampleShape.setFb(fb);
    }
}