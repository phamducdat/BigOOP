package com.game.control;

import java.awt.image.BufferedImage;

import com.game.effect.DataLoader;
import com.game.gameinteface.ButtonState;

// Nguoi dung thong qua nut de ra yeu cau cho he thong
public abstract class Button implements ButtonState{

	protected String name;
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected boolean enabled;
	
	// trang thai nut
	protected int state;
	
	// hinh anh nut
	protected BufferedImage none;
	protected BufferedImage press;
	protected BufferedImage hover;
	
	public Button(String name, int posX, int posY) {
		this.name = name;
		this.posX = posX;
		this.posY = posY;
		
		none = DataLoader.getInstance().getFrameImage(name + "_none").getImage();
		hover = DataLoader.getInstance().getFrameImage(name + "_hover").getImage();
		none = DataLoader.getInstance().getFrameImage(name + "_press").getImage();
		
		this.width = none.getWidth();
		this.height = none.getHeight();
		
		enabled = true;
	}
	
	public void setEnable(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setState(int state) {
		this.state = state;
	}
}
