package com.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.w3c.dom.css.Rect;

import com.game.gameinteface.Actable;
import com.game.state.GameState;

// Done

public abstract class HumanoidObject extends SpecificObject implements Actable{

	private boolean isJumping;
    private boolean isDicking;
    private boolean isLanding;

    public HumanoidObject(float x, float y, float width, float height, float mass, int blood, GameState gameState) {
        super(x, y, width, height, mass, blood, gameState);
        setState(ALIVE);
    }

    public abstract void run();
    
    public abstract void jump();
    
    public abstract void dick();
    
    public abstract void standUp();
    
    public abstract void stopRun();

    public boolean getIsJumping() {
        return isJumping;
    }
    
    public void setIsLanding(boolean b){
        isLanding = b;
    }
    
    public boolean getIsLanding(){
        return isLanding;
    }
    
    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public boolean getIsDicking() {
        return isDicking;
    }

    public void setIsDicking(boolean isDicking) {
        this.isDicking = isDicking;
    }
    
    @Override
    public void Update(){
        
        super.Update();
        
        if(getState() == ALIVE || getState() == CANTBEHURT){
        
            if(!isLanding){

                setPosX(getPosX() + getSpeedX());


                if(getDirection() == LEFT_DIR && 
                        getGameState().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap())!=null){

                    Rectangle rectLeftWall = getGameState().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);

                }
                if(getDirection() == RIGHT_DIR && 
                		getGameState().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null){

                    Rectangle rectRightWall = getGameState().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    setPosX(rectRightWall.x - getWidth()/2);

                }



                /**
                 * Codes below check the posY of megaMan
                 */
                // plus (+2) because we must check below the character when he's speedY = 0

                Rectangle boundForCollisionWithMapFuture = getBoundForCollisionWithMap();
                boundForCollisionWithMapFuture.y += (getSpeedY()!=0?getSpeedY(): 2);
                Rectangle rectLand = getGameState().physicalMap.haveCollisionWithLand(boundForCollisionWithMapFuture);
                
                Rectangle rectTop = getGameState().physicalMap.haveCollisionWithTop(boundForCollisionWithMapFuture);
                
                if(rectTop !=null){
                    
                    setSpeedY(0);
                    setPosY(rectTop.y + getGameState().physicalMap.getTileSize() + getHeight()/2);
                    
                }else if(rectLand != null){
                    setIsJumping(false);
                    if(getSpeedY() > 0) setIsLanding(true);
                    setSpeedY(0);
                    setPosY(rectLand.y - getHeight()/2 - 1);
                }else{
                    setIsJumping(true);
                    setSpeedY(getSpeedY() + getMass());
                    setPosY(getPosY() + getSpeedY());
                }
            }
        }
    }
    
}
