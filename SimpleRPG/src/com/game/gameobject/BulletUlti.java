package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class BulletUlti extends Bullet {
	private Animation bossBulletForWard, bossBulletBack;

	public BulletUlti(float posX, float posY, GameState gameState) {
		super(posX, posY, 60, 30, 1.0f, 10, gameState);
		 bossBulletBack = DataLoader.getInstance().getAnimation("boss_bullet");
		 bossBulletForWard = DataLoader.getInstance().getAnimation("boss_bullet");
		 bossBulletForWard.flipAllImages();
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		return getBoundForCollisionWithMap();
	}

	@Override
	public void draw(Graphics g) {
		 if(getSpeedX() > 0){          
	            bossBulletBack.Update(System.nanoTime());
	            bossBulletBack.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY());
	        }else{
	            bossBulletForWard.Update(System.nanoTime());
	            bossBulletForWard.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY());
	        }
	}
	
	@Override
	 public void Update() {
       super.Update();
   }

}
