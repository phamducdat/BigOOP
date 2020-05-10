package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.gameinteface.Actable;
import com.game.state.GameState;

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
		
		super.Update();
		
		
		
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
