                             

package myGame.Game.gfx;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Assets {
	
	private static final int width = 32, height = 32;
	private static final int charWidth = 65, charHeight = 65;
	
	
	public static BufferedImage[] walkRight = new BufferedImage[9];
	public static BufferedImage[] walkLeft = new BufferedImage[9];
	public static BufferedImage[] shootRight = new BufferedImage[13];
	public static BufferedImage[] shootLeft = new BufferedImage[13];
	public static BufferedImage[]  walkUp = new BufferedImage[11];
	
	public static BufferedImage[] jumpRight = new BufferedImage[7];
	public static BufferedImage[] jumpLeft = new BufferedImage[7];
	public static BufferedImage[] die =  new BufferedImage[6];
	
    public static BufferedImage[] slugLeft = new BufferedImage[6];
    public static BufferedImage[] slugRight = new BufferedImage[6];
    public static BufferedImage[] slugDieRight  = new BufferedImage[6];
    public static BufferedImage[] slugDieLeft  = new BufferedImage[6];
    
    public static BufferedImage[] door = new BufferedImage[5];
    public static BufferedImage[] diamonds = new BufferedImage[5];
    public static BufferedImage[] hudNumbers = new BufferedImage[10];
    public static BufferedImage hudDiamond;
    
    public static BufferedImage dimondValue;
    public static Image leftArrow;
    public static Image rightArrow;
    public static Image hudFace;
    public static BufferedImage hudBackg;
	
	public static void init() {
		
		diamondAssets();
        arrowAssets();
		playerAssets();
		enemyAssets();
		doorAssets();
		hudAssets();
	}
	
	  
	
	   public static void playerAssets(){
		   
		   SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/myPlayerSheet.png"));
			
			for (int i =0; i < walkRight.length; i++) {
				walkRight[i]	= playerSheet.crop(charWidth * i, 0, charWidth, charHeight);
			}
			
			for (int i =0; i < walkLeft.length; i++) {
				walkLeft[i]	= playerSheet.crop(charWidth * i, charHeight, charWidth, charHeight);
			}			

			for (int i =0; i < shootRight.length; i++) {
				shootRight[i]	= playerSheet.crop(charWidth * i, charHeight * 2, charWidth, charHeight);
			}
			
			for (int i =0; i < shootLeft.length; i++) {
				shootLeft[i]	= playerSheet.crop(charWidth * i, charHeight * 3, charWidth, charHeight);
			}
			
			for (int i =0; i < jumpRight.length; i++) {
				jumpRight[i]	= playerSheet.crop(charWidth * i, charHeight * 4, charWidth, charHeight);
			}
			
			for (int i =0; i < jumpLeft.length; i++) {
				jumpLeft[i]	= playerSheet.crop(charWidth * i, charHeight * 5, charWidth, charHeight);
			}
			
			for (int i =0; i < die.length; i++) {
				die[i]	= playerSheet.crop(charWidth * i, charHeight * 6, charWidth, charHeight);
			}
			
			for (int i =0; i < walkUp.length; i++) {
				walkUp[i]	= playerSheet.crop(charWidth * i, charHeight * 7, charWidth, charHeight);
			}
			
			hudFace = playerSheet.crop(0, charHeight * 8, charWidth, charHeight);
		   
	   }
	   
	 

	public static void diamondAssets(){
		   
		   SpriteSheet diamondSheet = new SpriteSheet(ImageLoader.loadImage("/diamonds.png"));
		     
		  dimondValue = diamondSheet.crop(40 * 5, 0, 40, 40);
		  
		      for (int i =0; i < diamonds.length -1; i++) {
				diamonds[i]	= diamondSheet.crop(40 * i, 0, 40, 40);
			}
		        
		      hudDiamond =	diamondSheet.crop(40, 0, 40, 40);
	   }
	
	   public static void enemyAssets(){
		   //SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/myPlayerSheet.png"));
		   SpriteSheet sluggerSheet = new SpriteSheet(ImageLoader.loadImage("/myEnemy.png"));
		   
		   
		   for (int i =0; i < slugLeft.length; i++) {
				slugLeft[i]	= sluggerSheet.crop(50 * i, 0, 50, 50);
			}
		   
		   for (int i =0; i < slugRight.length; i++) {
				slugRight[i]= sluggerSheet.crop(50 * i, 50, 50, 50);
			}
		   
		   for (int i =0; i < slugDieRight.length; i++) {
				slugDieRight[i]= slugRight[0];
			}
		   
		   for (int i =0; i < slugDieLeft.length; i++) {
				slugDieLeft[i]= slugLeft[5];
		   }
		   
	   }
	   
	   
	   public static void arrowAssets(){
		   SpriteSheet arrowSheet = new SpriteSheet(ImageLoader.loadImage("/theArrow.png"));
		   leftArrow = arrowSheet.crop(0, 0, 20, 20); 
		   rightArrow = arrowSheet.crop(20, 0, 20, 20); 
	   }
	   
	   public static void doorAssets(){
		   SpriteSheet doorSheet = new SpriteSheet(ImageLoader.loadImage("/door.png"));
		   for (int i =0; i < door.length; i++) {
				door[i]= doorSheet.crop(67 * i, 0, 67, 67);
				
				
				
			}   
		  
	   }

	   
	   public static void hudAssets(){
		   SpriteSheet hudSheet = new SpriteSheet(ImageLoader.loadImage("/hudBackg.png"));
		      hudBackg = hudSheet.crop(0, 0, 160, 34);
		      
		      SpriteSheet hudSheet2 = new SpriteSheet(ImageLoader.loadImage("/hudNumbers.png"));
			   for (int i =0; i <  hudNumbers.length; i++) {
				   hudNumbers[i]= hudSheet2.crop(50 * i, 0, 50, 50);
					
					
					
				}   
		     
		      
				
			}
}


