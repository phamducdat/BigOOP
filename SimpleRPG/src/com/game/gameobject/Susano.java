package com.game.gameobject;

import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class Susano extends SpecificObject{
	
	public static final float BULLETSPEED = 15.0f;
	
    private Animation forwardAnim, backAnim;
    
    private long startTimeToShoot;
    
    private AudioClip shooting;
    
    public Susano(float x, float y, GameState gameState) {
        super(x, y, 250, 200, 0.5f, 100, gameState);
        forwardAnim = DataLoader.getInstance().getAnimation("susano");
        backAnim = DataLoader.getInstance().getAnimation("susano");
        backAnim.flipAllImage();
        startTimeToShoot = 0;
        setDamage(20);
        setTimeForCantBeHurt(300000000);
        shooting = DataLoader.getInstance().getSound("arrowsound");
    }

    @Override
    public void attack() {
    
        shooting.play();
        Bullet bullet = new FireArrow(getPosX(), getPosY(), getGameState());
        if(getDirection() == LEFT_DIR) bullet.setSpeedX(- BULLETSPEED);
        else bullet.setSpeedX(BULLETSPEED);
        bullet.setTeamType(getTeamType());
        getGameState().bulletManager.addObject(bullet);
            
    }

    @Override
    public void Update(){
    	super.Update();
        if(forwardAnim.isLastFrame() || backAnim.isLastFrame()){
            attack();
            System.out.println("Susano attack");
            startTimeToShoot = System.nanoTime();
        }
    }
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
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
