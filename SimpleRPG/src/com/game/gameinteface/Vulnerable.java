package com.game.gameinteface;

import java.awt.Rectangle;

// Co the tann cong va bi thuong
public interface Vulnerable {

	public abstract void attack();
	
	public abstract Rectangle getBoundForCollisionWithEnemy();
	
}
