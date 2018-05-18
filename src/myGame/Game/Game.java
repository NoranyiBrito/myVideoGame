package myGame.Game;

import java.applet.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import myGame.Game.BackgroundLayer.ImageLayer;
import myGame.Game.entities.Arrow;
import myGame.Game.entities.Diamonds;
import myGame.Game.entities.Entity;
import myGame.Game.entities.Miniplatform;
import myGame.Game.entities.Player;
import myGame.Game.entities.Slugger;
import myGame.Game.gfx.Assets;
import myGame.Game.gfx.Camera;
import myGame.Game.gfx.Sounds;
import myGame.Game.utils.Door;
import myGame.Game.utils.Hud;





public class Game extends Applet implements KeyListener, Runnable, MouseListener, MouseMotionListener{
	/// we can implement runnable to be able to use the Thread
	//create a reference to a Thread
	    private Image    off_screen;
	    private Graphics off_g;
         
	   
	    
	    Player Jany; 
	    Camera camera;
		boolean moving = false;
		int counter = 0;
		
		private Hud hud;
		
		Thread thread;
		boolean lt_Pressed = false;
		boolean rt_Pressed = false;
		boolean up_Pressed = false;
		boolean dn_Pressed = false;
		boolean r_pressed  = false;
		boolean l_pressed  = false;	
		boolean entranceSoundPlaying = false;
		boolean deadSoundPlaying = false;
		boolean levelUpSoundPlaying = false;
		
		int mx; 
		int my;
		
		Handler handler;
		Slugger slugger;
		Slugger slugger1;
		
		Slugger[] slugger2 = new Slugger[20];
		Arrow[] rightArrows = new Arrow[200];
		Arrow[] leftArrows = new Arrow[200];
		int currentRArrow =0;
		int currentLArrow =0;
         
		Door door;
		
		
		
		ImageLayer mount     = new ImageLayer(handler,"myMountainsBackground.png", 0, 0, 70);
		ImageLayer trees     = new ImageLayer(handler,"treeTest.png", 0, 350, 20);
		ImageLayer platform  = new ImageLayer(handler,"platform.png", 0, 550, 20);
		
		Diamonds[] diamonds = new Diamonds[40];
		Diamonds[] diamonds2 = new Diamonds[10];
		
		Miniplatform miniplatform;
		
		LinkedList<Entity> entities = new LinkedList<Entity>();
		
		////////Sounds//////////
	    Sounds entranceSound;
	    Sounds levelUpSound;
	    Sounds deadSound;
		
		public void init() {
			//you can choose to use this at the beginning or not since you are inside the program
			Assets.init();
			
			
			off_screen = this.createImage(1000, 700);
		    off_g      = off_screen.getGraphics();
		   
		    
		    
			this.requestFocus();//makes your program to focus // it requests it to the OS
			this.addKeyListener(this);//add this KeyListener to the operating system
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			handler = new Handler(this);
			camera = new Camera(handler, 0, 0);
			
			hud = new Hud(handler, 50, 50, 50, 50, Assets.hudFace); 
			door = new Door(handler, 4390, 420, 20, 100, 150);
			Jany = new Player(handler, 100, 480, 90, 90);
			
			
			addEntity(Jany);
			initDiamonds();
			initSlugger();
			initArrows();
			
			
			////////////sounds//////////
			entranceSound = new Sounds("/sounds/Pim Poy.wav");
			deadSound = new Sounds("/sounds/159408__noirenex__life-lost-game-over.wav");
			levelUpSound = new Sounds("/sounds/156447__bustedearlobes__level-up.wav");
			
			////////////////////
			thread = new Thread(this);//this is making the run method to run
			thread.start();
			
		}
		
		public Camera getCamera() {
			return camera;
		}
		
		public void initDiamonds() {
			int xPos = 300;
		
			int yPos = 400;
			
			for(int i = 0; i < diamonds.length; i++) {	
				diamonds[i] = new Diamonds(handler, xPos * (i + 1) , yPos, 30, 40);
				addEntity(diamonds[i]);
				
			
			}
			
			int xPos2 = 450;
			for(int i = 0; i < diamonds2.length; i++) {	
			diamonds2[i] = new Diamonds(handler, xPos2 * (i + 1) , 500, 30, 40);
			addEntity(diamonds2[i]);
			}
		 
		}
		
		public void initSlugger() {
			int xPos = 500;
			int yPos = 500;
			
			for(int i = 0; i < slugger2.length; i++) {	
				slugger2[i] = new Slugger(handler, xPos * (i + 1) , yPos, 70, 70);
				addEntity(slugger2[i]);
			}
		}

