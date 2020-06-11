package com.game.gameobject;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;
import com.game.userinterface.GameFrame;

// Done

public class Hero extends HumanoidObject{

	public static final float BULLET_ACCELARATION = 20.0f;
	public static final float BULLET_SPEED = 3.0f;
    public static final float RUNSPEED = 10.0f;
    public static final float JUMPSPEED = 15.0f;
    public static final float MASS = 0.4f;
    public static final int MAXHP = 100;
    public static final int MAX_MANA_POINT = 1000;
    public static final float DASHSPEED = 40.0f;
    public static final double SMITERANGE = 300;
    
    private Animation runForwardAnim, runBackAnim, runShootingForwarAnim, runShootingBackAnim;
    private Animation idleForwardAnim, idleBackAnim, idleShootingForwardAnim, idleShootingBackAnim;
    private Animation kneelForwardAnim, kneelBackAnim;
    private Animation flyForwardAnim, flyBackAnim, flyShootingForwardAnim, flyShootingBackAnim;
    private Animation landingForwardAnim, landingBackAnim;
    
    private Animation climWallForward, climWallBack;
    
    private Animation smiteAnim;
    
    private long lastShootingTime;
    private boolean isShooting = false;
    
    private long beginTimeToDash;
    private boolean isDashing = false;
    
    public boolean smiteFLag = false;
    
    private AudioClip hurtingSound;
    private AudioClip smiteSound;
    private AudioClip shooting1, shooting2;
    private boolean shootFlag; 
    
    public Hero(float x, float y, GameState gameState) {
        super(x, y, 70, 90, MASS, MAXHP, gameState);
        
        shooting1 = DataLoader.getInstance().getSound("lucianshooting1");
        shooting2 = DataLoader.getInstance().getSound("lucianshooting2");
        hurtingSound = DataLoader.getInstance().getSound("galiohurt");
        smiteSound = DataLoader.getInstance().getSound("smitesound");
        
        setTeamType(HERO_TEAM);

        shootFlag = false;
        setMana(MAX_MANA_POINT);
        
        setTimeForCantBeHurt(2000*1000000);
        
        smiteAnim = DataLoader.getInstance().getAnimation("smite");
        
        runForwardAnim = DataLoader.getInstance().getAnimation("run");
        runBackAnim = DataLoader.getInstance().getAnimation("run");
        runBackAnim.flipAllImage();   
        
        idleForwardAnim = DataLoader.getInstance().getAnimation("idle");
        idleBackAnim = DataLoader.getInstance().getAnimation("idle");
        idleBackAnim.flipAllImage();
        
        kneelForwardAnim = DataLoader.getInstance().getAnimation("kneel");
        kneelBackAnim = DataLoader.getInstance().getAnimation("kneel");
        kneelBackAnim.flipAllImage();
        
        flyForwardAnim = DataLoader.getInstance().getAnimation("flyingup");
        flyForwardAnim.setIsRepeated(false);
        flyBackAnim = DataLoader.getInstance().getAnimation("flyingup");
        flyBackAnim.setIsRepeated(false);
        flyBackAnim.flipAllImage();
        
        landingForwardAnim = DataLoader.getInstance().getAnimation("landing");
        landingBackAnim = DataLoader.getInstance().getAnimation("landing");
        landingBackAnim.flipAllImage();
        
        climWallBack = DataLoader.getInstance().getAnimation("clim_wall");
        climWallForward = DataLoader.getInstance().getAnimation("clim_wall");
        climWallForward.flipAllImage();
        
        beHurtForward = DataLoader.getInstance().getAnimation("hurting");
        beHurtBackward = DataLoader.getInstance().getAnimation("hurting");
        beHurtBackward.flipAllImage();
        
        idleShootingForwardAnim = DataLoader.getInstance().getAnimation("idleshoot");
        idleShootingBackAnim = DataLoader.getInstance().getAnimation("idleshoot");
        idleShootingBackAnim.flipAllImage();
        
        runShootingForwarAnim = DataLoader.getInstance().getAnimation("runshoot");
        runShootingBackAnim = DataLoader.getInstance().getAnimation("runshoot");
        runShootingBackAnim.flipAllImage();
        
        flyShootingForwardAnim = DataLoader.getInstance().getAnimation("flyingupshoot");
        flyShootingBackAnim = DataLoader.getInstance().getAnimation("flyingupshoot");
        flyShootingBackAnim.flipAllImage();
        
    }

