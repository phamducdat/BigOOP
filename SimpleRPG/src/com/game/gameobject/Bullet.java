package com.game.gameobject;

import java.awt.Graphics;

import com.game.state.GameState;
import com.game.gameobject.SpecificObject;

public abstract class Bullet extends SpecificObject {

	public Bullet(float posX, float posY, float width, float height, float mass, int healthPoint, int manaPoint,
			GameState gameState) {
		super(posX, posY, width, height, mass, healthPoint, manaPoint, gameState);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void draw(Graphics g);

    public void Update(){
        super.Update();
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        SpecificObject object = getGameState().specificObjectManager.getObjectCollideWithThisObject(this);
        if(object!=null && object.getState() == ALIVE){
            setHealthPoint(0);
            object.beHurt(getDamage());
            System.out.println("Bullet set behurt for enemy");
        }
    }
	
}
