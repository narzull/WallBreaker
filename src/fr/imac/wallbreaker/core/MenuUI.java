package fr.imac.wallbreaker.core;

import java.awt.Color;
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
public class MenuUI extends JPanel {

	private String m_BackgroundPath;
	private Image m_BackGroundImg;

	public MenuUI() {
		m_BackgroundPath = "src/fr/imac/wallbreaker/img/menu.png";

		this.initUI();

	}

	public void initUI() {

		this.setPreferredSize(new Dimension(200, 600));

		try {
			m_BackGroundImg = ImageIO.read(new File(m_BackgroundPath));
		} catch (IOException e) {
		}

		this.repaint();

	}

	@Override
	public void paintComponent(Graphics g) {

		g.drawImage(m_BackGroundImg, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), this);
		g.setColor(new Color(209, 208, 191));
		g.drawString(String.valueOf(Game.getInstance().getScore()), 90, 135);
		g.drawString(String.valueOf(Game.getInstance().getLives()), 90, 240);
		g.drawString(String.valueOf(Game.getInstance().getIdLvl()), 90, 345);
		g.drawString(String.valueOf(Game.getInstance().getLevel().getStringLettersFound()), 20, 455);
	}

	public void updateDisplays() {
		repaint();
	}
}
