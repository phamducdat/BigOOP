package com.game.gameobject;

import java.awt.Graphics;

import com.game.state.GameState;

// Status: Completed

public class Camera extends GameObject{

	// Doi tuong de cac doi tuong khac lay lam he quy chieu de xac dinh vi tri
	
	private float width;
	private float height;
	
	private boolean isLocked;
	
	public Camera(float x, float y, float width, float height, GameState gameState) {
		super(x, y, gameState);
		
		this.width = width;
		this.height = height;
		isLocked = false;
	}
	
	@Override
	public void Update() {
		// Camera se di chuyen theo nhan vat chinh
		
		Hero megaman = getGameState().megaman;
		
		if(megaman.getPosX() - getPosX() > 400) setPosX(megaman.getPosX() - 400);
		if(megaman.getPosX() - getPosX() < 200) setPosX(megaman.getPosX() - 200);
		
		if(megaman.getPosY() - getPosY() > 400) setPosY(megaman.getPosY() - 400);
		if(megaman.getPosY() - getPosY() < 250) setPosY(megaman.getPosY() - 250);
	}

	@Override
	public void draw(Graphics g) {}

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

	public boolean getIsLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
}
