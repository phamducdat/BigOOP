package com.game.userinterface;

import java.io.IOException;

import javax.swing.JFrame;

import com.game.effect.DataLoader;

//  Status: Completed

public class GameFrame extends JFrame {
	
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 750;
	
	GamePanel gamePanel;
	
	public GameFrame() {
		
		gamePanel = new GamePanel();
		
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			DataLoader.getInstance().LoadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(gamePanel);
		addKeyListener(gamePanel);
		
	}
	
	public void startGame() {
		gamePanel.startGame();
	}
	
	public static void main(String[] args) {
		GameFrame gameFrame = new GameFrame();
		gameFrame.startGame();
	}
}
