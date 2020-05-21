package com.game.objectmanager;

import com.game.gameobject.SpecificObject;
import com.game.state.GameState;

// Class quan ly Bullet rieng vi Bullet se bien mat khi ra ngoai man hinh hien thi con cac su vat khac thi khong
public class BulletManager extends SpecificObjectManager {
	
	public BulletManager(GameState gameWorld) {
		super(gameWorld);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void UpdateObjects() {
		// Xoa bullet khoi danh sach quan ly neu bullet ra khoi man hinh hien thi
		
		super.UpdateObjects();
		synchronized (specificObjects) {
			
			for(int i = 0; i < specificObjects.size(); i++) {
				
				SpecificObject object = specificObjects.get(i);
				
				if(object.isOutOfCameraView()) specificObjects.remove(i);
				
			}
			
		}
	}

}
