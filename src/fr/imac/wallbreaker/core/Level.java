package fr.imac.wallbreaker.core;

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
     * Index of current level
     */
    private int idCurrentLevel;
    /**
     * Letter already found
     */
    private ArrayList<String> m_LetterFound;
    /**
     * is paddle big
     */
    private boolean m_BigPaddle;

    /**
     * constructor of level
     * @param idCurrentLevel
     */
    public Level(int idCurrentLevel) {

        this.idCurrentLevel = idCurrentLevel;
        this.word = "";
        this.m_LetterFound = new ArrayList<String>();
        this.bricks = new ArrayList<Brick>();
        this.listObs = new ArrayList<Observer>();
        this.balls = new ArrayList<Ball>();

        this.xmlLevelsLoader();
        this.xmlWordLoader();
        this.updateInsertWordInBrick();
    }

    /**
     * initialize the set of bricks with random bricks
     */
    public void initializeLevel() {
        /* generate balls */

        Ball ball = new Ball(2.65f, 3.0f, "src/fr/imac/wallbreaker/img/ball.png");
        this.addBall(ball);


        /* generate paddle */
        this.paddle = new Paddle(2.65f, 1.30f, 100f / PhysicWorld.scalePhysicWorldToRealWorld,
                10f / PhysicWorld.scalePhysicWorldToRealWorld, "src/fr/imac/wallbreaker/img/smallPaddle.png");
        this.m_BigPaddle = false;
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

    public void validateWord(String answer) {
        if (word.compareToIgnoreCase(answer) == 0) {
            System.out.println("You Win !");
            Game.getInstance().finishCurrentLvl();
        } else {
            System.out.println("You lose !");
        }
    }

    /**
     * add a ball
     * @param ball 
     */
    public void addBall(Ball ball) {
        this.balls.add(ball);
    }
    
    /**
	 *	Add Ball
	 */
	public void addBall() {
		Ball ball = new Ball(2.65f, 3.0f, "src/fr/imac/wallbreaker/img/ball.png");
		this.addBall(ball);
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

        for (Brick b : this.bricks) {
            if (b.destroyed) {
                PhysicWorld.getInstance().destroyBody(b.physicalBody);
            }
        }

        this.updateObs();
        if (balls.get(0).getY() < 1) {
            Game.getInstance().finishCurrentLvl();
        }

        if (balls.get(0).physicalBody.getLinearVelocity().lengthSquared() > 49) {
            balls.get(0).physicalBody.getLinearVelocity().normalize();
            balls.get(0).physicalBody.setLinearVelocity(balls.get(0).physicalBody.getLinearVelocity().mul(7));
        }
        //System.out.println(balls.get(0).physicalBody.getLinearVelocity().toString());

        this.updateCollision();
    }

    //TODO Fixer la taille des bricks dans Brick et pas avec un magique number
    private void xmlLevelsLoader() {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("src/fr/imac/wallbreaker/levels/level" + idCurrentLevel + ".xml"));


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

						
                        if((x>=0)&&(x<=9)&&(y>=0)&&(y<=9)){
                                NodeList textFNList = brickListElement.getChildNodes();

                                float xPosition = x * (60 / PhysicWorld.scalePhysicWorldToRealWorld) + (60 / PhysicWorld.scalePhysicWorldToRealWorld) / 2;
                                float yPosition = 6 - y * (30 / PhysicWorld.scalePhysicWorldToRealWorld) - (30 / PhysicWorld.scalePhysicWorldToRealWorld) / 2;
                                float width = 60 / PhysicWorld.scalePhysicWorldToRealWorld;
                                float height = 30 / PhysicWorld.scalePhysicWorldToRealWorld;

                                if (brickListElement.getTextContent().equalsIgnoreCase("n")) {
                                        this.bricks.add(new Brick(xPosition, yPosition, width, height, "src/fr/imac/wallbreaker/img/level" + idCurrentLevel + "/brick/brickNormal.png"));
                                } else if (brickListElement.getTextContent().equalsIgnoreCase("bo")) {
                                        this.bricks.add(new BonusBrick(xPosition, yPosition, width, height, "src/fr/imac/wallbreaker/img/level" + idCurrentLevel + "/brick/brickBonus.png"));
                                } else if (brickListElement.getTextContent().equalsIgnoreCase("ba")) {
                                        this.bricks.add(new BallBrick(xPosition, yPosition, width, height, "src/fr/imac/wallbreaker/img/level" + idCurrentLevel + "/brick/brickBall.png"));
                                } else if (brickListElement.getTextContent().equalsIgnoreCase("u")) {
                                        this.bricks.add(new UnbreakableBrick(xPosition, yPosition, width, height, "src/fr/imac/wallbreaker/img/level" + idCurrentLevel + "/brick/brickUnbreakable.png"));
                                } else if (brickListElement.getTextContent().equalsIgnoreCase("h")) {
                                        this.bricks.add(new HardBrick(xPosition, yPosition, width, height, "src/fr/imac/wallbreaker/img/level" + idCurrentLevel + "/brick/brickHard.png"));
                                } else if (brickListElement.getTextContent().equalsIgnoreCase("m")) {
								this.bricks.add(new MagicBrick(xPosition, yPosition, width, height, "src/fr/imac/wallbreaker/img/level" + idCurrentLevel + "/brick/brickBonus.png"));
							}
                        }

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
            Document doc = docBuilder.parse(new File("src/fr/imac/wallbreaker/levels/level" + idCurrentLevel + "dico.xml"));

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

    /**
     * Insert Letter in letter brick and put randomly in brick / Word and Brick must be created
     */
    private void updateInsertWordInBrick() {
        for (int i = 0; i < word.length(); ++i) {
            int index = (int) (Math.random() * bricks.size());

            Brick currentBrick = bricks.get(index);
            if ((currentBrick instanceof UnbreakableBrick)
                    || (currentBrick instanceof LetterBrick)
                    || (currentBrick instanceof HardBrick)
                    || (currentBrick.destroyed)) {
                --i;
            } else {
                String letterToInsert = word.substring(i, i + 1);
                Brick newBrick = new LetterBrick(currentBrick.getX(),
                        currentBrick.getY(),
                        currentBrick.width,
                        currentBrick.height,
                        "src/fr/imac/wallbreaker/img/level" + idCurrentLevel + "/brick/brickLetter.png",
                        letterToInsert);
                currentBrick.destroy();
                bricks.add(newBrick);
            }
        }
    }

    public void updateCollision() {
        //System.out.println(m_LetterFound);

        PhysicWorld physicWorld = PhysicWorld.getInstance();
        for (Contact c = physicWorld.getContactList(); c != null; c = c.getNext()) {
            if (c.isTouching()) {
                Fixture contactFixture = null;
                Ball ball;
                if (c.getFixtureA().getBody().getUserData() instanceof Ball) {
                    ball = (Ball) c.getFixtureA().getBody().getUserData();
                    contactFixture = c.getFixtureB();
                } else if (c.getFixtureB().getBody().getUserData() instanceof Ball) {
                    ball = (Ball) c.getFixtureB().getBody().getUserData();
                    contactFixture = c.getFixtureA();
                } else {
                    break;
                }

                Body body = contactFixture.getBody();
                if (body.getUserData() instanceof Brick) {
                    System.out.println("collisiooooon brick");
                    Brick b = (Brick) body.getUserData();
                    this.destroy(b);
                }
            }
        }

    }

    public void destroy(Brick b) {
        //Get score and add to current score
        Game.getInstance().addScore(b.getScore());

        //If Brick is a LetterBrick get character and add with all character already found
        if (b instanceof LetterBrick) {
            m_LetterFound.add(((LetterBrick) b).getLetter());
        }

        //If it's a ball brick, life up
        else if (b instanceof BallBrick) {
            Game.getInstance().addLife();
        }
        
        else if (b instanceof MagicBrick) {
            System.out.println("magiiiic");
            ((MagicBrick) b).doMagicThing();
        }

        //Finally destroy the brick
        b.destroy();
    }
    
  
	public int getCurrentLvlId(){

		return idCurrentLevel;
	}
	
	public void increasePaddle(){
		if(!m_BigPaddle){
			//Create new paddle
			Paddle newPaddle = new Paddle(this.paddle.getX(), this.paddle.getY(),2* 100f / PhysicWorld.scalePhysicWorldToRealWorld,
				2*10f / PhysicWorld.scalePhysicWorldToRealWorld, "src/fr/imac/wallbreaker/img/smallPaddle.png");
			
			PhysicWorld.getInstance().destroyBody(this.paddle.physicalBody);
			this.paddle = newPaddle;
		}
	}
}
