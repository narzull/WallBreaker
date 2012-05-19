/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

/**
 *
 * @author hekiat
 */
public class HardBrick extends Brick{
	int m_Life;

	public HardBrick(float x, float y, float width, float height, String imagePath) {
		super(x, y, width, height, imagePath);
		m_Life = 3;
	}
	
	@Override
	public void destroy(){
		m_Life--;
		if(m_Life == 0){
			super.destroy();
		}
	}
}
