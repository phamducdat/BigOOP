package com.game.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.game.control.Button;
import com.game.control.RectangleButton;
import com.game.effect.DataLoader;
import com.game.userinterface.GameFrame;
import com.game.userinterface.GamePanel;

// Trang thai thua cuoc
public class LoseState extends State {

	public final int NUMBER_OF_BUTTON = 2;
	
	// Hinh anh
    private BufferedImage bufferedImage;
    Graphics2D graphicsPaint;

    // Do kho
    private int difficulty;
    
    // Danh sach nut
    private Button[] buttons;
	private int buttonSelected = 0;
	
	public LoseState(GamePanel gamePanel, int difficulty) {
		super(gamePanel);
		
		bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
	        
	    buttons = new Button[NUMBER_OF_BUTTON];    
	    buttons[0] = new RectangleButton("tryagainbutton", GameFrame.SCREEN_WIDTH/2 - 160, 320);
	    buttons[1] = new RectangleButton("menubutton", GameFrame.SCREEN_WIDTH/2 - 160, 500);
		
	}

	// Cap nhat
	@Override
	public void Update() {
		for(int i = 0;i<NUMBER_OF_BUTTON;i++) {
            if(i == buttonSelected) {
                buttons[i].setState(Button.HOVER);
            } else {
                buttons[i].setState(Button.NONE);
            }
        }
		
	}

	// Ve
	@Override
	public void Render() {
		if(bufferedImage == null) {
            bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            return;
        }
        graphicsPaint = (Graphics2D) bufferedImage.getGraphics();
        if(graphicsPaint == null) {
            graphicsPaint = (Graphics2D) bufferedImage.getGraphics();
            return;
        }
        graphicsPaint.setColor(Color.CYAN);
		graphicsPaint.drawImage(DataLoader.getInstance().getFrameImage("gamelose").getImage(), 0, 0, null);
		for (Button bt : buttons) {
			bt.draw(graphicsPaint);
		}
		
	}
	
	// Xu ly su kien tu ban phim
	@Override
	public void processPressButton(int keyEvent) {
		switch(keyEvent) {
        case KeyEvent.VK_DOWN:
            buttonSelected++;
            if(buttonSelected >= NUMBER_OF_BUTTON) {
                buttonSelected = 0;
            }
            break;
        case KeyEvent.VK_UP:
            buttonSelected--;
            if(buttonSelected < 0) {
                buttonSelected = NUMBER_OF_BUTTON - 1;
            }
            break;
        case KeyEvent.VK_ENTER:
            actionMenu();
            break;
		}
	}

	@Override
	public void processReleaseButton(int keyEvent) {}

	public void actionMenu() {
		switch(buttonSelected) {
        case 0:
            gamePanel.setState(new GameState(gamePanel, difficulty));
            break;
       
        case 1:
        	gamePanel.setState(new MenuState(gamePanel));
            break;
		}
	}
	
	@Override
	public BufferedImage getBufferedImage() {
		// TODO Auto-generated method stub
		return bufferedImage;
	}
}