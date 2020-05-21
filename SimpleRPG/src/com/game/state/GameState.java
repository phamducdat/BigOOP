package com.game.state;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;

import com.game.gameinteface.GameWorldState;
import com.game.gameobject.BackgroundMap;
import com.game.gameobject.Camera;
import com.game.gameobject.Hero;
import com.game.gameobject.PhysicalMap;
import com.game.gameobject.RedEyeDevil;
import com.game.gameobject.SmallRedGun;
import com.game.gameobject.SpecificObject;
import com.game.objectmanager.BulletManager;
import com.game.objectmanager.SpecificObjectManager;
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
	
	public GameState(GamePanel gamePanel) {
		super(gamePanel);
		
		megaman = new  Hero(0, 0, this);
		camera = new Camera(0, 0, 0, 0, this);
		
		physicalMap = new PhysicalMap(0, 0, this);
		backgroundMap =  new BackgroundMap(0, 0, this);
		
		specificObjectManager = new SpecificObjectManager(this);
		
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
    
//    SpecificObject darkraise2 = new DarkRaise(2800, 350, this);
//    darkraise2.setTeamType(SpecificObject.ENEMY_TEAM);
//    specificObjectManager.addObject(darkraise2);
    
    SpecificObject robotR = new RobotR(572, 1112, this);
    robotR.setTeamType(SpecificObject.ENEMY_TEAM);
    specificObjectManager.addObject(robotR);
    
//    SpecificObject robotR2 = new RobotR(3400, 350, this);
//    robotR2.setTeamType(SpecificObject.ENEMY_TEAM);
//    specificObjectManager.addObject(robotR2);
    
    
    SpecificObject redeye2 = new RedEyeDevil(1757,1440, this);
    redeye2.setDirection(SpecificObject.LEFT_DIR);
    redeye2.setTeamType(SpecificObject.ENEMY_TEAM);
    specificObjectManager.addObject(redeye2);
    
//    SpecificObject redeye3 = new RedEyeDevil(3450, 500, this);
//    redeye3.setDirection(SpecificObject.LEFT_DIR);
//    redeye3.setTeamType(SpecificObject.ENEMY_TEAM);
//    specificObjectManager.addObject(redeye3);
    
//    SpecificObject redeye4 = new RedEyeDevil(500, 1190, this);
//    redeye4.setDirection(SpecificObject.RIGHT_DIR);
//    redeye4.setTeamType(SpecificObject.ENEMY_TEAM);
//    specificObjectManager.addObject(redeye4);
//    

//    SpecificObject darkraise3 = new DarkRaise(750, 650, this);
//    darkraise3.setTeamType(SpecificObject.ENEMY_TEAM);
//    specificObjectManager.addObject(darkraise3);
    
//    SpecificObject robotR3 = new RobotR(1500, 1150, this);
//    robotR3.setTeamType(SpecificObject.ENEMY_TEAM);
//    specificObjectManager.addObject(robotR3);
    
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
	    
//	    SpecificObject darkraise2 = new DarkRaise(2800, 350, this);
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
	@Override
	public void processPressButton(int keyEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processReleaseButton(int keyEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Render() {
		// TODO Auto-generated method stub
		
	}

}
