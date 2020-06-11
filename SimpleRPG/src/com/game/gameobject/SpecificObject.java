package com.game.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.gameinteface.Profile;
import com.game.gameinteface.Vulnerable;
import com.game.state.GameState;

// Lop doi tuong cu the
public abstract class SpecificObject extends GameObject implements Profile, Vulnerable  {
	
	private int state;			// trang thai
	private int teamType;		// team
	private int direction;		// huong
	
	private float width;
	private float height;
	private float speedX;		// Van toc theo phuong ngang
	private float speedY;		// Van toc theo phuong thang dung
	private float mass; 		// De tao gia toc huong tam Trai Dat
	
	private int blood; 			// Mau
	private int mana;			// Nang luong
	private int damage;			// sat thuong
	private int armor;			// giap
	
	private long beginTimeForCantBeHurt;
	private long timeForCantBeHurt;
	
	protected Animation beHurtForward, beHurtBackward;;
	
	public SpecificObject(float x, float y, float width, float height, float mass, int blood, GameState gameState){

		// vi tri cua doi tuong nam o chinh giua
        super(x, y, gameState);
        setWidth(width);
        setHeight(height);
        setMass(mass);
        setBlood(blood);
        
        state = ALIVE;
        direction = RIGHT_DIR;

    }
    
	// getter va setter
    public long getTimeForCantBeHurt() {
		return timeForCantBeHurt;
	}

	public void setTimeForCantBeHurt(long timeForCantBeHurt) {
		this.timeForCantBeHurt = timeForCantBeHurt;
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
    
    // tra ve khung de kiem tra va cham
    public Rectangle getBoundForCollisionWithMap(){
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth()/2));
        bound.y = (int) (getPosY() - (getHeight()/2));
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    public void beHurt(int damage){
        setBlood(getBlood() - damage);
        hurtingCallback();
    }
    
    public void hurtingCallback() {
    	// Phuong thuc se duoc hoan thien o lop con
    }

    // cap nhat trang thai cua nhan vat
    @Override
    public void Update(){
        switch(state){
            case ALIVE: 
                SpecificObject object = getGameState().specificObjectManager.getEnemyObjectCollideWith(this);
                if(object!=null){
                    if(object.getDamage() > 0){
                        System.out.println("eat damage.... from collision with enemy........ "+object.getDamage());
                        beHurt(object.getDamage());	
                        state = BEHURT;
                        
                    }
                } 
                break;
                
            case BEHURT:
                if(beHurtBackward == null){
                    state = CANTBEHURT;
                    beginTimeForCantBeHurt = System.nanoTime();
                    if(getBlood() == 0)
                            state = FEY;
                } else {
                    beHurtForward.Update(System.nanoTime());
                    if(beHurtForward.isLastFrame()){
                    	beHurtForward.reset();
                        state = CANTBEHURT;
                        if(getBlood() == 0)
                            state = FEY;
                        beginTimeForCantBeHurt = System.nanoTime();
                    }
                } 
                break;
                
            case FEY:
                state = DEATH;
                break;
            
            case DEATH:
                break;
                
            case CANTBEHURT:
                System.out.println("state = cantbehurt");
                if(System.nanoTime() - beginTimeForCantBeHurt > timeForCantBeHurt)
                    state = ALIVE;
                break;
        }
        
    }
    
    // ve
    @Override
    public void draw(Graphics2D g){
    	
    	// ve thanh mau cua doi tuong
    	if(getState() != DEATH) {
    		
    		int bloodLength = (int) (getBlood() / 100.0f * 50);
    		
    		g.setColor(Color.GRAY);
    		g.fillRect((int) (getPosX() - 50 / 2 - 1 - getGameState().camera.getPosX()), (int) (getPosY() - getHeight() / 2 - 10 - 1 - getGameState().camera.getPosY()), 52, 5);
    		g.setColor(Color.RED);
    		g.fillRect((int) (getPosX() - 50 / 2 - getGameState().camera.getPosX()), (int) (getPosY() - getHeight() / 2 - 10 - getGameState().camera.getPosY()), bloodLength, 3);

    	}
    }

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		if(mana <= 0) this.mana = 0;
		else if(mana >= MAX_MANA_POINT) this.mana = MAX_MANA_POINT;
		else this.mana = mana;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}
}
