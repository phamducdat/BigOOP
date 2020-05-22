package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Hashtable;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;


public class FinalBossHard extends HumanoidObject {
	
	private Animation idleforward, idleback;
	private Animation shootingforward, shootingback;
	private Animation slideforward, slideback;
	private Animation transfigurationback, transfigurationforward;
	private Animation readyforward, readyback;
	private Animation standupforward, standupback;
	private Animation ultifirstforward, ultifirstback;
	private Animation ultilastforward, ultilastback;
	
	private long startTimeForAttacked;

	private Hashtable<String, Long> timeAttack = new Hashtable<String, Long>();
	private ArrayList<String> attackType =

			new ArrayList<String>();
	private ArrayList<Animation> a = new ArrayList<Animation>();
	private int attackIndex = 0;


public FinalBossHard(float x, float y, GameStateState GameState) {
		
		super(x, y, 110, 150, 0.1f, 100, GameState);
		idleback = DataLoader.getInstance().getAnimation("boss_idle");
		idleforward = DataLoader.getInstance().getAnimation("boss_idle");
		idleforward.flipAllImages();

		shootingback = DataLoader.getInstance().getAnimation("boss_shooting");
		shootingforward = DataLoader.getInstance().getAnimation("boss_shooting");
		shootingforward.flipAllImages();

		slideback = DataLoader.getInstance().getAnimation("boss_slide");
		slideforward = DataLoader.getInstance().getAnimation("boss_slide");
		slideforward.flipAllImages();

		readyback = DataLoader.getInstance().getAnimation("boss_ready");
		readyforward = DataLoader.getInstance().getAnimation("boss_ready");
		readyforward.flipAllImages();

		transfigurationback = DataLoader.getInstance().getAnimation("boss_transfiguration");
		transfigurationforward = DataLoader.getInstance().getAnimation("boss_transfiguration");
		transfigurationforward.flipAllImages();

		ultifirstback = DataLoader.getInstance().getAnimation("boss_ultifirst");
		ultifirstforward = DataLoader.getInstance().getAnimation("boss_ultifirst");
		ultifirstforward.flipAllImages();

		ultilastback = DataLoader.getInstance().getAnimation("boss_ultilast");
		ultilastforward = DataLoader.getInstance().getAnimation("boss_ultilast");
		ultifirstforward.flipAllImages();

		standupback = DataLoader.getInstance().getAnimation("boss_transfiguration");
		standupforward = DataLoader.getInstance().getAnimation("boss_transfiguration");
		standupback.reverse();
		standupforward.reverse();

		setTimeForNoBeHurt(500 * 1000000);
		setDamage(10);

		attackType.add("NONE");
		
		
		
		
		attackType.add("ready");
		attackType.add("shooting");
		attackType.add("NONE");
		attackType.add("transfiguration");
		attackType.add("slide");
		attackType.add("standup");
		attackType.add("ultifirst");
		attackType.add("ultilast");

		timeAttack.put("NONE", new Long(2000));
		timeAttack.put("shooting", shootingback.time());
		timeAttack.put("transfiguration", transfigurationback.time()); // bug
		timeAttack.put("ready", readyback.time());
		timeAttack.put("slide", new Long(5000));
		timeAttack.put("standup", (transfigurationback.time()));
		timeAttack.put("ultifirst", ultifirstback.time());
		timeAttack.put("ultilast", ultilastback.time());

	}

