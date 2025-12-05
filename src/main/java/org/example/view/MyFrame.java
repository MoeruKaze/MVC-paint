package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private MyPanel panel;

    public MyFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void setPanel(MyPanel panel) {
        if (this.panel != null) {
            remove(this.panel);
        }
        this.panel = panel;
        add(panel, BorderLayout.CENTER);
    }
}