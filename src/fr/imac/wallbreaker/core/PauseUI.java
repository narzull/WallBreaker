package fr.imac.wallbreaker.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nexus_21
 */
public class PauseUI extends JPanel {

    private Image m_backImg;
    private String m_Word;

    public PauseUI() {

        try {
            m_backImg = ImageIO.read(new File("src/fr/imac/wallbreaker/img/wordMenu.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        m_Word = new String();

        repaint();

        this.setPreferredSize(new Dimension(200, 200));


    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(m_backImg, 0, 0, this.getWidth(), this.getHeight(), null);
        g.setFont(new Font("",Font.PLAIN,20));
        g.drawString(m_Word, 250, 325);
    }


    public void setWord(String s) {
        m_Word = s;
    }

    public String getWord() {
        return m_Word;
    }
}
