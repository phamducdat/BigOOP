package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.game.state.GameState;


public class FinalBossHard extends Boss{

	public FinalBossHard(float posX, float posY, float width, float height, float mass, int healthPoint, int manaPoint,
			GameState gameState) {
		super(posX, posY, 110, 150, 0.1f, 100, 0, gameState);
		attackType.add("NONE");
		attackType.add("ready");
		attackType.add("shooting");
		attackType.add("NONE");
		attackType.add("ultifirst");
		attackType.add("ultilast");
		attackType.add("transfiguration");
		attackType.add("slide");
		attackType.add("standup");
		

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
	public void attack() {
		super.attack();

	}

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
						bullet.setSpeedX(-10);
						bullet.setPosX(bullet.getPosX() - 40);
						if (getSpeedX() != 0 && getSpeedY() == 0) {
							bullet.setPosX(bullet.getPosX() - 10);
							bullet.setPosY(bullet.getPosY() - 5);
						}
					} else {
						bullet.setDirection(LEFT_DIR);
						bullet.setSpeedX(10);
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
	public void draw(Graphics g) {
		super.draw(g);
	}

}
