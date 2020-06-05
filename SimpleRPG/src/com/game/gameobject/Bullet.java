package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.game.state.GameState;
import com.game.gameobject.SpecificObject;

// Done

public abstract class Bullet extends SpecificObject {

    public Bullet(float x, float y, float width, float height, float mass, int damage, GameState gameState) {
        super(x, y, width, height, mass, 1, gameState);
        setDamage(damage);
}

    public void Update(){
	    super.Update();
	    setPosX(getPosX() + getSpeedX());
	    setPosY(getPosY() + getSpeedY());
	    SpecificObject object = getGameState().specificObjectManager.getCollisionWidthEnemyObject(this);
	    if(object!=null && object.getState() == ALIVE){
	        setState(DEATH);;
	        object.beHurt(getDamage());
	        System.out.println("Bullet set behurt for enemy");
    }
}

	
}
