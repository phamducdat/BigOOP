package com.game.userinterface;

import com.game.state.State;

// Lop phan hoi su kien tu ban phim
public class KeyEventManager {
	
	// Trang thai hien tai cua tro choi
	// Vi trong moi trang thai thi tro choi se co phan hoi su kien tu ban phim khac nhau
	private State gameState;

	public KeyEventManager(State state) {
		this.gameState = state;
	}
	
	// Xu ly su kien
	public void processKeyPressed(int keyCode) {
		gameState.processPressButton(keyCode);
	}
	
	public void processKeyReleased(int keyCode) {
		gameState.processReleaseButton(keyCode);
	}
	
	// Chuyen trang thai
	public void setState(State state) {
		this.gameState = state;
	}
}
