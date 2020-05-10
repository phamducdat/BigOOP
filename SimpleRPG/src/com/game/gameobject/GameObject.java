package com.game.gameobject;

import com.game.gameinteface.Changeable;
import com.game.state.GameState;

// Status: Completed

public abstract class GameObject implements Changeable {

	private float posX;
	private float posY;
	
	GameState gameState;
	
	public GameObject() {
		posX = 0;
		posY = 0;
		gameState = null;
	}
	
	public GameObject(float posX, float posY, GameState gameState) {
		
		this.posX = posX;
		this.posY = posY;
		
		this.gameState = gameState;
		
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
}
