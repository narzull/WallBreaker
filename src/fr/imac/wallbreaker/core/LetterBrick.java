/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imac.wallbreaker.core;

/**
 *
 * @author hekiat
 */
public class LetterBrick extends Brick {

    String m_Letter;

    public LetterBrick(float x, float y, float width, float height, String imagePath, String letter) {
        super(x, y, width, height, imagePath);
        m_Letter = letter;
    }

    public String getLetter() {
        return m_Letter;
    }
}
