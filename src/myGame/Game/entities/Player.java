package myGame.Game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.SynchronousQueue;

import javafx.scene.*;

import myGame.Game.Handler;
import myGame.Game.gfx.Animation;
import myGame.Game.gfx.Assets;
import myGame.Game.gfx.Sounds;


public class Player extends Entity{

	Random randomForVelocity = new Random();
	int A = 270; //this is angle used to calculate velocity

	   
	   private double dx;
	   private double dy;
	   private double vx;
	   private double vy;
	   
	   
	  
	   private double gravity = .5;
	   private String direction;
	   
	   private boolean falling = false;
	   private boolean jumping = false;
	   private boolean moving =  false;
	   private boolean dead = false;
	   private boolean levelUp = false;
	   

	private boolean shooting = true;

	  
	   private boolean collitionDetected = false;
	   private boolean onTheFloor;
	   private boolean walkingUp = false;
	   private Rectangle rect;
	   private int health = 5;
	   
	  
	   private int maxLimits;
	   private int maxOffset;
	   
	   private int arrowYoffset = 45;

	   double ax = 0;
	   double ay = gravity;
	   private double MAX_VELOCITY = 10;
	   
	   Random random = new  Random();//this is to set arrow position
	
	//Animation
	private Animation walkRight;
	private Animation walkLeft;
	private Animation shootRight;
	private Animation shootLeft;
	private Animation die;
	private Animation walkUp;

	protected long lastAttackTimer= 0;
    protected long attackCooldown = 1200;
    protected long attackTimer = attackCooldown;
    
    Sounds shootArrowSound;
 
	Handler handler;
	
	Animation[] animation = new Animation[4];
	String current = "walkRight";
	
	//keeps tracks of the current image or and still image to be display
	BufferedImage stillImage;
	BufferedImage currentImage;
		
		public Player(Handler handler,int x, int y, int width, int height) {
			super(handler, x, y, width, height);

			bounds.x = 30;
			bounds.y = 25;
			bounds.width = 30;
			bounds.height= 65;
			this.handler = handler;
			
			health = 6;	
			
			//Animations
			walkRight  = new Animation(200, Assets.walkRight);
			walkLeft   = new Animation(400, Assets.walkLeft);
			shootRight = new Animation(50, Assets.shootRight);
			shootLeft = new Animation(50, Assets.shootLeft);//there is somethig wrong with this one
			die = new Animation(400, Assets.die);
			walkUp = new Animation(400, Assets.walkUp);
			
			onTheFloor = y == 480;
			stillImage = walkRight.getStillImage();
			
			/////////////sounds////////////
			shootArrowSound = new Sounds("/sounds/bowArrow 1.wav");
		
			
			//maxOffset = 4113;
			maxLimits = 4509;
		}

		 
		   public boolean isShooting() {
			return shooting;
		}

		   public boolean isDead() {
				return dead;
			}


			public boolean isLeveUp() {
				return levelUp;
			}


		public void setShooting(boolean shooting) {
			this.shooting = shooting;
		}


		public String getDirection() {
			return direction;
		}


	
		public int getMaxOffsxet() {
			return maxOffset;
		}
		
		public int getMaxLimits() {
			return maxLimits;
		}

		public void tick() {
			//Animations
			walkRight.tick();
			walkLeft.tick();
			shootRight.tick();
			shootLeft.tick();
			if(currentImage != Assets.walkUp[10]) {
			walkUp.tick();
			}
			
			checkIfHurt();
			
			
			if(dead) {	
		      if(currentImage != Assets.die[5])
			   die.tick();
			}
			
			
			collitionDetected = isCollitionDetected();
			
			
			if (jumping)
				moving = true;
	         
			if (y < 480)
	        	 onTheFloor = false;
			
			
			
			if(health <= 0) {
				dead = true;
			}
         
			////create a method to fall
			if ((!collitionDetected) && (!onTheFloor) && (!jumping)){
	    	    if ((y < 480) || (!(y > 480)))
				y += vy;
	    	    if (y > 480)
	    	    	y =480;
	 	    
		}
			
			
		    //////check is levelup
			levelUp = isLevelUp();
			if(levelUp) {
				walkingUP();
				levelUp();
				
			}
			
			
			if(vy > MAX_VELOCITY) {
				vy = MAX_VELOCITY;
			}
			
		}
		
		
		public int getX() {
			return x;
		}
		
