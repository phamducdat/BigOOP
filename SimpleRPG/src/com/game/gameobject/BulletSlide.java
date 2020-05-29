package com.game.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;



public class BulletSlide extends Bullet {
	
	private Animation bulletForward, bulletBack;

	public BulletSlide(float x, float y, GameState GameState) {

		super(x, y, 60, 30, 1.0f, 10, GameState);
		bulletBack = DataLoader.getInstance().getAnimation("rocket");
		bulletForward = DataLoader.getInstance().getAnimation("rocket");
		bulletForward.flipAllImage();
	}

	@Override
	public void draw(Graphics2D g2) {
		if (getDirection() == RIGHT_DIR) {
			bulletBack.Update(System.nanoTime());
			bulletBack.draw((int) (getPosX() - getGameState().camera.getPosX()),
					(int) getPosY() - (int) getGameState().camera.getPosY(), g2);
		} else {
			bulletForward.Update(System.nanoTime());
			bulletForward.draw((int) (getPosX() - getGameState().camera.getPosX()),
					(int) getPosY() - (int) getGameState().camera.getPosY(), g2);
		}

	}

	@Override
	public void attack() {
		super.Update();
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		return getBoundForCollisionWithMap();
	}

}
