package me.bretg96.pong;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args) {
        Dimension d = new Dimension(Pong.WIDTH,Pong.HEIGHT);
        Pong p = new Pong();
        p.setPreferredSize(d);
        p.setMaximumSize(d);
        p.setMinimumSize(d);

        JFrame jf = new JFrame();
        jf.add(p);
        jf.pack();
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

        p.run();
    }
}
