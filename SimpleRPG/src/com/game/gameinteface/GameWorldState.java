package com.game.gameinteface;

import com.game.gameobject.BackgroundMap;

public interface GameWorldState {

	public static final int finalBossX = 3000; 
	
	// Game State
	public static final int INIT_GAME = 0;
	public static final int TUTORIAL = 1;
	public static final int GAMEPLAY = 2;
	public static final int GAMEOVER = 3;
	public static final int GAMEPAUSE = 4;
	public static final int GAMEWIN = 5;
	
	// Tutorial State
	public static final int INTROGAME = 0;
	public static final int MEET_FINAL_BOSS = 1;
	
	// Difficult
	public static final int EASY = 0;
	public static final int DIFFICULT = 1;

}