    @Override
    public void Update() {

    	
    	 super.Update();
    	 
    	// Khi di ra khoi map
         int[][] mapClone = getGameState().physicalMap.getPhys_map();
         int tileSize = getGameState().physicalMap.getTileSize();
         if(getPosX() < 0 || getPosX() > mapClone[0].length * tileSize || getPosY() < 0 || getPosY() > mapClone.length * tileSize ) {
         	setState(DEATH);
         	setPosX(400);
         	setPosY(400);
         }
         
         if(getSpeedX() != 0) setMana(getMana() + 1);
         
         if(isDashing) {
          	if(System.nanoTime() - beginTimeToDash > 500 * 1000000) {	
          		isDashing = false;
          		setSpeedX(0);
          		setSpeedY(DASHSPEED * (float) 1.5);		
          	}
          
         }
         
         if(isShooting){
             if(System.nanoTime() - lastShootingTime > 500 * 1000000){
                 isShooting = false;
             }
         }
         
         if(getIsLanding()){
             landingBackAnim.Update(System.nanoTime());
             if(landingBackAnim.isLastFrame()) {
                 setIsLanding(false);
                 landingBackAnim.reset();
                 runForwardAnim.reset();
                 runBackAnim.reset();
             }
         }
             
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        Rectangle rect = getBoundForCollisionWithMap();
        
        if(getIsKneeling()){
            rect.x = (int) getPosX() - 22;
            rect.y = (int) getPosY() - 20;
            rect.width = 44;
            rect.height = 65;
        }else{
            rect.x = (int) getPosX() - 22;
            rect.y = (int) getPosY() - 40;
            rect.width = 44;
            rect.height = 80;
        }
        
        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
    	
        switch(getState()){
        
            case ALIVE:
            case CANTBEHURT:
                if(getState() == CANTBEHURT && (System.nanoTime()/10000000)%2!=1)	
                {
                    System.out.println("Plash...");
                }else{
                	
                    if(getIsLanding()){

                        if(getDirection() == RIGHT_DIR){
                            landingForwardAnim.setCurrentFrame(landingBackAnim.getCurrentFrame());
                            landingForwardAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameState().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - landingForwardAnim.getCurrentImage().getHeight()/2),
                                    g2);
                        }else{
                            landingBackAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameState().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - landingBackAnim.getCurrentImage().getHeight()/2),
                                    g2);
                        }

                    }else if(getIsJumping()){

                        if(getDirection() == RIGHT_DIR){
                            flyForwardAnim.Update(System.nanoTime());
                            if(isShooting){
                                flyShootingForwardAnim.setCurrentFrame(flyForwardAnim.getCurrentFrame());
                                flyShootingForwardAnim.draw((int) (getPosX() - getGameState().camera.getPosX()) + 10, (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                            }else
                                flyForwardAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                        }else{
                            flyBackAnim.Update(System.nanoTime());
                            if(isShooting){
                                flyShootingBackAnim.setCurrentFrame(flyBackAnim.getCurrentFrame());
                                flyShootingBackAnim.draw((int) (getPosX() - getGameState().camera.getPosX()) - 10, (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                            }else
                            flyBackAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                        }

                    }else if(getIsKneeling()){

                        if(getDirection() == RIGHT_DIR){
                        	kneelForwardAnim.Update(System.nanoTime());
                        	kneelForwardAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameState().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - kneelForwardAnim.getCurrentImage().getHeight()/2),
                                    g2);
                        }else{
                            kneelBackAnim.Update(System.nanoTime());
                            kneelBackAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameState().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - kneelBackAnim.getCurrentImage().getHeight()/2),
                                    g2);
                        }

                    }else{
                        if(getSpeedX() > 0){
                            runForwardAnim.Update(System.nanoTime());
                            if(isShooting){
                                runShootingForwarAnim.setCurrentFrame(runForwardAnim.getCurrentFrame() - 1);
                                runShootingForwarAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                            }else
                                runForwardAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                            if(runForwardAnim.getCurrentFrame() == 1) runForwardAnim.setIgnoreFrame(0);
                        }else if(getSpeedX() < 0){
                            runBackAnim.Update(System.nanoTime());
                            if(isShooting){
                                runShootingBackAnim.setCurrentFrame(runBackAnim.getCurrentFrame() - 1);
                                runShootingBackAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                            }else
                                runBackAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                            if(runBackAnim.getCurrentFrame() == 1) runBackAnim.setIgnoreFrame(0);
                        }else{
                            if(getDirection() == RIGHT_DIR){
                                if(isShooting){
                                    idleShootingForwardAnim.Update(System.nanoTime());
                                    idleShootingForwardAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                                }else{
                                    idleForwardAnim.Update(System.nanoTime());
                                    idleForwardAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                                }
                            }else{
                                if(isShooting){
                                    idleShootingBackAnim.Update(System.nanoTime());
                                    idleShootingBackAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                                }else{
                                    idleBackAnim.Update(System.nanoTime());
                                    idleBackAnim.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                                }
                            }
                        }            
                    }
                }
                
                break;
            
            case BEHURT:
                if(getDirection() == RIGHT_DIR){
                    beHurtForward.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                }else{
                    beHurtBackward.setCurrentFrame(beHurtForward.getCurrentFrame());
                    beHurtBackward.draw((int) (getPosX() - getGameState().camera.getPosX()), (int) getPosY() - (int) getGameState().camera.getPosY(), g2);
                }
                break;
             
            case FEY:
                
                break;

        }
        
        if(smiteFLag == true) {
        	
        	SpecificObject object = getGameState().specificObjectManager.getEnemyInRange(this, SMITERANGE);
        	
        	if(object != null) {
        		smiteAnim.Update(System.nanoTime());
            	smiteAnim.setIsRepeated(false);
            	smiteAnim.draw((int) (object.getPosX() - getGameState().camera.getPosX()), (int) (object.getPosY() - getGameState().camera.getPosY()), g2);
            	if(smiteAnim.isLastFrame()) {
            		smiteFLag = false;
            		smiteAnim.setCurrentFrame(0);
            	}
        		
        	}
        }
    }

    public void dash() {
    	if(!getIsKneeling() && !getIsLanding()) {
    		if(getMana() >= 200) {
    			if(getDirection() == RIGHT_DIR)
        			setSpeedX(DASHSPEED);
        		else setSpeedX(- DASHSPEED);
    			setMana(getMana() - 200);
    			beginTimeToDash = System.nanoTime();
    			isDashing = true;
    		}else {
    			System.out.println("Mana is not enough");
    		}
    		
    	}
    }
    
    public void smite() {
    	if(getMana() >= 500) {
    		
    		SpecificObject object = getGameState().specificObjectManager.getEnemyInRange(this, SMITERANGE);
    		if(object != null) {
    			smiteFLag = true;
    			object.beHurt(50);
    			object.setState(BEHURT);
    			
    			this.heal(20);
    			smiteSound.play();
    			setMana(getMana() - 500);
    		}
    	}
    }
    
    @Override
    public void run() {
    	if(!getIsKneeling()) {
    		if(getDirection() == LEFT_DIR)
                setSpeedX(- RUNSPEED);
            else setSpeedX(RUNSPEED);
    	}
    }

    @Override
    public void jump() {

        if(!getIsJumping()){
            setIsJumping(true);
            setSpeedY(- JUMPSPEED);           
            flyBackAnim.reset();
            flyForwardAnim.reset();
        }
        // de leo tuong
        else{
            Rectangle rectRightWall = getBoundForCollisionWithMap();
            rectRightWall.x += 1;
            Rectangle rectLeftWall = getBoundForCollisionWithMap();
            rectLeftWall.x -= 1;
            
            if(getGameState().physicalMap.haveCollisionWithRightWall(rectRightWall)!=null && getSpeedX() > 0){
                setSpeedY(- JUMPSPEED);
                flyBackAnim.reset();
                flyForwardAnim.reset();
            }else if(getGameState().physicalMap.haveCollisionWithLeftWall(rectLeftWall)!=null && getSpeedX() < 0){
                setSpeedY(- JUMPSPEED);
                flyBackAnim.reset();
                flyForwardAnim.reset();
            }
                
        }
    }

    @Override
	public void kneel() {
		if(!getIsJumping())
            setIsKneeling(true);
            if(getSpeedX() != 0) setSpeedX(0);
		
	}

    @Override
    public void standUp() {
    	setIsKneeling(false);
        idleForwardAnim.reset();
        idleBackAnim.reset();
        kneelForwardAnim.reset();
        kneelBackAnim.reset();
    }

    @Override
    public void stopRun() {
        setSpeedX(0);
        runForwardAnim.reset();
        runBackAnim.reset();
        runForwardAnim.unIgnoreFrame(0);
        runBackAnim.unIgnoreFrame(0);
    }

    @Override
    public void attack() {
        
    	// Dung trong truong hop ban dan nhanh
        if(!isShooting && !getIsKneeling()){
            if(shootFlag) {
            	System.out.println("Shoot 1");
            	shooting1.play();
            	shootFlag = false;
            }else {
            	System.out.println("Shoot 2");
            	shooting2.play();
            	shootFlag = true;
            	
            	
            	
            }
            
            BlueFire bullet = new BlueFire(getPosX(), getPosY(), getGameState());
            if(getDirection() == LEFT_DIR) {
                bullet.setSpeedX(- BULLET_SPEED);
                bullet.setAccelaration(- BULLET_ACCELARATION);
                bullet.setPosX(bullet.getPosX() - 40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                    bullet.setPosX(bullet.getPosX() - 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            }else {
                bullet.setSpeedX(BULLET_SPEED);
                bullet.setAccelaration(BULLET_ACCELARATION);
                bullet.setPosX(bullet.getPosX() + 40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                    bullet.setPosX(bullet.getPosX() + 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            }
            if(getIsJumping())
                bullet.setPosY(bullet.getPosY() - 20);
            
            bullet.setTeamType(getTeamType());
            getGameState().bulletManager.addObject(bullet);
            
            lastShootingTime = System.nanoTime();
            isShooting = true;
            
        }
    
    }
    
    public void attackW() {
    	if(getMana() >= 200) {
    		Susano susano;
        	if(getDirection() == LEFT_DIR) {
        		susano = new Susano(getPosX() - 40, getPosY() - 40, getGameState());
        		susano.setDirection(LEFT_DIR);
        	}else {
        		susano = new Susano(getPosX() + 40, getPosY() - 40, getGameState());
        		susano.setDirection(RIGHT_DIR);
        	}
        	
            
            susano.setTeamType(getTeamType());
            getGameState().specificObjectManager.addObject(susano);
            
            lastShootingTime = System.nanoTime();
            isShooting = true;
            
            setMana(getMana() - 200); 
    	}
    	
    }
   
    @Override
    public void hurtingCallback(){
        System.out.println("Call back hurting");
        hurtingSound.play();
    }

    public void heal(int blood) {
    	if(getBlood() + blood >= MAXHP) setBlood(MAXHP);
    	else setBlood(blood + getBlood());
    }
	

}
