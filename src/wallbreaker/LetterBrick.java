/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

/**
 *
 * @author hekiat
 */
public class LetterBrick extends Brick{
	char m_Letter;
	
	public LetterBrick(float x, float y, float width, float height, String imagePath, char letter){
		super(x, y, width, height, imagePath);
		m_Letter = letter;
	}
	
	public char getLetter(){
		return m_Letter;
	}
}
