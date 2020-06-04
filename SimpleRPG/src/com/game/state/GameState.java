package com.game.state;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.game.effect.DataLoader;
import com.game.effect.FrameImage;
import com.game.gameinteface.GameWorldState;
import com.game.gameinteface.Profile;
import com.game.gameobject.BackgroundMap;
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
    
    
    private int numberOfLife = 3;
    
    public AudioClip bgMusic;
    
    public GameState(GamePanel gamePanel, int difficulty){
            super(gamePanel);
        
        texts[0] = "We are heros, and our mission is protecting our Home\nEarth....";
        texts[1] = "There was a Monster from University on Earth in 10 years\n"
                + "and we lived in the scare in that 10 years....";
        texts[2] = "Now is the time for us, kill it and get freedom!....";
        texts[3] = "      LET'S GO!.....";
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
                    g2.drawImage(avatar.getImage(), 600, 350, null);
                    g2.setColor(Color.BLUE);
                    g2.fillRect(280, 450, 350, 80);
                    g2.setColor(Color.WHITE);
                    String text = textTutorial.substring(0, currentSize - 1);
                    drawString(g2, text, 290, 480);
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
                        megaMan.setBlood(100);
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
                    g2.drawString("PRESS ENTER TO CONTINUE", 400, 300);
                    break;
                case GAMEPAUSE:
                    g2.setColor(Color.BLACK);
                    g2.fillRect(300, 260, 500, 70);
                    g2.setColor(Color.WHITE);
                    g2.drawString("PRESS ENTER TO CONTINUE", 400, 300);
                    break;
                case TUTORIAL:
                    backgroundMap.draw(g2);
                    if(tutorialState == MEET_FINAL_BOSS){
                    	specificObjectManager.draw(g2);
                    }
                    TutorialRender(g2);
                    
                    break;
                case GAMEWIN:
                case GAMEPLAY:
                    backgroundMap.draw(g2);
                    specificObjectManager.draw(g2);  
                    bulletManager.draw(g2);
                    
                    g2.setColor(Color.GRAY);
                    g2.fillRect(19, 59, 102, 22);
                    g2.setColor(Color.red);
                    g2.fillRect(20, 60, megaMan.getBlood(), 20);
                    
                    for(int i = 0; i < numberOfLife; i++){
                        g2.drawImage(DataLoader.getInstance().getFrameImage("hearth").getImage(), 20 + i*40, 18, null);
                    }
                    
                    if(tutorialState == MEET_FINAL_BOSS) {
                    	g2.setColor(Color.GRAY);
                        g2.fillRect(GameFrame.SCREEN_WIDTH - 400, 59, 302, 22);
                        g2.setColor(Color.red);
                        g2.fillRect(GameFrame.SCREEN_WIDTH - 400 + 1, 60, boss.getBlood(), 20);
                    }
                    
                    if(state == GAMEWIN){
                        g2.drawImage(DataLoader.getInstance().getFrameImage("gamewin2").getImage(), 0, 0, null);
                    }
                    
                    break;
                case GAMEOVER:
//                    g2.setColor(Color.BLACK);
//                    g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
//                    g2.setColor(Color.WHITE);
//                    g2.drawString("GAME OVER!", 450, 300);
                	
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
                    
                    bgMusic.loop();
                    bgMusic.play();
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
                if(state == GAMEOVER || state == GAMEWIN) {
                    gamePanel.setState(new MenuState(gamePanel));
                } else if(state == GAMEPAUSE) {
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
