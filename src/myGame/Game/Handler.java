package myGame.Game;

import myGame.Game.gfx.Camera;

public class Handler {
	private Game game;
	
	
	public Handler(Game game) {
		this.game = game;
		
	}
	
	public int getWidth() {
	    return game.getWidth();
	}
	
	public int getHeight() {
		return game.getWidth();
	}
	
	
	public Game getGame() {
		return game;
	}

   public Camera getCamera() {
	   return game.getCamera();
   }
	



}
