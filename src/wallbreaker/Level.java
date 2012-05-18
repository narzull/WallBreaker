package wallbreaker;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
     * number of rows 
     */
    private int nbBricksXMax;
    /**
     * number of lines
     */
    private int nbBricksYMax;
    /**
     * list of balls
     */
    private ArrayList<Ball> balls;

    /**
     * constructor of level
     * @param word 
     */
    public Level(String word) {
        this.word = word;
        this.bricks = new ArrayList<Brick>();
        this.listObs = new ArrayList<Observer>();

        this.nbBricksXMax = 8;
        this.nbBricksYMax = 4;

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
        //PhysicWorld.getInstance().clearForces();
        this.updateObs();

    }

    //TODO Fixer la taille des bricks dans Brick et pas avec un magique number
    private void xmlLevelsLoader() {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("src/levels/level1.xml"));


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
                        this.bricks.add(new Brick(x * (60 / PhysicWorld.scalePhysicWorldToRealWorld) + (60 / PhysicWorld.scalePhysicWorldToRealWorld) / 2,
                                6 - y * (30 / PhysicWorld.scalePhysicWorldToRealWorld) - (30 / PhysicWorld.scalePhysicWorldToRealWorld) / 2,
                                60 / PhysicWorld.scalePhysicWorldToRealWorld,
                                30 / PhysicWorld.scalePhysicWorldToRealWorld, "src/img/brick1.png"));
                    }
                }


            }//end of for loop with s var


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
            Document doc = docBuilder.parse(new File("src/levels/level1dico.xml"));

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
}
