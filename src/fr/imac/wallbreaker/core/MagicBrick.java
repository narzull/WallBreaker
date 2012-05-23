/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imac.wallbreaker.core;

/**
 *
 * @author hekiat
 */
public class MagicBrick extends Brick {

	public MagicBrick(float x, float y, float width, float height, String imagePath) {
		super(x, y, width, height, imagePath);
	}

	public void doMagicThing() {
		int random = (int) ((Math.random() * 100) % 2);

		if (random == 0) {
			increasePaddle();
		} else {
			splitBall();
		}
	}

	private void splitBall() {
		Game.getInstance().getLevel().addBall();
	}

	private void increasePaddle() {
		Game.getInstance().getLevel().increasePaddle();
	}
}
