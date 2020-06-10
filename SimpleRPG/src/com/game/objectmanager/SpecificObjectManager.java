package com.game.objectmanager;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.game.gameobject.SpecificObject;
import com.game.state.GameState;

// Lop quan ly quai va nhan vat chinh
public class SpecificObjectManager {

	// Nguyen tac da hinh Polymophism
    protected List<SpecificObject> specificObjects;

    // Dong vai tro nhu con tro tro ve GameState so huu doi tuong
    private GameState gameState;
    
    public SpecificObjectManager(GameState gameState){
        
    	specificObjects = Collections.synchronizedList(new LinkedList<SpecificObject>());
        this.gameState = gameState;
        
    }
    
    public GameState getGameState(){
        return gameState;
    }
    
    // Them doi tuong
    public void addObject(SpecificObject particularObject){ 
        synchronized(specificObjects){
        	specificObjects.add(particularObject);
        } 
    }
    
    // Loai bo doi tuong
    public void RemoveObject(SpecificObject particularObject){
        synchronized(specificObjects){
            for(int id = 0; id < specificObjects.size(); id++){
                
            	SpecificObject object = specificObjects.get(id);
                if(object == particularObject)
                	specificObjects.remove(id);

            }
        }
    }
    
    // Tra ve doi tuong va cham voi doi tuong dau vao trong danh sach
    public SpecificObject getEnemyObjectCollideWith(SpecificObject object){
        synchronized(specificObjects){
            for(int id = 0; id < specificObjects.size(); id++){
                
            	SpecificObject objectInList = specificObjects.get(id);

                if(object.getTeamType() != objectInList.getTeamType() && 
                        object.getBoundForCollisionWithEnemy().intersects(objectInList.getBoundForCollisionWithEnemy())){
                    return objectInList;
                }
            }
        }
        return null;
    }
    
    // Cap nhat toan bo doi tuong trong danh sach
    public void UpdateObjects(){
        
        synchronized(specificObjects){
            for(int id = 0; id < specificObjects.size(); id++){
                
            	SpecificObject object = specificObjects.get(id);
                
                if(!object.isObjectOutOfCameraView()) object.Update();
                
                if(object.getState() == SpecificObject.DEATH){
                	specificObjects.remove(id);
                }
            }
        }
        
    }
    
    // Ve toan bo doi tuong trong danh sach
    public void draw(Graphics2D g2){
        synchronized(specificObjects){
            for(SpecificObject object: specificObjects)
                if(!object.isObjectOutOfCameraView()) object.draw(g2);
        }
    }
	
}
