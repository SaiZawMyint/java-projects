package com.J9Smart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.J9Smart.Background.BGProperty;


/**
 * JLoaderFrame is the Application interface frame, file loader,
 * resources loader width the progress information. This can perform 
 * by the desktop performance, resolution.
 *
 */
public class JLoaderFrame extends JFrame implements MouseMotionListener,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *Generate frame display. Set the location of the frame depend on the desktop screen.
	 */
	public static enum Display{
		/**
		 *Display your Frame in the centre of window Desktop. 
		 */
		CENTRE,
		/**
		 *Display your Frame at the top of window Desktop.
		 *If you using <code>useDefault</code> the top left or right position may display on the centre.
		 */
		TOP,
		/**
		 *Display your frame at top-left of window Disktop. 
		 */
		TOP_LEFT,
		/**
		 *Display your frame at top-right of window Disktop. 
		 */
		TOP_RIGHT,
		/**
		 *Display your Frame at the left of window Desktop.
		 *If you using <code>useDefault</code> the left of top or bottom position may display on the centre. 
		 */
		LEFT,
		
		/**
		 *Display your Frame at the bottom of window Desktop.
		 *If you using <code>useDefault</code> the bottom left or right position may display on the centre. 
		 */
		BOTTOM,
		/**
		 * Display your frame at bottom-left of window Disktop. 
		 */
		BOTTOM_LEFT,
		/**
		 * Display your frame at bottom-right of window Disktop. 
		 */
		BOTTOM_RIGHT,
		/**
		 *Display your Frame at the bottom of window Desktop.
		 *If you using <code>useDefault</code> the right position top or bottom may display on the centre.   
		 */
		RIGHT,
	}
	
	/**
	 * The dimension of desktop size.
	 */
	Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Default <code>JFrameLoader</code> application logo.
	 */
	ImageIcon LOGO=new ImageIcon(getClass().getClassLoader().getResource("default-logo.png"));
	
	static int width, height;
	
	int screenWidth=screenSize.width;
	int screenHeight=screenSize.height;
	
	static ArrayList<JLoaderFrame> loader=new ArrayList<JLoaderFrame>();
	static ArrayList<Text> text=new ArrayList<Text>();
	static ArrayList<Background> backgroundList=new ArrayList<Background>();
	/**
	 *Default Smart Frame.
	 *Customize window frame available.
	 */
	public JLoaderFrame(int width,int height) {
		// TODO Auto-generated constructor stub
		
		setSize(width, height);
		JLoaderFrame.width=width;
		JLoaderFrame.height=height;
		setUndecorated(true);
		this.setLayout(new BorderLayout());
		logo(LOGO);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loader.add(this);
	}
	
	/**
	 * Generate the application logo with the specify of <code>ImageIcon</code>.
	 * <pre style="color : orange">
	 * Note : If using the original parameter of the image 
	 * which not contains in the project source,
	 * the logo will store as default while exporting.
	 * </pre>
	 * @param img
	 */
	public void logo(ImageIcon img) {
		this.setIconImage(img.getImage());
	}
	/**
	 * Set the display postion of your window.
	 * @param Display
	 */
	public void Display(Display Display) {
		
		if(Display == com.J9Smart.JLoaderFrame.Display.CENTRE) {
			Centre();
		}
		if(Display == com.J9Smart.JLoaderFrame.Display.TOP) {
			Top();
		}
		if(Display == com.J9Smart.JLoaderFrame.Display.LEFT) {
			Left();
		}
		if(Display == com.J9Smart.JLoaderFrame.Display.BOTTOM) {
			Bottom();
		}
		if(Display == com.J9Smart.JLoaderFrame.Display.RIGHT) {
			Right();
		}
		if(Display == com.J9Smart.JLoaderFrame.Display.TOP_LEFT) {
			TopLeft();
		}
		if(Display == com.J9Smart.JLoaderFrame.Display.TOP_RIGHT) {
			TopRight();
		}
		if(Display == com.J9Smart.JLoaderFrame.Display.BOTTOM_LEFT) {
			BottomLeft();
		}
		if(Display == com.J9Smart.JLoaderFrame.Display.BOTTOM_RIGHT) {
			BottomRight();
		}
		
	}
	/**
	 * The location of <code>JLoaderFrame</code>
	 * @param x - <strong>x</strong> coordinate of the screen.
	 * @param y - <strong>y</strong> coordinate of the screen.
	 */
	public void Display(int x,int y) {
		this.setLocation(x, y);
	}

	/**
	 * Display the <code>JLoaderFrame</code> from the right-side of the screen.
	 */
	private void Right() {
		// TODO Auto-generated method stub
		int locationX=screenWidth-width;
		int locationY=(screenHeight/2)-(height/2);
		
		this.setLocation(locationX, locationY);
		
	}
	/**
	 * Display the <code>JLoaderFrame</code> from the left-side of the screen.
	 */
	private void Left() {
		// TODO Auto-generated method stub
		int locationX=0;
		int locationY=(screenHeight/2)-(height/2);
		
		this.setLocation(locationX, locationY);
		
	}
	/**
	 * Display the <code>JLoaderFrame</code> from the top of the screen.
	 */
	private void Top() {
		// TODO Auto-generated method stub
		int locationX=(screenWidth/2)-(width/2);
		int locationY=0;
		
		this.setLocation(locationX, locationY);
	}
	/**
	 * Display the <code>JLoaderFrame</code> from the top and left-side of the screen.
	 */
	private void TopLeft() {
		// TODO Auto-generated method stub
		int locationX=0;
		int locationY=0;
		
		this.setLocation(locationX, locationY);
	}
	/**
	 * Display the <code>JLoaderFrame</code> from the top and right-side of the screen.
	 */
	private void TopRight() {
		// TODO Auto-generated method stub
		int locationX=screenWidth-width;
		int locationY=0;
		
		this.setLocation(locationX, locationY);
	}
	/**
	 * Display the <code>JLoaderFrame</code> from the bottom the screen.
	 */
	private void Bottom() {
		// TODO Auto-generated method stub
		int locationX=(screenWidth/2)-(JLoaderFrame.width/2);
		int locationY=screenHeight-JLoaderFrame.height;
		
		this.setLocation(locationX, locationY);
	}
	/**
	 * Display the <code>JLoaderFrame</code> from the bottom and left-side of the screen.
	 */
	private void BottomLeft() {
		// TODO Auto-generated method stub
		int locationX=0;
		int locationY=screenHeight-height;
		
		this.setLocation(locationX, locationY);
	}
	/**
	 * Display the <code>JLoaderFrame</code> from the bottom and right-side of the screen.
	 */
	private void BottomRight() {
		// TODO Auto-generated method stub
		int locationX=screenWidth-width;
		int locationY=screenHeight-height;
		
		this.setLocation(locationX, locationY);
	}
	/**
	 * Display the <code>JLoaderFrame</code> from the centre of the screen.
	 */
	private void Centre() {
		// TODO Auto-generated method stub
		
		int locationX=(screenWidth/2)-(width/2);
		int locationY=(screenHeight/2)-(height/2);
		
		this.setLocation(locationX, locationY);
		
	}
	
	
	
	static JPanel defMenu;

	JPanel defClose;
	defTitle defTitle;
	
	static class defTitle extends JPanel{
		/**
		 * 
		 */
		boolean imgAdded;
		ImageIcon ic;
		Font WTF;
		private static final long serialVersionUID = 1L;
		public defTitle(ImageIcon icon,String windowTitle) {
			// TODO Auto-generated constructor stub
			this.setLayout(null);
			title=new JLabel();
			title.setText(windowTitle);
			title.setBackground(JLoaderFrame.mainBgColor);
			title.setBounds(30, 8, JLoaderFrame.width-90, 15);
			this.imgAdded=true;
			this.ic=icon;
			this.add(title);
			JLoaderFrame.defMenu.add(this,BorderLayout.CENTER);
			this.setBackground(JLoaderFrame.mainBgColor);
		}
		public defTitle(ImageIcon icon,String windowTitle,String font) {
			// TODO Auto-generated constructor stub
			this.setLayout(null);
			title=new JLabel();
			title.setText(windowTitle);
			title.setBackground(JLoaderFrame.mainBgColor);
			title.setBounds(30, 8, JLoaderFrame.width-90, 15);
			WTF=new Font(font, Font.BOLD, 13);
			title.setFont(WTF);
			this.imgAdded=true;
			this.ic=icon;
			this.add(title);
			JLoaderFrame.defMenu.add(this,BorderLayout.CENTER);
			this.setBackground(JLoaderFrame.mainBgColor);
		}
		public defTitle(String windowTitle) {
			// TODO Auto-generated constructor stub
			this.setLayout(null);
			title=new JLabel();
			title.setBackground(JLoaderFrame.mainBgColor);
			title.setText(windowTitle);
			title.setBounds(5, 8, JLoaderFrame.width-90, 15);
			this.imgAdded=false;
			this.add(title);
			this.setBackground(JLoaderFrame.mainBgColor);
			JLoaderFrame.defMenu.add(this,BorderLayout.CENTER);
		}
		public defTitle(String windowTitle,String font) {
			// TODO Auto-generated constructor stub
			this.setLayout(null);
			title=new JLabel();
			title.setBackground(JLoaderFrame.mainBgColor);
			title.setText(windowTitle);
			title.setBounds(5, 8, JLoaderFrame.width-90, 15);
			WTF=new Font(font, Font.BOLD, 13);
			title.setFont(WTF);
			this.imgAdded=false;
			this.add(title);
			this.setBackground(JLoaderFrame.mainBgColor);
			JLoaderFrame.defMenu.add(this,BorderLayout.CENTER);
		}
		public defTitle(ImageIcon icon) {
			// TODO Auto-generated constructor stub
			this.setLayout(null);
			this.imgAdded=true;
			this.ic=icon;
			this.setBackground(JLoaderFrame.mainBgColor);
			JLoaderFrame.defMenu.add(this,BorderLayout.CENTER);
		}
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(JLoaderFrame.mainBgColor);
			if(imgAdded) {
				g.drawImage(this.ic.getImage(), 5, 5, 20, 20, null);
			}
			//g.dispose();
		}
	}
	JLabel close,icon;

	static JLabel title;
	Font font=new Font("Arial", Font.BOLD, 13);
	ImageIcon closeIcon=new ImageIcon(getClass().getClassLoader().getResource("close-icon.png"));
	
	
	/**
	 * The menu of the frame which contains <strong>close menu</strong> only.
	 */
	public void defaultCMenu() {
		defMenu=new JPanel();
		defMenu.setLayout(new BorderLayout());
		defMenu.setBackground(JLoaderFrame.mainBgColor);
		
		customizeRightMenu();
		
		this.add(defMenu,BorderLayout.NORTH);
	}
	
	/**
	 * The menu of the frame which contains <strong>title</strong> and <strong>close menu</strong>.
	 * @param windowTitle - the title of the frame
	 */
	public void defaultCMenu(String windowTitle) {
		defMenu=new JPanel();
		defMenu.setLayout(new BorderLayout());
		defMenu.setBackground(JLoaderFrame.mainBgColor);
		
		defTitle=new defTitle(windowTitle);
		
		
		customizeRightMenu();
		
		this.add(defMenu,BorderLayout.NORTH);
	}
	/**
	 * The menu of the frame which contains <strong>title</strong> with specified <strong>font</strong> and <strong>close menu</strong>.
	 * @param windowTitle - the title of the frame.
	 * @param font - the font of the title.
	 */
	public void defaultCMenu(String windowTitle,String font) {
		defMenu=new JPanel();
		defMenu.setLayout(new BorderLayout());
		defMenu.setBackground(JLoaderFrame.mainBgColor);
		
		defTitle=new defTitle(windowTitle,font);
		
		
		customizeRightMenu();
		
		this.add(defMenu,BorderLayout.NORTH);
	}
	
	/**
	 *  The menu of the frame which contains <strong>icon</strong> and <strong>close menu</strong>.
	 * @param iconImage - the icon of the window.
	 */
	public void defaultCMenu(ImageIcon iconImage) {
		defMenu=new JPanel();
		defMenu.setLayout(new BorderLayout());
		defMenu.setBackground(JLoaderFrame.mainBgColor);
		
		defTitle=new defTitle(iconImage);
		
		customizeRightMenu();
		
		this.add(defMenu,BorderLayout.NORTH);
	}
	
	/**
	 * The menu of the frame which contains <strong>Icon</strong>,<strong>title</strong> and <strong>close menu</strong>.
	 * @param iconImage - the icon of the frame.
	 * @param windowTitle - the title of the frame.
	 */
	public void defaultCMenu(ImageIcon iconImage,String windowTitle) {
		defMenu=new JPanel();
		defMenu.setLayout(new BorderLayout());
		defMenu.setBackground(JLoaderFrame.mainBgColor);
		
		defTitle=new defTitle(iconImage,windowTitle);
		
		customizeRightMenu();
		this.add(defMenu,BorderLayout.NORTH);
	}
 
	/**
	 * The menu of the frame which contains <strong>Icon</strong>,<strong>title</strong> with specified <strong>font</strong> and <strong>close menu</strong>.
	 * @param iconImage
	 * @param windowTitle
	 * @param font
	 */
	public void defaultCMenu(ImageIcon iconImage,String windowTitle,String font) {
		defMenu=new JPanel();
		defMenu.setLayout(new BorderLayout());
		defMenu.setBackground(JLoaderFrame.mainBgColor);
		
		defTitle=new defTitle(iconImage,windowTitle,font);
		
		customizeRightMenu();
		
		this.add(defMenu,BorderLayout.NORTH);
	}
 
	
	/**
	 * Close menu.
	 */
	private void customizeRightMenu() {
		defClose=new JPanel();
		defClose.setBackground(defMenu.getBackground());
		defClose.addMouseListener(this);
		close = new JLabel();
		close.addMouseListener(this);
		close.setIcon(closeIcon);
		close.setHorizontalAlignment(SwingConstants.CENTER);
		close.setBounds(0, 0, 70, 30);
		//close.setBackground(defMenu.getBackground());
		close.setForeground(Color.BLACK);
		
		defClose.add(close);
		defMenu.add(defClose,BorderLayout.EAST);
	}
	
	static Color mainBgColor;
	public Background background;
	/**
	 * The frame <strong>Background Clor</strong>
	 * @param color - background color.
	 */
	public void setFrameBackground(Color color) {
		background=new Background();
		
		background.setBackgroundColor(color);
		
		this.add(background,BorderLayout.CENTER);
	}
	/**
	 * <strong>Gradient Background</strong> of the frame with color C1 and color C2 and their property <code>BGProperty</code>.
	 * @param color1 - main color for gradient.
	 * @param color2 - second color for gradient.
	 * @param bg - property of gradient.
	 */
	public void setFrameBackground(Color color1,Color color2,BGProperty bg) {
		background = new Background();
		GradientPaint gp = null;
		/**if(bg == BGProperty.BACKGROUND_CENTRE) {
			gp=new GradientPaint(JLoaderFrame.width/2, JLoaderFrame.height/2, color1, 0, 0, color2, true);
		}*/
		if(bg == BGProperty.BACKGROUND_CENTRE) {
			gp=new GradientPaint(JLoaderFrame.width/2, JLoaderFrame.height/2, color1, 0, JLoaderFrame.height/2, color2, true);
		}
		if(bg == BGProperty.BACKGROUND_LEFT) {
			gp=new GradientPaint(0, 0, color1, JLoaderFrame.width, 0, color2);
		}
		if(bg == BGProperty.BACKGROUND_RIGHT) {
			gp=new GradientPaint(0, 0, color2, JLoaderFrame.width, 0, color1);
		}
		if(bg == BGProperty.BACKGROUND_TOP) {
			gp=new GradientPaint(0, 0, color1,0, JLoaderFrame.height, color2);
		}
		if(bg == BGProperty.BACKGROUND_BOTTOM) {
			gp=new GradientPaint(0, JLoaderFrame.height/2, color1,0, 0, color2);
		}
		if(bg == BGProperty.BACKGROUND_FILL) {
			gp=new GradientPaint(0, JLoaderFrame.height, color1,0, JLoaderFrame.height, color2);
		}
		if(bg == BGProperty.BACKGROUND_REPEAT) {
			gp=new GradientPaint(0, 10, color1, 20, 40, color2, true);
		}
		background.setBackgroundGradient(gp,color2, color2, bg);
		
		this.add(background,BorderLayout.CENTER);
	}
	public static enum Direction{
		TOP,
		TOP_RIGHT,
		TOP_LEFT,
		RIGHT,
		BOTTOM,
		BOTTOM_RIGHT,
		BOTTOM_LEFT,
		LEFT,
		CENTRE
	}
	public void setFrameBackground(Color color1,Color color2, Direction d) {
		background = new Background();
		
		GradientPaint gp = null;
		
		if(d == Direction.TOP || d == Direction.BOTTOM) {
			gp=new GradientPaint(0, 0, color1, 0, percentageWidth(20), color2, true);
		}
		
		if(d == Direction.TOP_LEFT || d == Direction.BOTTOM_RIGHT) {
			gp=new GradientPaint(0, percentageWidth(5), color1, percentageWidth(20), percentageWidth(30), color2, true);
		}
		
		if(d == Direction.RIGHT || d == Direction.LEFT) {
			gp=new GradientPaint(0, percentageWidth(20), color1, percentageWidth(20), percentageWidth(20), color2, true);
		}
		
		if(d == Direction.TOP_RIGHT || d == Direction.BOTTOM_LEFT) {
			gp=new GradientPaint(percentageWidth(100), percentageHeight(10), color1, percentageWidth(80), percentageHeight(30), color2, true);
		}
		background.setBackgroundGradient(gp, color1, color2, d);
		this.add(background,BorderLayout.CENTER);
	}
	/**
	 * Calculate the percentage of the width of frame.
	 * @param percentage - the percentage that we want.
	 * @return the percentage of the frame.
	 */
	public static int percentageWidth(int percentage) {
		double calculateR=0;
		if(percentage < 100) {
			if(percentage<10) {
				percentage=percentage*100;
				int c=percentage/100;
				calculateR=c*0.01;
			}else {
				percentage=percentage*10;
				int c=percentage/100;
				calculateR=c*0.1;
			}
		}else {
		calculateR=percentage/100;
		}
		int result=(int) (calculateR*(JLoaderFrame.width));
		return result;
	}
	/**
	 * Calculate the percentage height of frame.
	 * @param percentage - the percentage that we want.
	 * @return the percentage of the frame.
	 */
	public static int percentageHeight(int percentage) {
		double calculateR=0;
		if(percentage < 100) {
			if(percentage<10) {
				percentage=percentage*100;
				int c=percentage/100;
				calculateR=c*0.01;
			}else {
				percentage=percentage*10;
				int c=percentage/100;
				calculateR=c*0.1;
			}
		}else {
		calculateR=percentage/100;
		}
		int result=(int) (calculateR*(JLoaderFrame.height))-25;
		return result;
	}
	/**
	 * Set the custom <strong>Image Background</strong> for the frame.
	 * @param image - background image.
	 * @param x - <strong>x<strong> coordinate of the frame.
	 * @param y - <strong>y<strong> coordinate of the frame.
	 * @param width - width of the image.
	 * @param height - height of the image.
	 */
	public void setFrameBackground(ImageIcon image,int x,int y,int width,int height) {
		background = new Background();
		background.setBackgroundImage(image, x, y, width, height);
		this.add(background,BorderLayout.CENTER);
	}
	/**
	 * Set the custom <strong>Image Background</strong> for the frame with background property.
	 * <pre style="color: orange">Note : the width and height of the image will store as its origin.</pre>
	 * @param image - background image
	 * @param bg - the property of the background.
	 */
	public void setFrameBackground(ImageIcon image,BGProperty bg) {
		background = new Background();
		background.setBackgroundImage(image, bg);
		this.add(background,BorderLayout.CENTER);
	}
	/**
	 * Set the custom <strong>Image Background</strong> for the frame width background property and its width and height.
	 * @param image - background image.
	 * @param width - width of the image.
	 * @param height - height of the image.
	 * @param bg - the property of the background.
	 */
	public void setFrameBackground(ImageIcon image,int width,int height,BGProperty bg) {
		background = new Background();
		background.setBackgroundImage(image,width,height, bg);
		this.add(background,BorderLayout.CENTER);
	}
	
	Text textContent;
	public void drawText(String text,Direction d) {
		JLoaderFrame.text.add(new Text(text, d));
		
	}
	public void drawText(String text,Font font,Direction d) {
		
	}
	
	/**public void paint(Graphics g) {
		g.setColor(Color.blue);
		JLoaderFrame.mainBgColor=Color.BLUE;
		g.fillRect(0, 0, width, height);
		defTitle.paint(g);
		
	}*/
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this.close) {
			System.exit(0);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this.close) {
			this.defClose.setBackground(Color.red);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this.close) {
			this.defClose.setBackground(JLoaderFrame.defMenu.getBackground());
		}
	}
	
}
