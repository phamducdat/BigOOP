package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class RedEyeBullet extends Bullet {

private Animation forward, backward;
	
	public RedEyeBullet(float posX, float posY, GameState gameState) {
		super(posX, posY, 30, 30, 1.0f, 10, 0, gameState);
		forward = DataLoader.getInstance().getAnimation("redeyebullet");
		backward = DataLoader.getInstance().getAnimation("redeyebullet");
		backward.flipAllImages();
	}

	@Override
	public void attack() {}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		// TODO Auto-generated method stub
		return getBoundForCollisionWithMap();
	}

	@Override
	public void draw(Graphics g) {
		if(getSpeedX() >= 0) {
			forward.Update(System.nanoTime());
			forward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
		}else {
			backward.Update(System.nanoTime());
			backward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
		}
		
	}

	@Override
	public void Update() {
		super.Update();
	}
	
	
}
