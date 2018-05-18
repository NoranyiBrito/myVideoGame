package myGame.Game.utils;

import java.awt.Graphics;
import java.awt.Image;

import myGame.Game.Handler;
import myGame.Game.gfx.Animation;
import myGame.Game.gfx.Assets;

public class Door {
	int x;
	int y;
	int z;
	int width;
	int height;
	Handler handler;
	Image image;
	Animation door;
	Image currentImage;
	Image stillImage;
	boolean moving = false;
	boolean doorOpen = false;
	
	public Door(Handler handler, int x, int y, int z, int width, int height) {

		this.x = x;
		this.y = y;
		this.z =z;
		this.width = width;
		this.height = height;
		this.handler = handler;
		
		door = new Animation(400, Assets.door);
		stillImage = Assets.door[1];
		
	}
	
	public void tick() {
		
	if(handler.getGame().getPlayer().isLevelUp()) {
		moving =true;
		if(currentImage != Assets.door[4]) {
		 door.tick();
	    currentImage = door.getCurrentFrame();
		} else {
			doorOpen = true;
			moving = false;
		}
		
	}
	
	if(doorOpen == true) {
		stillImage = Assets.door[4];
	}
	
		
		
	}
	
	public boolean isDoorOpen() {
		return doorOpen;
	}

	public void draw(Graphics g) {
		//if(image != null)
		if(moving)
		g.drawImage(currentImage, x, y, width, height, null);
		else
			g.drawImage(stillImage, x, y, width, height, null);
		
	}


}
