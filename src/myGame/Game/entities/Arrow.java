package myGame.Game.entities;

import java.awt.Graphics;
import java.awt.Image;
import myGame.Game.Handler;


public class Arrow extends Entity {
	Handler handler;
	
	Image arrow;
	
	Image currentArrow;
	
	private int width;
	private int height;
	private String direction;
	
	private boolean collitionDetected = false;
	private boolean moving = true;


	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Arrow(Handler handler, int x, int y, int width, int height, Image arrow) {
		super(handler, x, y, width, height);
		this.arrow  = arrow;//Assets.leftArrow;

		this.handler = handler;
		this.width = width;
		this.height = height;
		
		bounds.x = 15;
		bounds.y = 13;
		bounds.width = 20;
		bounds.height= 6;
		
	}
	
	public void setArrowPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void move() {   
		
		
		
		if(!collitionDetected) {
				
			
			if (direction == "right")
		          x +=5;
		           else
			       x -=5;
		
			
	}	else {
		new java.util.Timer().schedule(
	            new java.util.TimerTask() {
	                @Override
	                public void run() {
	                   
	                	setArrowPosition(-100, -100);
	                }
	            },
	           300
	    );

	}
			    
	   }
	
	
	
	@Override
	public void tick() {
		collitionDetected = isCollitionDetected();
		
		
	}


	@Override
	public void draw(Graphics g) {
		//System.out.println(arrow);
//		 g.drawRect((int)(300 + bounds.getX()), 
//				 (int)(400 + bounds.getY()), (int)bounds.getWidth(), (int)bounds.getHeight());
//		 g.drawImage(arrow, 300, 400, width, height, null);
		
		g.drawImage(arrow, x, y, width, height, null);
		
	}
	
	

}
