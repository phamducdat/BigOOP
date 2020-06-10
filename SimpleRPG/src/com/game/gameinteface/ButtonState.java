package com.game.gameinteface;

import java.awt.Graphics2D;

// Trang thai cua cac nut
public interface ButtonState {

	public static final int NONE = 0;
	public static final int PRESS = 1;
	public static final int HOVER = 2;
	
	public abstract void draw(Graphics2D g);
	
}
