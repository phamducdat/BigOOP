package com.game.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;

import com.game.effect.DataLoader;
import com.game.state.GameState;
import com.game.userinterface.GameFrame;

// Ban do hien thi chi co tac dung hien thi chu khong anh huong den hoat dong cua he thong
public class BackgroundMap extends GameObject{

    public int[][] map;
    private int tileSize;
    
    public BackgroundMap(float x, float y, GameState gameState) {
        super(x, y, gameState);
        map = DataLoader.getInstance().getBackgroundMap();
        tileSize = 128;
    }

    @Override
    public void Update() {}
    
    // ve ban do
    public void draw(Graphics2D g2){
        
        Camera camera = getGameState().camera;
        
        g2.drawImage(DataLoader.getInstance().getFrameImage("background").getImage(), 0, 0, null);
        
        g2.setColor(Color.RED);
        for(int i = 0;i< map.length;i++)
            for(int j = 0;j<map[0].length;j++)
                if(j*tileSize - camera.getPosX() > - 3 * tileSize && j*tileSize - camera.getPosX() < GameFrame.SCREEN_WIDTH
                        && i*tileSize - camera.getPosY() > - 3 * tileSize && i*tileSize - camera.getPosY() < GameFrame.SCREEN_HEIGHT){ 
                    g2.drawImage(DataLoader.getInstance().getFrameImage("tile" + map[i][j]).getImage(), (int) getPosX() + j*tileSize - (int) camera.getPosX(), 
                        (int) getPosY() + i*tileSize - (int) camera.getPosY(), null);
                }
        
    }
    
}
