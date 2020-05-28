package com.game.gameobject;
//thieu  bullet nen phan attack t comment lai
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.gameinteface.Profile;
import com.game.gameinteface.Vulnerable;
import com.game.state.GameState;

import com.game.effect.Animation;
import com.game.effect.DataLoader;

// Done

public class DarkRaise extends SpecificObject implements Profile, Vulnerable{
    private Animation forwardAnim, backAnim;
    
    private long startTimeToShoot;
    private float x1, x2;
    
    public DarkRaise(float x, float y, GameState gameState) {
        super(x, y, 127, 89, 0, 100, gameState);
        backAnim = DataLoader.getInstance().getAnimation("darkraise");
        forwardAnim = DataLoader.getInstance().getAnimation("darkraise");
        forwardAnim.flipAllImage();
        startTimeToShoot = 0;
        setTimeForNoBehurt(300000000);
        
        x1 = x - 100;
        x2 = x + 100;
        setSpeedX(1);
        
        setDamage(10);
    }

    @Override
    public void attack() {
    
        float megaManX = getGameState().megaMan.getPosX();
        float megaManY = getGameState().megaMan.getPosY();
        
        float deltaX = megaManX - getPosX();
        float deltaY = megaManY - getPosY();
        
        float speed = 3;
        float a = Math.abs(deltaX/deltaY);
        
        float speedX = (float) Math.sqrt(speed*speed*a*a/(a*a + 1));
        float speedY = (float) Math.sqrt(speed*speed/(a*a + 1));
        
        
        
        Bullet bullet = new DarkRaiseBullet(getPosX(), getPosY(), getGameState());
        
        if(deltaX < 0)
            bullet.setSpeedX(-speedX);
        else bullet.setSpeedX(speedX);
        bullet.setSpeedY(speedY);
        bullet.setTeamType(getTeamType());
        getGameState().bulletManager.addObject(bullet);

    }

    
    public void Update(){
        super.Update();
        if(getPosX() < x1)
            setSpeedX(1);
        else if(getPosX() > x2)
            setSpeedX(-1);
        setPosX(getPosX() + getSpeedX());
        
        if(System.nanoTime() - startTimeToShoot > 1000*10000000*1.5){
            attack();
            startTimeToShoot = System.nanoTime();
        }
    }
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        rect.x += 20;
        rect.width -= 40;
        
        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        if(!isObjectOutOfCameraView()){
            if(getState() == CANTBEHURT && (System.nanoTime()/10000000)%2!=1){
                // plash...
            }else{
                if(getDirection() == LEFT_DIR){
                    backAnim.Update(System.nanoTime());
                    backAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), 
                            (int)(getPosY() - getGameState().camera.getPosY()), g2);
                }else{
                    forwardAnim.Update(System.nanoTime());
                    forwardAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), 
                            (int)(getPosY() - getGameState().camera.getPosY()), g2);
                }
            }
        }
        //drawBoundForCollisionWithEnemy(g2);
    }
    
}
