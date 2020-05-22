package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class RobotRBullet extends Bullet {
	
	private Animation forwardBulletAnim, backBulletAnim;
	
	public RobotRBullet(float posX, float posY,GameState gameState) {
		super(posX, posY, 60, 30, 0.1f, 15, 0, gameState);
		forwardBulletAnim = DataLoader.getInstance().getAnimation("robotRbullet");
		backBulletAnim = DataLoader.getInstance().getAnimation("robotbullet");
		backBulletAnim.flipAllImages();
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(getSpeedX() > 0) {
			forwardBulletAnim.Update(System.nanoTime());
			forwardBulletAnim.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
		}else {
			backBulletAnim.Update(System.nanoTime());
			backBulletAnim.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
		}
		
	}
	
	@Override
	public void Update() {
		
	}
	
	@Override
	public void attack() {}

	

}
