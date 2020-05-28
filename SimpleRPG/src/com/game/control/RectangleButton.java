package com.game.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// Done

public class RectangleButton extends Button{

	public RectangleButton(String text, int posX, int posY, int width, int height, int paddingTextX, int paddingTextY,
			Color bgColor) {
		// TODO Auto-generated constructor stub
		super(text, posX, posY, width, height, paddingTextX, paddingTextY, bgColor);
	}

	@Override
	public boolean isInButton(int x, int y) {
		return (enabled && x >= posX && x <= posX + width && y >= posY && y <= posY + height);
	}
	
	@Override
	public void draw(Graphics g) {
		if(enabled) {
			switch (state) {
				case NONE: g.setColor(bgColor); break;
				case PRESS: g.setColor(pressedBgColor); break;
				case HOVER: g.setColor(hoverBgColor); break;
			}
		} else {
			g.setColor(Color.GRAY);
		}
		g.fillRect(posX, posY, width, height);
		
		g.setColor(Color.PINK);
		g.drawRect(posX, posY, width, height);
		g.drawRect(posX + 1, posY + 1, width - 2, height - 2);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 14)); 
		g.drawString(text, posX + paddingTextX, posY + paddingTextY);
	}

}
