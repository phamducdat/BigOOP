package com.game.gameobject;

import com.game.gameinteface.Changeable;
import com.game.state.GameState;

// Done

// Class chung nhat cho moi doi tuong trong Game de ke thua

public abstract class GameObject implements Changeable {

	
	private float posX;
	private float posY;
	
	private GameState gameState;
	
	public GameObject(float x, float y, GameState gameState){
		posX = x;
		posY = y;
		this.gameState = gameState;
	}
	
	public void setPosX(float x){
		posX = x;
	}
	
	public float getPosX(){
		return posX;
	}
	
	public void setPosY(float y){
		posY = y;
	}
	
	public float getPosY(){
		return posY;
	}
	
	public GameState getGameState(){
		return gameState;
	}
	
}
