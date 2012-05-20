/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imac.wallbreaker.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author nexus_21
 */
public class BasicPanel extends JPanel {
    
    private Image m_Image;
    
    public BasicPanel(int width, int height, String imgPath)
    {
        /* create images */
        try {
            m_Image = ImageIO.read(new File(imgPath));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.setPreferredSize(new Dimension(width,height));
    }
    
    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(m_Image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
    
}
