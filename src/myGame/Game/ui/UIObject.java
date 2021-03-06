package myGame.Game.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {
	protected int width, height;
	protected float x, y;
	protected boolean hovering = false;
	protected Rectangle bounds;
	
	public UIObject(float x, float y,int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int)x, (int)y, width, height);
		
		
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick();
	
	public void onMouseMove(MouseEvent e) {
		
		if(bounds.contains(e.getX(),e.getY()))
			hovering = true;
		else
			hovering = false;
	}
	
	public void onMouseRelease(MouseEvent e) {
		if (hovering)
			onClick();
	}
	
	//Getters and setter
	public float getX() {
		return x;
	}

}
