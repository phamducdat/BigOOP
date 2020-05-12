package com.game.objectmanager;

import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.game.gameinteface.Profile;
import com.game.gameobject.SpecificObject;
import com.game.state.GameState;

// Status: Completed

public class SpecificObjectManager {

	protected List<SpecificObject> specificObjects;
	
	private GameState gameWorld;
	
	public SpecificObjectManager(GameState gameWorld) {
		
		// LinkedList la kieu danh sach lien ket doi, khong dong bo va la mang tinh
		// Bat dong bo se nhanh hon dong bo do dong bo can nhieu thao tac voi du lieu
		// Vi vay, bat dong bo phu hop voi thao tac them, sua, xoa tren du lieu, con dong bo phu voi sap xep, truy cap du lieu
		
		specificObjects = Collections.synchronizedList(new LinkedList<SpecificObject>()); 	
		this.gameWorld = gameWorld;
	}
	
	public void addObject(SpecificObject object) {
		
		synchronized (specificObjects) {
			specificObjects.add(object);
		}
		
	}
	
	public void removeObject(SpecificObject object) {
		
		synchronized (specificObjects) {
			
			for(int i = 0; i < specificObjects.size(); i++) {
				
				SpecificObject objectInList = specificObjects.get(i);
				
				if(objectInList == object)
					// Dung dau == la do object la mang tinh nen no duoc luu tren vung nho STACK
					specificObjects.remove(i);
				
			}
			
		}
		
	}
	
	public void UpdateObjects() {
		
		synchronized (specificObjects) {
			for(int i = 0; i < specificObjects.size(); i++) {
				
				SpecificObject object = specificObjects.get(i);
				
				if(!object.isOutOfCameraView()) object.Update();
				
				if(object.getState() == Profile.DEATH) specificObjects.remove(i);
				
			}
		}
	}
	
	public SpecificObject getObjectCollideWithThisObject(SpecificObject object) {
		
		synchronized (specificObjects) {
		
			for(int i = 0; i < specificObjects.size(); i++) {
				SpecificObject objectInList = specificObjects.get(i);
				
				if(objectInList.getTeamType() != object.getTeamType()
						&& object.getBoundForCollisionWithEnemy().intersects(objectInList.getBoundForCollisionWithEnemy()))
						
						return objectInList;
			}
		}
		return null;
	}
	
	public void draw(Graphics g) {
		synchronized (specificObjects) {
			
			for(int i = 0; i < specificObjects.size(); i++) {
				SpecificObject object = specificObjects.get(i);
				
				if(!object.isOutOfCameraView()) object.draw(g);
			}
			
		}
	}
	
}
