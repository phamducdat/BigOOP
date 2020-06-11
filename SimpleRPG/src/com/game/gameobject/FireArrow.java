package com.game.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class FireArrow extends Bullet{

	private Animation forwardAnim, backwardAnim;
	
	public FireArrow(float x, float y, GameState gameState) {
		super(x, y, 180, 50, 0.1f, 20, gameState);
		forwardAnim = DataLoader.getInstance().getAnimation("firearrow");
		backwardAnim = DataLoader.getInstance().getAnimation("firearrow");
		backwardAnim.flipAllImage();
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		// TODO Auto-generated method stub
		return getBoundForCollisionWithMap();
	}

	@Override
	public void attack() {}
	
	public void Update() {
		super.Update();
	}
	
	public void draw(Graphics2D g2) {
		if(getSpeedX() > 0){          
            forwardAnim.Update(System.nanoTime());
            forwardAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
        }else{
            backwardAnim.Update(System.nanoTime());
            backwardAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
        }
	}

}
