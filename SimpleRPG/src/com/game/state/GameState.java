package com.game.state;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.game.effect.DataLoader;
import com.game.effect.FrameImage;
import com.game.gameinteface.GameWorldState;
import com.game.gameinteface.Profile;
import com.game.gameobject.BackgroundMap;
import com.game.gameobject.Boss;
import com.game.gameobject.Camera;
import com.game.gameobject.Hero;
import com.game.gameobject.PhysicalMap;
import com.game.gameobject.RedEyeDevil;
import com.game.gameobject.SmallRedGun;
import com.game.gameobject.SpecificObject;
import com.game.objectmanager.BulletManager;
import com.game.objectmanager.SpecificObjectManager;
import com.game.userinterface.GameFrame;
import com.game.userinterface.GamePanel;
import com.game.gameobject.DarkRaise;
import com.game.gameobject.RobotR;

public class GameState extends State implements GameWorldState {
	
	public Hero megaman;
	public Camera camera;
	
	public PhysicalMap physicalMap;
	public BackgroundMap backgroundMap;
	
	public SpecificObjectManager specificObjectManager;
	public BulletManager bulletManager;
	
	private BufferedImage bufferedImage;
	private int lastState;
	
	// Khau do camera
	public int openScopeY = 0;
	public static final int MAX_OPEN_SCOPE_Y = 450;
	
	// Trang thai game
	public int state = INIT_GAME;
	public int previousState = state;
	
	// Trang thai Tutorial
	public int tutorialState = INTROGAME;
	public String[] text = new String[4];
	public int storyTutorial = 0;
	
	public String textTutorial;
	public int currentSize = 1;
	
	// Trigger de bat va tat boss
	private boolean bossTriger = true;
	
	SpecificObject boss;
	
	FrameImage avatar = DataLoader.getInstance().getFrameImage("avatar");
	
	private int life = 3;
	
	public AudioClip bgMusic;
	
