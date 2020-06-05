package com.game.gameobject;
//thieu yellow bullet nen phan attack t comment lai

import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

// Done

public class SmallRedGun extends SpecificObject{

	public static final float BULLETSPEED = 15.0f;
	
    private Animation forwardAnim, backAnim;
    
    private long startTimeToShoot;
    
    private float x1, x2;
    
    private AudioClip shooting = DataLoader.getInstance().getSound("towershooting");
    
    public SmallRedGun(float x, float y, GameState gameState) {
        super(x, y, 127, 89, 0, 100, gameState);
        backAnim = DataLoader.getInstance().getAnimation("smallredgun");
        forwardAnim = DataLoader.getInstance().getAnimation("smallredgun");
        forwardAnim.flipAllImage();
        startTimeToShoot = 0;
        setTimeForNoBehurt(300000000);
        
        x1 = x - 20;
        x2 = x + 20;
        setSpeedX(1);
    }

    @Override
    public void attack() {
    
        Bullet bullet = new YellowFlowerBullet(getPosX(), getPosY(), getGameState());
        shooting.play();
        
        bullet.setSpeedX(- BULLETSPEED);
        bullet.setSpeedY(BULLETSPEED);
        bullet.setTeamType(getTeamType());
        getGameState().bulletManager.addObject(bullet);
        
        bullet = new YellowFlowerBullet(getPosX(), getPosY(), getGameState());
        bullet.setSpeedX(BULLETSPEED);
        bullet.setSpeedY(BULLETSPEED);
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
         
        if(System.nanoTime() - startTimeToShoot > 1000 * 10000000 * 2.0){
            attack();
            System.out.println("Red Eye attack");
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
    	
    	super.draw(g2);
    	
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
    }
    
}
