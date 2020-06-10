package com.game.userinterface;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import com.game.effect.DataLoader;

// Khung hinh
public class GameFrame extends JFrame {
	
	// Kich thuoc khung hinh
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 750;
	
	// Bang ve cua khung hinh
	GamePanel gamePanel;
	
	public GameFrame() {
		
		super("BKMan: An unknown adventure");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Lay kich thuoc man hinh
        Toolkit toolkit = this.getToolkit();
        Dimension solution = toolkit.getScreenSize();

        try {
        	// Load du lieu
        	DataLoader.getInstance().LoadData();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Dat kich thuoc Frame
        this.setBounds((solution.width - SCREEN_WIDTH)/2, (solution.height - SCREEN_HEIGHT)/2, SCREEN_WIDTH, SCREEN_HEIGHT);

        gamePanel = new GamePanel();
        addKeyListener(gamePanel);
        add(gamePanel);
		
	}
	
	// Phuong thuc khoi dong game
	public void startGame() {
		gamePanel.startGame();
		this.setVisible(true);
	}
	
	// Thread-0
	public static void main(String[] args) {
		GameFrame gameFrame = new GameFrame();
		gameFrame.startGame();
	}
}
