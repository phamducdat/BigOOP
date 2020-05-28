package com.game.state;

import java.awt.image.BufferedImage;

import com.game.userinterface.GamePanel;

//  Done

public abstract class State {

	GamePanel gamePanel;
	
	public State(GamePanel gamePanel) {
		// TODO Auto-generated constructor stub
		this.gamePanel = gamePanel;
	}
	
	public abstract void Update();
	
	public abstract void Render();
	
	public abstract void processPressButton(int keyEvent);
	
	public abstract void processReleaseButton(int keyEvent);
	
	public abstract BufferedImage getBufferedImage();
	
}
