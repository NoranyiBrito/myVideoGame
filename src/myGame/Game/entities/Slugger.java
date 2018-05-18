package myGame.Game.entities;



import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import myGame.Game.Game;
import myGame.Game.Handler;

import myGame.Game.gfx.Animation;
import myGame.Game.gfx.Assets;
import myGame.Game.gfx.Sounds;


public class Slugger extends Entity{

	   //Animations
	   Animation slugRight;
	   Animation slugLeft;
	   Animation slugDieRight;
	   Animation slugDieLeft;
	   
	   
	   BufferedImage stillImage;
	   BufferedImage currentImage;
	   String direction;
	   
	   Handler handler;
	   int originalPosition;
	   
	   //is the enemy moving?
	   boolean moving = true;
	   
	   //This keeps track of the current animation
	   Animation currentAnimation;
	   
	   Sounds killed;
	   
		public Slugger(Handler handler, int x, int y, int width, int height) {
			super(handler, x, y, width, height);
			bounds.x = 15;
			bounds.y = 26;
			bounds.width = 35;
	      	bounds.height= 45;
			
			health = 6;
			this.handler = handler;
			
			
			//Animations
			slugLeft = new Animation(400, Assets.slugLeft);
			slugRight = new Animation(400, Assets.slugRight);
		
	
			stillImage = Assets.slugLeft[5];
			
			//currentImage in animation
			currentImage = slugLeft.getCurrentFrame();
			
			//current animation displaying
			currentAnimation = slugLeft;
			
			originalPosition = x;
			killed = new Sounds("/sounds/enemyHit 2.wav");
		}

	
		public void tick() {
			//Animations
			currentAnimation.tick();
			currentImage = currentAnimation.getCurrentFrame();
			
             move();
             checkIfKilled();
             
             
             
             
			
		}
		
		 public void checkIfKilled() {
			 
			 if(((isCollitionDetected()) && (collidedWith instanceof Arrow))) {
                 killed.play();
           	  moving =false;
           	  health--;
           	  if(health == 0) {
           		  if(direction == "left")
           		stillImage = Assets.slugRight[0];
           		  else
           	    stillImage = Assets.slugLeft[5];	  
           	
           		  new java.util.Timer().schedule(
     			            new java.util.TimerTask() {
     			                @Override
     			                public void run() {
     			                   
     			                	isActive = false;
     			                }
     			            },
     			           300
     			    );
           	        
           		 
           	  }
            }
            
			 
		 }
		
		
		private Game getGame() {
			// TODO Auto-generated method stub
			return null;
		}


		public void move() {
						   
			moving = true;
			int playerXPos = handler.getGame().getPlayer().getX();
			
			if((playerXPos  >= (originalPosition - 200)) && (playerXPos  <= (originalPosition + 200))) {
				   if(moving) {
				      // if player pos is less than my pos
				   if (playerXPos > x) {
					 currentAnimation = slugRight;
					 direction = "right";
				  	 moveRight(1);
				   }
				    else { // if player pos is greater than my pos
					  if(moving) {
					 currentAnimation = slugLeft;
					 direction = "left";
					 moveLeft(1);		 
					   }
		    			}	
		    				
				   }
		    			}
	    	    	 
		}
	
		
		public void moveLeft(int vx) {
			
	
			currentAnimation = slugLeft;
			stillImage =  stillImage = Assets.slugLeft[0];
			if(!isCollitionDetected()) {
		
    	         x -= vx; //if link is in the left go left
   
			} 
		}
		
		
		public void moveRight(int vx) {
			
			 currentAnimation = slugRight;//not sure
			 stillImage =  stillImage = Assets.slugRight[5];
			 if(!isCollitionDetected()) {
			 
				 
				 
              x += vx; //if link is to the right go right
			 }
		}
		
		
		public void draw(Graphics g) {
//	     g.drawRect((int)(x + bounds.x), (int)(y + bounds.getY()), (int)bounds.getWidth(), (int)bounds.getHeight());
//		     		    	 	
			if (moving) {
				g.drawImage(currentImage, x, y, width, height, null);
				} else
			         {
					if(stillImage != null)
				 g.drawImage(stillImage, x, y, width, height, null);
					
			         }		
			
	}

}
