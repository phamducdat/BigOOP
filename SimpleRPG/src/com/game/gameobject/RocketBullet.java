package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class RocketBullet extends Bullet {
	
	private Animation forwardBulletAnimUp, forwardBulletAnimDown, forwardBulletAnim;
	private Animation backBulletAnimUp, backBulletAnimDown, backBulletAnim;
	
	private long startTimeForChangeSpeedY;
	
	public RocketBullet(float posX, float posY, float width, float height, float mass, int healthPoint, int manaPoint,
			GameState gameState) {
		super(posX, posY, 30, 30, 0.1f, 20, 10, gameState);
		
		backBulletAnimUp = DataLoader.getInstance().getAnimation("rocketUp");
		backBulletAnimDown = DataLoader.getInstance().getAnimation("rocketDown");
		backBulletAnim = DataLoader.getInstance().getAnimation("rocket");
		
		forwardBulletAnimUp = DataLoader.getInstance().getAnimation("rocketUp");
		forwardBulletAnimUp.flipAllImages();
		forwardBulletAnimDown = DataLoader.getInstance().getAnimation("rocketDown");
		forwardBulletAnimDown.flipAllImages();
		forwardBulletAnim = DataLoader.getInstance().getAnimation("rocket");
		forwardBulletAnim.flipAllImages();
		
		
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
			if(getSpeedY() > 0) {
				forwardBulletAnimDown.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() -getGameState().camera.getPosY()));	
			}else if(getSpeedY() < 0) {
				forwardBulletAnimUp.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
			}else 
				forwardBulletAnim.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
		}else {
			if(getSpeedY() > 0) {
				backBulletAnimDown.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
			}else if(getSpeedY() > 0) {
				backBulletAnimUp.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
			}else
				backBulletAnim.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
		}
		
	}
	
	private void changeSpeedY() {
		if(System.currentTimeMillis() % 3 == 0) {
			setSpeedY(getSpeedX());
		}else if(System.currentTimeMillis() % 3 == 1) {
			setSpeedY(-getSpeedX());
		}else {
			setSpeedY(0);
		}
	}
	
	@Override
	public void Update() {
		super.Update();
		
		if(System.nanoTime() - startTimeForChangeSpeedY > 500*1000000) {
            startTimeForChangeSpeedY = System.nanoTime();
            changeSpeedY();
		}
		
	}
	
	@Override
	public void attack() {}

}
