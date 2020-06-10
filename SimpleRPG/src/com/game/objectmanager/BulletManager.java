package com.game.objectmanager;

import com.game.gameinteface.Profile;
import com.game.gameobject.SpecificObject;
import com.game.state.GameState;

// Class quan ly Bullet rieng vi Bullet se bien mat khi ra ngoai man hinh hien thi con cac su vat khac thi khong

public class BulletManager extends SpecificObjectManager {
	
    public BulletManager(GameState gameState) {
        super(gameState);
    }

    @Override
    public void UpdateObjects() {
    	
        super.UpdateObjects();
        
        synchronized(specificObjects){
            for(int id = 0; id < specificObjects.size(); id++){
                
                SpecificObject object = specificObjects.get(id);
                
                // Loai bo doi tuong neu doi tuong chet hoac ra ngoai man hinh
                if(object.isObjectOutOfCameraView() || object.getState() == Profile.DEATH){
                    specificObjects.remove(id);
                }
            }
        }
    }
    
    
    
}
