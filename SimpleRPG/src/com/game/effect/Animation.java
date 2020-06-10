package com.game.effect;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    
    private String name;		// ten animation
    private int currentFrame;	// index cua frame hien tai
    private boolean isRepeated;
    private long beginTime;		// thoi gian bat dau cua animation
    
    // Danh sach Animation, su dung ArrayList de dam bao thu tu cua cac Frame
    private ArrayList<FrameImage> frameImages;	// danh sach frame
    private ArrayList<Boolean> ignoreFrames;	// thoi gian cho giua cac frame
    private ArrayList<Double> delayFrames;		// co bo qua frame trong luc chay hay khong?
    
    public Animation(){
        delayFrames = new ArrayList<Double>();
        beginTime = 0;
        currentFrame = 0;

        ignoreFrames = new ArrayList<Boolean>();
        frameImages = new ArrayList<FrameImage>();        
        isRepeated = true;
    }
    
    // Copy-constructor
    public Animation(Animation animation){
        
        beginTime = animation.beginTime;
        currentFrame = animation.currentFrame;
        isRepeated = animation.isRepeated;
        
        delayFrames = new ArrayList<Double>();
        for(Double d : animation.delayFrames){
            delayFrames.add(d);
        }
        
        ignoreFrames = new ArrayList<Boolean>();
        for(boolean b : animation.ignoreFrames){
            ignoreFrames.add(b);
        }
        
        frameImages = new ArrayList<FrameImage>();
        for(FrameImage f : animation.frameImages){
            frameImages.add(new FrameImage(f));
        }
    }
    
    // getter va setter
    public void setIsRepeated(boolean isRepeated){
        this.isRepeated = isRepeated;
    }
    
    public boolean getIsRepeated(){
        return isRepeated;
    }
    
    public boolean isIgnoreFrame(int id){
        return ignoreFrames.get(id);
    }
    
    public void setIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, true);
    }
    
    public void unIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, false);
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void setCurrentFrame(int currentFrame){
        if(currentFrame >= 0 && currentFrame < frameImages.size())
            this.currentFrame = currentFrame;
        else this.currentFrame = 0;
    }
    public int getCurrentFrame(){
        return this.currentFrame;
    }
    
    public void reset(){
        currentFrame = 0;
        beginTime = 0;
    }
    
    // Them frame vao animation
    public void add(FrameImage frameImage, double timeToNextFrame){

        ignoreFrames.add(false);
        frameImages.add(frameImage);
        delayFrames.add(new Double(timeToNextFrame));
        
    }
    
    public BufferedImage getCurrentImage(){
        return frameImages.get(currentFrame).getImage();
    }
    
    // cap nhat Animation
    public void Update(long deltaTime){
        
        if(beginTime == 0) beginTime = deltaTime;
        else{
            
            if(deltaTime - beginTime > delayFrames.get(currentFrame)){
                nextFrame();
                beginTime = deltaTime;
            }
        }
        
    }

    public boolean isLastFrame(){
        if(currentFrame == frameImages.size() - 1)
            return true;
        else return false;
    }
    
    // nhay sang frame tiep theo
    private void nextFrame(){
        
        if(currentFrame >= frameImages.size() - 1){
            
            if(isRepeated) currentFrame = 0;
        }
        else currentFrame++;
        
        if(ignoreFrames.get(currentFrame)) nextFrame();
        
    }
    
    // Lat toan bo Animation
    public void flipAllImage(){
        
        for(int i = 0;i < frameImages.size(); i++){
            
            BufferedImage image = frameImages.get(i).getImage();
            
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(), 0);

            AffineTransformOp op = new AffineTransformOp(tx,
            AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);
            
            frameImages.get(i).setImage(image);
            
        }
        
    }
    
    public long time() { // tinh thoi gian animation
		long a = 0;
		for (int i = 0; i < delayFrames.size(); i++) {
			a = (long) (a + delayFrames.get(i));
		}
		a = (a-1000)/1000000;
		return a;
	}
    
    
    
    public void reverse() { // dao thu tu cac anh trong animation

		ArrayList<FrameImage> a = new ArrayList<FrameImage>();

		for (int i = frameImages.size() - 1; i >= 0; i--) {
			a.add(new FrameImage(frameImages.get(i)));
		}

		for (int i = 0; i < frameImages.size(); i++) {
			BufferedImage image = a.get(i).getImage();
			frameImages.get(i).setImage(image);
		}

	}
    
    // ve
    public void draw(int x, int y, Graphics2D g2){
        
        BufferedImage image = getCurrentImage();
        
        g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
        
    }
    
}
