package myGame.Game.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import myGame.Game.Handler;
import myGame.Game.gfx.Assets;



public class Hud {
	int x;
	int y;
	int width;
	int height;
	Handler handler;
	Image image;
	BufferedImage bg;
	BufferedImage diamond;
	BufferedImage[] numbers;
	int healthNumber;
	
	public Hud(Handler handler, int x, int y, int width, int height, Image image) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		this.image = image;
		bg = Assets.hudBackg;
		numbers = new BufferedImage[10];
		diamond = Assets.hudDiamond;
		numbers = Assets.hudNumbers;
		
	}
	
	
	
	public void tick() {
		x = (int) (-handler.getCamera().getX());
		healthNumber = handler.getGame().getPlayer().getHealth();
		System.out.println("health " + healthNumber);
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(bg, x + 20, 20, 100, height + 50, null);
		g.drawImage(image, x + 25, 30, width, height + 20, null);
		if(healthNumber > 0) {
		g.drawImage(numbers[healthNumber], x + 60, 50, width, height - 20, null);
		} else {
		g.drawImage(numbers[0], x + 60, 50, width, height -20, null);
		}
		
		
		
	
		
	}

}
