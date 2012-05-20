package fr.imac.wallbreaker.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author nexus_21
 */
public class StartUI extends BasicPanel implements MouseListener {
  

    public StartUI(int width, int height,String imgPath) {
        super(width,height,imgPath);
        addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println(me.getX() + "  " + me.getY());
        Point p = me.getPoint();
        if(p.x < 430 && p.x > 340 && p.y > 480 && p.y < 520){
            System.out.println("clicked");
            GameUI.getInstance().getContentPane().removeAll();
            GameUI.getInstance().launchGame();
        }
        else
            System.out.println("out");
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
