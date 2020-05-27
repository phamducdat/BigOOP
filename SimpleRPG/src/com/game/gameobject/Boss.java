package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Hashtable;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class Boss extends HumanoidObject{
	
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
	protected ArrayList<Animation> a = new ArrayList<Animation>();
	protected int attackIndex = 0;


	public Boss(float posX, float posY, GameState gameState) {
		super(posX, posX, 110, 150, 0.1f, 100, 0, gameState);
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
		ultilastforward.flipAllImages();

		standupback = DataLoader.getInstance().getAnimation("boss_transfiguration");
		standupforward = DataLoader.getInstance().getAnimation("boss_transfiguration");
		standupback.reverse();
		standupforward.reverse();

		setTimeForNoBeHurt(500 * 1000000);
		setDamage(10);
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
				if (getPosX() < getGameState().megaman.getPosX())
					setSpeedX(5);
				else
					setSpeedX(-5);
			}
			if (attackType.get(attackIndex).equals("ultilast")) {
				Bullet bullet = new BossBullet(getPosX(), getPosY() - 50, getGameState());
				if (getDirection() == RIGHT_DIR) {
					bullet.setSpeedX(4);
				} else {
					bullet.setSpeedX(-4);
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
	public void draw(Graphics g) {
		if (getState() == CANTBEHURT && (System.nanoTime() / 10000000) % 2 != 1) {

		} else if (attackType.get(attackIndex).equals("NONE")) {

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
		}

		else if (attackType.get(attackIndex).equals("ultifirst")) {
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
