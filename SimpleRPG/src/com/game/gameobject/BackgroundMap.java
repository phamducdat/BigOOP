package com.game.gameobject;

import java.awt.Graphics;

import com.game.effect.DataLoader;
import com.game.state.GameState;
import com.game.userinterface.GameFrame;

// Status: Completed

public class BackgroundMap extends GameObject{

	// Ban do duoc luu bang mang 2 chieu
	private int[][] backgroundMap;
	private int tileSize; 			//Kich thuoc 1 o trong ban do
	
	public BackgroundMap(float x, float y, GameState gameState) {
		super(x, y, gameState);
		
		backgroundMap = DataLoader.getInstance().getBackgroundMap();
		tileSize = 128;
		
	}
	
	@Override
	public void Update() {}

	@Override
	public void draw(Graphics g) {
		
		// Tien hanh ve ban do len man hinh
		
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

	public int[][] getBackgroundMap() {
		return backgroundMap;
	}

	public void setBackgroundMap(int[][] backgroundMap) {
		this.backgroundMap = backgroundMap;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
}
