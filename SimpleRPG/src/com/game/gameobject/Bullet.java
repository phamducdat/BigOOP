package com.game.gameobject;

import com.game.state.GameState;

public abstract class Bullet extends SpecificObject {

	public Bullet(float posX, float posY, float width, float height, float mass, int healthPoint, int manaPoint,
			GameState gameState) {
		super(posX, posY, width, height, mass, healthPoint, manaPoint, gameState);
		// TODO Auto-generated constructor stub
	}

	
}
