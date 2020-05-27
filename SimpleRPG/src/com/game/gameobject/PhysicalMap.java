package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.w3c.dom.css.Rect;

import com.game.effect.DataLoader;
import com.game.state.GameState;

// Status: Completed

public class PhysicalMap extends GameObject {

	// Ban do duoc the hien bang mang 2 chieu
	private int[][] physicalMap;
	private int tileSize; 		// Kich thuoc 1 o tren ban do
	
	public PhysicalMap(float x, float y, GameState gameState) {
		super(x, y, gameState);
		
		physicalMap = DataLoader.getInstance().getPhysicalMap();
		tileSize = 128;
		
	}
	
	@Override
	public void Update() {}

	@Override
	public void draw(Graphics g) {}

	// 4 Phuong thuc de kiem tra va cham cua mo su vat voi ban do vat ly
	
	public Rectangle haveCollisionWithTop(Rectangle rect) {
		
		
		int x1 = rect.x / tileSize;
		if(x1 >= 1) x1 -=1;
		else x1 = 0;
		
		int x2 = (rect.x +  rect.width) / tileSize + 1;
		if(x2 <= physicalMap[0].length - 3) x2 += 2;
		else x2 = physicalMap[0].length - 1;
		
		int y = (int) (rect.y / tileSize);
		
		for(int j = x1; j<= x2; j++) {
				
			if(physicalMap[y][j] == 1) {
				Rectangle rectangle = new Rectangle((int) getPosX() + j * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
						
					if(rect.intersects(rectangle)) 
						return rectangle;
						
			}
		}

		
		return null;
	}
	
	public Rectangle haveCollisionWithLand(Rectangle rect) {
		
		int x1 = rect.x / tileSize;
		if(x1 >= 1) x1 -= 1;
		else x1 = 0;
		
		int x2 = (rect.x + rect.width) / tileSize;
		if(x2 <= physicalMap[0].length - 3) x2 +=2;
		else x2 = physicalMap[0].length - 1;
		
		int y = (rect.y + rect.height) / tileSize;
		
		for(int j = x1; j <= x2; j++) {
				
			if(physicalMap[y][j] == 1) {
					
				Rectangle rectangle = new Rectangle((int) getPosX() + j * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
					
				if(rect.intersects(rectangle)) {
					return rectangle;
				}	
			}	
		}
		return null;
	}
	
	public Rectangle haveCollisionWithRightWall(Rectangle rect) {
		
		int y1 = rect.y / tileSize;
		if(y1 >= 1) y1 -= 1;
		else y1 = 0;
		
		int y2 = (rect.y + rect.height) / tileSize;
		if(y2 <= physicalMap.length - 3) y2 += 2;
		else y2 = physicalMap.length - 1;
		
		int x = (rect.x + rect.width) / tileSize;
		
		for(int  i = y1; i <= y2 ; i++) {
			
			if(physicalMap[i][x] == 1) {
					
				Rectangle rectangle = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + i * tileSize, tileSize, tileSize);
				
				if(rect.intersects(rectangle))
					return rectangle;		
			}
		}
		return null;
	}
	
	public Rectangle haveCollisionWithLeftWall(Rectangle rect) {
		
		int y1 = rect.y / tileSize;
		if(y1 >= 1) y1 --;
		else y1 = 0;
		
		int y2 = (rect.y + rect.height) / tileSize;
		if(y2 < physicalMap.length - 3) y2 += 2;
		else y2 = physicalMap.length - 1;
		
		int  x = rect.x / tileSize;
		
		for(int i = y1; i <= y2; i++) {
			
			if(physicalMap[i][x] == 1) {
				
				Rectangle rectangle = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + i * tileSize, tileSize, tileSize);
				
				if(rect.intersects(rectangle))
					return rectangle;
			}
		}
		return null;
	}

	public int[][] getPhysicalMap() {
		return physicalMap;
	}

	public void setPhysicalMap(int[][] physicalMap) {
		this.physicalMap = physicalMap;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
}
