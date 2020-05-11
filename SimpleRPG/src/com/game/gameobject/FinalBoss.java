package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Hashtable;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class FinalBoss extends HumanoidObject {
	
	private Animation idleforward, idleback;
	private Animation shootingforward, shootingback;
	private Animation slideforward,slideback;
	
	private long startTimeForAttacked;
	
	private Hashtable<String, Long> timeAttack = new Hashtable<String, Long>();
	private String[] attackType = new String[4];
	private int attackIndex = 0;
	private long lastAttackTime;

	public FinalBoss(float posX, float posY, float width, float height, float mass, int healthPoint, int manaPoint,
			GameState gameState) {
		super(posX, posY, width, height, mass, healthPoint, manaPoint, gameState);
		idleback = DataLoader.getInstance().getAnimation("boss_idle");
		idleforward = DataLoader.getInstance().getAnimation("boss_idle");
		idleforward.flipAllImages();
		
		shootingback = DataLoader.getInstance().getAnimation("boss_shooting");
		shootingforward = DataLoader.getInstance().getAnimation("boss_shooting");
		shootingforward.flipAllImages();
		
		slideback = DataLoader.getInstance().getAnimation("boss_slide");
		slideforward = DataLoader.getInstance().getAnimation("boss_slide");
		slideforward.flipAllImages();
		
		setTimeForNoBeHurt(500*1000000);
		setDamage(16);
		
		attackType[0] = "NONE";
		attackType[1] = "shooting";
		attackType[2] = "NONE";
		attackType[3] = "slide";
		
		timeAttack.put("NONE", new Long(2000));
		timeAttack.put("shooting", new Long(500));
		timeAttack.put("slide", new Long(5000));
		
	
	}
	
	public void Update() {
		super.Update();
		
		if(getGameState().megaman.getPosX() > getPosX())
			setDirection(RIGHT_DIR);
		else
			setDirection(LEFT_DIR);
		
		if(startTimeForAttacked == 0)
			startTimeForAttacked = System.currentTimeMillis();
		else if(System.currentTimeMillis() - startTimeForAttacked > 300) {
				attack();
				startTimeForAttacked = System.currentTimeMillis();
			}
		
		if(!attackType[attackIndex].equals("NONE")) {
			if(attackType[attackIndex].equals("shooting")) {
				
				// bullet
				
			}else if (attackType[attackIndex].equals("slide")) {
				
				// set speed
				
				setPosX(getPosX()+ getSpeedX());
			}
		}else {
			setSpeedX(0);
		}
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
		
		if(System.currentTimeMillis() - lastAttackTime > timeAttack.get(attackType[attackIndex])) {
			lastAttackTime = System.currentTimeMillis();
			
			attackIndex++;
			if(attackIndex >= attackType.length) attackIndex = 0;
			
			if(attackType[attackIndex].equals("slide")) {
				if(getPosX() < getGameState().megaman.getPosX())
					setSpeedX(5);
				else
					setSpeedX(-5);
			}
		}
		
	}
	


	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		if(attackType[attackIndex].equals("slide")) {
			Rectangle rect = getBoundForCollisionWithMap();
			rect.y +=100;
			rect.height -= 100;
			return rect;
		}else
			return getBoundForCollisionWithMap();
	}

	@Override
	public void draw(Graphics g) {
		
	}

}
