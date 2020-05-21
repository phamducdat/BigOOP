package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.gameinteface.Profile;
import com.game.gameinteface.Vulnerable;
import com.game.state.GameState;
import com.game.gameobject.Bullet;
//import com.game.gameobject.DarkRaiseBullet;
import com.game.effect.Animation;
import com.game.effect.DataLoader;

public class DarkRaise extends SpecificObject implements Profile, Vulnerable{
    private Animation forwardAnim, backAnim; 
    private long startTimeToShoot;
    private float x1, x2;
	public DarkRaise(float x, float y, GameState gameState) {
		super(x, y, 127, 89, 0, 100, 90, gameState);
        backAnim =DataLoader.getInstance().getAnimations().get("darkraise");
        forwardAnim = DataLoader.getInstance().getAnimations().get("darkraise");
        forwardAnim.flipAllImages();
        startTimeToShoot = 0;
        setTimeForNoBeHurt(300000000);
        
        x1 = x - 100;
        x2 = x + 100;
        setSpeedX(1);
        
        setDamage(20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		if(!isOutOfCameraView()) {
			if(getState()==CANTBEHURT && (System.nanoTime()/10000000)%2!=1) {
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
	public void attack() {
	      float megamanX = getGameState().megaman.getPosX();
	        float megamanY = getGameState().megaman.getPosY();
	        
	        float deltaX = megamanX - getPosX();
	        float deltaY = megamanY - getPosY();
	        
	        float speed = 3;
	        float a = Math.abs(deltaX/deltaY);
	        
	        float speedX = (float) Math.sqrt(speed*speed*a*a/(a*a + 1));
	        float speedY = (float) Math.sqrt(speed*speed/(a*a + 1));
	        
	        
	        
//	        Bullet bullet = new DarkRaiseBullet(getPosX(), getPosY(), getGameState());
//	        
//	        if(deltaX < 0)
//	            bullet.setSpeedX(-speedX);
//	        else bullet.setSpeedX(speedX);
//	        bullet.setSpeedY(speedY);
//	        bullet.setTeamType(getTeamType());
//	        getGameState().bulletManager.addObject(bullet);

		
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		Rectangle rect= getBoundForCollisionWithMap();
		rect.x+=20;
		rect.width-=40;
		
		return rect;
	}
@Override
public void Update() {
	super.Update();
	if(getPosX()<x1)
		setSpeedX(1);
	else if(getPosX()>x2)
		setSpeedX(-1);
	setPosX(getPosX()+getSpeedX());
	
	if(System.nanoTime()-startTimeToShoot>1000*10000000*1.5) {
		attack();
		startTimeToShoot=System.nanoTime();
	}
}
}
