package com.game.gameobject;

import java.awt.Graphics;

import com.game.effect.DataLoader;
import com.game.state.GameState;
import com.game.userinterface.GameFrame;

// Status: Completed

public class BackgroundMap extends GameObject{

	private int[][] backgroundMap;
	private int tileSize;
	
	public BackgroundMap(float x, float y, GameState gameState) {
		super(x, y, gameState);
		
		backgroundMap = DataLoader.getInstance().getBackgroundMap();
		tileSize = 128;
		
	}
	
	@Override
	public void Update() {}

	@Override
	public void draw(Graphics g) {
		
		g.drawImage(DataLoader.getInstance().getFrameImages().get("background").getImage(), 0, 0, null);
		
		for(int i = 0; i < backgroundMap.length; i++) {
			for(int j = 0; j < backgroundMap[0].length; j++) {
				if(j * tileSize - getGameState().camera.getPosX() > - tileSize 
						&& j * tileSize - getGameState().camera.getPosX() < GameFrame.SCREEN_WIDTH
						&& i * tileSize - getGameState().camera.getPosY() > - tileSize 
						&& i * tileSize - getGameState().camera.getPosY() < GameFrame.SCREEN_HEIGHT) {
					
					g.drawImage(DataLoader.getInstance().getFrameImages().get("tile" + backgroundMap[i][j]).getImage(),
							(int) (getPosX() + j * tileSize - getGameState().camera.getPosX()),
							(int) (getPosY() + i * tileSize - getGameState().camera.getPosY()), null);
					
				}
			}
		}
		
		
	}

}
