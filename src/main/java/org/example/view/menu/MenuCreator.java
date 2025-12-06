package org.example.view.menu;

import lombok.Setter;
import org.example.controller.*;
import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.model.MyShape;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.util.ArrayList;

@Setter
public class MenuCreator {
    private static MenuCreator instance;
    private MenuState state;
    private Model model;
    private MyShape sampleShape;
    private UndoMachine undoMachine;

    private MenuCreator() {
    }

    public static MenuCreator getInstance() {
        if (instance == null) {
            instance = new MenuCreator();
        }
        return instance;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu actionMenu = createShapeMenu();
        menuBar.add(actionMenu);

        JMenu figureMenu = createFigureMenu();
        menuBar.add(figureMenu);

        JMenu colorMenu = createColorMenu();
        menuBar.add(colorMenu);

        JMenu fillMenu = createFillMenu();
        menuBar.add(fillMenu);


        return menuBar;
    }

    public JToolBar createToolBar() {
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar();
        subMenuItems.forEach(jToolBar::add);
        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems() {
        ArrayList<Action> menuItems = new ArrayList<>();

        AppCommand drawCommand = new SwitchAction(new ActionDraw(sampleShape, model), state);
        URL drawUrl = getClass().getClassLoader().getResource("ico/draw_16x16.png");
        ImageIcon drawIco = drawUrl == null ? null : new ImageIcon(drawUrl);
        menuItems.add(new CommandActionListener("Рисовать", drawIco, drawCommand));

        AppCommand moveCommand = new SwitchAction(new ActionMove(model), state);
        URL moveUrl = getClass().getClassLoader().getResource("ico/move_16x16.png");
        ImageIcon moveIco = moveUrl == null ? null : new ImageIcon(moveUrl);
        menuItems.add(new CommandActionListener("Двигать", moveIco, moveCommand));

        URL colorUrl = getClass().getClassLoader().getResource("ico/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        AppCommand colorCommand = new SwitchColor(state, false, null, null);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));

        AppCommand fillCommand = new SwitchFill(state, true);
        URL fillUrl = getClass().getClassLoader().getResource("ico/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        menuItems.add(new CommandActionListener("Заливка", fillIco, fillCommand));

        AppCommand noFillCommand = new SwitchFill(state, false);
        URL noFillUrl = getClass().getClassLoader().getResource("ico/no_fill_16x16.png");
        ImageIcon noFillIco = noFillUrl == null ? null : new ImageIcon(noFillUrl);
        menuItems.add(new CommandActionListener("Без заливки", noFillIco, noFillCommand));


        CommandActionListener undoAction = new CommandActionListener(new SwitchUndo(undoMachine));
        URL undoUrl = getClass().getClassLoader().getResource("ico/undo_16x16.png");
        ImageIcon undoIco = undoUrl == null ? null : new ImageIcon(undoUrl);
        undoAction.putValue(Action.NAME, "Отменить");
        undoAction.putValue(Action.SMALL_ICON, undoIco);
        undoAction.setEnabled(false);
        undoMachine.setUndoActionListener(undoAction);
        menuItems.add(undoAction);

        CommandActionListener redoAction = new CommandActionListener(new SwitchRedo(undoMachine));
        URL redoUrl = getClass().getClassLoader().getResource("ico/redo_16x16.png");
        ImageIcon redoIco = redoUrl == null ? null : new ImageIcon(redoUrl);
        redoAction.putValue(Action.NAME, "Повторить");
        redoAction.putValue(Action.SMALL_ICON, redoIco);
        redoAction.setEnabled(false);
        undoMachine.setRedoActionListener(redoAction);
        menuItems.add(redoAction);

        return menuItems;
    }


    private JMenu createShapeMenu() {
        JMenu shapeMenu = new JMenu("Действие");
        ButtonGroup group = new ButtonGroup();

        AppCommand drawCommand = new SwitchAction(new ActionDraw(sampleShape, model), state);
        JRadioButtonMenuItem draw = new JRadioButtonMenuItem("Рисовать");
        draw.addActionListener(new CommandActionListener("Рисовать", null, drawCommand));
        shapeMenu.add(draw);
        group.add(draw);
        draw.setSelected(true);

        AppCommand moveCommand = new SwitchAction(new ActionMove(model), state);
        JRadioButtonMenuItem move = new JRadioButtonMenuItem("Двигать");
        move.addActionListener(new CommandActionListener("Двигать", null, moveCommand));
        shapeMenu.add(move);
        group.add(move);

        return shapeMenu;
    }

    private JMenu createFigureMenu() {
        JMenu figureMenu = new JMenu("Фигура");
        ButtonGroup figureGroup = new ButtonGroup();

        AppCommand rectangleCommand = new SwitchShape(state, sampleShape, new Rectangle2D.Double());
        JRadioButtonMenuItem rectangle = new JRadioButtonMenuItem("Прямоугольник");
        rectangle.addActionListener(new CommandActionListener("Прямоугольник", null, rectangleCommand));
        figureMenu.add(rectangle);
        figureGroup.add(rectangle);
        rectangle.setSelected(true);

        AppCommand ellipseCommand = new SwitchShape(state, sampleShape, new Ellipse2D.Double());
        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(new CommandActionListener("Эллипс", null, ellipseCommand));
        figureMenu.add(ellipse);
        figureGroup.add(ellipse);

        AppCommand roundedRectCommand = new SwitchShape(state, sampleShape,
                new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20));
        JRadioButtonMenuItem roundedRect = new JRadioButtonMenuItem("Закругленный прямоугольник");
        roundedRect.addActionListener(new CommandActionListener("Закругленный прямоугольник", null, roundedRectCommand));
        figureMenu.add(roundedRect);
        figureGroup.add(roundedRect);

        return figureMenu;
    }

    private JMenu createColorMenu() {
        JMenu colorMenu = new JMenu("Цвет");
        ButtonGroup colorGroup = new ButtonGroup();

        AppCommand blackCommand = new SwitchColor(state, true, Color.BLACK, null);
        JRadioButtonMenuItem black = new JRadioButtonMenuItem("Черный");
        black.addActionListener(new CommandActionListener("Черный", null, blackCommand));
        colorMenu.add(black);
        colorGroup.add(black);
        black.setSelected(true);

        AppCommand blueCommand = new SwitchColor(state, true, Color.BLUE, null);
        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Синий");
        blue.addActionListener(new CommandActionListener("Синий", null, blueCommand));
        colorMenu.add(blue);
        colorGroup.add(blue);

        AppCommand redCommand = new SwitchColor(state, true, Color.RED, null);
        JRadioButtonMenuItem red = new JRadioButtonMenuItem("Красный");
        red.addActionListener(new CommandActionListener("Красный", null, redCommand));
        colorMenu.add(red);
        colorGroup.add(red);

        AppCommand greenCommand = new SwitchColor(state, true, Color.GREEN, null);
        JRadioButtonMenuItem green = new JRadioButtonMenuItem("Зеленый");
        green.addActionListener(new CommandActionListener("Зеленый", null, greenCommand));
        colorMenu.add(green);
        colorGroup.add(green);

        return colorMenu;
    }

    private JMenu createFillMenu() {
        JMenu fillMenu = new JMenu("Заливка");
        ButtonGroup fillGroup = new ButtonGroup();

        AppCommand fillCommand = new SwitchFill(state, true);
        JRadioButtonMenuItem fill = new JRadioButtonMenuItem("Закрашивать");
        fill.addActionListener(new CommandActionListener("Закрашивать", null, fillCommand));
        fillMenu.add(fill);
        fillGroup.add(fill);

        AppCommand noFillCommand = new SwitchFill(state, false);
        JRadioButtonMenuItem noFill = new JRadioButtonMenuItem("Не закрашивать");
        noFill.addActionListener(new CommandActionListener("Не закрашивать", null, noFillCommand));
        fillMenu.add(noFill);
        fillGroup.add(noFill);
        noFill.setSelected(true);

        return fillMenu;
    }
}