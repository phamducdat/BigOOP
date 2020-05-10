package com.game.gameinteface;

import java.awt.Graphics;

public interface ButtonState {

	public static final int NONE = 0;
	public static final int PRESS = 1;
	public static final int HOVER = 2;
	
	public abstract boolean isInButton();
	
	public abstract void draw(Graphics g);
	
}
