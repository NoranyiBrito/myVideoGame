package myGame.Game.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import myGame.Game.Handler;
import myGame.Game.gfx.Animation;
import myGame.Game.gfx.Assets;
import myGame.Game.gfx.Sounds;

public class Diamonds extends Entity{
	
	Animation diamond;
	BufferedImage currentImage;
	BufferedImage stillImage;
	boolean moving = true;
	Sounds diamondPickedUp;

	public Diamonds(Handler handler, int x, int y, int width, int height) {
		super(handler, x, y, width, height);
		
		diamond = new Animation (400, Assets.diamonds);
		diamondPickedUp = new Sounds("/sounds/341695__projectsu012__coins-1.wav");
	}

	@Override
	public void tick() {
	   diamond.tick();
	   currentImage = diamond.getCurrentFrame();
	    
	   if((isCollitionDetected()) && (!(collidedWith instanceof Slugger) && (!(collidedWith instanceof Arrow)))) {
	    	 diamondPickedUp.play();
	    	currentImage = Assets.dimondValue;
	    	
	    	new java.util.Timer().schedule(
			            new java.util.TimerTask() {
			                @Override
			                public void run() {
			                   
			                	isActive = false;
			                }
			            },
			           200
			    );
	    	
	    }
		
	}

	@Override
	public void draw(Graphics g) {
		if(moving)
			g.drawImage(currentImage, x, y, width, height, null);
			else
				g.drawImage(stillImage, x, y, width, height, null);
			
		}
		
	

}
