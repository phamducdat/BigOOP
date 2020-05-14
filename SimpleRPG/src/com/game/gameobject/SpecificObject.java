package com.game.gameobject;

import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.gameinteface.Profile;
import com.game.gameinteface.Vulnerable;
import com.game.state.GameState;

// Status: Completed

public abstract class SpecificObject extends GameObject implements Profile, Vulnerable  {

	private int state = ALIVE;
	private int teamType;
	private int direction;
	
	private float width;
	private float height;
	private float speedX;		// Van toc theo phuong ngang
	private float speedY;		// Van toc theo phuong thang dung
	private float mass; 		// De tao gia toc huong tam Trai Dat
	
	private int healthPoint; 	// Mau
	private int manaPoint;		// Nang luong
	private int damage;
	
	private long beginTimeToNoBeHurt;
	private long timeForNoBeHurt;
	
	protected Animation beHurtForward, beHurtBackward;;
	
	public SpecificObject(float posX, float posY, float width, float height, float mass, int healthPoint, int manaPoint, GameState gameState) {
		super(posX, posY, gameState);
		setWidth(width);
		setHeight(height);
		setMass(mass);
		setHealthPoint(healthPoint);
		setManaPoint(manaPoint);
		
		direction = RIGHT_DIR;
		
	}
	
	public boolean isOutOfCameraView() {
		if(getPosX() - getGameState().camera.getPosX() > getGameState().camera.getWidth()
				|| getPosX() - getGameState().camera.getPosX() < -50
				|| getPosY() - getGameState().camera.getPosY() > getGameState().camera.getHeight()
				|| getPosY() - getGameState().camera.getPosY() < -50)
			return true;
		
		else return false;
	}
	
	public void beHurt (int damageRecieved) {
		setHealthPoint(getHealthPoint() - damageRecieved);
	}
	
	public Rectangle getBoundForCollisionWithMap() {
		// Lay bien de kiem tra va cham voi ban do vat ly
		Rectangle rect = new Rectangle();
		
		rect.x = (int) (getPosX() - getWidth()/2);
		rect.y = (int) (getPosY() - getHeight()/2);
		rect.width = (int) getWidth();
		rect.height = (int) getHeight();
		
		return rect;
	}

	@Override
	public void Update() {
		
		// Update trang thai cua nhan vat
		
		switch(state) {
		
		case ALIVE: 
			// Trang thai con song
			
			SpecificObject object = getGameState().specificObjectManager.getObjectCollideWithThisObject(this);
			
			if(object != null) {
				
				if(object.getDamage() > 0) {
					beHurt(object.getDamage());
					setState(BEHURT);
				}
				
			}
			
			break;
			
		case BEHURT:
			// Trang thai bi thuong
			
			if(beHurtBackward == null) {
				// Nhan vat khong co hoat anh bi thuong
				state = CANTBEHURT;
				beginTimeToNoBeHurt = System.nanoTime();
				if(getHealthPoint() == 0) {
					state = FEY;
				}
			} else {
				// Neu co thi cho chay Animation bi thuong
				beHurtForward.Update(System.nanoTime());
				if(beHurtForward.isLastFrame()) {
					beHurtForward.reset();
					state = CANTBEHURT;
					if(getHealthPoint() == 0) 
						state = FEY;
					beginTimeToNoBeHurt = System.nanoTime();

				}
			}
			
			break;
			
		case FEY:
			// Trang thai hap hoi
			state = DEATH;
			
			break;
			
		case CANTBEHURT:
			// Trang thai khong the bi thuong
			if(System.nanoTime() - beginTimeToNoBeHurt > timeForNoBeHurt) {
				state = ALIVE;
			}
			
			break;
			
		case DEATH:
			// Trang thai chet
			break;
		
		}
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTeamType() {
		return teamType;
	}

	public void setTeamType(int teamType) {
		this.teamType = teamType;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		if(healthPoint >= 0)
			this.healthPoint = healthPoint;
		else this.healthPoint = 0;
	}

	public int getManaPoint() {
		return manaPoint;
	}

	public void setManaPoint(int manaPoint) {
		if(manaPoint >= 0)
			this.manaPoint = manaPoint;
		else this.manaPoint = 0;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public long getBeginTimeToBeHurt() {
		return beginTimeToNoBeHurt;
	}

	public void setBeginTimeToBeHurt(long beginTimeToBeHurt) {
		this.beginTimeToNoBeHurt = beginTimeToBeHurt;
	}

	public long getTimeForNoBeHurt() {
		return timeForNoBeHurt;
	}

	public void setTimeForNoBeHurt(long timeForNoBeHurt) {
		this.timeForNoBeHurt = timeForNoBeHurt;
	}
}
