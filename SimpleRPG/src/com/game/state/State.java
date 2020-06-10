package com.game.state;

import java.awt.image.BufferedImage;

import com.game.userinterface.GamePanel;

// Trang thai cua tro choi
public abstract class State {

	GamePanel gamePanel;	// Bang ve
	
	public State(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public abstract void Update();		// Cap nhat cac doi tuong trong trang thai
	
	public abstract void Render();		// Ve cac doi tuong trong trang thai
	
	// Xu ly tin hieu tu ban phim
	public abstract void processPressButton(int keyEvent);		
	public abstract void processReleaseButton(int keyEvent);
	
	public abstract BufferedImage getBufferedImage();
	
}
