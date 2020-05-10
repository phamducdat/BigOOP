package com.game.gameinteface;

public interface GameWorldState {

	public static final int FINAL_BOSS = 100; 
	
	// Game State
	public static final int INIT_GAME = 0;
	public static final int TUTORIAL = 1;
	public static final int GAMEPLAY = 2;
	public static final int GAMEOVER = 3;
	public static final int GAMEPAUSE = 4;
	
	// Tutorial State
	public static final int INTROGAME = 0;
	public static final int MEET_FINAL_BOSS = 1;
	
}
