package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class SmallRedGun extends SpecificObject{
	private Animation backAnim, forwardAnim;
	private long startTimeToShoot;
	private float x1, x2;
	
	public SmallRedGun(float x, float y, GameState gameState) {
		super(x, y, 127, 89, 0,100, 90, gameState);
		backAnim=DataLoader.getInstance().getAnimations().get("smallredgun");
		forwardAnim=DataLoader.getInstance().getAnimations().get("smallredgun");
		forwardAnim.flipAllImages();
		startTimeToShoot=0;
		setBeginTimeToBeHurt(300000000);
		 x1 = x - 50;
	     x2 = x + 50;
	     setSpeedX(2);
	     setDamage(50);
	}

	@Override
	public void attack() {
//		Bullet bullet = new YellowFlowerBullet(getPosX(),getPosY(), getGameState());
//		bullet.setSpeedX(-3);
//		bullet.setSpeedY(3);
//		bullet.setTeamType(getTeamType());
//		getGameState().bulletManager.addObject(bullet);
//		
//		bullet= new YellowFlowerBullet(getPosX(),getPosY(), getGameState());
//		bullet.setSpeedX(3);
//		bullet.setSpeedY(3);
//		bullet.setTeamType(getTeamType());
//		getGameState().bulletManager.addObject(bullet);
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
			if(getState()==CANTBEHURT&& (System.nanoTime()/10000000)%2!=1) {
				//phash...
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

    
	 public void Update(){
	        super.Update();
	        if(getPosX() < x1)
	            setSpeedX(1);
	        else if(getPosX() > x2)
	            setSpeedX(-1);
	        setPosX(getPosX() + getSpeedX());
	        
	        if(System.nanoTime() - startTimeToShoot > 1000*10000000*2.0){
	            attack();
	            System.out.println("Red Eye attack");
	            startTimeToShoot = System.nanoTime();
	        }
	    }

}
