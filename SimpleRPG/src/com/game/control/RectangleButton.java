package com.game.control;

import java.awt.Color;
import java.awt.Graphics2D;

// Nut hinh chu nhat
public class RectangleButton extends Button{

	public RectangleButton(String text, int posX, int posY) {
		// TODO Auto-generated constructor stub
		super(text, posX, posY);
	}

	@Override
	public void draw(Graphics2D g) {
		if(enabled) {
			switch (state) {
				case NONE: g.drawImage(none, posX, posY, null); break;
				case PRESS: g.drawImage(press, posX, posY, null); break;
				case HOVER: g.drawImage(hover, posX, posY, null); break;
			}
		} else {
			g.setColor(Color.GRAY);
			g.fillRect(posX, posY, width, height);
		}
	}
}
