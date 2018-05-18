package myGame.Game.gfx;

import myGame.Game.Game;
import myGame.Game.Handler;
import myGame.Game.entities.Player;

public class Camera {
	
	private float xOffset, yOffset;
	Handler handler;
	int MAX_WIDHT_OFFSET = -3711;
	private boolean worldEnd = false;
	
	public Camera(Handler handler, float x, float y) {
		this.xOffset = x;
		this.yOffset = y;
		this.handler = handler;
		
	}
	

	public void tick() {
		xOffset = -handler.getGame().getPlayer().getX() + (handler.getGame().getWidth()/2) - handler.getGame().getPlayer().getWidth();
		checkBlankSpace();
		
	}
	
	public void checkBlankSpace() {// check if there are blanks spaces around the word and hide them
//		if((handler.getGame().getMountLayer().getX() < 0) || (handler.getGame().getMountLayer().getX() > 0)){
//			handler.getGame().getMountLayer().setX(0);
			
		//}

		
		
		
		
		if(xOffset > 0) {//check blank spaces to the left
			xOffset = 0;
		} else {
			if(xOffset < MAX_WIDHT_OFFSET){
				xOffset = MAX_WIDHT_OFFSET;
				worldEnd = true;
			} else {
				worldEnd = false;
			}
		}
	
	}
	
	public int getMAX_WIDHT_OFFSET() {
		return MAX_WIDHT_OFFSET;
	}

	public boolean isWorldEnded() {
		return worldEnd;
	}


	public void setWorldEnd(boolean worldEnd) {
		this.worldEnd = worldEnd;
	}


	public float getX() {
		return xOffset;
	}

	public void setX(float x) {
		this.xOffset = x;
	}

	public float getY() {
		return yOffset;
	}

	public void setY(float y) {
		this.yOffset = y;
	}

	

}
