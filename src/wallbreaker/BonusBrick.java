/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

/**
 *
 * @author hekiat
 */
public class BonusBrick extends Brick {

    private int m_BonusScore;

    public BonusBrick(float x, float y, float width, float height, String imagePath) {
        super(x, y, width, height, imagePath);
        m_BonusScore = 10;
    }

    @Override
    public int getScore() {
        return m_BonusScore + super.getScore();
    }
}
