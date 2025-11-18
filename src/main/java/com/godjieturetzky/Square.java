package com.godjieturetzky;

import java.awt.Graphics;
import java.awt.Color;

public class Square {
    private int x;
    private int y;
    private int size;
    private Color color;
    private String name;

    public Square(int x, int y, int size, Color color, String name) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.name = name;
        return;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
        return;
    }

    public String getName(){
        return name;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void updatePosition(int x, int y){
        this.x = x;
        this.y = y;
        return;
    }
}
