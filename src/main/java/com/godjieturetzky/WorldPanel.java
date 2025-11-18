package com.godjieturetzky;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class WorldPanel extends JPanel implements KeyListener, PropertyChangeListener{
    public WorldPanel() {
        Blackboard.getInstance().addPropertyChangeListener(this);
        setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        for (Square square : Blackboard.getInstance().getSquares()) {
            square.draw(g);
        }
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= getWidth(); i += 50) {
            g.drawLine(i, 0, i, getHeight());
        }
        for (int j = 0; j <= getHeight(); j += 50) {
            g.drawLine(0, j, getWidth(), j);
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyChar());
        Square self = Blackboard.getInstance().getSquares().get(0);
        int x = self.getX();
        int y = self.getY();
        switch(e.getKeyChar()){
            case 's':
                y += 10;
                break;
            case 'a':
                x -= 10;
                break;
            case 'w':
                y -= 10;
                break;
            case 'd':
                x += 10;
                break;
            default:
                break;
        }
        Blackboard.getInstance().updateSquare(self.getName(), x, y);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        return;
    }

}

