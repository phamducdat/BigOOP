package com.game.state;

import com.game.userinterface.GamePanel;

public abstract class State {

	public State(GamePanel gamePanel) {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void Update();
	
	public abstract void Render();
	
	public abstract void processPressButton(int keyEvent);
	
	public abstract void processReleaseButton(int keyEvent);
	
}
