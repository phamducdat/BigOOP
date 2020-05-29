package com.game.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;



public class BossBullet extends Bullet {
	
private Animation bossBulletForWard, bossBulletBack;
	
	public BossBullet(float x, float y, GameState GameState) {
		
		 super(x, y, 60, 30, 1.0f, 10, GameState);
		 bossBulletBack = DataLoader.getInstance().getAnimation("boss_bullet");
		 bossBulletForWard = DataLoader.getInstance().getAnimation("boss_bullet");
		 bossBulletForWard.flipAllImage();
	}
	
	@Override
	public void draw(Graphics2D g2) {
	    // TODO Auto-generated method stub
        if(getSpeedX() > 0){          
            bossBulletBack.Update(System.nanoTime());
            bossBulletBack.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
        }else{
            bossBulletForWard.Update(System.nanoTime());
            bossBulletForWard.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
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
