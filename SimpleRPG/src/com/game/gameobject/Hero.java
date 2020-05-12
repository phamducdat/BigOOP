package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.state.GameState;

public class Hero extends HumanoidObject{
	
	public static final int RUNSPEED = 3;
	


	public Hero(float posX, float posY, float width, float height, float mass, int healthPoint, int manaPoint,
			GameState gameState) {
		super(posX, posY, width, height, mass, healthPoint, manaPoint, gameState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kneel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void standUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