		public void initArrows() {
			
			for(int i =0; i < rightArrows.length; i++) {
				
			rightArrows[i] = new Arrow(handler, 160, -1000, 50, 20, Assets.rightArrow);
			addEntity(rightArrows[i]);
			
			leftArrows[i] = new Arrow(handler, 160, -1000, 50, 20, Assets.leftArrow);
			addEntity(leftArrows[i]);
			}
		
		}
		
		
		@Override// comes from keylistener
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stubs
			
		}

		@Override/// comes from keylistener
		public void keyPressed(KeyEvent e) {
			
			int code = e.getKeyCode();
			
			if(code == e.VK_LEFT) lt_Pressed = true;
			if(code == e.VK_RIGHT) rt_Pressed = true;
			if(code == e.VK_UP)  up_Pressed = true;
			if(code == e.VK_DOWN) dn_Pressed = true;
			if(code == e.VK_R) r_pressed = true;
			if(code == e.VK_L) l_pressed = true;	
		}

		
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			int code = e.getKeyCode();
			if(code == e.VK_LEFT) lt_Pressed = false;
			if(code == e.VK_RIGHT) rt_Pressed = false;
			if(code == e.VK_UP)  up_Pressed = false;
			if(code == e.VK_DOWN) dn_Pressed = false;
			if(code == e.VK_R) {
				//rightArrow.setArrowPosition((Jany.getX() + Jany.getBounds().x +Jany.getBounds().width),( Jany.getY() + arrowYoffset));
				r_pressed = false;
			}
			if(code == e.VK_L) {
				//leftArrow.setArrowPosition(Jany.getX(),  (Jany.getY() + arrowYoffset));
				l_pressed = false;	
			}
			
		}
     
		@Override
		public void run() {//THis comes from the runnable interface that is implemented
			
			while(true) {//THis loop runs forever
			     tick();
	             
			     if(!entranceSoundPlaying) {
			    	 entranceSoundPlaying = true; 
	                 entranceSound.loop();
	              }
			     
			     if(Jany.isDead() || Jany.isLeveUp()) {
			    	 entranceSound.stop(); 
			    	 if(Jany.isDead())
			    	    if(!deadSoundPlaying) {
			    	    	deadSoundPlaying = true;
			    	    deadSound.loop();
			    	  }
			    	 if(Jany.isLeveUp())
			    		 if(!levelUpSoundPlaying) {
				    	    	levelUpSoundPlaying = true;
			    	          levelUpSound.loop();
			    		 }
			     }
			     
	             for(int i =0; i < rightArrows.length; i++) {
	     			rightArrows[i].move();
	     			leftArrows[i].move();
	     			
	     			}
	             
	             if(Jany.isJumping() || (Jany.isFalling())) {
	            	 Jany.move();
	             }
	             
	             
				if(lt_Pressed)
	            {
				  Jany.moveLeftBy(3);
				  mount.moveRightBy(20);
	              trees.moveRightBy(20);
	              platform.moveRightBy(20);
	               
	               
	               if(mount.getX() > 0) {
	            	   
	            	   mount.setX(0);
	            	   trees.setX(0);
	            	   platform.setX(0);
	            	   
	               }
					
	            }
	            if(rt_Pressed)
	            {
	            	   Jany.moveRightBy(3);
	            	   
	            	   //if world ended background will not move
	            	   if(!camera.isWorldEnded()) {
	               mount.moveLeftBy(20);
	               platform.moveLeftBy(20);
	               trees.moveLeftBy(20);
	            	   }
	            }
				
				if(up_Pressed) {
					
					Jany.jump();
					
				}  
				
				if( r_pressed) {
					
					Jany.shootRight(rightArrows[currentRArrow]);
					     currentRArrow++;
						if(currentRArrow >= rightArrows.length) {
							currentRArrow  =0;
					   }
	
				}
				
				if(l_pressed) {
					
					
						Jany.shootLeft(leftArrows[currentLArrow]);
						currentLArrow++;
						if(currentLArrow >= rightArrows.length) {
							currentLArrow =0;
					   }
						
				
				}
				//we dont call repaint we ask the operating system to paint
				repaint();///THis is what makes to see the change  as movement in the screen
				
				try {
					thread.sleep(15);//we give the os a sleep time after it repaint
					
				}catch(Exception x) {
					
					
				}
				
			}
			
		}
	
		public Arrow[] getRightArrows() {
			return rightArrows;
		}

		public void setRightArrows(Arrow[] rightArrows) {
			this.rightArrows = rightArrows;
		}

		public void checkActiveEntity() {
		
			for (int i =0; i < entities.size(); i++) {
				Entity e  = entities.get(i);
				e.tick();
				if(!e.isActive()) {
					entities.remove(e);
					
				}
			}
		}
		 public Door getDoor() {
				return door;
			}

		public Slugger getSlugger() {
			return slugger;
		}

		public void setSlugger(Slugger slugger) {
			this.slugger = slugger;
		}

		public Thread getThread() {
			return thread;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			 //TODO Auto-generated method stub
			mx = e.getX();
			my = e.getY();
				
		}


		@Override
		public void mouseReleased(MouseEvent e) {//this Methods are not in use
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {//this Methods are not in use
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {//this Methods are not in use
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {//this Methods are not in use
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			mx = e.getX();
			my = e.getY();
			
		}

		public Player getPlayer() {
			return Jany;
		}
		
         public ImageLayer getMountLayer() {
			
			return mount;
		}
		
         public ImageLayer getTreeLayer() {
			
			return trees;
		}
		
     
         public ImageLayer getPlatformLayer() {
			
			return platform;
		}
		public void removeEntity(Entity e) {
			entities.remove(e);
		}
		
		public void addEntity(Entity e) {
			entities.add(e);
		}
		
		public LinkedList<Entity> getEntities() {
			return entities;
		}
		
		public void tick() {
			 camera.tick();
              hud.tick();
			  door.tick();
			  
			  checkActiveEntity();
			  
			for(int i=0; i < entities.size(); i++) {
				entities.get(i).tick();
			}

		}
		
		
		public void paint(Graphics g) {
			
			///////////////////////this is the static for the camera///////////
			Graphics2D  g2d = (Graphics2D)g;
			
			g2d.translate(camera.getX(), camera.getY());
			
			
			mount.draw(g);
	        trees.draw(g);
			platform.draw(g);
			door.draw(g);
			hud.draw(g);
			for(int i=0; i < entities.size(); i++) {
				entities.get(i).draw(g);
			}
			
			
			g2d.translate(-camera.getX(), -camera.getY());////////end of camera
		}
		
	}

		