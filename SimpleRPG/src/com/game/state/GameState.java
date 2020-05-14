package com.game.state;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;

import com.game.gameinteface.GameWorldState;
import com.game.gameobject.BackgroundMap;
import com.game.gameobject.Camera;
import com.game.gameobject.Hero;
import com.game.gameobject.PhysicalMap;
import com.game.gameobject.SpecificObject;
import com.game.objectmanager.SpecificObjectManager;
import com.game.userinterface.GamePanel;

public class GameState extends State implements GameWorldState {
	
	public Hero megaman;
	public Camera camera;
	
	public PhysicalMap physicalMap;
	public BackgroundMap backgroundMap;
	
	public SpecificObjectManager specificObjectManager;
	
	
	private BufferedImage bufferedImage;
	
	public GameState(GamePanel gamePanel) {
		super(gamePanel);
		
		megaman = new  Hero(0, 0, this);
		camera = new Camera(0, 0, 0, 0, this);
		
		physicalMap = new PhysicalMap(0, 0, this);
		backgroundMap =  new BackgroundMap(0, 0, this);
		
		specificObjectManager = new SpecificObjectManager(this);
		
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
