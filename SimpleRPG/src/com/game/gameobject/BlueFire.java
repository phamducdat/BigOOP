package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class BlueFire extends Bullet {
	
	private Animation forwardBulletAnim, backBulletAnim;
	

	public BlueFire(float posX, float posY, GameState gameState) {
		super(posX, posY, 60, 30, 0.1f, 10, 0, gameState);
		forwardBulletAnim = DataLoader.getInstance().getAnimation("bluefire");
		backBulletAnim = DataLoader.getInstance().getAnimation("bluefire");
		backBulletAnim.flipAllImages();
	}
	
	
	@Override
	public void attack() {}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		return getBoundForCollisionWithMap();
	}

	@Override
	public void draw(Graphics g) {
		
		if(getSpeedX() > 0) {
			if(!forwardBulletAnim.isIgnoreFrame(0) && forwardBulletAnim.getCurrentFrame() == 3) {
				forwardBulletAnim.setIgnoreFrame(0);
				forwardBulletAnim.setIgnoreFrame(1);
				forwardBulletAnim.setIgnoreFrame(3);
			}
			forwardBulletAnim.Update(System.nanoTime());
			forwardBulletAnim.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
		}else {
			if(!backBulletAnim.isIgnoreFrame(0) && backBulletAnim.getCurrentFrame() == 3) {
				backBulletAnim.setIgnoreFrame(0);
				backBulletAnim.setIgnoreFrame(1);
				backBulletAnim.setIgnoreFrame(2);
			}
			backBulletAnim.Update(System.nanoTime());
			backBulletAnim.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()) );
		}
		
	}

	@Override
	public void Update() {
		
		if(forwardBulletAnim.isIgnoreFrame(0) || backBulletAnim.isIgnoreFrame(0))
			setPosX(getPosX() + getSpeedX());
		SpecificObject object = getGameState().specificObjectManager.getObjectCollideWithThisObject(this);
		if(object != null && object.getState() == ALIVE) {
			setState(DEATH);
			object.setHealthPoint(object.getHealthPoint() - getDamage());
			object.setState(BEHURT);
			
		}
		
	}

}
