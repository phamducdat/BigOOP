package com.game.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.effect.DataLoader;
import com.game.state.GameState;

// Ban do vat ly phu trach viec kiem tra va cham cua doi tuong voi dia hinh
public class PhysicalMap extends GameObject {

    public int[][] phys_map;
    private int tileSize;
    
    public PhysicalMap(float x, float y, GameState gameWorld) {
        super(x, y, gameWorld);
        this.tileSize = 128;
        phys_map = DataLoader.getInstance().getPhysicalMap();
    }
    
    public int getTileSize(){
        return tileSize;
    }
    
    @Override
    public void Update() {}

    // kiem tra va cham voi tran
    public Rectangle haveCollisionWithTop(Rectangle rect){

        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;

        int posY = rect.y/tileSize;

        if(posX1 < 0) posX1 = 0;
        
        if(posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;
        
        for(int y = posY; y >= 0; y--){
            for(int x = posX1; x <= posX2; x++){
                
                if(phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }
    
    // kiem tra va cham voi dat
    public Rectangle haveCollisionWithLand(Rectangle rect){

        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;

        int posY = (rect.y + rect.height)/tileSize;

        if(posX1 < 0) posX1 = 0;
        
        if(posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;
        for(int y = posY; y < phys_map.length;y++){
            for(int x = posX1; x <= posX2; x++){
                
                if(phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }
    
    // kiem tra va cham voi hai ben tuong
    public Rectangle haveCollisionWithRightWall(Rectangle rect){
   
        
        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;
        
        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 + 3;
        if(posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;
        
        if(posY1 < 0) posY1 = 0;
        if(posY2 >= phys_map.length) posY2 = phys_map.length - 1;
        
        
        for(int x = posX1; x <= posX2; x++){
            for(int y = posY1; y <= posY2;y++){
                if(phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
        
    }
    
    public Rectangle haveCollisionWithLeftWall(Rectangle rect){

        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;
        
        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 - 3;
        if(posX2 < 0) posX2 = 0;
        
        if(posY1 < 0) posY1 = 0;
        if(posY2 >= phys_map.length) posY2 = phys_map.length - 1;
        
        
        for(int x = posX1; x >= posX2; x--){
            for(int y = posY1; y <= posY2;y++){
                if(phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
        
    }
    
    @Override
    public void draw(Graphics2D g2){}

	public int[][] getPhys_map() {
		return phys_map;
	}

	public void setPhys_map(int[][] phys_map) {
		this.phys_map = phys_map;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
}
