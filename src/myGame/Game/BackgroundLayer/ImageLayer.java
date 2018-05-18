package myGame.Game.BackgroundLayer;
	
	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.Toolkit;

    import myGame.Game.Handler;

	public class ImageLayer
	{
	  
	   Image image;
	
       Handler handler;
	   private int x;
	   private int y;
	   private int z;
       private int width;
       private int height;
       private int index;

	   public ImageLayer(Handler handler, String file, int x, int y, int z )
	   {
	      image = Toolkit.getDefaultToolkit().getImage(file);

	      this.x = x;
	      this.y = y;
	      this.z = z;
	  
	     // System.out.println(handler.getGame().getPlayer().); 
	      this.handler = handler;
	   }

	   public int getWidth() {
		return width;
	   }
	   public Image getImage() {
			return image;
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

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void moveLeftBy(int dx)
	{
	      x -= dx;
	   }

	   public void moveRightBy(int dx)
	   
	   {
		   x += dx;
	   }


	   public void draw(Graphics g)
	   { 
		   
		   width = x/z + 646;
	      for(int i = 0; i < 10; i++) {
             index = i;
	         g.drawImage(image, x/z + 646*i, y, null);
	      }
	   }

	}


