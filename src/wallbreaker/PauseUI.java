package wallbreaker;

import java.awt.Color;
import java.awt.Dimension;
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
    private JLabel m_explicitLabel;
    private JTextField m_textField;
    private String m_Word;

    public PauseUI() {
        
        try {
            m_backImg = ImageIO.read(new File("src/img/water.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        m_explicitLabel = new JLabel("Enter the secret word !");

        m_textField = new JTextField("******");
        
        m_Word = new String();

        repaint();
        this.add(m_explicitLabel);
        this.add(m_textField);
        
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(Color.BLUE);
        
        
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(m_backImg, 0, 0, this.getWidth(), this.getHeight(), null);
        g.drawString(m_Word,300 ,300);
    }
    
    
    public String  getStringTextField()
    {
        return m_textField.getText();
    }
    
    public void setWord(String s)
    {
        m_Word  = s;
    }
    
    public String getWord()
    {
        return m_Word;
    }
}
