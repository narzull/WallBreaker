package fr.imac.wallbreaker.core;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        Point p = me.getPoint();
        if(p.x < 430 && p.x > 340 && p.y > 480 && p.y < 520){
            GameUI.getInstance().getContentPane().removeAll();
            GameUI.getInstance().launchGame();
        }
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
