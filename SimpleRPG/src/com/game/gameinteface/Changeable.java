package com.game.gameinteface;

import java.awt.Graphics2D;

// Nhungg dooi tuong se thay doi vi tri trong qua trinh chay game
public interface Changeable {

	public abstract void Update();
	
	public abstract void draw(Graphics2D g2);
	
}
