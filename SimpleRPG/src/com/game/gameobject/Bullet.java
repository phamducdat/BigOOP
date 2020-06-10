package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.game.state.GameState;
import com.game.gameobject.SpecificObject;

// Lop DanS
public abstract class Bullet extends SpecificObject {

    public Bullet(float x, float y, float width, float height, float mass, int damage, GameState gameState) {
        super(x, y, width, height, mass, 1, gameState);
        setDamage(damage);
}

    // Cap nhat chuyen dong cua dan va va cham cua no voi cac doi tuong khac
    public void Update(){
	    super.Update();
	    setPosX(getPosX() + getSpeedX());
	    setPosY(getPosY() + getSpeedY());
	    SpecificObject object = getGameState().specificObjectManager.getEnemyObjectCollideWith(this);
	    if(object!=null && object.getState() == ALIVE){
	        setState(DEATH);
	        object.beHurt(getDamage());
	        object.setState(BEHURT);
    }
}

	
}
