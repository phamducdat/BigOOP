package com.game.gameobject;
//thieu  bullet nen phan attack t comment lai
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class RedEyeDevil extends SpecificObject{
	
	private float x1, x2,y1,y2;
	private long startTimeToShoot;
	private Animation backAnim, forwardAnim;
	private AudioClip shooting;
	
	public RedEyeDevil(float x, float y, GameState gameState) {
		super(x, y, 127, 89, 0, 100, 90, gameState);
		backAnim=DataLoader.getInstance().getAnimations().get("redeyedevil");
		forwardAnim=DataLoader.getInstance().getAnimations().get("redeyedevil");
		forwardAnim.flipAllImages();
		startTimeToShoot=0;
		setDamage(20);
		setTimeForNoBeHurt(300000000);
		shooting= DataLoader.getInstance().getSounds().get("redeyeshooting");
	}
	
	@Override
	public void attack() {
		shooting.stop();
		Bullet bullet= new RedEyeBullet(getPosX(),getPosY(), getGameState());
		if(getDirection()==LEFT_DIR) bullet.setSpeedX(-8);
		else bullet.setSpeedX(8);
		bullet.setTeamType(getTeamType());
		getGameState().bulletManager.addObject(bullet);
		
	}
	
	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
	   Rectangle rect= getBoundForCollisionWithMap();
	   rect.x+=20;
	   rect.width-=40;
	   return rect;
	}
	
	@Override
	public void draw(Graphics g) {
		if(!isOutOfCameraView()) {
			if(getState()==CANTBEHURT&& (System.nanoTime()/10000000%2!=1)) {
				//plash...
			}
			else {
				if(getDirection()==LEFT_DIR) {
					backAnim.Update(System.nanoTime());
					backAnim.draw(g, (int)(getPosX()-getGameState().camera.getPosX()), (int)(getPosY()-getGameState().camera.getPosY()));
				}
				else {
					forwardAnim.Update(System.nanoTime());
					forwardAnim.draw(g, (int)(getPosX()-getGameState().camera.getPosX()), (int)(getPosY()-getGameState().camera.getPosY()));
				}
			}
		}
		
	}
	
	@Override
	public void Update() {
		super.Update();
		if(System.nanoTime()-startTimeToShoot>1000*10000000) {
			attack();
			System.out.println("Red eye attack");
			startTimeToShoot=System.nanoTime();
		}
		
	}
}
