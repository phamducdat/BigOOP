package com.game.effect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// Hinh anh + ten
public class FrameImage {

	private String name;
    private BufferedImage image;
    
    // phuong thuc khoi tao
    public FrameImage(String name, BufferedImage image){
        this.name = name;
        this.image = image;
    }
    
    public FrameImage(FrameImage frameImage){
        image = new BufferedImage(frameImage.getWidthImage(), 
                frameImage.getHeightImage(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.drawImage(frameImage.image, 0, 0, null);
        name = frameImage.name;
    }
    
    public void draw(int x, int y, Graphics2D g2){
        
        g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
        
    }
    
    public FrameImage(){
        this.name = null;
        image = null;
    }
    
    // getter va setter
    public int getWidthImage(){
        return image.getWidth();
    }

    public int getHeightImage(){
        return image.getHeight();
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public BufferedImage getImage(){
        return image;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
	
}
