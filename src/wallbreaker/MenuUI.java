/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nexus_21
 */
public class MenuUI extends JPanel{
    
    private JLabel m_score;
    
    private JLabel m_life;
    
    private JLabel m_idLvl;
    
    private JLabel m_fLetters;
    
    private Image m_backImg;
    
    public MenuUI()
    {
        m_score = new JLabel("Score : ");
        m_life = new JLabel("Lives : ");
        m_idLvl = new JLabel("Level n° : ");
        m_fLetters = new JLabel("Letters :");
       
        BoxLayout layout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
        this.setLayout(layout);
        
        this.add(this.m_score);
        this.add(this.m_life);
        this.add(this.m_idLvl);
        this.add(this.m_fLetters);
        
        this.setPreferredSize(new Dimension(200,600));
        this.setBackground(Color.RED);
    }
    
    
    public JLabel getLabelScore()
    {
        return this.m_score;
    }
    
    public JLabel getLabelLifes()
    {
        return this.m_life;
    }
    
    public JLabel getLabelIdLevel()
    {
        return this.m_idLvl;
    }
    
    public JLabel getLabelfLetters()
    {
        return this.m_fLetters;
    }
}
