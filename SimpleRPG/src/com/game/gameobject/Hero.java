package com.game.gameobject;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.sound.midi.SysexMessage;
import javax.xml.crypto.Data;

import com.game.effect.Animation;
import com.game.effect.DataLoader;
import com.game.state.GameState;

public class Hero extends HumanoidObject{
	
	// Dat toc do va do cao nhan vat co the nhay duoc
	public static final float RUNSPEED = 3.0f;
	public static final float JUMPSPEED = 5.0f;
	
	private Animation runForward, runBackward, runShootForward, runShootBackward;
	private Animation idleForward, idleBackward, idleShootForward, idleShootBackward;
	private Animation kneelForward, kneelBackward;
	private Animation flyForward, flyBackward, flyShootForward, flyShootBackward;
	private Animation landForward,  landBackward;
	private Animation climbForward, climbBackward;
	
	private AudioClip shootingSound;
	private AudioClip hurtingSound;
	
	private long beginShootingTime;
	private boolean isShooting;
	

	public Hero(float posX, float posY, GameState gameState) {
		super(posX, posY, 70, 90, 0.1f, 100, 100, gameState);
		
		// Tai cac Animation tu DataLoader
		
		runForward = DataLoader.getInstance().getAnimation("run");
		runBackward = DataLoader.getInstance().getAnimation("run");
		runBackward.flipAllImages();
		
		idleForward = DataLoader.getInstance().getAnimation("idle");
		idleBackward = DataLoader.getInstance().getAnimation("idle");
		idleBackward.flipAllImages();
		
		kneelForward = DataLoader.getInstance().getAnimation("kneel");
		kneelBackward = DataLoader.getInstance().getAnimation("kneel");
		kneelBackward.flipAllImages();
		
		flyForward = DataLoader.getInstance().getAnimation("flyingup");
		flyForward.setIsRepeating(false);
		flyBackward = DataLoader.getInstance().getAnimation("flyingup");
		flyBackward.setIsRepeating(false);
		flyBackward.flipAllImages();
		
		landForward = DataLoader.getInstance().getAnimation("landng");
		landBackward = DataLoader.getInstance().getAnimation("landing");
		landBackward.flipAllImages();
		
		beHurtForward = DataLoader.getInstance().getAnimation("hurting");
		beHurtBackward = DataLoader.getInstance().getAnimation("hurting");
		beHurtBackward.flipAllImages();
		
		idleShootForward = DataLoader.getInstance().getAnimation("idleshoot");
		idleShootBackward = DataLoader.getInstance().getAnimation("idleshoot");
		idleShootBackward.flipAllImages();
		
		runShootForward = DataLoader.getInstance().getAnimation("runshoot");
		runShootBackward = DataLoader.getInstance().getAnimation("runshoot");
		runShootBackward.flipAllImages();
		
		flyShootForward = DataLoader.getInstance().getAnimation("flyingupshoot");
		flyShootBackward = DataLoader.getInstance().getAnimation("flyingupshoot");
		flyShootBackward.flipAllImages();
		
		climbBackward = DataLoader.getInstance().getAnimation("clim_wall");
		climbForward = DataLoader.getInstance().getAnimation("clim_wall");
		climbForward.flipAllImages();
		
		// Tai cac Sound tu DataLoader
		
		shootingSound = DataLoader.getInstance().getSounds().get("bluefireshooting");
		hurtingSound = DataLoader.getInstance().getSounds().get("megamanhurt");
		
		setTeamType(HERO_TEAM);
		
		setTimeForNoBeHurt(2000 * 1000000);
		
		isShooting = false;
	}
	
	@Override
	public void Update() {
		
		super.Update();
		
		if(isShooting) {
			if(System.nanoTime() - beginShootingTime > 500 * 1000000) {
				isShooting = false;
			}
		}
		
		if(isLanding()) {
			landBackward.Update(System.nanoTime());
			if(landBackward.isLastFrame()) {
				setLanding(false);
				runBackward.reset();
				runForward.reset();
				landBackward.reset();
			}
		}
	}

	@Override
	public void run() {
		if(getDirection() == RIGHT_DIR) {
			setSpeedX(RUNSPEED);
		}else setSpeedX(- RUNSPEED);
	}

	@Override
	public void jump() {
		if(!isJumping()) {
			setSpeedY(- JUMPSPEED);
			setJumping(true);
			flyBackward.reset();
			flyForward.reset();
			
		}else {
		// Danh cho viec leo tuong
			
			Rectangle rectRightWall = getBoundForCollisionWithMap();
			Rectangle rectLeftWall = getBoundForCollisionWithMap();
			rectRightWall.x += 1;
			rectLeftWall.x -= 1;
			
			// Leo tuong phai
			if(getGameState().physicalMap.haveCollisionWithRightWall(rectRightWall) != null && getSpeedX() > 0) {
				setSpeedY(- JUMPSPEED);
				flyBackward.reset();
				flyForward.reset();
			}
			
			// Leo tuong trai
			if(getGameState().physicalMap.haveCollisionWithLeftWall(rectLeftWall) != null && getSpeedX() < 0) {
				setSpeedX(- JUMPSPEED);
				flyBackward.reset();
				flyForward.reset();
			}
		}
	}

	@Override
	public void kneel() {
		if(!!isJumping()) {
			setKneeling(true);
		}
	}

	@Override
	public void standUp() {
		// Dung day va reset cac dong tac dung va quy
		setKneeling(false);
		idleForward.reset();
		idleBackward.reset();
		kneelForward.reset();
		kneelBackward.reset();
	}

