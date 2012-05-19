package fr.imac.wallbreaker.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nexus_21
 */
public class MenuUI extends JPanel {

    private JLabel m_Score;
    private JLabel m_Life;
    private JLabel m_IdLvl;
    private JLabel m_FLetters;
    private String m_BackgroundPath;
    private Image m_BackGroundImg;

    public MenuUI() {
        m_Score = new JLabel("Score : ");
        m_Life = new JLabel("Lives : ");
        m_IdLvl = new JLabel("Level n° : ");
        m_FLetters = new JLabel("Letters :");
        m_BackgroundPath = "src/fr/imac/wallbreaker/img/water.png";

        this.initUI();

    }

    public void initUI() {

        this.setPreferredSize(new Dimension(200, 600));

        try {
            m_BackGroundImg = ImageIO.read(new File(m_BackgroundPath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.repaint();
        
        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(layout);

        this.add(this.m_Score);
        this.add(this.m_Life);
        this.add(this.m_IdLvl);
        this.add(this.m_FLetters);


    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(m_BackGroundImg, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), this);
    }

    public JLabel getLabelScore() {
        return this.m_Score;
    }

    public JLabel getLabelLifes() {
        return this.m_Life;
    }

    public JLabel getLabelIdLevel() {
        return this.m_IdLvl;
    }

    public JLabel getLabelfLetters() {
        return this.m_FLetters;
    }

    public void updateDisplays(int score, int lives, int lvlid) {
        this.m_Score.setText("Score : " + String.valueOf(score));
        this.m_Life.setText("Lives : " + String.valueOf(lives));
        this.m_IdLvl.setText("Level n° : " + String.valueOf(lvlid));
    }
}
