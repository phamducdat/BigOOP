package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

// Done

public class RocketBullet extends Bullet {
	private Animation forwardBulletAnimUp, forwardBulletAnimDown, forwardBulletAnim;
    private Animation backBulletAnimUp, backBulletAnimDown, backBulletAnim;

    private long startTimeForChangeSpeedY;
    
    public RocketBullet(float x, float y, GameState gameWorld) {
        
            super(x, y, 30, 30, 1.0f, 10, gameWorld);
            
            backBulletAnimUp = DataLoader.getInstance().getAnimation("rocketUp");
            backBulletAnimDown = DataLoader.getInstance().getAnimation("rocketDown");
            backBulletAnim = DataLoader.getInstance().getAnimation("rocket");
            
            forwardBulletAnimUp = DataLoader.getInstance().getAnimation("rocketUp");
            forwardBulletAnimUp.flipAllImage();
            forwardBulletAnimDown = DataLoader.getInstance().getAnimation("rocketDown");
            forwardBulletAnimDown.flipAllImage();
            forwardBulletAnim = DataLoader.getInstance().getAnimation("rocket");
            forwardBulletAnim.flipAllImage();

    }
  
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
            // TODO Auto-generated method stub
            return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
            // TODO Auto-generated method stub
        if(getSpeedX() > 0){  
            if(getSpeedY() > 0){
                forwardBulletAnimDown.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
            }else if(getSpeedY() < 0){
                forwardBulletAnimUp.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
            }else
                forwardBulletAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
        }else{
            if(getSpeedY() > 0){
                backBulletAnimDown.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
            }else if(getSpeedY() < 0){
                backBulletAnimUp.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
            }else
                backBulletAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
        }
        //drawBoundForCollisionWithEnemy(g2);
    }

    private void changeSpeedY(){
        if(System.currentTimeMillis() % 3 == 0){
            setSpeedY(getSpeedX());
        }else if(System.currentTimeMillis() % 3 == 1){
            setSpeedY(-getSpeedX());
        }else {
            setSpeedY(0);
            
        }
    }
    
    @Override
    public void Update() {
            // TODO Auto-generated method stub
        super.Update();
        
        if(System.nanoTime() - startTimeForChangeSpeedY > 500*1000000){
            startTimeForChangeSpeedY = System.nanoTime();
            changeSpeedY();
        }
    }

    @Override
    public void attack() {}

}
