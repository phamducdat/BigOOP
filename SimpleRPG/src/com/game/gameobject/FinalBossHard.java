package com.game.gameobject;

import java.awt.Graphics2D;

import com.game.state.GameState;


public class FinalBossHard extends Boss {
	public FinalBossHard(float x, float y, GameState gameWorld) {
		super(x, y, 110, 150, 0.1f, 300, gameWorld);
		a = 1;
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
		timeAttack.put("slide", new Long(8000));
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
	}

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
	}


}
