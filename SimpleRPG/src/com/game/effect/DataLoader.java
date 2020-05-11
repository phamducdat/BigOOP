package com.game.effect;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;

// Status: Completed

// Singletone
public class DataLoader {

	private static DataLoader instance;
	
	private String framefile = "data/frame.txt";
	private String animationfile = "data/animation.txt";
	private String backgroundfile = "data/backgroundmap.txt";
	private String physicalfile = "data/physicalmap.txt";
	private String soundfile = "data/sounds.txt";
	
	private Hashtable<String, FrameImage> frameImages;
	private Hashtable<String, Animation> animations;
	private Hashtable<String, AudioClip> sounds;
	
	private int[][] physicalMap;
	private int[][] backgroundMap;
	
	private DataLoader() {}
	
	public static DataLoader getInstance() {
		if(instance == null) {
			instance = new DataLoader();
		}
		return instance;
	}
	
	public Animation getAnimation(String name) {
		Animation animation = new Animation(instance.animations.get(name));
		return animation;
	}
	
	public void LoadData() throws IOException {
		LoadFrame();
		LoadAnimation();
		LoadBackgroundMap();
		LoadPhysicalMap();
		LoadSound();
	}
	
	public void LoadFrame() throws IOException {
		
		frameImages = new Hashtable<String, FrameImage>();
		
		FileReader fr = new FileReader(framefile);
		BufferedReader br = new BufferedReader(fr);
		
		String line = null;
		
		if(br.readLine() == null) {
			System.out.println("No data to load");
			throw new IOException();
		}else {
			fr = new FileReader(framefile);
			br = new BufferedReader(br);
			
			while((line = br.readLine()).equals(""));
			
			int n = Integer.parseInt(line);
			
			for(int i = 0; i < n; i++) {
				
				while((line = br.readLine()).equals(""));
				String name = line;
				
				while((line = br.readLine()).equals(""));
				String[] str = line.split(" ");
				String path = str[1];
				
				while((line = br.readLine()).equals(""));
				str = line.split(" ");
				int x = Integer.parseInt(str[1]);
				
				while((line = br.readLine()).equals(""));
				str = line.split(" ");
				int y = Integer.parseInt(str[1]);
				
				while((line = br.readLine()).equals(""));
				str = line.split(" ");
				int w = Integer.parseInt(str[1]);
				
				while((line = br.readLine()).equals(""));
				str = line.split(" ");
				int h = Integer.parseInt(str[1]);
				
				
				BufferedImage image = ImageIO.read(new File(path));
				BufferedImage subImage = image.getSubimage(x, y, w, h);
				
				FrameImage frameImage = new FrameImage(name, subImage);
				
				instance.frameImages.put(name, frameImage);
			}
		}
		
		br.close();
		
	}
	
	public void LoadAnimation() throws IOException {
		
		animations = new Hashtable<String, Animation>();
		
		FileReader fr = new FileReader(animationfile);
		BufferedReader br = new BufferedReader(fr);
		
		String line = null;
		
		
		if(br.readLine() == null) {
			System.out.println("No data to load");
			throw new IOException();
		}else {
			fr = new FileReader(animationfile);
			br = new BufferedReader(fr);
			
			while((line = br.readLine()).equals(""));
			int n = Integer.parseInt(line);
			
			for(int i = 0; i < n; i++) {
				
				while((line = br.readLine()).equals(""));
				String name = line;
				
				while((line = br.readLine()).equals(""));
				String[] str = line.split(" ");
				
				Animation animation = new Animation();
				animation.setName(name);
				
				for(int j = 0; j < str.length/2; j++) {
					animation.add(instance.frameImages.get(str[2*j]), Double.parseDouble(str[2*j+1]));
				}
				
				instance.animations.put(name, animation);
			}
		}
		
		br.close();
	}
	
	public void LoadPhysicalMap() throws IOException{
		
		FileReader fr = new FileReader(physicalfile);
		BufferedReader br = new BufferedReader(fr);
		
		String line = null;
		
		line = br.readLine();
		int row = Integer.parseInt(line);
		line = br.readLine();
		int col = Integer.parseInt(line);
		
		instance.physicalMap = new int[row][col];
		
		for(int i = 0; i < row; i++) {
			line = br.readLine();
			String[] str = line.split(" ");
			
			for(int j = 0; j < col; j++) {
				instance.physicalMap[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				System.out.print(" " + instance.physicalMap[i][j]);
			}
			System.out.println();
		}
		
		br.close();
	}
	
	public void LoadBackgroundMap() throws IOException {
		
		FileReader fr = new FileReader(backgroundfile);
		BufferedReader br = new BufferedReader(fr);
		
		String line = null;
		
		line = br.readLine();
		int row = Integer.parseInt(line);
		
		line = br.readLine();
		int col = Integer.parseInt(line);
		
		instance.backgroundMap = new int[row][col];
		
		for(int i = 0; i < row; i++) {
			line = br.readLine();
			String[] str = line.split(" ");
			
			for(int j = 0; j < col; j++) {
				instance.backgroundMap[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				System.out.print(" " + instance.backgroundMap[i][j]);
			}
			System.out.println();
		}
		
		br.close();
	}
	
	public void LoadSound() throws IOException {
		
		FileReader fr = new FileReader(soundfile);
		BufferedReader br = new BufferedReader(fr);
		
		sounds = new Hashtable<String, AudioClip>();
		
		String line = null;
		
		if(br.readLine() == null) {
			System.out.println("No data to load");
			throw new IOException();
		}else {
			fr = new FileReader(soundfile);
			br = new BufferedReader(fr);
			
			while((line = br.readLine()).equals(""));
			
			int n = Integer.parseInt(line);
			
			for(int i = 0; i < n; i++) {
				
				while((line = br.readLine()).equals(""));
				
				String[] str = line.split(" ");
				String name = str[0];
				String path = str[1];
				
				AudioClip audioClip = null;
				try {
					audioClip = Applet.newAudioClip(new URL("file", "", str[1]));
				}catch(MalformedURLException ex) {};
				
				instance.sounds.put(name, audioClip);
			}
		}
		
		br.close();
	}

	public int[][] getPhysicalMap() {
		return physicalMap;
	}

	public void setPhysicalMap(int[][] physicalMap) {
		this.physicalMap = physicalMap;
	}

	public int[][] getBackgroundMap() {
		return backgroundMap;
	}

	public void setBackgroundMap(int[][] backgroundMap) {
		this.backgroundMap = backgroundMap;
	}

	public Hashtable<String, FrameImage> getFrameImages() {
		return frameImages;
	}

	public void setFrameImages(Hashtable<String, FrameImage> frameImages) {
		this.frameImages = frameImages;
	}

	public Hashtable<String, Animation> getAnimations() {
		return animations;
	}

	public void setAnimations(Hashtable<String, Animation> animations) {
		this.animations = animations;
	}

	public Hashtable<String, AudioClip> getSounds() {
		return sounds;
	}

	public void setSounds(Hashtable<String, AudioClip> sounds) {
		this.sounds = sounds;
	}
}
