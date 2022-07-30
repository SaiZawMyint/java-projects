package com.J9FrameWork;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import com.J9Tool.J9TextEditorPane;
import com.J9Tool.J9ToolBar;
import com.J9Tool.StyleTool;
import com.J9Tool.TextEditor;

import ErrorLogs.CodeException;
import ErrorLogs.MenuException;
import ErrorLogs.StyleException;

/**
 * <strong>Smart Frame for your GUI application.</string><br>
 * <br>
 * This is the same as <code>JFrame</code>. But this's helping us for more
 * shorter code.
 *
 */
public class J9Frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Menu
	/**
	 * The main menu bar using <code>JMenuBar</code>.
	 */
	JMenuBar mainBar;
	Container container;

	//TextEditor
	public static TextEditor editor,errorLog;
	
	// boolean
	boolean def = true;

	// Screen size
	/**
	 * The value of <strong>Desktop Screen Size</strong>.
	 * 
	 * @Return Dimension value as screen size.
	 */
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	int screenWidth=screenSize.width;
	int screenHeight=screenSize.height;
	// Menu Array list
	/**
	 * <strong><code>smartMenu</code></strong><br>
	 * <br>
	 * Return the added menus on the frame.
	 * 
	 */

	/**
	 * <strong>Main Frame array list</strong> <br>
	 * You can get all menu added on frame from this.
	 */
	public static ArrayList<J9Frame> j9Frames=new ArrayList<J9Frame>();
	/**
	 *<strong>Main Menu array list</strong> <br>
	 * You can get all menu added on frame from this. 
	 */
	public static ArrayList<J9Menu> j9menu = new ArrayList<J9Menu>();
	/**
	 * <strong>Main Menu item array list</strong> <br>
	 * You can get all menu item added on frame from this. 
	 */
	public static ArrayList<J9MenuItem> items = new ArrayList<J9MenuItem>();
	
	public static ArrayList<StyleTool> style=new ArrayList<StyleTool>();
	
	public static ArrayList<J9ToolBar> toolBar=new ArrayList<J9ToolBar>();

	/**
	 * <strong>Smart Frame for your GUI application.</string><br>
	 * <br>
	 * This is the same as <code>JFrame</code>. But this's helping us for more
	 * shorter code.
	 *
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
	static int width;

	static int height;
	
	public static Color frameBgColor=Color.BLACK;
	public static Color menuBgColor=Color.BLACK;
	public static Color toolBarBgColor=Color.GRAY;
	
	public static Color frameFgColor=Color.WHITE;
	public static Color menuFgColor=Color.WHITE;
//	public static Color toolBarFgColor=Color.WHITE;
	
	/**
	 *The smart frame. 
	 */
	ImageIcon logo=new ImageIcon(getClass().getClassLoader().getResource("appLogo.png"));
	public J9Frame() {
		// TODO Auto-generated constructor stub
//		setType(Type.UTILITY);
//		useDefault(def);
		AppLogo(logo);
		mainBar = new JMenuBar();
		mainBar.setBackground(SystemColor.textInactiveText);
		mainBar.setForeground(Color.WHITE);
		mainBar.setBorder(new EmptyBorder(0, 0, 0, 0));
		mainBar.setBorderPainted(false);
		mainBar.setMargin(new Insets(2, 2, 2, 2));
		this.setJMenuBar(mainBar);
		this.Display(Display.CENTRE);
		J9Frame.j9Frames.add(this);
	}

	/**
	 * The smart frame with it's name.
	 */
	public J9Frame(String frameName) {
		setTitle(frameName);
		mainBar = new JMenuBar();
		mainBar.setBorder(new EmptyBorder(0, 0, 0, 0));
		mainBar.setBorderPainted(false);
		mainBar.setBackground(SystemColor.textInactiveText);
		mainBar.setForeground(Color.WHITE);
		mainBar.setMargin(new Insets(2, 2, 2, 2));
		this.setJMenuBar(mainBar);
		this.Display(Display.CENTRE);
		J9Frame.j9Frames.add(this);
	}

	/**
	 * Apply the logo of the frame.
	 * @param logo - Image logo source.
	 */
	public void AppLogo(ImageIcon logo) {
		this.setIconImage(logo.getImage());
	}
