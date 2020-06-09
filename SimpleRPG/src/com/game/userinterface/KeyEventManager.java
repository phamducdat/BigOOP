package com.game.userinterface;

import com.game.state.State;

// Status: Completed

public class KeyEventManager {
	
	private State gameState;

	public KeyEventManager(State state) {
		// TODO Auto-generated constructor stub
		this.gameState = state;
	}
	
	public void processKeyPressed(int keyCode) {
		gameState.processPressButton(keyCode);
	}
	
	public void processKeyReleased(int keyCode) {
		gameState.processReleaseButton(keyCode);
	}
	
	public void setState(State state) {
		this.gameState = state;
	}
}
