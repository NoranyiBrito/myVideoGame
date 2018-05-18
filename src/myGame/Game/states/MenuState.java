package myGame.Game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;



public class MenuState extends GameState{
	
	///private Background gameBackground;
	private int currentChoice = 0;
	private String[] options = { "START", "HELP", "QUIT"};
	
    private Color titleColor;
    private Font titleFont;
    private Font font;
	
    
    
	public MenuState(State gsm) {
		this.gsm = gsm;
		
		try {
		//	gameBackground = new Background("/Backgrounds/menubg.gif", 1);
		//	gameBackground.setVector(-0.1,  0);
			
			titleColor = Color.GREEN;
			titleFont = new Font("Century Gothic", Font.PLAIN, 25);
			
			font = new Font("Arial", Font.PLAIN, 10);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		//gameBackground.update();
	}

	@Override
	public void draw(Graphics2D g) {
		//draw backg
		//gameBackground.draw(g);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Lets Get Fun", 100, 70);//maybe change numbers
		
		
		//Draw menu
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 145, 140 + i *15);
		}
		
	}
	
	public void select() {
		if (currentChoice == 0) {//this will change the state to the game
		// gsm.setState(GameStateManager.LEVEL1STATE);
		}
       if (currentChoice == 1) {
			
		}
       if (currentChoice == 2) {
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {//if enter..them it selects that option
			select();
		}
		
		if (k == KeyEvent.VK_UP) {//it goes up in the menu
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length -1;
			}
		}
		
		if (k == KeyEvent.VK_DOWN) {//it goes up in the menu
			currentChoice++;
			if (currentChoice == options.length) {//if it is after the last go back to the first
				currentChoice = 0;
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