public void Display(Display Display) {
		
	J9Frame.width=this.getWidth();
	J9Frame.height=this.getHeight();
		if(Display == com.J9FrameWork.J9Frame.Display.CENTRE) {
			Centre();
		}
		if(Display == com.J9FrameWork.J9Frame.Display.TOP) {
			Top();
		}
		if(Display == com.J9FrameWork.J9Frame.Display.LEFT) {
			Left();
		}
		if(Display == com.J9FrameWork.J9Frame.Display.BOTTOM) {
			Bottom();
		}
		if(Display == com.J9FrameWork.J9Frame.Display.RIGHT) {
			Right();
		}
		if(Display == com.J9FrameWork.J9Frame.Display.TOP_LEFT) {
			TopLeft();
		}
		if(Display == com.J9FrameWork.J9Frame.Display.TOP_RIGHT) {
			TopRight();
		}
		if(Display == com.J9FrameWork.J9Frame.Display.BOTTOM_LEFT) {
			BottomLeft();
		}
		if(Display == com.J9FrameWork.J9Frame.Display.BOTTOM_RIGHT) {
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
		int locationX=(screenWidth/2)-(J9Frame.width/2);
		int locationY=screenHeight-J9Frame.height;
		
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
		if(width==0) {
			width=100;
		}
		if(height==0) {
			height=40;
		}
		int locationX=(screenWidth/2)-(width/2);
		int locationY=(screenHeight/2)-(height/2);
		
		this.setLocation(locationX, locationY);
		
	}
	/**
	 * <p style="padding : 10px;">
	 * You can set the title of your frame, icon and type. You can skip the
	 * parameter by set it null and so the skipped parameter will return null.
	 * 
	 * <pre>
	 * <span style="color:orange;">Note : Using this method before setting any
	 * operation make more better. Because Some of the <strong>method</strong> of
	 * <code>J9Frame</code> make it displayable.</span>
	 * 
	 * <pre>
	 * </p>
	 * @param frameName - frame name.
	 * @param icon - frame logo.
	 * @param type - frame type.
	 */
	public void customFrame(String frameName, String icon, Type type) {
		if (type != null) {
			this.setType(type);
		}
		if (frameName != null) {
			this.setTitle(frameName);
		}
		if (icon != null) {
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(icon));
		}

	}

	/**
	 * You can use this for the simple default frame.
	 * 
	 * <pre>
	 * defWidth = 1000;
	 * defHeight = 500;
	 * defLocation=centre;
	 * </pre>
	 * 
	 * @return defaultFrame
	 * @param def : use or not (true/false)
	 */
	public void useDefault(boolean def) {
		if (def) {
			setDefault();
		} else {
			this.def = false;
		}
	}

	/**
	 *<strong>Generate full screen display</strong><br>
	 *Generate your frame to full screen. You can use this for your project UI or gaming frame.
	 *<pre style='color:orange;'>
	 *Note : This is the close method because you can't 
	 *<strong>resize</strong> your fram and can't set the <strong>Menu Bar</strong>.
	 *<pre>
	 */
	public void fullScreen() {
		this.setSize(screenSize);
		this.setUndecorated(true);
	}
	/**
	 *<center><strong>Welcome to J9FrameWork</strong></center>
	 *<p style='padding: 5px;'>
	 *Today is the IT age, the programming become the main part of the our business.
	 *Everybody known ! So they're trying harder and harder. What're we doing now?
	 *We must follow them and pass them and become the leader. So, we need to faster. 
	 *On the other hand, you're needing something's help for you to faster. We're trying 
	 *to help you to make you faster and faster. Let Using this frame work, it's very easy, simple, faster to generate your GUI application!
	 *</p>
	 *
	 *<a href="https://J9FrameWork/About">About</a><br>
	 *<a href="https://J9FrameWork/Contact">Contact</a>
	 */
	public void __() {
		String message="Welcome to J9FrameWork \r\n\n"+ 
						"Today is the IT age, the programming become the main part of the our business.\r\n" + 
						"Everybody known ! So they're trying harder and harder. What're we doing now?\r\n" + 
						"We must follow them and pass them and become the leader. So, we need to faster. \r\n" + 
						"On the other hand, you're needing something's help for you to faster. We're trying \r\n" + 
						"to help you to make you faster and faster. Let Using this frame work, \r\n"
						+"it's very easy, simple, faster to generate your GUI application!\r\n\n"
						+ "Get more information, \n\n";
		String text1="Tools : https://J9FrameWork/Tools \n";
		String text2="Source Code : https://J9FrameWork/Soure-Code \n";
		String text3="Community : https://J9FrameWork/Community \n";
		String text4="About : https://J9FrameWork/About \n";
		String text5="Contact : https://J9FrameWork/Contact \n";
		String text6="Help : https://J9FrameWork/Help \n";
		
		JLabel lb=new JLabel(text1);
		lb.setFont(new Font("Arial", Font.BOLD, 13));
		lb.setForeground(Color.BLUE);
		lb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		JLabel lb1=new JLabel(text2);
		lb1.setFont(new Font("Arial", Font.BOLD, 13));
		lb1.setForeground(Color.BLUE);
		lb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JLabel lb2=new JLabel(text3);
		lb2.setFont(new Font("Arial", Font.BOLD, 13));
		lb2.setForeground(Color.BLUE);
		lb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JLabel lb3=new JLabel(text4);
		lb3.setFont(new Font("Arial", Font.BOLD, 13));
		lb3.setForeground(Color.BLUE);
		lb3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JLabel lb4=new JLabel(text5);
		lb4.setFont(new Font("Arial", Font.BOLD, 13));
		lb4.setForeground(Color.BLUE);
		lb4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JLabel lb5=new JLabel(text6);
		lb5.setFont(new Font("Arial", Font.BOLD, 13));
		lb5.setForeground(Color.BLUE);
		lb5.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		Object[] obj= {
			message,lb,lb1,lb2,lb3,lb4,lb4,lb5
		};
		JOptionPane.showMessageDialog(null, obj);
		
	}
	
	/**
	 * Return the added menu item by its name on this frame.
	 * 
	 * @param item - the name of menu item.
	 * @return - return the J9MenuItem.
	 * @throws MenuException thorws when the menu item is null.
	 */
	public J9MenuItem getMenuItem(String item){
		J9MenuItem m=null;
		for(int i=0;i<J9Frame.items.size();i++) {
			if(J9Frame.items.get(i).getText().equalsIgnoreCase(item)) {
				m=J9Frame.items.get(i);
			}
		}
		if(m==null) {
			try {
				throwMenuException("No Item found !");
			} catch (MenuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}
	
	/**
	 * Return the added menu item by its index on this frame.
	 * @param index - the index of menu item on this frame.
	 * @return <code>J9MenuItem</code>
	 * @throws MenuException throws when null <code>J9MenuItem</code> occoured.
	 */
	public J9MenuItem getMenuItem(int index){
		J9MenuItem m=null;
		m=J9Frame.items.get(index);
		if(m==null) {
			try {
				throwMenuException("No Item found !");
			} catch (MenuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}
	
	/**
	 * Get the added menu on this frame.
	 * @param item - the name of menu.
	 * @return J9Menu
	 * @throws MenuException throws when null J9Menu occoured.
	 */
	public J9Menu getMenu(String item) {
		J9Menu m = null;
		for(int i=0;i<J9Frame.j9menu.size();i++) {
			if(J9Frame.j9menu.get(i).getText().equalsIgnoreCase(item)) {
				m=J9Frame.j9menu.get(i);
			}
		}
		if(m==null) {
			try {
				throwMenuException("No menu found !");
			} catch (MenuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}
	
	/**
	 * Return the added menu by its index on this frame.
	 * @param index - the index of the menu on the frame.
	 * @return J9Menu
	 * @throws MenuException throws when null J9Menu occoured.
	 */
	public J9Menu getMenu(int index){
		J9Menu m=null;
		m=J9Frame.j9menu.get(index);
		if(m==null) {
			try {
				throwMenuException("No Item found !");
			} catch (MenuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}
	
	
	/**
	 * The default frame display.
	 */
	public void setDefault() {
		int width = 1000, height = 600;

		this.setSize(width, height);
		J9Frame.width=width;
		J9Frame.height=height;
		this.Display(Display.TOP);
		mainBar = new JMenuBar();
		mainBar.setBackground(SystemColor.textInactiveText);
		mainBar.setForeground(Color.WHITE);
		mainBar.setBorder(new EmptyBorder(0, 0, 0, 0));
		mainBar.setBorderPainted(false);
		mainBar.setMargin(new Insets(2, 2, 2, 2));
		mainBar.setOpaque(true);
		this.setJMenuBar(mainBar);
		this.Display(Display.CENTRE);
		setVisible(true);
		container = getContentPane();
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	__();

	}


	/**
	 * @return The centre point for your frame.
	 */
	public Point CentrePoint() {
		Point p = new Point();
		int width = this.getWidth();
		int height = this.getHeight();
		int locationX = (screenSize.width / 2) - (width / 2);
		int locationY = (screenSize.height / 2) - (height / 2);

		p.setLocation(locationX, locationY);

		return p;
	}

	/**
	 * Smart add Menu as <strong>String array</strong>.<br>
	 * <br>
	 * You can add the main menu by adding them in the array string.<br>
	 * For example : Adding the menus named File<br>
	 * 
	 * <pre>
	 * <code>
	
	 *setJ9Menu("File");</code><br>
	 *<i>Another way</i>,
	 *<code>
	 *String menu="File";
	 *setJ9Menu(menu);
	 *</code>
	 * </pre>
	 * This will return the added menu on your <code>J9Frame</code>.<br>
	 * See more <a href='http://J9Frame/Menu'>https://J9Frame/Menu</a>
	 * @param menu : the value of main menu.
	 * @throws MenuException 
	 */
	public void setJ9Menu(String menu){
		mainBar.add(new J9Menu(menu));
	}
	
	public void setJ9Menu(String menuName,ImageIcon menu,int width,int height) {
		mainBar.add(new J9Menu(menuName,menu,width,height));
	}

	/**
	 * Smart add Menu item as <strong>String array</strong>.<br>
	 * <br>
	 * You can add the main menu by adding them in the array string.<br>
	 * For example : Adding the item of <strong>File</strong> menu named <i>New,
	 * Open, Save.</i>
	 * 
	 * <pre>
	 * <code>
	 *setJ9Menu("File",new String[]{"New","Open","Save" });</code><br>
	 *<i>Another way</i>,
	 *<code>
	 *String mainMenu="File";
	 *String[] menu={
	 *	"File","Edit","Setting"
	 *};
	 *setJ9Menu(mainMenu,menu);
	 *</code>
	 * </pre>
	 * 
	 * <span style="color:orange;">Note : In the item string you can type
	 * <span style="color: seagreen;"><<span>separator</span>></span> or
	 * <span style="color: seagreen;"><<span>s</span>></span> to generate item separator. 
	 * And you can type <span style='color:seagreen;'><<span>item</span>></span> to generate another menu in the item string.
	 * You can set the icon using the icon tag  <span style='color:seagreen;'><<span>i src(resources of the icon) size(width,height)</span>></span>
	 * .The icon tag is very strong , make correctly with its nature.
	 * <ul>
	 * <li style='color: darkgray;'><<span>separator</span>> - separator.</li>
	 * <li style='color: darkgray;'><<span>s</span>> - separator.</li>
	 * <li style='color: darkgray;'><text><<span>item</span>> - New Menu Item.</text></li>
	 *  <li style='color: darkgray;'><text><<span>i src(the resources of the icon) size(width,height)</span>> - The icon tag.</text></li>
	 * </ul>
	 * </span> This will return the added menu item on your <code>J9Menu</code>.<br>
	 * See more <a href='http://J9Frame/Menu'>https://J9Frame/Menu</a>
	 * @param mainMenu : the value of main menu
	 * @param item : the array of this main menu item.
	 * @throws MenuException throws when the menu is doublicate or the icon tag has errors.
	 */
	public void setJ9Menu(String mainMenu, String[] item) {
		mainBar.add(new J9Menu(mainMenu, item));
	}


	/**
	 * Adding the menu item to the main menu of your frame by the specify of the
	 * <strong>value</strong> of first parameter string. The item you added must be
	 * in the second parameter as <strong>String array</strong>.<br>
	 * <span style='color:orange;'>Note : If the first parameter value does not
	 * represent the name of the menu on your frame, <strong>there're noting change or update!</strong><span>
	 * @param mainMenu - main menu.
	 * @param item - the item for main menu.
	 * @throws MenuException 
	 * @throws throws when some of item has damage or missing with the main menu.
	 */
	public void addJ9MenuItem(String mainMenu, String[] item) {
		J9Menu newMenu=null;
		for (int i = 0; i < j9menu.size(); i++) {
			if (j9menu.get(i).getText().equalsIgnoreCase(mainMenu)) {
				newMenu=j9menu.get(i);
			}
		}
		if(newMenu!=null) {
			for (String a : item) {
				if(a.equalsIgnoreCase("<separator>") || a.equalsIgnoreCase("<s>")) {
					JSeparator sp=new JSeparator();
					newMenu.add(sp);
				}else {
					J9MenuItem menuItem = new J9MenuItem(a);
					newMenu.add(menuItem);
				}
				
			}
		}else {
			try {
				throwMenuException("No Menu found !");
			} catch (MenuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static void throwMenuException(String e) throws MenuException {
		J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).updateTextConsole(e+"\n",true);
		//throw new MenuException(e);
	}
	public static void throwCodeException(String e) throws CodeException {
		J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).updateTextConsole(e+"\n",true);
	//	throw new CodeException(e);
	}
	public static void throwStyleException(String e) throws StyleException {
		J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).updateTextConsole(e+"\n",true);
	//	throw new StyleException(e);
	}
	
	public static void updateFrameTitle(String title) {
		J9Frame.j9Frames.get(0).setTitle("Hyper Text - "+title);
	}
	
	/**
	 * Adding the child in the frame with its display position.
	 * @param c - child to be added
	 * @param d - the direction of the child in the frame.
	 */
	public void addChild(Component c,Display d) {
		if(d == Display.TOP || d== Display.TOP_LEFT || d == Display.TOP_RIGHT) {
			this.add(c,BorderLayout.NORTH);
		}
		if(d == Display.BOTTOM || d== Display.BOTTOM_LEFT || d == Display.BOTTOM_RIGHT) {
			this.add(c,BorderLayout.SOUTH);
		}
		if(d == Display.LEFT) {
			this.add(c,BorderLayout.WEST);
		}
		if(d == Display.RIGHT) {
			this.add(c,BorderLayout.EAST);
		}
		if(d == Display.CENTRE) {
			this.add(c,BorderLayout.CENTER);
		}
		
	}
	
	static void Quit() {
		
	}
}
