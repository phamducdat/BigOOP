package com.game.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.game.control.Button;
import com.game.control.RectangleButton;
import com.game.effect.DataLoader;
import com.game.gameinteface.GameWorldState;
import com.game.userinterface.GameFrame;
import com.game.userinterface.GamePanel;

public class DifficultyState extends State{

	private final int NUMBER_OF_BUTTON = 2;
	BufferedImage bufferedImage;
	Graphics graphicsPaint;
	
	Button[] buttons;
	int buttonSelected = 0;
	
	public DifficultyState(GamePanel gamePanel) {
		super(gamePanel);
		
		buttons = new Button[NUMBER_OF_BUTTON];
		
		bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_WIDTH, BufferedImage.TYPE_INT_ARGB);
		
		buttons[0] = new RectangleButton("EASY", 450, 400, 100, 40, 15, 25, Color.ORANGE);
		buttons[0].setHoverBgColor(Color.BLUE);
		buttons[0].setPressedBgColor(Color.GREEN);
		
		buttons[1] = new RectangleButton("DIFFICULT", 450, 500, 100, 40, 15, 25, Color.ORANGE);
		buttons[1].setHoverBgColor(Color.BLUE);
		buttons[1].setPressedBgColor(Color.GREEN);
		
	}

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

	@Override
	public void Render() {
		if(bufferedImage == null) {
            bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            return;
        }
        graphicsPaint = bufferedImage.getGraphics();
        if(graphicsPaint == null) {
            graphicsPaint = bufferedImage.getGraphics();
            return;
        }
        graphicsPaint.setColor(Color.CYAN);
		graphicsPaint.drawImage(DataLoader.getInstance().getFrameImage("openscene").getImage(), 0, 0, null);
		for (Button bt : buttons) {
			bt.draw(graphicsPaint);
		}
		
	}

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

	@Override
	public BufferedImage getBufferedImage() {
		// TODO Auto-generated method stub
		return bufferedImage;
	}
	
	public void actionMenu() {
		switch(buttonSelected) {
		
		case 0:
			gamePanel.setState(new GameState(gamePanel, GameWorldState.EASY));
			break;
			
		case 1:
			gamePanel.setState(new GameState(gamePanel, GameWorldState.DIFFICULT));
			break;
		
		}
	}

}