	@Override
	public void Update() {
		super.Update();

		if (getGameState().megaman.getPosX() > getPosX())
			setDirection(RIGHT_DIR);
		else
			setDirection(LEFT_DIR);

		attack();

		if (!attackType.get(attackIndex).equals("NONE")) {
			if (attackType.get(attackIndex).equals("shooting")) {

//				Bullet bullet = new RocketBullet(getPosX(), getPosY() - 50, getGameState());
//				if (getDirection() == RIGHT_DIR) {
//					bullet.setSpeedX(4);
//				} else
//					bullet.setSpeedX(-4);
//
//				bullet.setTeamType(getTeamType());
//				getGameState().bulletManager.addObject(bullet);

			} else if (attackType.get(attackIndex).equals("slide")) {

				if ((getPosX() - 3600) % 100 == 0) {
					BulletSlide bullet = new BulletSlide(getPosX(), getPosY() + 50, getGameState());
					if (getSpeedX() < 0) {
						bullet.setDirection(RIGHT_DIR);
						bullet.setSpeedX(-7);
						bullet.setPosX(bullet.getPosX() - 40);
						if (getSpeedX() != 0 && getSpeedY() == 0) {
							bullet.setPosX(bullet.getPosX() - 10);
							bullet.setPosY(bullet.getPosY() - 5);
						}
					} else {
						bullet.setDirection(LEFT_DIR);
						bullet.setSpeedX(7);
						bullet.setPosX(bullet.getPosX() + 40);
						if (getSpeedX() != 0 && getSpeedY() == 0) {
							bullet.setPosX(bullet.getPosX() + 10);
							bullet.setPosY(bullet.getPosY() - 5);
						}
					}

					bullet.setTeamType(getTeamType());
					getGameState().bulletManager.addObject(bullet);

//				}

				if (getGameState().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null) {
					setSpeedX(5);
				}
				if (getGameState().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null) {
					setSpeedX(-5);
				}
				setPosX(getPosX() + getSpeedX());

			} else if (attackType.get(attackIndex).equals("ultilast")) {

//				Bullet bullet = new bossBullet(getPosX(), getPosY() - 50, getGameState());
//				if (getDirection() == RIGHT_DIR) {
//					bullet.setSpeedX(4);
//				} else {
//					bullet.setSpeedX(-4);
//				}
//				bullet.setTeamType(getTeamType());
//				getGameState().bulletManager.addObject(bullet);
//			}

			else {
				setSpeedX(0);
			}

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
		
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		if (attackType.get(attackIndex).equals("slide")) {
			Rectangle rect = getBoundForCollisionWithMap();
			rect.y += 100;
			rect.height -= 100;
			return rect;
		} else
			return getBoundForCollisionWithMap();
	}

	@Override
	public void draw(Graphics g) {
		 if (getState() == CANTBEHURT && (System.nanoTime() / 10000000) % 2 != 1) {
	} else {

		if (attackType.get(attackIndex).equals("NONE")) {

			if (getDirection() == RIGHT_DIR) {

				idleforward.Update(System.nanoTime());
				idleforward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			} else {
				idleback.Update(System.nanoTime());
				idleback.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			}
		} else if (attackType.get(attackIndex).equals("shooting")) {
			if (getDirection() == RIGHT_DIR) {
				shootingforward.Update(System.nanoTime());
				shootingforward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			} else {
				shootingback.Update(System.nanoTime());
				shootingback.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			}

		}

		else if (attackType.get(attackIndex).equals("transfiguration")) {

			if (getDirection() == RIGHT_DIR) {

				transfigurationforward.Update(System.nanoTime());
				transfigurationforward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			} else {
				transfigurationback.Update(System.nanoTime());

				if (transfigurationback.isLastFrame()) {

				}
				transfigurationback.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			}

		} else if (attackType.get(attackIndex).equals("slide")) {
			if (getSpeedX() > 0) {
				slideforward.Update(System.nanoTime());
				slideforward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY() + 50);
			} else {

				slideback.Update(System.nanoTime());
				slideback.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY() + 50);
			}
		}

		else if (attackType.get(attackIndex).equals("ready")) {

			if (getDirection() == RIGHT_DIR) {
				readyforward.Update(System.nanoTime());
				readyforward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			} else {
				readyback.Update(System.nanoTime());
				readyback.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			}
		}

		else if (attackType.get(attackIndex).equals("standup")) {
			if (getDirection() == RIGHT_DIR) {
				standupforward.Update(System.nanoTime());

				standupforward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			} else {
				standupback.Update(System.nanoTime());
				standupback.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			}
		} else if (attackType.get(attackIndex).equals("ultifirst")) {
			if (getDirection() == RIGHT_DIR) {
				ultifirstforward.Update(System.nanoTime());
				ultifirstforward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			} else {
				ultifirstback.Update(System.nanoTime());
				ultifirstback.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			}
		}

		else if (attackType.get(attackIndex).equals("ultilast")) {
			if (getDirection() == RIGHT_DIR) {
				ultilastforward.Update(System.nanoTime());
				ultilastforward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			} else {
				ultilastback.Update(System.nanoTime());
				ultilastback.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY());
			}
		}
	}
		
	}

}
