package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.game.state.GameState;


public class FinalBoss extends Boss {

	public FinalBoss(float x, float y, GameState GameState) {
		super(x, y, 110, 150, 0.1f, 100, 0, GameState);

		attackType.add("NONE");
		attackType.add("ready");
		attackType.add("shooting");
		attackType.add("NONE");
		attackType.add("transfiguration");
		attackType.add("slide");
		attackType.add("standup");

		timeAttack.put("NONE", new Long(2000));
		timeAttack.put("shooting", shootingback.time());
		timeAttack.put("transfiguration", (transfigurationback.time()));
		timeAttack.put("ready", readyback.time());
		timeAttack.put("slide", new Long(5000));
		timeAttack.put("standup", (transfigurationback.time()));

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
//				bullet.setTeamType(getTeamType());
//				getGameState().bulletManager.addObject(bullet);
			} else if (attackType.get(attackIndex).equals("slide")) {
				if (getGameState().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null) {
					setSpeedX(5);
				}
				if (getGameState().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null) {
					setSpeedX(-5);
				}

				setPosX(getPosX() + getSpeedX());
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
