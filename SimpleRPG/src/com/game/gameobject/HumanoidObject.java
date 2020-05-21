package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.w3c.dom.css.Rect;

import com.game.gameinteface.Actable;
import com.game.state.GameState;

// Status: Completed

public abstract class HumanoidObject extends SpecificObject implements Actable{
	
	private boolean isJumping;
	private boolean isKneeling;
	private boolean isLanding;

	public HumanoidObject(float posX, float posY, float width, float height, float mass, int healthPoint, int manaPoint,
			GameState gameState) {
		super(posX, posY, width, height, mass, healthPoint, manaPoint, gameState);
		setState(ALIVE);
	}
	
	@Override
	public void Update() {
		
		// Thuc hien Update ve vi tri nhan vat va tuong tac voi PhysicalMap
		
		super.Update();
		
		// Chi Update nhan vat trong tuong tac voi ban do vat ly khi nhan vat o trang thai ALIVE hoac CANTBEHURT
		if(getState() == ALIVE || getState() == CANTBEHURT) {
			
			if(!isLanding) {
				
				// Cap nhat vi tri theo phuoong ngang cua nhan vat
				
				setPosX(getPosX() + getSpeedX());
				
				// Kiem tra va cham voi tuong trai va phai sau do thiet lap lai vi tri object
				
				if(getDirection() == LEFT_DIR && getGameState().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null) {
					Rectangle rectLeft = getGameState().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
					
					setPosX(rectLeft.x + rectLeft.width + getWidth() / 2);
					
				} else if(getDirection() == RIGHT_DIR && getGameState().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null) {
					Rectangle rectRight = getGameState().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());;
					
					setPosX(rectRight.x - getWidth() / 2);
				}
				
				// Kiem tra va cham voi mat dat va tran nha dong thoi cai dat vi tri theo phuong thang dung
				
				
				Rectangle futureBound =  getBoundForCollisionWithMap();
				futureBound.y += (getSpeedY() == 0)? 2 : getSpeedY();
				// Kiem tra va cham theo phuong thang dung trong tuong lai de dung nhan vat lai truoc
				//==> Tranh tinh trang nhan vat bi giat khi tiep xuc voi dat
				
				Rectangle rectLand = getGameState().physicalMap.haveCollisionWithLand(futureBound);
				Rectangle rectTop = getGameState().physicalMap.haveCollisionWithTop(rectLand);
				
				if(rectLand != null) {
					// Nhan vat cham dat
					setJumping(false);
					if(getSpeedY() != 0) setLanding(true);
					setSpeedY(0);
					setPosY(rectLand.y - getHeight() / 2);
					
				}else if(rectTop != null) {
					// Nhan vat cham tran
					setSpeedY(0);
					setPosY(rectTop.y + rectTop.height + getHeight() / 2); 
					
				}else {
					// Neu khong bi can boi tran va dat
					setJumping(true);
					setSpeedY(getSpeedY() + getMass());
					setPosY(getPosY() + getSpeedY());
					
				}
				
			}
			
		}
		
		
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public boolean isKneeling() {
		return isKneeling;
	}

	public void setKneeling(boolean isKneeling) {
		this.isKneeling = isKneeling;
	}

	public boolean isLanding() {
		return isLanding;
	}

	public void setLanding(boolean isLanding) {
		this.isLanding = isLanding;
	}
}
