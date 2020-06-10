package com.game.userinterface;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.game.state.MenuState;
import com.game.state.State;

// Bang ve
public class GamePanel extends JPanel implements KeyListener, Runnable {
	
	// Trang thai hien tai cua tro choi
	State gameState;
	
	// Bo xu ly su kien tren ban phim
	KeyEventManager keyEventManager;
	
	// Thread-1
	private Thread thread;
	private boolean isRunning;	// tro  choi dang chay???
	
	public GamePanel() {
		
		// Khi duoc khoi dong thi tro choi se co trang thai la Menu
		gameState = new MenuState(this);
		keyEventManager = new KeyEventManager(gameState);
		
	}

	// Phuong thuc ve cua GamePanel
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(gameState.getBufferedImage(), 0, 0, this);
	}
	
	// Khoi chay tro choi
	public void startGame() {
		if(thread == null) {
			thread = new Thread(this);
			isRunning = true;
			thread.start();
		}
	}
	
	// Chuyen trang thai cua tro choi, trang thai moi se duoc hien thi len khung hinh
	public void setState(State state) {
		this.gameState = state;
		keyEventManager.setState(state);
	}
	
	// Xu ly su kien tu ban phim
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

	// Than cua Thread-1
	@Override
	public void run() {
		
		// Tinh thoi gian 1 chu ky
		long FPS = 60;
		long period = 1000000000 / FPS;
		
		long previousTime = System.nanoTime();
		long sleepTime;
		long currentTime;
		
		// GameLoop
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
