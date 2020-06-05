package com.game.state;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import com.game.gameobject.FinalBoss;
import com.game.gameobject.FinalBossHard;
import com.game.gameobject.RobotR;

// Done

public class GameState extends State implements GameWorldState {
	
    private BufferedImage bufferedImage;
    private int lastState;
    
    public int difficulty;

    public SpecificObjectManager specificObjectManager;
    public BulletManager bulletManager;

    public Hero megaMan;
   
    public PhysicalMap physicalMap;
    public BackgroundMap backgroundMap;
    public Camera camera;
    
    public int openIntroGameY = 0;
    public int state = INIT_GAME;
    public int previousState = state;
    public int tutorialState = INTROGAME;
    
    public int storyTutorial = 0;
    public String[] texts = new String[4];

    public String textTutorial;
    public int currentSize = 1;
    
    private boolean finalbossTrigger = true;
    SpecificObject boss;
    
    FrameImage avatar = DataLoader.getInstance().getFrameImage("avatar");
    
    FrameImage tutorialWindow = DataLoader.getInstance().getFrameImage("tutorialwindow");
    FrameImage buttonQ = DataLoader.getInstance().getFrameImage("buttonQ");
    FrameImage buttonW = DataLoader.getInstance().getFrameImage("buttonW");
    FrameImage buttonE = DataLoader.getInstance().getFrameImage("buttonE");
    FrameImage buttonR = DataLoader.getInstance().getFrameImage("buttonR");
    FrameImage buttonRIGHT = DataLoader.getInstance().getFrameImage("buttonRIGHT");
    FrameImage buttonLEFT = DataLoader.getInstance().getFrameImage("buttonLEFT");
    FrameImage buttonD = DataLoader.getInstance().getFrameImage("buttonD");
    FrameImage buttonF = DataLoader.getInstance().getFrameImage("buttonF");
    FrameImage buttonSPACE = DataLoader.getInstance().getFrameImage("buttonSPACE");
    
    FrameImage herobar = DataLoader.getInstance().getFrameImage("herobar");
    FrameImage herohp = DataLoader.getInstance().getFrameImage("herohp");
    FrameImage heromana = DataLoader.getInstance().getFrameImage("heromana");
    FrameImage bossbar = DataLoader.getInstance().getFrameImage("bossbar");
    FrameImage bosshp = DataLoader.getInstance().getFrameImage("bosshp");
    FrameImage bossarmor = DataLoader.getInstance().getFrameImage("bossarmor");
    
    
    private int numberOfLife = 3;
    
    public AudioClip bgMusic = DataLoader.getInstance().getSound("bgmusic");
    public AudioClip bgMusic2 = DataLoader.getInstance().getSound("bgmusic");
    public AudioClip welcome = DataLoader.getInstance().getSound("welcome");
    
