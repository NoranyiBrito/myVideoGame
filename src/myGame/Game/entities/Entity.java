package myGame.Game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import myGame.Game.Handler;

public abstract class Entity {
	 
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected double xVel;
	protected double yVel;
	private Handler handler;
	protected int health;
	protected Entity collidedWith;
	protected boolean isActive = true;
	
	Rectangle bounds;
	
	
	public Entity(Handler handler, int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void draw(Graphics g);
	
	public boolean isCollitionDetected() {
		
		LinkedList<Entity> entities = handler.getGame().getEntities();
		
		for(int i = 0; i < entities.size(); i++) {
		 Entity e = entities.get(i);	
		if ((e.equals(this) ) || ((this instanceof Arrow)  && (e instanceof Arrow)) || ((this instanceof Arrow)  && (e instanceof Diamonds))){
			continue;
		}else {
		double eBoundsW = (e.x + e.getBounds().x + e.getBounds().getWidth());
		double eBoundsX = e.x + e.getBounds().x; 
		double eBoundsY = (e.y + e.getBounds().getY());
		double eBoundsH = (e.y + e.getBounds().getY() + e.getHeight());
		
		double myBoundsW = (x + bounds.x + bounds.getWidth());
		double myBoundsX = x + bounds.x;
		double myBoundsY = y + bounds.y;
		double myBoundsH = (y + bounds.y + bounds.getHeight());
	
		
		//if other entity above or below not collision
		if  ((myBoundsH < eBoundsY)  && (myBoundsY < eBoundsY)  
			|| ((myBoundsY > eBoundsH) && (myBoundsY < eBoundsH)) ) {
			return false;
		}
		
		 else if (((myBoundsH >= eBoundsY  && myBoundsY < eBoundsY))||
			(myBoundsY <= eBoundsH && myBoundsY < eBoundsH ))	
		{	
		 //collided in the right go back 5
		 if((myBoundsW > eBoundsX) && (myBoundsX < eBoundsX )) 	{
			  collidedWith = e;
				return true;	
			
		} //collided in the left go back 5
			else if((myBoundsX < eBoundsW) && (myBoundsW > eBoundsW)) {
				 collidedWith = e;
				return true;
			}
		
		  }
		} 
		
	}
		return false;
		
		}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isActive() {
		return isActive ;
	}


}
