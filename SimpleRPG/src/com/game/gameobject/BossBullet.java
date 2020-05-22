package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;



public class BossBullet extends Bullet {
private Animation bossBulletForWard, bossBulletBack;
	
	public BossBullet(float x, float y, GameState gameWorld) {
		
		 super(x, y, 60, 30, 1.0f, 10, 1, gameWorld);
		 bossBulletBack = DataLoader.getInstance().getAnimation("boss_bullet");
		 bossBulletForWard = DataLoader.getInstance().getAnimation("boss_bullet");
		 bossBulletForWard.flipAllImages();
	}
	
	@Override
	public void draw(Graphics g) {
	    // TODO Auto-generated method stub
        if(getSpeedX() > 0){          
            bossBulletBack.Update(System.nanoTime());
            bossBulletBack.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY());
        }else{
            bossBulletForWard.Update(System.nanoTime());
            bossBulletForWard.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY());
        }
        //drawBoundForCollisionWithEnemy(g2);
		
	}
	@Override
	public void attack() {
		
	}
	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		
		return getBoundForCollisionWithMap();
	}

	@Override
	 public void Update() {
        super.Update();
    }

	

}