    public GameState(GamePanel gamePanel, int difficulty){
            super(gamePanel);
        
            texts[0] = "Our Earth is being threatened by aliens ... \nIt's time for us to save our planet ... \nHere're some advice for a beginner...  ";
            texts[1] = "Press \n\nto control your character to move ... \n\nPress                     to jump ... \n\nPress             to shoot ...";
            texts[2] = "Here is some of your special skills ... \n\nDash \n\n\n Smite \n\n\nAnd others ";
            texts[3] = "I think you've been ready. \n\nLET'S GO !!!!!";
            textTutorial = texts[0];

        
        bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        megaMan = new Hero(400, 400, this);
        physicalMap = new PhysicalMap(0, 0, this);
        backgroundMap = new BackgroundMap(0, 0, this);
        camera = new Camera(0, 50, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
        bulletManager = new BulletManager(this);
        
        specificObjectManager = new SpecificObjectManager(this);
        specificObjectManager.addObject(megaMan);

        bgMusic = DataLoader.getInstance().getSound("bgmusic");
        this.difficulty = difficulty;
        
        if(difficulty == EASY) {
        	initEasyEnemies();
        }else initDifficultEnemies();
    }
    
    private void initEasyEnemies(){
    	 SpecificObject redeye = new RedEyeDevil(627, 474, this);
         redeye.setDirection(Profile.LEFT_DIR);
         redeye.setTeamType(Profile.ENEMY_TEAM);
         specificObjectManager.addObject(redeye);
         
         SpecificObject smallRedGun = new SmallRedGun(237, 291, this);
         smallRedGun.setDirection(Profile.LEFT_DIR);
         smallRedGun.setTeamType(Profile.ENEMY_TEAM);
         specificObjectManager.addObject(smallRedGun);
         
         SpecificObject darkraise = new DarkRaise(1223, 855, this);
         darkraise.setTeamType(Profile.ENEMY_TEAM);
         specificObjectManager.addObject(darkraise);
         
//         SpecificObject darkraise2 = new DarkRaise(279, 1362, this);
//         darkraise2.setTeamType(Profile.ENEMY_TEAM);
//         specificObjectManager.addObject(darkraise2);
         
         SpecificObject robotR = new RobotR(542, 1112, this);
         robotR.setTeamType(Profile.ENEMY_TEAM);
         specificObjectManager.addObject(robotR);
         
         SpecificObject robotR2 = new RobotR(1985, 744, this);
         robotR2.setTeamType(Profile.ENEMY_TEAM);
         specificObjectManager.addObject(robotR2);
         
         
//         SpecificObject redeye2 = new RedEyeDevil(1763, 1370, this);
//         redeye2.setDirection(Profile.LEFT_DIR);
//         redeye2.setTeamType(Profile.ENEMY_TEAM);
//         specificObjectManager.addObject(redeye2);
         
         SpecificObject redeye3 = new RedEyeDevil(1105, 213, this);
         redeye3.setDirection(Profile.LEFT_DIR);
         redeye3.setTeamType(Profile.ENEMY_TEAM);
         specificObjectManager.addObject(redeye3);
         
//         SpecificObject redeye4 = new RedEyeDevil(2664, 1627, this);
//         redeye4.setDirection(Profile.LEFT_DIR);
//         redeye4.setTeamType(Profile.ENEMY_TEAM);
//         specificObjectManager.addObject(redeye4);
         

         SpecificObject darkraise3 = new DarkRaise(2561, 1058, this);
         darkraise3.setTeamType(Profile.ENEMY_TEAM);
         specificObjectManager.addObject(darkraise3);
         
         SpecificObject robotR3 = new RobotR(1250, 1688, this);
         robotR3.setTeamType(Profile.ENEMY_TEAM);
         specificObjectManager.addObject(robotR3);
         
         
//         SpecificObject smallRedGun2 = new SmallRedGun(2254, 1433, this);
//         smallRedGun2.setDirection(Profile.LEFT_DIR);
//         smallRedGun2.setTeamType(Profile.ENEMY_TEAM);
//         specificObjectManager.addObject(smallRedGun2);
    }
    
    private void initDifficultEnemies(){
    	initEasyEnemies();
//        SpecificObject redeye = new RedEyeDevil(627, 474, this);
//        redeye.setDirection(Profile.LEFT_DIR);
//        redeye.setTeamType(Profile.ENEMY_TEAM);
//        specificObjectManager.addObject(redeye);
//        
//        SpecificObject smallRedGun = new SmallRedGun(237, 291, this);
//        smallRedGun.setDirection(Profile.LEFT_DIR);
//        smallRedGun.setTeamType(Profile.ENEMY_TEAM);
//        specificObjectManager.addObject(smallRedGun);
//        
//        SpecificObject darkraise = new DarkRaise(1223, 855, this);
//        darkraise.setTeamType(Profile.ENEMY_TEAM);
//        specificObjectManager.addObject(darkraise);
        
        SpecificObject darkraise2 = new DarkRaise(279, 1362, this);
        darkraise2.setTeamType(Profile.ENEMY_TEAM);
        specificObjectManager.addObject(darkraise2);
        
//        SpecificObject robotR = new RobotR(542, 1112, this);
//        robotR.setTeamType(Profile.ENEMY_TEAM);
//        specificObjectManager.addObject(robotR);
//        
//        SpecificObject robotR2 = new RobotR(1985, 744, this);
//        robotR2.setTeamType(Profile.ENEMY_TEAM);
//        specificObjectManager.addObject(robotR2);
//        
        
        SpecificObject redeye2 = new RedEyeDevil(1763, 1370, this);
        redeye2.setDirection(Profile.LEFT_DIR);
        redeye2.setTeamType(Profile.ENEMY_TEAM);
        specificObjectManager.addObject(redeye2);
        
//        SpecificObject redeye3 = new RedEyeDevil(1105, 213, this);
//        redeye3.setDirection(Profile.LEFT_DIR);
//        redeye3.setTeamType(Profile.ENEMY_TEAM);
//        specificObjectManager.addObject(redeye3);
        
        SpecificObject redeye4 = new RedEyeDevil(2664, 1627, this);
        redeye4.setDirection(Profile.LEFT_DIR);
        redeye4.setTeamType(Profile.ENEMY_TEAM);
        specificObjectManager.addObject(redeye4);
        

//        SpecificObject darkraise3 = new DarkRaise(2561, 1058, this);
//        darkraise3.setTeamType(Profile.ENEMY_TEAM);
//        specificObjectManager.addObject(darkraise3);
//        
//        SpecificObject robotR3 = new RobotR(1250, 1688, this);
//        robotR3.setTeamType(Profile.ENEMY_TEAM);
//        specificObjectManager.addObject(robotR3);
        
        
        SpecificObject smallRedGun2 = new SmallRedGun(2254, 1433, this);
        smallRedGun2.setDirection(Profile.LEFT_DIR);
        smallRedGun2.setTeamType(Profile.ENEMY_TEAM);
        specificObjectManager.addObject(smallRedGun2);
    }

    public void switchState(int state){
        previousState = this.state;
        this.state = state;
    }
    
    private void TutorialUpdate(){
        switch(tutorialState){
            case INTROGAME:
                
                if(storyTutorial == 0){
                    if(openIntroGameY < 450) {
                        openIntroGameY += 10;
                    }else storyTutorial ++;
                    
                }else{
                
                    if(currentSize < textTutorial.length()) currentSize++;
                }
                break;
            case MEET_FINAL_BOSS:
                if(storyTutorial == 0){
                    if(openIntroGameY >= 450) {
                        openIntroGameY -= 10;
                    }
                    if(camera.getPosX() < finalBossX){
                        camera.setPosX(camera.getPosX() + 10);
                    }
                    
                    if(megaMan.getPosX() < finalBossX + 150){
                        megaMan.setDirection(Profile.RIGHT_DIR);
                        megaMan.run();
                        megaMan.Update();
                    }else{
                        megaMan.stopRun();
                    }
                    
                    if(openIntroGameY < 450 && camera.getPosX() >= finalBossX && megaMan.getPosX() >= finalBossX + 150){ 
                        camera.lockBoss();
                        storyTutorial++;
                        megaMan.stopRun();
                        physicalMap.phys_map[4][22] = 1;
                        physicalMap.phys_map[4][23] = 1;
                        physicalMap.phys_map[5][22] = 1;
                        physicalMap.phys_map[5][23] = 1;
                        
                        backgroundMap.map[3][22] = 4;
                        backgroundMap.map[4][22] = 4;
                        backgroundMap.map[5][22] = 4;
                        backgroundMap.map[3][23] = 6;
                        backgroundMap.map[4][23] = 6;
                        backgroundMap.map[5][23] = 10;
                        backgroundMap.map[5][24] = 11;
                        
                    }
                    
                }else{
                
                    if(currentSize < textTutorial.length()) currentSize++;
                }
                break;
        }
    }
    
    private void drawString(Graphics2D g2, String text, int x, int y){
    	
    	if(storyTutorial == 2) {
    		if(currentSize >= 6) {
        		g2.drawImage(buttonRIGHT.getImage(), 390, 180, null);
        		g2.drawImage(buttonLEFT.getImage(), 300, 180, null);	
        	}
    		
    		if(currentSize >= 50) {
        		g2.drawImage(buttonSPACE.getImage(), 280, 310, null);
        	}
    		
    		if(currentSize >= 90) {
        		g2.drawImage(buttonQ.getImage(), 280, 380, null);
        	}
    	}else if(storyTutorial == 3) {
    		
    		if(currentSize >= 40)
    			g2.drawImage(buttonD.getImage(), 350, 260, null);
    		
    		if(currentSize >= 50)
    			g2.drawImage(buttonF.getImage(), 350, 350, null);
    		
    		if(currentSize >= 60) {
    			g2.drawImage(buttonW.getImage(), 350, 440, null);
    			g2.drawImage(buttonE.getImage(), 440, 440, null);
    			g2.drawImage(buttonR.getImage(), 530, 440, null);
    		}
    		
    	}
    	
    	g2.setColor(Color.BLACK);
    	g2.setFont(new Font("BK", Font.BOLD, 24));
    	
        for(String str : text.split("\n"))
            g2.drawString(str, x, y+=g2.getFontMetrics().getHeight());
    }
    
    private void TutorialRender(Graphics2D g2){
        switch(tutorialState){
            case INTROGAME:
                int yMid = GameFrame.SCREEN_HEIGHT/2 - 15;
                int y1 = yMid - GameFrame.SCREEN_HEIGHT/2 - openIntroGameY/2;
                int y2 = yMid + openIntroGameY/2;

                g2.setColor(Color.BLACK);
                g2.fillRect(0, y1, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);
                g2.fillRect(0, y2, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);
                
                if(storyTutorial >= 1){
                	g2.drawImage(tutorialWindow.getImage(), 150, 150, null);
                    g2.drawImage(avatar.getImage(), 600, 450, null);
                    
                    String text = textTutorial.substring(0, currentSize - 1);
                    drawString(g2, text, 200, 200);
                }
                
                break;
            case MEET_FINAL_BOSS:
                yMid = GameFrame.SCREEN_HEIGHT/2 - 15;
                y1 = yMid - GameFrame.SCREEN_HEIGHT/2 - openIntroGameY/2;
                y2 = yMid + openIntroGameY/2;

                g2.setColor(Color.BLACK);
                g2.fillRect(0, y1, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);
                g2.fillRect(0, y2, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);
                break;
        }
    }
    
public void Update(){
        
        switch(state){
            case INIT_GAME:
                
                break;
            case TUTORIAL:
                TutorialUpdate();
                
                break;
            case GAMEPLAY:
            	specificObjectManager.UpdateObjects();
                bulletManager.UpdateObjects();
        
                physicalMap.Update();
                camera.Update();
                
                
                if(megaMan.getPosX() > finalBossX && finalbossTrigger){
                    finalbossTrigger = false;
                    switchState(TUTORIAL);
                    tutorialState = MEET_FINAL_BOSS;
                    storyTutorial = 0;
                    openIntroGameY = 550;
                    
                    boss = new FinalBoss(finalBossX + 700, 560, this);
                    
                    if(difficulty == EASY) {
                    	boss = new FinalBoss(finalBossX + 700, 560, this);
                    } else 
                    	boss = new FinalBossHard(finalBossX + 700, 560, this);
                    
                    boss.setTeamType(Profile.ENEMY_TEAM);
                    boss.setDirection(Profile.LEFT_DIR);
                    specificObjectManager.addObject(boss);

                }
                
                if(megaMan.getState() == Profile.DEATH){
                    numberOfLife --;
                    if(numberOfLife >= 0){
                        megaMan.setBlood(Hero.MAXHP);
                        megaMan.setPosY(megaMan.getPosY() - 50);
                        megaMan.setState(Profile.CANTBEHURT);
                        specificObjectManager.addObject(megaMan);
                    }else{
                        switchState(GAMEOVER);
                        bgMusic.stop();
                    }
                }
                if(!finalbossTrigger && boss.getState() == Profile.DEATH)
                    switchState(GAMEWIN);
                
                break;
                
            case GAMEPAUSE:
            	
            	break;
            case GAMEOVER:
            	
                break;
            case GAMEWIN:
                
                break;
        }
        

    }

public void Render(){

    Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();

    if(g2!=null){

        // NOTE: two lines below make the error splash white screen....
        // need to remove this line
        //g2.setColor(Color.WHITE);
        //g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
        
        
        //physicalMap.draw(g2);
        
        switch(state){
            case INIT_GAME:
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
                g2.setColor(Color.WHITE);
                g2.drawString("PRESS ENTER TO CONTINUE", 400, 350);
                break;
            case GAMEPAUSE:
                g2.setColor(Color.BLACK);
                g2.fillRect(200, 300, 600, 100);
                g2.setColor(Color.WHITE);
                g2.drawString("PRESS ENTER TO CONTINUE", 400, 350);
                break;
            case TUTORIAL:
                backgroundMap.draw(g2);
                if(tutorialState == MEET_FINAL_BOSS){
                	specificObjectManager.draw(g2);
                }
                TutorialRender(g2);
                
                break;
            case GAMEWIN:
            	
                g2.drawImage(DataLoader.getInstance().getFrameImage("gamewin1").getImage(), 300, 300, null);
                
            	break;
            case GAMEPLAY:
                backgroundMap.draw(g2);
                specificObjectManager.draw(g2);  
                bulletManager.draw(g2);
                
                g2.drawImage(herobar.getImage(), 20, 20, null);
                int bloodBar = (int) (megaMan.getBlood() / (float) Hero.MAXHP * herohp.getWidthImage()); 
                int manaBar = (int) ((float) megaMan.getMana() / Hero.MAX_MANA_POINT * heromana.getWidthImage());
                
                if(bloodBar != 0) {
                	BufferedImage currentBlood = herohp.getImage().getSubimage(0, 0, bloodBar, herohp.getHeightImage());
                    g2.drawImage(currentBlood, 77, 32, null);
                }
                
                if(manaBar != 0) {
                	BufferedImage currentMana = heromana.getImage().getSubimage(0, 0, manaBar, 6);
                    g2.drawImage(currentMana, 77, 48, null);
                }
                
//                g2.fillRect(20, 60, megaMan.getBlood(), 20);
                
//                for(int i = 0; i < numberOfLife; i++){
//                    g2.drawImage(DataLoader.getInstance().getFrameImage("hearth").getImage(), 20 + i*40, 18, null);
//                }
                
                if(tutorialState == MEET_FINAL_BOSS) {
                	g2.drawImage(bossbar.getImage(), 750, 20, null);
                    int bloodBarB = (int) ((float) boss.getBlood() / Boss.MAXHP * bosshp.getWidthImage()); 
                    int armorBarB = (int) ((float) boss.getArmor() / Boss.MAXARMOR * bossarmor.getWidthImage());
                    
                    if(bloodBarB != 0) {
                    	BufferedImage currentBossBlood = bosshp.getImage().getSubimage(0, 0, bloodBarB, bosshp.getHeightImage());
                        g2.drawImage(currentBossBlood, 764 + bosshp.getWidthImage() - bloodBarB, 37, null);
                    }
                    
                    if(armorBarB != 0) {
                    	BufferedImage currentBossArmor = bossarmor.getImage().getSubimage(0, 0, armorBarB, bossarmor.getHeightImage());
                        g2.drawImage(currentBossArmor, 834 + bossarmor.getWidthImage() - armorBarB, 25, null);
                    }
                    
                }
                
                break;
            case GAMEOVER:
//                g2.setColor(Color.BLACK);
//                g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
//                g2.setColor(Color.WHITE);
//                g2.drawString("GAME OVER!", 450, 300);
            	
            	g2.drawImage(DataLoader.getInstance().getFrameImage("gamelose").getImage(), 0, 0, null);
            	
                break;

        }
        

    }

}

    public BufferedImage getBufferedImage(){
        return bufferedImage;
    }

    @Override
    public void processPressButton(int code) {
        switch(code){
             
             case KeyEvent.VK_DOWN:
                 megaMan.dick();
                 break;
                 
             case KeyEvent.VK_RIGHT:
                 megaMan.setDirection(megaMan.RIGHT_DIR);
                 megaMan.run();
                 break;
                 
             case KeyEvent.VK_LEFT:
                 megaMan.setDirection(megaMan.LEFT_DIR);
                 megaMan.run();
                 break;
                 
             case KeyEvent.VK_ENTER:
                 if(state == GameWorldState.INIT_GAME){
                     if(previousState == GameWorldState.GAMEPLAY)
                         switchState(GameWorldState.GAMEPLAY);
                     else switchState(GameWorldState.TUTORIAL);
                     
                     welcome.play();
                     bgMusic2.play();
                 }
                 if(state == GameWorldState.TUTORIAL && storyTutorial >= 1){
                     if(storyTutorial<=3){
                         storyTutorial ++;
                         currentSize = 1;
                         textTutorial = texts[storyTutorial-1];
                     }else{
                         switchState(GameWorldState.GAMEPLAY);
                     }
                     
                     // for meeting boss tutorial
                     if(tutorialState == GameWorldState.MEET_FINAL_BOSS){
                         switchState(GameWorldState.GAMEPLAY);
                     }
                 }
                 if(state == GAMEPAUSE) {
                 	state = GAMEPLAY;
                 }
                 break;
                 
             case KeyEvent.VK_SPACE:
                 megaMan.jump();
                 break;
                 
             case KeyEvent.VK_Q:
                 megaMan.attack();
                 break;
                
                 
         }
     }

    @Override
    public void processReleaseButton(int code) {
        switch(code){
            
            case KeyEvent.VK_UP:
                
                break;
                
            case KeyEvent.VK_DOWN:
                megaMan.standUp();
                break;
                
            case KeyEvent.VK_RIGHT:
                if(megaMan.getSpeedX() > 0)
                    megaMan.stopRun();
                break;
                
            case KeyEvent.VK_LEFT:
                if(megaMan.getSpeedX() < 0)
                    megaMan.stopRun();
                break;
                
            case KeyEvent.VK_ENTER:
                if(state == GAMEOVER) {
                    gamePanel.setState(new LoseState(gamePanel, this.difficulty));
                } else if(state == GAMEWIN) {
                	gamePanel.setState(new WinState(gamePanel));
                    
                }else if(state == GAMEPAUSE) {
                	state = lastState;
                }
                break;
                
            case KeyEvent.VK_SPACE:
            
                break;
                
            case KeyEvent.VK_Q:
                
                break;
            case KeyEvent.VK_ESCAPE:
                lastState = state;
                state = GAMEPAUSE;
                break;
                
        }
    }
}
