package fr.imac.wallbreaker.core;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jbox2d.dynamics.World;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author nexus_21
 */
public class Game implements Runnable, Observable {

	/**
	 * instance of Game
	 */
	private static Game m_game = new Game();
	/**
	 * number of lifes
	 */
	private int m_Lives;
	/**
	 * game's score
	 */
	private int m_Score;
	/**
	 * index of current level
	 */
	private int m_IdCurrentLvl;
	/**
	 * Max id for m_IdCurrentLvl
	 */
	private int m_IdFinalLvl;
	/**
	 * list of game's levels
	 */
	private Level m_Level;
	/**
	 * list of observer
	 */
	private ArrayList<Observer> listObs;
	private boolean isRunning;
	/**
	 * If current level is ok
	 */
	private boolean m_LvlIsRunning;
	private boolean m_onPause;
	private boolean m_Finished;

	/**
	 * game's constructor
	 */
	private Game() {
		this.m_Lives = 3;
		this.m_Score = 0;

		this.m_IdCurrentLvl = 0;
		this.m_IdFinalLvl = 3;

		this.listObs = new ArrayList<Observer>();
		this.isRunning = true;
		this.m_LvlIsRunning = false;
		this.m_onPause = false;
		this.m_Finished = false;

	}

	/**
	 * return the unique instance on singleton Game
	 *
	 * @return m_game
	 */
	public static Game getInstance() {
		return m_game;
	}

	/**
	 * initialize
	 */
	public void initializeGame() {
		m_Level = new Level(m_IdCurrentLvl);
		m_Level.initializeLevel();
		m_LvlIsRunning = true;
	}

	/**
	 * save the current game
	 */
	public void save() {
	}

	/**
	 * load the previous game
	 */
	public void load() {
	}

	public boolean isFinished() {
		return m_Finished;
	}

	public void setFinished(boolean b) {
		m_Finished = b;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean b) {
		isRunning = b;
	}

	@Override
	public void run() {
		while (this.isRunning) {
			try {
				Thread.sleep(15);
			} catch (InterruptedException ex) {
				Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			}
			if (!m_onPause) {
				this.m_Level.updatePosition();
			}

			//End of current lvl
			if (m_LvlIsRunning == false) {
				startNextLvl();
			}
		}
	}

	@Override
	public void addObs(Observer o) {
		this.listObs.add(o);

	}

	/**
	 * delete observer o
	 *
	 * @param o
	 */
	@Override
	public void delObs(Observer o) {
		this.listObs.remove(o);
	}

	/**
	 * update all observers
	 */
	@Override
	public void updateObs() {
		for (Observer o : this.listObs) {
			o.update();
		}
	}

	/**
	 * Return current level
	 *
	 * @return currentLevel
	 */
	public Level getLevel() {
		return m_Level;
	}

	public int getIdLvl() {
		return m_IdCurrentLvl;
	}

	public void finishCurrentLvl() {
		m_LvlIsRunning = false;
	}

	public boolean getLvlisRunning() {
		return m_LvlIsRunning;
	}

	public void startNextLvl() {
		if (m_IdCurrentLvl == m_IdFinalLvl) {
			setRunning(false);
			setFinished(true);
			updateHighscore();
			updateObs();
		} else {
			++m_IdCurrentLvl;
			PhysicWorld.getInstance().reset();
			m_Level = new Level(m_IdCurrentLvl);
			m_Level.initializeLevel();
			updateObs();
			m_LvlIsRunning = true;
		}
	}

	public void setOnPause(boolean pause) {
		this.m_onPause = pause;
	}

	public boolean isOnPause() {
		return this.m_onPause;
	}

	public void addScore(int score) {
		m_Score += score;
		updateObs();
	}

	public int getScore() {
		return m_Score;
	}

	public void addLife() {
		++m_Lives;
		updateObs();
	}

	public int getLives() {
		return m_Lives;
	}

	public void gameOver() {
		updateHighscore();
		setRunning(false);
		updateObs();
	}

	public void loseLife() {
		if (m_Lives != 0) {
			--m_Lives;
		} else {
			gameOver();
		}
	}

	private void updateHighscore() {
		int scoreToInsert = m_Score;

		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("src/fr/imac/wallbreaker/levels/highscore.xml"));

			doc.getDocumentElement().normalize();

			NodeList scoreList = doc.getElementsByTagName("score");

			for (int i = 0; i < 3; ++i) {
				Node currentScore = scoreList.item(i);

				if (currentScore.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentScore;
					NodeList textFNList = currentElement.getChildNodes();
					int score = Integer.parseInt(((Node) textFNList.item(0)).getNodeValue().trim());
					if (scoreToInsert > score) {
						textFNList.item(0).setNodeValue(Integer.toString(scoreToInsert));
						scoreToInsert = score;
					}
				}
			}
			//Ecriture du fichier
			writeXml(doc, "src/fr/imac/wallbreaker/levels/highscore.xml");
		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
		} catch (Throwable t) {
		}
	}

	public static void writeXml(Document document, String fichier) {
		try {
			// Création de la source DOM
			Source source = new DOMSource(document);

			// Création du fichier de sortie
			File file = new File(fichier);
			Result resultat = new StreamResult(fichier);

			// Configuration du transformer
			TransformerFactory fabrique = TransformerFactory.newInstance();
			Transformer transformer = fabrique.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

			// Transformation
			transformer.transform(source, resultat);
		} catch (Exception e) {
		}
	}
}
