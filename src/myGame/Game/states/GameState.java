package myGame.Game.states;

import java.awt.Graphics;
import myGame.Game.Handler;


public abstract class GameState {
	
	protected State gsm;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
		
	

}
