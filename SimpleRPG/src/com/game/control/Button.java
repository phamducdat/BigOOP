package com.game.control;

import java.awt.Color;
import java.awt.Graphics;

import com.game.gameinteface.ButtonState;

// Done

public abstract class Button implements ButtonState{

	protected String text;
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected int paddingTextX;
	protected int paddingTextY;
	protected boolean enabled;
	
	protected int state;
	protected Color bgColor;
	protected Color pressedBgColor;
	protected Color hoverBgColor;
	
	public Button(String text, int posX, int posY, int width, int height, int paddingTextX, int paddingTextY,
			Color bgColor) {
		this.text = text;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.paddingTextX = paddingTextX;
		this.paddingTextY = paddingTextY;
		this.bgColor = bgColor;
		enabled = true;
	}
	
	public void setEnable(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public void setBgColor(Color color) {
		bgColor = color;
	}

	public void setHoverBgColor(Color color) {
		hoverBgColor = color;
	}
	public void setPressedBgColor(Color color) {
		pressedBgColor = color;
	}
}
