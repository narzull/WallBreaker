package fr.imac.wallbreaker.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author nexus_21
 */
public class StartUI extends JPanel implements ActionListener {

    private JButton m_StartButton;
    private JButton m_OptionButton;
    private String m_BackgroundPath;
    private Image m_BackGroundImg;

    public StartUI() {
        m_StartButton = new JButton("Start");
        m_OptionButton = new JButton("Option");
        m_BackgroundPath = "src/fr/imac/wallbreaker/img/water.png";
        
        this.initUI();
    }

    public void initUI() {
        this.setPreferredSize(new Dimension(800, 600));

        try {
            m_BackGroundImg = ImageIO.read(new File(m_BackgroundPath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.repaint();

        this.add(m_StartButton);
        m_StartButton.addActionListener(this);
        this.add(m_OptionButton);
        m_OptionButton.addActionListener(this);



    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(m_BackGroundImg, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(m_StartButton)) {
            System.out.println("Action event start button");
            GameUI.getInstance().getContentPane().removeAll();
            GameUI.getInstance().launchGame();

        }

        if (ae.getSource().equals(m_OptionButton)) {
            System.out.println("Action event option button");
        }

    }
}
