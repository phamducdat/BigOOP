
package com.game.gameobject;

import com.game.state.GameState;
import com.game.effect.Animation;
import com.game.effect.DataLoader;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RedEyeDevil extends SpecificObject {

	public static final float BULLETSPEED = 12.0f;
	
    private Animation forwardAnim, backAnim;
    
    private long startTimeToShoot;
    
    private AudioClip shooting;
    
    public RedEyeDevil(float x, float y, GameState gameState) {
        super(x, y, 127, 89, 0, 100, gameState);
        backAnim = DataLoader.getInstance().getAnimation("redeye");
        forwardAnim = DataLoader.getInstance().getAnimation("redeye");
        forwardAnim.flipAllImage();
        startTimeToShoot = 0;
        setDamage(10);
        setTimeForCantBeHurt(300000000);
        shooting = DataLoader.getInstance().getSound("redeyeshooting");
    }

    @Override
    public void attack() {
    
        shooting.play();
        Bullet bullet = new RedEyeBullet(getPosX(), getPosY(), getGameState());
        if(getDirection() == LEFT_DIR) bullet.setSpeedX(- BULLETSPEED);
        else bullet.setSpeedX(BULLETSPEED);
        bullet.setTeamType(getTeamType());
        getGameState().bulletManager.addObject(bullet);
            
    }

    
    public void Update(){
        super.Update();
        if(System.nanoTime() - startTimeToShoot > 1000*10000000){
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