	public GameState(GamePanel gamePanel) {
		super(gamePanel);
		
		System.out.println("Game State is running well !!!");
		
		text[0] = "This world is in danger ...";
		text[1] = "Innocent people are suffering ...";
		text[2] = "We have a duty to save our people ...";
		text[3] = "Let's go !!! ";
		textTutorial = text[0];
		
		bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		megaman = new  Hero(400, 400, this);
		camera = new Camera(0, 50, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
		
		physicalMap = new PhysicalMap(0, 0, this);
		backgroundMap =  new BackgroundMap(0, 0, this);
		
		specificObjectManager = new SpecificObjectManager(this);
		specificObjectManager.addObject(megaman);
		
		bulletManager = new BulletManager(this);
		
		initEasyEnemies();
		
		bgMusic = DataLoader.getInstance().getSounds().get("bgmusic");
		
	}
	
	public void initEasyEnemies() {
		SpecificObject redeye = new RedEyeDevil(627, 474, this);
	    redeye.setDirection(SpecificObject.LEFT_DIR);
	    redeye.setTeamType(SpecificObject.ENEMY_TEAM);
	    specificObjectManager.addObject(redeye);
	    
	    SpecificObject smallRedGun = new SmallRedGun(1573, 87, this);
	    smallRedGun.setDirection(SpecificObject.LEFT_DIR);
	    smallRedGun.setTeamType(SpecificObject.ENEMY_TEAM);
	    specificObjectManager.addObject(smallRedGun);
	    
		SpecificObject darkraise = new DarkRaise(1223, 855, this);
		darkraise.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(darkraise);
		    
		SpecificObject darkraise2 = new DarkRaise(2800, 350, this);
		darkraise2.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(darkraise2);
			    
		SpecificObject robotR = new RobotR(572, 1112, this);
		robotR.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(robotR);
    
		SpecificObject robotR2 = new RobotR(3400, 350, this);
		robotR2.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(robotR2);
    
    
//		SpecificObject redeye2 = new RedEyeDevil(1757,1440, this);
//		redeye2.setDirection(SpecificObject.LEFT_DIR);
//		redeye2.setTeamType(SpecificObject.ENEMY_TEAM);
//		specificObjectManager.addObject(redeye2);
//    
//		SpecificObject redeye3 = new RedEyeDevil(3450, 500, this);
//		redeye3.setDirection(SpecificObject.LEFT_DIR);
//		redeye3.setTeamType(SpecificObject.ENEMY_TEAM);
//		specificObjectManager.addObject(redeye3);
//    
//		SpecificObject redeye4 = new RedEyeDevil(500, 1190, this);
//		redeye4.setDirection(SpecificObject.RIGHT_DIR);
//		redeye4.setTeamType(SpecificObject.ENEMY_TEAM);
//		specificObjectManager.addObject(redeye4);
//    
//
//		SpecificObject darkraise3 = new DarkRaise(750, 650, this);
//		darkraise3.setTeamType(SpecificObject.ENEMY_TEAM);
//		specificObjectManager.addObject(darkraise3);
    
		SpecificObject robotR3 = new RobotR(1500, 1150, this);
		robotR3.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(robotR3);
    
		SpecificObject smallRedGun2 = new SmallRedGun(2254, 1201, this);
		smallRedGun2.setDirection(SpecificObject.LEFT_DIR);
		smallRedGun2.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(smallRedGun2);
	}
	
	public void initDifficultEnemies() {
		SpecificObject redeye = new RedEyeDevil(627, 474, this);
		redeye.setDirection(SpecificObject.LEFT_DIR);
		redeye.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(redeye);
    
		SpecificObject smallRedGun = new SmallRedGun(1573, 87, this);
		smallRedGun.setDirection(SpecificObject.LEFT_DIR);
		smallRedGun.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(smallRedGun);
    
		SpecificObject darkraise = new DarkRaise(1223, 855, this);
		darkraise.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(darkraise);
    
//		SpecificObject darkraise2 = new DarkRaise(2800, 350, this);
//	    darkraise2.setTeamType(SpecificObject.ENEMY_TEAM);
//	    specificObjectManager.addObject(darkraise2);

		SpecificObject robotR = new RobotR(572, 1112, this);
		robotR.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(robotR);

		SpecificObject robotR2 = new RobotR(1985, 540, this);
		robotR2.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(robotR2);


		SpecificObject redeye2 = new RedEyeDevil(1757,1440, this);
		redeye2.setDirection(SpecificObject.LEFT_DIR);
		redeye2.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(redeye2);

		SpecificObject redeye3 = new RedEyeDevil(1105, 213, this);
		redeye3.setDirection(SpecificObject.LEFT_DIR);
		redeye3.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(redeye3);

		SpecificObject redeye4 = new RedEyeDevil(2664, 1627, this);
		redeye4.setDirection(SpecificObject.RIGHT_DIR);
		redeye4.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(redeye4);

		SpecificObject darkraise3 = new DarkRaise(2561, 374, this);
		darkraise3.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(darkraise3);

		SpecificObject robotR3 = new RobotR(1250, 1688, this);
		robotR3.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(robotR3);

		SpecificObject smallRedGun2 = new SmallRedGun(191, 960, this);
		smallRedGun2.setDirection(SpecificObject.LEFT_DIR);
		smallRedGun2.setTeamType(SpecificObject.ENEMY_TEAM);
		specificObjectManager.addObject(smallRedGun2);
	}
	 
	public void switchState(int state) {
		previousState = this.state;
		this.state = state;
	}
	
	public void TutorialUpdate() {
		switch(tutorialState) {
			
			case INTROGAME:
				
				if(storyTutorial == 0) {
					if(openScopeY < MAX_OPEN_SCOPE_Y) {
						openScopeY += 4;
					} else storyTutorial ++;
				}else {
					if(currentSize < textTutorial.length()) currentSize ++;
				}
				break;
				
			case MEET_FINAL_BOSS:
				if(storyTutorial == 0) {
					if(openScopeY >= MAX_OPEN_SCOPE_Y) {
						openScopeY --;
					}
					
					if(camera.getPosX() < FINAL_BOSS_X)
						camera.setPosX(camera.getPosX() + 2);
					
					if(megaman.getPosX() < FINAL_BOSS_X + 150) {
						megaman.setDirection(Profile.RIGHT_DIR);
						megaman.run();
						megaman.Update();
					}else {
						megaman.stopRun();
					}
					
					if(openScopeY < MAX_OPEN_SCOPE_Y && camera.getPosX() >= FINAL_BOSS_X && megaman.getPosX() > FINAL_BOSS_X + 150) {
						camera.setLocked(true);
						storyTutorial ++ ;
						physicalMap.getPhysicalMap()[4][22] = 1;
						physicalMap.getPhysicalMap()[4][23] = 1;
						
						backgroundMap.getBackgroundMap[4][23] = 6;
						backgroundMap.getBackgroundMap[3][23] = 6;
						backgroundMap.getBackgroundMap[5][23] = 10;
						backgroundMap.getBackgroundMap[5][24] = 11;
						
					}
					
				}else {
					if(currentSize < textTutorial.length()) currentSize ++;
				}
				break;
			
		}
	}
	
	public void TutorialRender(Graphics g) {
		switch(tutorialState){
		
			case INTROGAME:
				
				int y = GameFrame.SCREEN_HEIGHT/2 - 15;
				int y1 = y - openScopeY/2 - GameFrame.SCREEN_HEIGHT/2;
				int y2 = y + openScopeY/2;
				
				g.setColor(Color.BLACK);
				g.fillRect(0, y1, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);
				g.fillRect(0, y2, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);
				
				if(storyTutorial >= 1) {
					g.drawImage(avatar.getImage(), 600, 350, null);
					g.setColor(Color.BLUE);
					g.fillRect(280, 450, 350, 80);
					g.setColor(Color.WHITE);
					String text = textTutorial.substring(0, currentSize - 1);
					drawString(g, text, 290, 480);
				}
				
				break;
				
			case MEET_FINAL_BOSS:
				
				y = GameFrame.SCREEN_HEIGHT/2 - 15;
				y1 = y - openScopeY/2 - GameFrame.SCREEN_HEIGHT/2;
				y2 = y + openScopeY/2;
				
				g.setColor(Color.BLACK);
				g.fillRect(0, y1, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);
				g.fillRect(0, y2, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);
				
				break;
		}
	}
	
	public void drawString(Graphics g, String text, int x, int y) {
		for(String str : text.split("\n"))
			g.drawString(str, x, y + g.getFontMetrics().getHeight());
	}
	 
	@Override
	public void processReleaseButton(int keyEvent) {
		switch(keyEvent) {
			
			case KeyEvent.VK_DOWN:
				megaman.standUp();
				break;
				
			case KeyEvent.VK_UP:
				
				break;
				
			case KeyEvent.VK_RIGHT:
				if(megaman.getSpeedX() > 0) megaman.stopRun();
				break;
				
			case KeyEvent.VK_LEFT:
				if(megaman.getSpeedX() < 0) megaman.stopRun();
				break;
				
			case KeyEvent.VK_Q:
				
				break;
				
			case KeyEvent.VK_SPACE:
				
				break;
				
			case KeyEvent.VK_ENTER:
				if(state == GAMEOVER || state == VICTORY) {
					gamePanel.setState(new MenuState(gamePanel));
					
				}else if(state == GAMEPAUSE) {
					state = lastState;
					
				}
				break;
				
			case KeyEvent.VK_ESCAPE:
				lastState = state;
				switchState(GAMEPAUSE);
		
		}
		
	}

	@Override
	public void processPressButton(int keyEvent) {
		switch(keyEvent) {
			
			case KeyEvent.VK_DOWN:
				megaman.kneel();
				break;
				
			case KeyEvent.VK_UP:
				// in construction
				break;
				
			case KeyEvent.VK_RIGHT:
				megaman.setDirection(Profile.RIGHT_DIR);
				megaman.run();
				break;
				
			case KeyEvent.VK_LEFT:
				megaman.setDirection(Profile.LEFT_DIR);
				megaman.run();
				break;
				
			case KeyEvent.VK_Q:
				megaman.attack();
				break;
				
			case KeyEvent.VK_SPACE:
				megaman.jump();
				break;
				
			case KeyEvent.VK_ENTER:
				if(state == INIT_GAME) {
					if(previousState == GAMEPLAY)
						switchState(GAMEPLAY);
					else switchState(TUTORIAL);
					
					bgMusic.loop();
					bgMusic.play();
				}
				
				if(state == TUTORIAL && storyTutorial >= 1) {
					if(storyTutorial <= 3) {
						storyTutorial ++ ;
						currentSize = 1;
						textTutorial = text[storyTutorial - 1];
					}else {
						switchState(GAMEPLAY);
					}
					
					if(tutorialState == MEET_FINAL_BOSS) {
						switchState(GAMEPLAY);
					}
				}
				break;
		
		}
		
	}

	@Override
	public void Update() {
		switch(state) {
		
		case INIT_GAME:
			break;
			
		case TUTORIAL:
			TutorialUpdate();
			break;
			
		case GAMEPLAY:
			specificObjectManager.UpdateObjects();
			bulletManager.UpdateObjects();
			
			camera.Update();
			physicalMap.Update();
			
			if(megaman.getPosX() > FINAL_BOSS_X && bossTriger) {
				bossTriger = false;
				switchState(TUTORIAL);
				tutorialState = MEET_FINAL_BOSS;
				storyTutorial = 0;
				openScopeY = 550;
				
				boss = new Boss(FINAL_BOSS_X + 700, 650, this);
				boss.setTeamType(Profile.ENEMY_TEAM);
				boss.setDirection(Profile.LEFT_DIR);
				specificObjectManager.addObject(boss);
			}
			
			if(megaman.getState() == Profile.DEATH) {
				life --;
				if(life >= 0) {
					megaman.setHealthPoint(100);
					megaman.setPosY(megaman.getPosY() - 50);
					megaman.setState(Profile.CANTBEHURT);
					specificObjectManager.addObject(megaman);
				}else {
					switchState(GameWorldState.GAMEOVER);
					bgMusic.stop();
				}
			}
			
			if(!bossTriger && boss.getState() == Profile.DEATH) {
				switchState(VICTORY);
			}
			
			break;
			
		case GAMEOVER:
			break;
			
		case VICTORY:
			break;
		}
		
	}

	@Override
	public void Render() {
		
		Graphics g = bufferedImage.getGraphics();
		
		if(g != null) {
			switch(state) {
			
			case INIT_GAME:
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
				g.setColor(Color.WHITE);
				g.drawString("PRESS ENTER TO START", 400, 400);
				break;
				
			case GAMEPAUSE:
				g.setColor(Color.BLACK);
				g.fillRect(300, 260, 500, 70);
				g.setColor(Color.WHITE);
				g.drawString("PRESS ENTER TO CONTINUE", 400, 400);
				break;
				
			case GAMEOVER:
				
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
				g.setColor(Color.WHITE);
				g.drawString("GAME OVER !!!", 450, 300);
				
				break;
				
			case TUTORIAL:
				backgroundMap.draw(g);
				if(tutorialState == MEET_FINAL_BOSS)
					specificObjectManager.draw(g);
				TutorialRender(g);
				break;
				
			case GAMEPLAY:
			case VICTORY:
				
				backgroundMap.draw(g);
				specificObjectManager.draw(g);
				bulletManager.draw(g);
				
				g.setColor(Color.GRAY);
				g.fillRect(19, 59, 102, 22);
				g.setColor(Color.RED);
				g.fillRect(20, 60 , megaman.getHealthPoint(), 20);
				
				for(int i = 0; i < life; i++) {
					g.drawImage(DataLoader.getInstance().getFrameImage("heart").getImage(), 20 + i * 40, 18, null);
				}
				
				if(state == VICTORY) {
					g.drawImage(DataLoader.getInstance().getFrameImage("gamewin").getImage(), 300, 300, null);
				}
				
				break;
			
			}
		}
		
	}
	
	public BufferedImage getBufferedImage() {
		// TODO Auto-generated method stub
		return bufferedImage;
	}

}
