package wallbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author nexus_21
 */
public class LevelUI extends JPanel implements Observer, MouseMotionListener {

    /**
     * linked level
     */
    private Level level;
    private Image bgdImg;
    private Image waterImg;

    /**
     * constructor level user interface
     * @param level 
     */
    public LevelUI(Level level) {
        this.level = level;
        this.setSize(600, 600);
        this.level.addObs(this);

        addMouseMotionListener(this);


        try {
            bgdImg = ImageIO.read(new File("src/img/fond.jpg"));
            waterImg = ImageIO.read(new File("src/img/water.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * update the level user interface
     */
    public void update() {
        repaint();
        //System.out.println("Level updated");
    }

    /**
     * paint method
     */
    public void paint(Graphics g) {
        super.paint(g);

        /* background display */

        g.drawImage(this.bgdImg, 0, 0, this.getWidth(), this.getHeight(), this);

        /* bricks display*/
        ArrayList<Brick> bricks = this.level.getBricks();
        for (Brick b : bricks) {
            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(),
                        (int) ((b.getX() - (b.width / 2.0f)) * PhysicWorld.scalePhysicWorldToRealWorld),
                        (int) ((6 - b.getY() - (b.height / 2.0f)) * PhysicWorld.scalePhysicWorldToRealWorld),
                        (int) (b.getWidth() * PhysicWorld.scalePhysicWorldToRealWorld),
                        (int) (b.getHeight() * PhysicWorld.scalePhysicWorldToRealWorld),
                        this);

            }
        }

        /* water display*/
        g.drawImage(this.waterImg, 0, this.getHeight() - 100, 600, 100, this);


        /* ball display */
        ArrayList<Ball> balls = this.level.getBalls();

        //g.setColor(Color.red);
        //g.drawOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());

        for (Ball ball : balls) {
            g.setColor(Color.yellow);
            g.fillOval((int) ((ball.getX() - ball.getRadius()) * PhysicWorld.scalePhysicWorldToRealWorld),
                    (int) ((6 - ball.getY() - ball.getRadius()) * PhysicWorld.scalePhysicWorldToRealWorld),
                    (int) (ball.getRadius() * 2.0f * PhysicWorld.scalePhysicWorldToRealWorld),
                    (int) (ball.getRadius() * 2.0f * PhysicWorld.scalePhysicWorldToRealWorld));
            g.setColor(Color.red);
            g.drawOval((int) ((ball.getX() - ball.getRadius()) * PhysicWorld.scalePhysicWorldToRealWorld),
                    (int) ((6 - ball.getY() - ball.getRadius()) * PhysicWorld.scalePhysicWorldToRealWorld),
                    (int) (ball.getRadius() * 2.0f * PhysicWorld.scalePhysicWorldToRealWorld),
                    (int) (ball.getRadius() * 2.0f * PhysicWorld.scalePhysicWorldToRealWorld));

        }


        /* paddle display */
        Paddle paddle = this.level.getPaddle();
        g.drawImage(paddle.getImage(), (int) ((paddle.getX() - paddle.getWidth() / 2.0f) * PhysicWorld.scalePhysicWorldToRealWorld), (int) ((6 - paddle.getY() - paddle.getHeight() / 2.0f) * PhysicWorld.scalePhysicWorldToRealWorld), (int) (paddle.getWidth() * PhysicWorld.scalePhysicWorldToRealWorld), (int) (paddle.getHeight() * PhysicWorld.scalePhysicWorldToRealWorld), this);
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        System.out.println("Mouse clicked");
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        this.level.getPaddle().setX(me.getX());
    }
}
