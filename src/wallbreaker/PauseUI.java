package wallbreaker;

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
public class PauseUI extends JPanel{
    
    private Image m_backImg;
    
    private JLabel m_explicitLabel;
    
    private JTextField m_textField;
    
    public PauseUI()
    {
        try{
        m_backImg = ImageIO.read(new File("src/img/water.png"));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        m_explicitLabel.setText("Enter the secret word !");
        
        m_textField.setText("Oui ! Oui ! Ici !");
        
        this.add(m_explicitLabel);
        this.add(m_textField);
        
        repaint();
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        
        g.drawImage(m_backImg, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
