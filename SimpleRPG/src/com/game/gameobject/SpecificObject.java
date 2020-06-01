package com.game.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.gameinteface.Profile;
import com.game.gameinteface.Vulnerable;
import com.game.state.GameState;

// Done

public abstract class SpecificObject extends GameObject implements Profile, Vulnerable  {

	private int state = ALIVE;
	private int teamType;
	private int direction;
	
	private float width;
	private float height;
	private float speedX;		// Van toc theo phuong ngang
	private float speedY;		// Van toc theo phuong thang dung
	private float mass; 		// De tao gia toc huong tam Trai Dat
	
	private int blood; 			// Mau
	private int mana;			// Nang luong
	private int damage;
	
	private long beginTimeToNoBeHurt;
	private long timeForNoBeHurt;
	
	protected Animation beHurtForward, beHurtBackward;;
	
	public SpecificObject(float x, float y, float width, float height, float mass, int blood, GameState gameState){

        // posX and posY are the middle coordinate of the object
        super(x, y, gameState);
        setWidth(width);
        setHeight(height);
        setMass(mass);
        setBlood(blood);
        
        direction = RIGHT_DIR;

    }
    
    public void setTimeForNoBehurt(long time){
        timeForNoBeHurt = time;
    }
    
    public long getTimeForNoBeHurt(){
        return timeForNoBeHurt;
    }
    
    public void setState(int state){
        this.state = state;
    }
    
    public int getState(){
        return state;
    }
    
    public void setDamage(int damage){
            this.damage = damage;
    }

    public int getDamage(){
            return damage;
    }

    
    public void setTeamType(int team){
        teamType = team;
    }
    
    public int getTeamType(){
        return teamType;
    }
    
    public void setMass(float mass){
        this.mass = mass;
    }

    public float getMass(){
            return mass;
    }

    public void setSpeedX(float speedX){
        this.speedX = speedX;
    }

    public float getSpeedX(){
        return speedX;
    }

    public void setSpeedY(float speedY){
        this.speedY = speedY;
    }

    public float getSpeedY(){
        return speedY;
    }

    public void setBlood(int blood){
        if(blood>=0)
                this.blood = blood;
        else this.blood = 0;
    }

    public int getBlood(){
        return blood;
    }

    public void setWidth(float width){
        this.width = width;
    }

    public float getWidth(){
        return width;
    }

    public void setHeight(float height){
        this.height = height;
    }

    public float getHeight(){
        return height;
    }
    
    public void setDirection(int dir){
        direction = dir;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public abstract void attack();
    
    
    public boolean isObjectOutOfCameraView(){
        if(getPosX() - getGameState().camera.getPosX() > getGameState().camera.getWidthView() ||
                getPosX() - getGameState().camera.getPosX() < -50
            ||getPosY() - getGameState().camera.getPosY() > getGameState().camera.getHeightView()
                    ||getPosY() - getGameState().camera.getPosY() < -50)
            return true;
        else return false;
    }
    
    public Rectangle getBoundForCollisionWithMap(){
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth()/2));
        bound.y = (int) (getPosY() - (getHeight()/2));
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    public void beHurt(int damgeEat){
        setBlood(getBlood() - damgeEat);
        state = BEHURT;
        hurtingCallback();
    }

    @Override
    public void Update(){
        switch(state){
            case ALIVE:
                
                // note: SET DAMAGE FOR OBJECT NO DAMAGE
                SpecificObject object = getGameState().specificObjectManager.getCollisionWidthEnemyObject(this);
                if(object!=null){
                    
                    
                    if(object.getDamage() > 0){

                        // switch state to fey if object die
                        
                        
                        System.out.println("eat damage.... from collision with enemy........ "+object.getDamage());
                        beHurt(object.getDamage());
                    }
                    
                }
                
                
                
                break;
                
            case BEHURT:
                if(beHurtBackward == null){
                    state = CANTBEHURT;
                    beginTimeToNoBeHurt = System.nanoTime();
                    if(getBlood() == 0)
                            state = FEY;
                    
                } else {
                    beHurtForward.Update(System.nanoTime());
                    if(beHurtForward.isLastFrame()){
                    	beHurtForward.reset();
                        state = CANTBEHURT;
                        if(getBlood() == 0)
                            state = FEY;
                        beginTimeToNoBeHurt = System.nanoTime();
                    }
                }
                
                break;
                
            case FEY:
                
                state = DEATH;
                
                break;
            
            case DEATH:
                
                
                break;
                
            case CANTBEHURT:
                System.out.println("state = nobehurt");
                if(System.nanoTime() - beginTimeToNoBeHurt > timeForNoBeHurt)
                    state = ALIVE;
                break;
        }
        
    }

    public void drawBoundForCollisionWithMap(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithMap();
        g2.setColor(Color.BLUE);
        g2.drawRect(rect.x - (int) getGameState().camera.getPosX(), rect.y - (int) getGameState().camera.getPosY(), rect.width, rect.height);
    }

    public void drawBoundForCollisionWithEnemy(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithEnemy();
        g2.setColor(Color.RED);
        g2.drawRect(rect.x - (int) getGameState().camera.getPosX(), rect.y - (int) getGameState().camera.getPosY(), rect.width, rect.height);
    }

    public abstract Rectangle getBoundForCollisionWithEnemy();
    
    public void hurtingCallback(){};
}
