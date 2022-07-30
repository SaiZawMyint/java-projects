package com.J9Smart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.text.AttributedCharacterIterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.J9Smart.JLoaderFrame.Direction;

/**
 *The background of your loader frame. You can set any custom background.
 *
 */
public class Background extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *Background type of your loader frame. 
	 *<strong><code>Color</code></strong> is the default type.
	 */
	public static enum BackgroundType{
		
		/**
		 * Set the the background as <strong><i>Image</i></strong>.
		 */
		Image,
		/**
		 *Set the background as <strong><i>Gradient</i></strong>. 
		 */
		Gradient,
		/**
		 *Set the background as <strong><i>Color</i></strong>.
		 *<pre style='color:orange;'>Note : This is the default type.</pre>
		 */
		Color,
		
	}
	
	public static enum BGProperty{
		
		/**
		 *Repeat the background.<br>
		 *This method cannot use in background <strong><code>Color</code></strong> type(nothing effect on the background).
		 *
		 */
		BACKGROUND_REPEAT,
		/**
		 *Fill the background on all of the parent Component.<br>
		 *If using <strong><code>Image</code></strong> background type, the width and height
		 *of the background-image may set as the parent component width and height.
		 */
		BACKGROUND_FILL,
		/**
		 *Set the background at the centre of parent Component.
		 */
		BACKGROUND_CENTRE,
		/**
		 *Set the background at the left of parent Component. 
		 */
		BACKGROUND_LEFT,
		/**
		 *Set the background at the right of parent Component. 
		 */
		BACKGROUND_RIGHT,
		/**
		 *Set the background at the top of parent Component. 
		 */
		BACKGROUND_TOP,
		/**
		 *Set the background at the bottom of parent Component. 
		 */
		BACKGROUND_BOTTOM,
		
		BACKGROUND_DEFAULT
		
	}
	
	Color color;
	boolean image,gradient,colorType,bgrepeat,bgfill,bgcentre,bgleft,bgtop,bgright,bgbottom;
	BackgroundType BGT;
	BGProperty BGP;
	ImageIcon bgImage;
	GradientPaint GP;
	public Background() {
		// TODO Auto-generated constructor stub
		JLoaderFrame.backgroundList.add(this);
	}
	
	/**
	 * Set the background type.
	 * See also {@link getBackgroundType}
	 * @param bgtype - the type of the background.
	 */
	public void setBackgroundType(BackgroundType bgtype) {
		if(bgtype == BackgroundType.Image) 
			this.image=true;
		
		if(bgtype == BackgroundType.Gradient) 
			this.gradient=true;
		
		if(bgtype == BackgroundType.Color) 
			this.colorType=true;
		
		this.BGT=bgtype;
	}
	
	/**
	 * Set the background property.<br>
	 * The property of the background depends on the background type.
	 * <pre>
	 * <strong><code>Image</code></strong> : Can be used all the Property.
	 * <strong><code>Gradient</code></strong> : Set the gradient start. If using <strong><code>BACKGROUND_CENTRE</code></strong>
	 * the gradient will start from centre (Othe directions depend on this property). <strong><code>BACKGROUND_REPEAT</code></strong> and <storng><code>
	 * BACKGROUND_FILLL</code></strong> have no effect in this type.
	 * <strong><code>Color</code></strong> : <strong>DEFAULT TYPE</strong>. No Effect for this type.
	 * </pre>
	 * See also {@link getBackgroundProperty}
	 * @param bgp - Background type
	 */
	public void setBackgroundProperty(BGProperty bgp) {
		if(bgp == BGProperty.BACKGROUND_REPEAT) 
			this.bgrepeat=true;
		if(bgp == BGProperty.BACKGROUND_CENTRE)
			this.bgcentre=true;
		if(bgp == BGProperty.BACKGROUND_FILL)
			this.bgfill=true;
		if(bgp == BGProperty.BACKGROUND_LEFT)
			this.bgleft=true;
		if(bgp == BGProperty.BACKGROUND_BOTTOM)
			this.bgbottom=true;
		if(bgp == BGProperty.BACKGROUND_RIGHT)
			this.bgright=true;
		if(bgp == BGProperty.BACKGROUND_TOP)
			this.bgtop=true;
		this.BGP=bgp;
	}
	
	/**
	 * Set the type of the background and its releated property.
	 * <br><br>See also
	 * {@link setBackgroundType} ,
	 * {@link setBackgroundProperty}.
	 * @param bgtype - the type of the background.
	 * @param bgp - the property of the background.
	 */
	public void setBackground(BackgroundType bgtype,BGProperty bgp) {
		if(bgtype!=null)
			setBackgroundType(bgtype);
		if(bgp!=null)
			setBackgroundProperty(bgp);
	}
	
	public void setBackgroundColor(Color color) {
		this.color=color;
		this.colorType=true;
		this.BGT=BackgroundType.Color;
	}
	
	public void setBackgroundImage(ImageIcon image,BGProperty bgp) {
		this.bgImage=image;
		this.image=true;
		this.BGP=bgp;
	}
	public void setBackgroundImage(ImageIcon image,int width,int height,BGProperty bgp) {
		this.bgImage=image;
		this.image=true;
		this.imgwidth=width;
		this.imgheight=height;
		this.BGP=bgp;
	}
	public void setBackgroundImage(ImageIcon image,int x,int y,int width,int height) {
		this.bgImage=image;
		this.image=true;
		this.BGP=BGProperty.BACKGROUND_DEFAULT;
		this.imgx=x;
		this.imgy=y;
		this.imgwidth=width;
		this.imgheight=height;
	}
	Color color1,color2,color3;
	boolean twoColor,threeColor;
	public void setBackgroundGradient(GradientPaint gp,Color color1,Color color2,BGProperty bgp) {
		this.GP=gp;
		this.gradient=true;
		this.BGP=bgp;
		this.color1=color1;
		this.color2=color2;
	}
	
	public void setBackgroundGradient(GradientPaint gp,Color color1,Color color2,Direction d) {
		this.GP=gp;
		this.gradient=true;
		this.color1=color1;
		this.color2=color2;
	}
	boolean drawText;
	String text;
	Font font;
	public void drawText(Text text,Direction d) {
		this.drawText=true;
		this.text=text.getText();
	}
	public void drawText(Text text,Font font,Direction d) {
		this.drawText=true;
		this.text=text.getText();
		this.font=font;
		
	}
	
	/**
	 * @return the background type.
	 */
	public BackgroundType getBackgroundType() {
		return this.BGT;
	}
	/**
	 * @return the background property.
	 */
	public BGProperty getBackgroundProperty() {
		return this.BGP;
	}
	
	private int centerX(int width) {
		int centerX=0;
		
		centerX=(JLoaderFrame.width/2)-width/2;
		
		return centerX;
	}
	
	private int centerY(int height) {
		int centerX=0;
		
		centerX=((JLoaderFrame.height/2)-height/2)-20;
		
		return centerX;
	}
	
	int imgx,imgy,imgwidth=0,imgheight=0;
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2=(Graphics2D) g;
		
		
		if(this.gradient) {
			
			g2.setPaint(this.GP);
			g2.fill(new Rectangle2D.Float(0, 0, JLoaderFrame.width, JLoaderFrame.height));
			
		}
		if(this.image) {
			
			if(this.imgwidth == 0 || this.imgwidth == 0) {
				this.imgwidth=this.bgImage.getIconWidth();
				this.imgheight=this.bgImage.getIconHeight();
			}
			if(this.BGP == BGProperty.BACKGROUND_DEFAULT) {
				g2.drawImage(this.bgImage.getImage(), this.imgx, this.imgy, this.imgwidth, this.imgheight, null);
			}
			if(this.BGP == BGProperty.BACKGROUND_CENTRE) {
				g2.drawImage(this.bgImage.getImage(), centerX(this.imgwidth), centerY(this.imgheight),this.imgwidth,this.imgheight, null);
			}
			if(this.BGP == BGProperty.BACKGROUND_TOP) {
				g2.drawImage(this.bgImage.getImage(), centerX(this.imgwidth),0,this.imgwidth,this.imgheight, null);
			}
			if(this.BGP == BGProperty.BACKGROUND_LEFT) {
				g2.drawImage(this.bgImage.getImage(), 0, centerY(this.imgheight),this.imgwidth,this.imgheight, null);
			}
			if(this.BGP == BGProperty.BACKGROUND_BOTTOM) {
				g2.drawImage(this.bgImage.getImage(), centerX(this.imgwidth), JLoaderFrame.height-(this.imgheight)-30,this.imgwidth,this.imgheight, null);
			}
			if(this.BGP == BGProperty.BACKGROUND_RIGHT) {
				g2.drawImage(this.bgImage.getImage(), JLoaderFrame.width-(this.imgwidth), centerY(this.imgheight),this.imgwidth,this.imgheight, null);
			}
			if(this.BGP == BGProperty.BACKGROUND_FILL) {
				g2.drawImage(this.bgImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
			}
			if(this.BGP == BGProperty.BACKGROUND_REPEAT) {
				for(int i=0;i<JLoaderFrame.width;i++) {
					
					
					for(int j=0;j<JLoaderFrame.height;j++) {
						
						
						g2.drawImage(this.bgImage.getImage(), i*this.imgwidth, j*this.imgheight, this.imgwidth, this.imgheight, null);
					}
					
				}
			}
		}
		if(this.colorType) {
			g2.setColor(color);
			g2.fillRect(0, 0, JLoaderFrame.width, JLoaderFrame.height);
		}
		
			g2.setColor(Color.black);
			
			for(int i=0;i<JLoaderFrame.text.size();i++) {
				if(JLoaderFrame.text.get(i).font==null) {
					JLoaderFrame.text.get(i).font=new Font("Arial", Font.BOLD, 15);
					g2.setFont(JLoaderFrame.text.get(i).font);
				}else {
					g2.setFont(JLoaderFrame.text.get(i).font);
				}
				if(JLoaderFrame.text.get(i).d == Direction.CENTRE) {
					
					g2.drawString(JLoaderFrame.text.get(i).text,
							JLoaderFrame.percentageWidth(50)-(JLoaderFrame.text.get(i).text.length()+JLoaderFrame.text.get(i).font.getSize()), JLoaderFrame.percentageHeight(50));
				}
				if(JLoaderFrame.text.get(i).d == Direction.TOP) {
					g2.drawString(JLoaderFrame.text.get(i).getText(),
							JLoaderFrame.percentageWidth(50)-(JLoaderFrame.text.get(i).text.length()+JLoaderFrame.text.get(i).font.getSize()), JLoaderFrame.text.get(i).font.getSize());
				}
			
			}
			repaint();
		
		
	}

}
