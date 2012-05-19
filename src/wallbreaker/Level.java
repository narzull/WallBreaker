package wallbreaker;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;
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
public class Level implements Observable {
	/**
	 * Game Reference
	 */
	Game m_Game;
    /**
     * word to find to end the level
     */
    private String word;
    /**
     * list of bricks
     */
    private ArrayList<Brick> bricks;
    /**
     * paler
     */
    private Paddle paddle;
	
    /**
     * list of observer
     */
    private ArrayList<Observer> listObs;

    /**
     * list of balls
     */
    private ArrayList<Ball> balls;
	
	/**
	 * Letter already found
	 */
	private String m_LetterFound;
	
	/**
	 * Index of current level
	 */
	private int idCurrentLevel;
	
    /**
     * constructor of level
     * @param idCurrentLevel
     */
    public Level(Game game, int idCurrentLevel) {
		m_Game = game;
		this.idCurrentLevel = idCurrentLevel;
        this.word = "";
		this.m_LetterFound = "";
        this.bricks = new ArrayList<Brick>();
        this.listObs = new ArrayList<Observer>();
        this.balls = new ArrayList<Ball>();
		
		
        this.xmlLevelsLoader();
        this.xmlWordLoader();
    }

    /**
     * initialize the set of bricks with random bricks
     */
    public void initializeLevel() {
        /* generate balls */

        Ball ball = new Ball(2.65f, 3.0f, "src/img/ball.png");
        this.addBall(ball);


        /* generate paddle */
        this.paddle = new Paddle(2.65f, 1.30f, 100f / PhysicWorld.scalePhysicWorldToRealWorld,
                10f / PhysicWorld.scalePhysicWorldToRealWorld, "src/img/smallPaddle.png");

        System.out.println("Level initialized");
    }

    /**
     * getter for bricks
     * @return list of bricks
     */
    public ArrayList<Brick> getBricks() {
        return this.bricks;
    }

    /**
     * getter for word
     * @return String
     */
    public String getWord() {
        return word;
    }

    /**
     * add a ball
     * @param ball 
     */
    public void addBall(Ball ball) {
        this.balls.add(ball);
    }

    /**
     * getter for balls
     * @return 
     */
    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * add observer o to the list of observer
     * @param o 
     */
    @Override
    public void addObs(Observer o) {
        this.listObs.add(o);

    }

    /**
     * delete observer o
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

    public void updatePosition() {
        float timeStep = 1.0f / 60.0f;
        int velocityIterations = 6;
        int positionIterations = 2;

        PhysicWorld.getInstance().step(timeStep, velocityIterations, positionIterations);
        
        this.updateObs();
        if(balls.get(0).getY() < 1)
                m_Game.finishCurrentLvl();
        
        this.checkCollision();
    }

    //TODO Fixer la taille des bricks dans Brick et pas avec un magique number
    private void xmlLevelsLoader() {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("src/levels/level"+idCurrentLevel+".xml"));


            doc.getDocumentElement().normalize();


            NodeList lignList = doc.getElementsByTagName("lign");

            for (int s = 0; s < lignList.getLength(); ++s) {
                Node currentLign = lignList.item(s);

                if (currentLign.getNodeType() == Node.ELEMENT_NODE) {
                    Element currentElement = (Element) currentLign;
                    int y = Integer.parseInt(currentElement.getAttribute("y"));
                    NodeList brickList = currentElement.getElementsByTagName("brick");

                    for (int i = 0; i < brickList.getLength(); ++i) {
                        Element brickListElement = (Element) brickList.item(i);
                        int x = Integer.parseInt(brickListElement.getAttribute("x"));

                        NodeList textFNList = brickListElement.getChildNodes();
						
						System.out.println(brickListElement.getTextContent());
						
						float xPosition = x * (60 / PhysicWorld.scalePhysicWorldToRealWorld) + (60 / PhysicWorld.scalePhysicWorldToRealWorld) / 2;
						float yPosition = 6 - y * (30 / PhysicWorld.scalePhysicWorldToRealWorld) - (30 / PhysicWorld.scalePhysicWorldToRealWorld) / 2;
						float width = 60 / PhysicWorld.scalePhysicWorldToRealWorld;
						float height = 30 / PhysicWorld.scalePhysicWorldToRealWorld;
						
						if(brickListElement.getTextContent().equalsIgnoreCase("n"))
							this.bricks.add(new Brick(xPosition, yPosition, width, height, "src/img/brickn.png"));
						
						else if(brickListElement.getTextContent().equalsIgnoreCase("bo"))
							this.bricks.add(new BonusBrick(xPosition, yPosition, width, height, "src/img/brickbo.png"));
						
						else if(brickListElement.getTextContent().equalsIgnoreCase("ba"))
							this.bricks.add(new BallBrick(xPosition, yPosition, width, height, "src/img/brickba.png"));
						
                    }
                }
            }
        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
        } catch (SAXException e) {
            Exception x = e.getException();
        } catch (Throwable t) {
        }
    }

    private void xmlWordLoader() {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("src/levels/level"+idCurrentLevel+"dico.xml"));

            doc.getDocumentElement().normalize();

            NodeList wordList = doc.getElementsByTagName("word");

            int randomIndex = (int) (Math.random() * wordList.getLength());


            Node currentWord = wordList.item(randomIndex);

            if (currentWord.getNodeType() == Node.ELEMENT_NODE) {
                Element currentElement = (Element) currentWord;

                NodeList textFNList = currentElement.getChildNodes();
                word = ((Node) textFNList.item(0)).getNodeValue().trim();
                System.out.println(word);
            }

        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());

        } catch (SAXException e) {
            Exception x = e.getException();
        } catch (Throwable t) {
        }
    }
    
    
    public void checkCollision()
    {
        PhysicWorld physicWorld = PhysicWorld.getInstance();
        for (Contact c = physicWorld.getContactList(); c != null; c = c.getNext()) 
        {
            if(!c.isTouching())
                break;
            
            Fixture contact_with = null;
            Ball ball;
            if(c.getFixtureA().getBody().getUserData() instanceof Ball) 
            {
            ball = (Ball)c.getFixtureA().getBody().getUserData();
            contact_with = c.getFixtureB();
            } 
            else if (c.getFixtureB().getBody().getUserData() instanceof Ball) 
            {
            ball = (Ball)c.getFixtureB().getBody().getUserData();
            contact_with = c.getFixtureA();
            } 
            else
                break;
            
            Body body = contact_with.getBody();
            if(body.getUserData() instanceof Brick) {
                Brick b = (Brick)body.getUserData();
                this.destroy(b);
            }
        }
    }
	
	 public void destroy(Brick b){
		//Get score and add to current score
		m_Game.addScore(b.getScore());
		
		//If Brick is a LetterBrick get character and add with all character already found
		if(b instanceof LetterBrick)
			m_LetterFound += ((LetterBrick)b).getLetter();
		
		//If it's a ball brick, life up
		if(b instanceof BallBrick)
			m_Game.addLife();
		
		//Finally destroy the brick
		b.destroy();
	}
}