	@Override
	public void stopRun() {
		// Reset cac dong tac run
		setSpeedX(0);
		runForward.reset();
		runBackward.reset();
		runForward.unIgnoreFrame(0);
		runBackward.unIgnoreFrame(0);
	}

	@Override
	public void attack() {
		
		// Khong cho phep ban khi dang quy
		if(!isShooting && !isKneeling()) {
			
			shootingSound.play();
			
			// Cho Bullet
			
		}
		
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		// Lay bien de va cham voi cac doi tuong khac
		
		Rectangle rect = getBoundForCollisionWithMap();
		
		if(isKneeling()) {
			// Khi quy thi nhan vat se thap hon
			rect.x = (int) getPosX() - 22;
			rect.y = (int) getPosY() - 20;
			rect.width = 44;
			rect.height = 65;
			
		}else {
			// Khi khong quy
			rect.x = (int) getPosX() - 22;
			rect.y = (int) getPosY() - 40;
			rect.width = 44;
			rect.height = 80;
		
		}
		
		return rect;
	}

	@Override
	public void draw(Graphics g) {
		
		switch(getState()) {
			
			// Trong 2 trang thai ALIVE va CANTBEHURT thi ham draw se chon ra Animation phu hop voi trang thai nhan vat va ve Animation do
			case ALIVE: 
			case CANTBEHURT: 
				if(getState() == CANTBEHURT && (System.nanoTime() / 1000000) % 2 != 1) {
					// Tao hieu ung nhap nhay khi bi thuong
					System.out.println("Splash .......");
				}else {
					// Trong truong hop dang tiep dat
					if(isLanding()) {
						
						if(getDirection() == RIGHT_DIR) {
							landForward.setCurrentFrame(landBackward.getCurrentFrame());
							landForward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
									(int) (getPosY() - getGameState().camera.getPosY()) + getBoundForCollisionWithMap().height / 2 - landForward.getCurrentImage().getHeight() / 2);
							// Chon vi tri y nhu tren de hinh anh nhan vat luon cham dat
						}else {
							landBackward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
									(int) (getPosY() - getGameState().camera.getPosY()) + getBoundForCollisionWithMap().height / 2 - landBackward.getCurrentImage().getHeight() / 2);
						}
						
					// Trong truong hop dang tren khong
					}else if(isJumping()) {
						if(getDirection() == RIGHT_DIR) {
							flyForward.Update(System.nanoTime());
							
							if(isShooting) {
								flyShootForward.setCurrentFrame(flyForward.getCurrentFrame());
								flyShootForward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));	
							}else 
								flyForward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
								
							
						}else {
							flyBackward.Update(System.nanoTime());
							
							if(isShooting) {	
								flyShootBackward.setCurrentFrame(flyBackward.getCurrentFrame());
								flyShootBackward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
							}else
								flyBackward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));	
						}
						
					// Trong truong hop dang quy
					}else if(isKneeling()) {
						
						if(getDirection() == RIGHT_DIR) {
							
							kneelForward.Update(System.nanoTime());
							kneelForward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()),
									(int) (getPosY() - getGameState().camera.getPosY()) + getBoundForCollisionWithMap().height / 2 - kneelForward.getCurrentImage().getHeight() / 2);
						}else {
							
							kneelBackward.Update(System.nanoTime());
							kneelBackward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), 
									(int) (getPosY() - getGameState().camera.getPosY()) + getBoundForCollisionWithMap().height / 2 - kneelBackward.getCurrentImage().getHeight() / 2);	
						}
						
					// Trong cac truong hop con lai
					}else {
						
						// Chay ve ben phai
						if(getSpeedX() > 0) {
							runForward.Update(System.nanoTime());
							if(isShooting) {
								runShootForward.setCurrentFrame(runForward.getCurrentFrame() - 1);
								runShootForward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
								
							}else 
								runForward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
							
							if(runForward.getCurrentFrame() == 1) runForward.setIgnoreFrame(0); // Loai bo dong tac chay ban dau de dong tac chay muot hon
						
						// Chay ve ben trai
						}else if(getSpeedX() < 0) {
							runBackward.Update(System.nanoTime());
							if(isShooting) {
								runShootBackward.setCurrentFrame(runBackward.getCurrentFrame() - 1);
								runShootBackward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
							}else 
								runBackward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
							
							if(runBackward.getCurrentFrame() == 1) runBackward.setIgnoreFrame(0); // Loai bo dong tac chay ban dau de dong tac chay muot hon
						
						// Dung yen
						}else {
							if(getDirection() == RIGHT_DIR) {
								if(isShooting) {
									idleShootForward.Update(System.nanoTime());
									idleShootForward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
								}else {
									idleForward.Update(System.nanoTime());
									idleForward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
								}
							}else {
								if(isShooting) {
									idleShootBackward.Update(System.nanoTime());
									idleShootBackward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
								}else {
									idleBackward.Update(System.nanoTime());
									idleBackward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
								}
							}
						}	
					}
				}
				break;
				
			case BEHURT:
				
				// Animation beHurt da duoc khoi chay tu ham Update truoc
				if(getDirection() == RIGHT_DIR) {
					beHurtForward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
				}else {
					beHurtBackward.setCurrentFrame(beHurtForward.getCurrentFrame());
					beHurtBackward.draw(g, (int) (getPosX() - getGameState().camera.getPosX()), (int) (getPosY() - getGameState().camera.getPosY()));
				}
				
				break;
				
			case FEY:
				// Khong ve gi ca
				break;
				
			case DEATH:
				// Khong ve gi ca
				break;
		}
		
	}

}
