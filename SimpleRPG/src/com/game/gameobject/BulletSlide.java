package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class BulletSlide extends Bullet {
	private Animation bulletForward, bulletBack;

	public BulletSlide(float posX, float posY, GameState gameState) {
		super(posX, posY, 60, 30, 1.0f, 10, gameState);
		bulletBack = DataLoader.getInstance().getAnimation("rocket");
		bulletForward = DataLoader.getInstance().getAnimation("rocket");
		bulletForward.flipAllImages();
	}

	@Override
	public void attack() {
		super.Update();
		
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		return getBoundForCollisionWithMap();
	}

	@Override
	public void draw(Graphics g) {
		if (getDirection() == RIGHT_DIR) {
			bulletBack.Update(System.nanoTime());
			bulletBack.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
					(int) getPosY() - (int) getGameState().camera.getPosY());
		} else {
			bulletForward.Update(System.nanoTime());
			bulletForward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
					(int) getPosY() - (int) getGameState().camera.getPosY());
		}
		
	}

}
