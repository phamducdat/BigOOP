package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Hashtable;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class FinalBoss extends Boss{
	
	public FinalBoss(float x, float y, GameState gameWorld) {
		super(x, y, 110, 150, 0.1f, 100, gameWorld);
		a = 0;

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

		
	}

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);

	}
	// drawBoundForCollisionWithEnemy(g2);
    
}
