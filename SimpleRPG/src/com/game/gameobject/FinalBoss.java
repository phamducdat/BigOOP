package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Hashtable;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class FinalBoss extends HumanoidObject{
	
    private Animation idleforward, idleback;
    private Animation shootingforward, shootingback;
    private Animation slideforward, slideback;
    
    private long startTimeForAttacked;
    
    private Hashtable<String, Long> timeAttack = new Hashtable<String, Long>(); 
    private String[] attackType = new String[4];
    private int attackIndex = 0;
    private long lastAttackTime;
    
    public FinalBoss(float x, float y, GameState gameState) {
    	super(x, y, 110, 150, 0.1f, 100,gameState);
        idleback = DataLoader.getInstance().getAnimation("boss_idle");
        idleforward = DataLoader.getInstance().getAnimation("boss_idle");
        idleforward.flipAllImage();
        
        shootingback = DataLoader.getInstance().getAnimation("boss_shooting");
        shootingforward = DataLoader.getInstance().getAnimation("boss_shooting");
        shootingforward.flipAllImage();
        
        slideback = DataLoader.getInstance().getAnimation("boss_slide");
        slideforward = DataLoader.getInstance().getAnimation("boss_slide");
        slideforward.flipAllImage();
        
        setTimeForNoBehurt(500*1000000);
        setDamage(10);
        
        attackType[0] = "NONE";
        attackType[1] = "shooting";
        attackType[2] = "NONE";
        attackType[3] = "slide";
        
        timeAttack.put("NONE", new Long(2000));
        timeAttack.put("shooting", new Long(500));
        timeAttack.put("slide", new Long(5000));
        
    }

    public void Update(){
        super.Update();
        
        if(getGameState().megaMan.getPosX() > getPosX())
            setDirection(RIGHT_DIR);
        else setDirection(LEFT_DIR);
        
        if(startTimeForAttacked == 0)
            startTimeForAttacked = System.currentTimeMillis();
        else if(System.currentTimeMillis() - startTimeForAttacked > 300){
            attack();
            startTimeForAttacked = System.currentTimeMillis();
        }
        
        if(!attackType[attackIndex].equals("NONE")){
            if(attackType[attackIndex].equals("shooting")){
                
                Bullet bullet = new RocketBullet(getPosX(), getPosY() - 50, getGameState());
                if(getDirection() == LEFT_DIR) bullet.setSpeedX(-4);
                else bullet.setSpeedX(4);
                bullet.setTeamType(getTeamType());
                getGameState().bulletManager.addObject(bullet);
                
            }else if(attackType[attackIndex].equals("slide")){
                
                if(getGameState().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap())!=null)
                    setSpeedX(5);
                if(getGameState().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null)
                    setSpeedX(-5);
                
                
                setPosX(getPosX() + getSpeedX());
            }
        }else{
            // stop attack
            setSpeedX(0);
        }
        
    }
    
    @Override
    public void run() {}

    @Override
    public void jump() {
        setSpeedY(-5);
    }

    @Override
    public void dick() {}

    @Override
    public void standUp() {}

    @Override
    public void stopRun() {}

    @Override
    public void attack() {
    
        // only switch state attack
        
        if(System.currentTimeMillis() - lastAttackTime > timeAttack.get(attackType[attackIndex])){
            lastAttackTime = System.currentTimeMillis();
            
            attackIndex ++;
            if(attackIndex >= attackType.length) attackIndex = 0;
            
            if(attackType[attackIndex].equals("slide")){
                if(getPosX() < getGameState().megaMan.getPosX()) setSpeedX(5);
                else setSpeedX(-5);
            }
            
        }
    
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        if(attackType[attackIndex].equals("slide")){
            Rectangle rect = getBoundForCollisionWithMap();
            rect.y += 100;
            rect.height -= 100;
            return rect;
        }else
            return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
        
        if(getState() == CANTBEHURT && (System.nanoTime()/10000000)%2!=1)
        {
            System.out.println("Plash...");
        }else{
            
            if(attackType[attackIndex].equals("NONE")){
                if(getDirection() == RIGHT_DIR){
                    idleforward.Update(System.nanoTime());
                    idleforward.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                }else{
                    idleback.Update(System.nanoTime());
                    idleback.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                }
            }else if(attackType[attackIndex].equals("shooting")){
                
                if(getDirection() == RIGHT_DIR){
                    shootingforward.Update(System.nanoTime());
                    shootingforward.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                }else{
                    shootingback.Update(System.nanoTime());
                    shootingback.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                }
                
            }else if(attackType[attackIndex].equals("slide")){
                if(getSpeedX() > 0){
                    slideforward.Update(System.nanoTime());
                    slideforward.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY() + 50, g2);
                }else{
                    slideback.Update(System.nanoTime());
                    slideback.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY() + 50, g2);
                }
            }
        }
    }
    
}
