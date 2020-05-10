package com.game.gameinteface;

import java.awt.Rectangle;

public interface Vulnerable {

	public abstract void attack();
	
	public abstract Rectangle getBoundForCollisionWithEnemy();
	
}
