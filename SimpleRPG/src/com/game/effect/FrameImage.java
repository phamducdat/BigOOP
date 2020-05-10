package com.game.effect;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

// Status: Completed

public class FrameImage {

	private String name;
	private BufferedImage image;
	
	public FrameImage() {
		// TODO Auto-generated constructor stub
		this.image = null;
		this.name = null;
	}
	
	public FrameImage(String name, BufferedImage image) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.image = image;
	}
	
	public FrameImage(FrameImage frameImage) {
		// TODO Auto-generated constructor stub
		image = new BufferedImage(frameImage.getImage().getWidth(), frameImage.getImage().getHeight(), frameImage.getImage().getType());
		
		Graphics g = image.getGraphics();
		g.drawImage(frameImage.getImage(), 0, 0, null);
		this.name = frameImage.name;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}