		public void walkingUP() {
			y = y - 10;
		}
		
		public boolean isLevelUp() {
			
			if((x >=  4395) && (!dead)){
				
				return true;
			}
			return false;
		}
		
		

		public void levelUp(){
			if(handler.getGame().getDoor().isDoorOpen()) {
			  moving = true;
		       currentImage = walkUp.getCurrentFrame();
		     if (y <= 380 ){
		    	 isActive = false;
		     }
			// if(currentImage == Assets.walkUp[10]) {
				  new java.util.Timer().schedule(
   			            new java.util.TimerTask() {
   			                @Override
   			                public void run() {
   			                   
   			                	isActive = false;
   			                }
   			            },
   			           500
   			    );
				
				
			 // }
			}
		}

		public void checkIfHurt() {
			
		if(collitionDetected) {
			if(collidedWith instanceof Slugger) {
				
					hurt();
				}
				
			}
		}
		
		
		public void hurt() {
		  
		        attackTimer += System.currentTimeMillis() - lastAttackTimer;
		        lastAttackTimer = System.currentTimeMillis();
		        if (attackTimer < attackCooldown) {
		            return;
		        }
		        
		        attackTimer = 0;
		        
		
		        System.out.println("this is health " + health);
		        health--;
	
				if(dead ) {
				moving = true;
				stillImage = Assets.die[5];
				if(currentImage != Assets.die[5]) {
					currentImage = die.getCurrentFrame();
					} else {
						moving = false;
					}
				
		        	
				}	
		}
		
		
		public void moveLeftBy(int vx) {
			if((!dead) && (!levelUp))	{	
			moving = true;
			
			 
			//I HAVE TO CHANGE THIS TO CHECK ALL ENTITIES
			if((!collitionDetected) ||  (collidedWith instanceof Diamonds)) {
			
				     x -= vx;
		     	} else {
		     		if(direction == "left")
			         x = (int)(collidedWith.x + collidedWith.bounds.x + collidedWith.getBounds().getWidth() + 2);
		     		else 
		     		x = (collidedWith.getX() -width + bounds.x -2 );
		     	}
			
			
			//this will prevent player from walking left outside the world
			if(x+ bounds.x <= 0)
				x = -bounds.x;
			
			currentImage = walkLeft.getCurrentFrame();
			//currentAnimation = walkLeft;
			stillImage = walkLeft.getStillImage();
			direction = "left";
		  }
			
		}
		
		public void moveRightBy(int vx) {
			if((!dead) && (!levelUp))	{			
			moving = true;
		
			if((!collitionDetected) ||  (collidedWith instanceof Diamonds)) {	
			    x += vx;
			} else {
				if(direction == "right")
				x = (collidedWith.getX() -width + bounds.x -2 );
				else
					x = (int)(collidedWith.x + collidedWith.bounds.x + collidedWith.getBounds().getWidth() + 2);
			}
			
			//this will prevent the player to keep walking at the end of the world
			if( x + bounds.x + bounds.width >= maxLimits) {
				x =  x -( bounds.x + bounds.width);
			}
			
			currentImage = walkRight.getCurrentFrame();
			//currentAnimation = walkRight;
			stillImage = walkRight.getStillImage();
            direction = "right"; 
			}
		}
		
