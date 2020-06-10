package com.game.gameobject;

import java.awt.Graphics2D;

import com.game.state.GameState;
import com.game.userinterface.GameFrame;

// Lop lam khung hinh cua tro choi chuyen dong theo nhan vat
public class Camera extends GameObject{

	// Doi tuong de cac doi tuong khac lay lam he quy chieu de xac dinh vi tri
	
    private float widthView;
    private float heightView;
    
    private boolean isLocked = false;
    
    public Camera(float x, float y, float widthView, float heightView, GameState gameWorld) {
        super(x, y, gameWorld);
        this.widthView = widthView;
        this.heightView = heightView;
    }

    public void lockBoss() {
    	int[][] map = getGameState().physicalMap.getPhys_map();
    	int tileSize = getGameState().physicalMap.getTileSize();
    	
    	setPosX((int) (map[0].length * tileSize - GameFrame.SCREEN_WIDTH));
    	setPosY(tileSize);
    	lock();
    }
    
    public void lock(){
        isLocked = true;
        
          
    }
    
    public void unlock(){
        isLocked = false;
    }
    
    @Override
    public void Update() {
    
       // Khoa lai khi gap Final Boss
        if(!isLocked){
        
            Hero mainCharacter = getGameState().megaMan;
            int[][] mapClone = getGameState().physicalMap.getPhys_map();
            int tileSize = getGameState().physicalMap.getTileSize();

            if(mainCharacter.getPosX() - getPosX() > 400) {
            	if(mainCharacter.getPosX() - 400 + GameFrame.SCREEN_WIDTH <= mapClone[0].length * tileSize)
            		setPosX(mainCharacter.getPosX() - 400);
            	else setPosX(mapClone[0].length + tileSize - GameFrame.SCREEN_WIDTH);
            }
            if(mainCharacter.getPosX() - getPosX() < 200) {
            	if(mainCharacter.getPosX() - 200 >= 0)
            		setPosX(mainCharacter.getPosX() - 200);
            	else setPosX(0);
            }

            if(mainCharacter.getPosY() - getPosY() > 400) {
            	if(mainCharacter.getPosY() - 400 + GameFrame.SCREEN_HEIGHT <= mapClone.length * tileSize)
            		setPosY(mainCharacter.getPosY() - 400); // bottom
            	
            	else setPosY(mapClone.length * tileSize - GameFrame.SCREEN_HEIGHT);
            }
            else if(mainCharacter.getPosY() - getPosY() < 250) {
            	if(mainCharacter.getPosY() - 250 >= 0)
            		setPosY(mainCharacter.getPosY() - 250);// top 
            	else setPosY(0);
            }
        }
    
    }

    public float getWidthView() {
        return widthView;
    }

    public void setWidthView(float widthView) {
        this.widthView = widthView;
    }

    public float getHeightView() {
        return heightView;
    }

    public void setHeightView(float heightView) {
        this.heightView = heightView;
    }

	@Override
	public void draw(Graphics2D g2) {}
}
