package com.game.userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.game.effect.DataLoader;
import com.game.gameobject.BackgroundMap;
import com.game.state.GameState;
import com.game.state.MenuState;
import com.game.state.State;

// Done

public class GamePanel extends JPanel implements KeyListener, Runnable {
	
	State gameState;
	
	KeyEventManager keyEventManager;
	
	private Thread thread;
	private boolean isRunning;
	
	public GamePanel() {
		
		gameState = new MenuState(this);
		keyEventManager = new KeyEventManager(gameState);
		
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(gameState.getBufferedImage(), 0, 0, this);
	}
	
	public void startGame() {
		if(thread == null) {
			thread = new Thread(this);
			isRunning = true;
			thread.start();
		}
	}
	
	public void setState(State state) {
		this.gameState = state;
		keyEventManager.setState(state);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("You pressed");
		keyEventManager.processKeyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("You released");
		keyEventManager.processKeyReleased(e.getKeyCode());
	}

	@Override
	public void run() {
		long FPS = 60;
		long period = 1000000000 / FPS;
		
		long previousTime = System.nanoTime();
		long sleepTime;
		long currentTime;
		
		while(isRunning) {
			
			gameState.Update();
			gameState.Render();
			
			repaint();
			currentTime = System.nanoTime();
			
			sleepTime = period - (currentTime - previousTime);
			
			try {
				if(sleepTime > 0)
					thread.sleep(sleepTime/1000000);
				else thread.sleep(period/2000000);
			} catch (InterruptedException e) {
				// Xu ly ngoai le
				e.printStackTrace();
			}
		}
	}
}