		public void shootRight(Arrow arrow) {
			if((!dead) && (!levelUp))	{	
			shootArrowSound.play();
			moving = true;
			currentImage =	shootRight.getCurrentFrame();
			stillImage =  shootRight.getStillImage();
			direction = "right";

			 int r = random.nextInt() % 5;
			
			arrow.setArrowPosition((x + bounds.x + bounds.width + r),( y + arrowYoffset));
			arrow.setDirection("right");
			
	
			shooting = true;
			}
		}
		
		public void shootLeft(Arrow arrow) {
			if((!dead) && (!levelUp))	{	
			shootArrowSound.play();
			moving = true;
			currentImage = shootLeft.getCurrentFrame();
			stillImage   = shootLeft.getStillImage();
			direction = "left";
			
			arrow.setDirection("left");
			
			 int r = random.nextInt() % 5;
			arrow.setArrowPosition((x - arrow.getWidth() - r),  (y + arrowYoffset));
			
			shooting = true;
			}
		}
		
		public void jump()
		  {
			if((!dead) && (!levelUp))	{	
			 jumping = true;
			    
			
		     double unit_vx = Math.cos(A*Math.PI/180);
		     double unit_vy = Math.sin(A*Math.PI/180);

		     int r = randomForVelocity.nextInt() % 5;

		     double vx = 5 * unit_vx;
		     double vy = 5 * unit_vy;
             
		   
		     setVelocity(vx, vy);
			
			
		     //after  this is execute the move function is call int the game in the game loop
			}
		  }
		
		
		
		public void setVelocity(double vx, double vy) {
			this.vx = vx;
			this.vy = vy;
		}
		
		public void move(){
			if((!dead) && (!levelUp))	{	
			  
				moving = true;
				
		      if(vy > 0 ) {
		    	    falling = true;
		    	    jumping = false;
		      }
		      
		      
		      ///////////This does not have any effect right now
		      if (direction == "right") {
					if(falling) {moving =false; }
					currentImage = Assets.walkRight[5];
					
				}else {
					if(falling) {moving =false; }
					currentImage = Assets.walkLeft[5];
					
				}
		      
		   
			   if(collitionDetected) {
				   if(!(collidedWith instanceof Slugger)){
					y += gravity;
				   } else {
					y = ((collidedWith.y  + collidedWith.bounds.y) - (this.height + bounds.y + 2));
					jumping= false;
					falling = false;
				   }
					
				} else {
					if ((falling) || (jumping)) {
					  vx += ax;
				      vy += ay;

				      x += vx;
				      y += vy;
					}
				}
		    
		      if(y < 260) {//if it is this highg start falling 
		    	    y += gravity;
					jumping = false;
					falling = true;
				}
		      
		      if (y > 480)  {//If Player is on the floor stop falling
		    	   y = 480;
		    	   //falling = false;
		    	   jumping =false;
		      }
			}
		}
		
		
		public boolean isJumping() {
			return jumping;
		}
		
		public boolean isFalling() {
			return falling;
		}


		public void setJumping(boolean jumping) {
			this.jumping = jumping;
		}

		
		double myBoundsW = (x + bounds.x + bounds.getWidth());
		double myBoundsX = x + bounds.x ;
		double myBoundsY = y + bounds.y;
		double myBoundsH = (y + bounds.y + bounds.getHeight());
			
		
		public int getHealth() {
			return health;
		}


		public void draw(Graphics g) {
		
//			 g.drawRect((int)(x + bounds.getX()), 
//					 (int)(y + bounds.getY()), (int)bounds.getWidth(), (int)bounds.getHeight());
//		
			
			if (moving) {
			g.drawImage(currentImage, x, y, width, height, null);
			} else
		         {
				if(stillImage != null)
			g.drawImage(stillImage, x, y, width, height, null);
				else
			g.drawImage(walkRight.getStillImage(), x, y, width, height, null);	
				
				
				
			}
			moving = false;
            shooting = false;
			
	
		 
			
		}
		

	}
