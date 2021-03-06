package com.game.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Hashtable;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.gameinteface.GameWorldState;
import com.game.state.GameState;

// Lop Boss
public class Boss extends HumanoidObject {

	public static final int MAXHP = 300;
	public static final int MAXARMOR = 300;
	
	protected Animation idleforward, idleback;
	protected Animation shootingforward, shootingback;
	protected Animation slideforward, slideback;
	protected Animation transfigurationback, transfigurationforward;
	protected Animation readyforward, readyback;
	protected Animation standupforward, standupback;
	protected Animation ultifirstforward, ultifirstback;
	protected Animation ultilastforward, ultilastback;

	protected long startTimeForAttacked;

	protected Hashtable<String, Long> timeAttack = new Hashtable<String, Long>();
	protected ArrayList<String> attackType = new ArrayList<String>();
	protected Hashtable<String, Animation> noMoveLeft = new Hashtable<String, Animation>();
	protected Hashtable<String, Animation> noMoveRight = new Hashtable<String, Animation>();
	protected int attackIndex = 0;
	protected int a;

	public Boss(float x, float y, float width, float height, float mass, int blood, GameState GameState) {
		super(x, y, 110, 150, 0.1f, blood,  GameState);
		idleback = DataLoader.getInstance().getAnimation("boss_idle");
		idleforward = DataLoader.getInstance().getAnimation("boss_idle");
		idleforward.flipAllImage();

		shootingback = DataLoader.getInstance().getAnimation("boss_shooting");
		shootingforward = DataLoader.getInstance().getAnimation("boss_shooting");
		shootingforward.flipAllImage();

		slideback = DataLoader.getInstance().getAnimation("boss_slide");
		slideforward = DataLoader.getInstance().getAnimation("boss_slide");
		slideforward.flipAllImage();

		readyback = DataLoader.getInstance().getAnimation("boss_ready");
		readyforward = DataLoader.getInstance().getAnimation("boss_ready");
		readyforward.flipAllImage();

		transfigurationback = DataLoader.getInstance().getAnimation("boss_transfiguration");
		transfigurationforward = DataLoader.getInstance().getAnimation("boss_transfiguration");
		transfigurationforward.flipAllImage();

		ultifirstback = DataLoader.getInstance().getAnimation("boss_ultifirst");
		ultifirstforward = DataLoader.getInstance().getAnimation("boss_ultifirst");
		ultifirstforward.flipAllImage();

		ultilastback = DataLoader.getInstance().getAnimation("boss_ultilast");
		ultilastforward = DataLoader.getInstance().getAnimation("boss_ultilast");
		ultilastforward.flipAllImage();

		standupback = DataLoader.getInstance().getAnimation("boss_transfiguration");
		standupforward = DataLoader.getInstance().getAnimation("boss_transfiguration");
		standupback.reverse();
		standupforward.reverse();

		noMoveLeft.put("NONE", idleback);
		noMoveLeft.put("shooting", shootingback);
		noMoveLeft.put("transfiguration", transfigurationback);
		noMoveLeft.put("ready", readyback);
		noMoveLeft.put("standup", standupback);
		noMoveLeft.put("ultifirst", ultifirstback);
		noMoveLeft.put("ultilast", ultilastback);

		noMoveRight.put("NONE", idleforward);
		noMoveRight.put("shooting", shootingforward);
		noMoveRight.put("transfiguration", transfigurationforward);
		noMoveRight.put("ready", readyforward);
		noMoveRight.put("standup", standupforward);
		noMoveRight.put("ultifirst", ultifirstforward);
		noMoveRight.put("ultilast", ultilastforward);

		setTimeForCantBeHurt(500 * 1000000);
		setDamage(10);
		setArmor(MAXARMOR);

	}

