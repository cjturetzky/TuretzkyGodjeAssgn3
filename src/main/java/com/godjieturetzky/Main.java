package com.godjieturetzky;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.lang.Thread;

public class Main extends JFrame{
    public static String IDNAME = "GodjieTuretzky";
    public static void main(String[] args) {
        Main m = new Main();
        m.setTitle("Assignment 3");
        m.setSize(500, 500);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setVisible(true);

        MQTTPub mp = new MQTTPub();
        Thread t1 = new Thread(mp);
        t1.start();

        MQTTSub ms = new MQTTSub();
        Thread t2 = new Thread(ms);
        t2.start();
    }

    public Main() {
        this.setLayout(new GridLayout(1, 1));
        WorldPanel wp = new WorldPanel();
        wp.addKeyListener(wp);
        wp.setFocusable(true);
        this.add(wp);
    }
}
