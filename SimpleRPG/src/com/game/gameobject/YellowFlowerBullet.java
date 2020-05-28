package com.game.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class YellowFlowerBullet extends Bullet {

    private Animation forwardBulletAnim, backBulletAnim;
    
    public YellowFlowerBullet(float x, float y, GameState gameState) {
            super(x, y, 30, 30, 1.0f, 10, gameState);
            forwardBulletAnim = DataLoader.getInstance().getAnimation("yellow_flower_bullet");
            backBulletAnim = DataLoader.getInstance().getAnimation("yellow_flower_bullet");
            backBulletAnim.flipAllImage();
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
            forwardBulletAnim.Update(System.nanoTime());
            forwardBulletAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
        }else{
            backBulletAnim.Update(System.nanoTime());
            backBulletAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
        }
        //drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void Update() {
            // TODO Auto-generated method stub
        super.Update();
    }

    @Override
    public void attack() {}

}