	public void Update() {
		super.Update();

		if (getGameState().megaMan.getPosX() > getPosX())
			setDirection(RIGHT_DIR);
		else
			setDirection(LEFT_DIR);

		attack();

		if (!attackType.get(attackIndex).equals("NONE")) {
			if (attackType.get(attackIndex).equals("shooting")) {
				int b = 0;
				if(a ==1 ) {
					b = 2;
				}else {
					b = 10;
				}
				Bullet bullet = new RocketBullet(getPosX(), getPosY() - 50, getGameState(), b);
				
				if (getDirection() == RIGHT_DIR) {
					bullet.setSpeedX(8);
				} else
					bullet.setSpeedX(-8);

				bullet.setTeamType(getTeamType());
				getGameState().bulletManager.addObject(bullet);
				

			} else if (attackType.get(attackIndex).equals("slide")) {

				if (a == 1) {
					if ((getPosX() - GameWorldState.finalBossX) % 100 == 0) {
						BulletSlide bullet = new BulletSlide(getPosX(), getPosY() + 50, getGameState());
						if (getSpeedX() < 0) {
							bullet.setDirection(RIGHT_DIR);
							bullet.setSpeedX(-20);
							bullet.setPosX(bullet.getPosX() - 40);
							if (getSpeedX() != 0 && getSpeedY() == 0) {
								bullet.setPosX(bullet.getPosX() - 10);
								bullet.setPosY(bullet.getPosY() - 5);
							}
						} else {
							bullet.setDirection(LEFT_DIR);
							bullet.setSpeedX(20);
							bullet.setPosX(bullet.getPosX() + 40);
							if (getSpeedX() != 0 && getSpeedY() == 0) {
								bullet.setPosX(bullet.getPosX() + 10);
								bullet.setPosY(bullet.getPosY() - 5);
							}
						}

						bullet.setTeamType(getTeamType());
						getGameState().bulletManager.addObject(bullet);

					}
					
					if (getGameState().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null) {
						setSpeedX(100);
					}
					if (getGameState().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null) {
						setSpeedX(-100);
					}
					setPosX(getPosX() + getSpeedX());
				}

				if (getGameState().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null) {
					setSpeedX(5);
				}
				if (getGameState().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null) {
					setSpeedX(-5);
				}
				setPosX(getPosX() + getSpeedX());
				

			} else if (attackType.get(attackIndex).equals("ultilast")) {

			}

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

		if (System.currentTimeMillis() - startTimeForAttacked > timeAttack.get(attackType.get(attackIndex))) {

			startTimeForAttacked = System.currentTimeMillis();

			attackIndex++;
			if (attackIndex >= attackType.size())
				attackIndex = 0;
			if (attackType.get(attackIndex).equals("slide")) {
				if (getPosX() < getGameState().megaMan.getPosX())
					setSpeedX(5);
				else
					setSpeedX(-5);
			}
			if (attackType.get(attackIndex).equals("ultilast")) {
				Bullet bullet = new BossBullet(getPosX(), getPosY() - 50, getGameState());
				if (getDirection() == RIGHT_DIR) {
					bullet.setSpeedX(10);
				} else {
					bullet.setSpeedX(-10);
				}
				bullet.setTeamType(getTeamType());
				getGameState().bulletManager.addObject(bullet);

			}

		}

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
	public void draw(Graphics2D g2) {
		
		if (getState() == CANTBEHURT && (System.nanoTime() / 10000000) % 2 != 1) {

		} else if (!attackType.get(attackIndex).equals("slide")) {
			if (getDirection() == RIGHT_DIR) {
				noMoveRight.get(attackType.get(attackIndex)).Update(System.nanoTime());
				noMoveRight.get(attackType.get(attackIndex)).draw((int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY(), g2);

			} else {
				noMoveLeft.get(attackType.get(attackIndex)).Update(System.nanoTime());
				noMoveLeft.get(attackType.get(attackIndex)).draw((int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY(), g2);
			}

		}
		else if(attackType.get(attackIndex).equals("slide")) {
			if (getSpeedX() > 0) {
				slideforward.Update(System.nanoTime());
				slideforward.draw((int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY() + 50, g2);
			} else {
				slideback.Update(System.nanoTime());
				slideback.draw((int) (getPosX() - getGameState().camera.getPosX()),
						(int) getPosY() - (int) getGameState().camera.getPosY() + 50, g2);
			}
		}
	}

}
