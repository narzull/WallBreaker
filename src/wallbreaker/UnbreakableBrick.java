/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

/**
 *
 * @author hekiat
 */
public class UnbreakableBrick extends Brick{

	public UnbreakableBrick(float x, float y, float width, float height, String imagePath) {
		super(x, y, width, height, imagePath);
	}
	
	@Override
	public void destroy(){
	}
}
