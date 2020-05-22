package com.game.effect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


// Status: Completed

public class Animation {

	private String name;
	
	private boolean isRepeating;
	
	private int currentFrame;
	
	private long beginTime;
	
	private boolean drawRectFrame;
	
	private ArrayList<FrameImage> frameImages;
	private ArrayList<Double> delayTimes;
	private ArrayList<Boolean> isIgnoringFrames;
	
	public Animation() {
		// TODO Auto-generated constructor stub
		frameImages = new ArrayList<FrameImage>();
		delayTimes = new ArrayList<Double>();
		isIgnoringFrames = new ArrayList<Boolean>();
		
		isRepeating = true;
		currentFrame = 0;
		drawRectFrame = false;
		beginTime = 0;
		
	}
	
	public Animation(Animation animation) {
		
		this.isRepeating = animation.isRepeating;
		this.beginTime = animation.beginTime;
		this.drawRectFrame = animation.drawRectFrame;
		this.currentFrame = animation.currentFrame;
		
		frameImages = new ArrayList<FrameImage>();
		for(FrameImage f: animation.frameImages)
			frameImages.add(new FrameImage(f));
		
		delayTimes = new ArrayList<Double>();
		for(Double d: animation.delayTimes)
			delayTimes.add(d);
		
		isIgnoringFrames = new ArrayList<Boolean>();
		for(boolean b: animation.isIgnoringFrames)
			isIgnoringFrames.add(b);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsRepeating() {
		return isRepeating;
	}

	public void setIsRepeating(boolean isRepeating) {
		this.isRepeating = isRepeating;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		if(currentFrame >= 0 && currentFrame <= frameImages.size() - 1)
			this.currentFrame = currentFrame;
		this.currentFrame = currentFrame;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public boolean getIsDrawRectFrame() {
		return drawRectFrame;
	}

	public void setDrawRectFrame(boolean drawRectFrame) {
		this.drawRectFrame = drawRectFrame;
	}
	
	public boolean isIgnoreFrame(int id) {
		return isIgnoringFrames.get(id);
	}

	public void unIgnoreFrame(int id) {
		isIgnoringFrames.set(id, false);
	}
	
	public void setIgnoreFrame(int id) {
		isIgnoringFrames.set(id, true);
	}
	
	public void reset() {
		currentFrame = 0;
		beginTime = 0;
	}
	
	public void add(FrameImage frameImage, double timeToNextFrame) {
		frameImages.add(frameImage);
		delayTimes.add(timeToNextFrame);
		isIgnoringFrames.add(false);
	}
	
	public boolean isLastFrame() {
		if(currentFrame == frameImages.size() - 1) return true;
		else return false;
	}
	
	public void nextFrame() {
		if(isLastFrame()) {
			if(isRepeating) currentFrame = 0;
		}
		else currentFrame++;
		
		if(isIgnoreFrame(currentFrame)) nextFrame();
	}
	
	public BufferedImage getCurrentImage() {
		return frameImages.get(currentFrame).getImage();
	}
	
	public void Update(long currentTime) {
		if(beginTime == 0) beginTime = currentTime;
		else {
			if(currentTime - beginTime > delayTimes.get(currentFrame)) {
				beginTime = currentTime;
				nextFrame();
			}
		}
	}
	
	public void flipAllImages() {
		for(int i = 0; i<frameImages.size(); i++) {
			BufferedImage image = frameImages.get(currentFrame).getImage();
			
			AffineTransform  tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-image.getWidth(), 0);
			
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			image = op.filter(image, null);
			
			frameImages.get(i).setImage(image);
			
		}
	}
	
	public void reverse() {

		ArrayList<FrameImage> a = new ArrayList<FrameImage>();

		for (int i = frameImages.size() - 1; i >= 0; i--) {
			a.add(new FrameImage(frameImages.get(i)));
		}

		for (int i = 0; i < frameImages.size(); i++) {
			BufferedImage image = a.get(i).getImage();
			frameImages.get(i).setImage(image);
		}

	}
	
	public long time() {
		long a = 0;
		for (int i = 0; i < delayTimes.size(); i++) {
			a = (long) (a + delayTimes.get(i));
		}
		a = (a-1000)/1000000;
		return a;
	}
	
	public void draw(Graphics g, int x, int y) {
		g.drawImage(getCurrentImage(), (int)(x - getCurrentImage().getWidth()/2), (int) (y - getCurrentImage().getHeight()/2), null);
		
		if(drawRectFrame) {
			g.drawRect((int)(x - getCurrentImage().getWidth()/2), (int)(y - getCurrentImage().getHeight()/2), 
					getCurrentImage().getWidth(), getCurrentImage().getHeight());
		}
	}
	
}
