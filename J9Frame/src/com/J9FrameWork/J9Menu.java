package com.J9FrameWork;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JSeparator;

import ErrorLogs.MenuException;

/**
 *Smart add Menu as <strong>String array</strong>.<br><br>
 *You can add the main menu by adding them in the array string.<br>
 *For example : Adding the menus named File,Edit,Setting.<br>
 *<pre><code>

 *J9Menu( new String[]{"File","Edit","Setting" });</code><br>
 *<i>Another way</i>,<br>
 *<code>
 *String[] menu={
 *	"File","Edit","Setting"
 *};
 *J9Menu(menu);
 *</code>
 *</pre>
 *This will return the added menu on your <code>J9Frame</code>.<br>
 * See more <a href='http://J9Frame/Menu'>https://J9Frame/Menu</a>
 */
public class J9Menu extends JMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	static String[] m;
	
	public J9Menu() {
		this(null);
		this.setOpaque(true);
		this.setBackground(new Color(0,0,30));
		this.setForeground(Color.WHITE);
		
	}
	
	/**
	 * The smart menu of the frame.
	 * @param menu - the name of the menu.
	 * @throws MenuException throws when the error occoured on the current menu.
	 */
	public J9Menu(String menu) {
		// TODO Auto-generated constructor stub
		
			try {
				sepcifyMenu(menu);
			} catch (MenuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				AnalysisMenu(menu);
			} catch (MenuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	this.setBorder(new LineBorder(new Color(0,0,30)));
			this.setOpaque(true);
			this.setMargin(new Insets(3, 3, 3, 3));
			this.setBackground(new Color(0,0,30));
			this.setForeground(Color.WHITE);
			this.setToolTipText(menu);
			J9Frame.j9menu.add(this);
	}
	public J9Menu(String menuName,ImageIcon menu, int width, int height){
		// TODO Auto-generated constructor stub
		
		try {
			sepcifyMenu(menuName);
		} catch (MenuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			AnalysisMenu(menuName);
		} catch (MenuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	this.setBorder(new LineBorder(new Color(0,0,30)));
		this.setOpaque(true);
		this.setMargin(new Insets(3, 3, 3, 3));
		this.setBackground(new Color(0,0,30));
		this.setForeground(Color.WHITE);
		this.setToolTipText(menuName);
		setMenuIcon(menu, width, height);
		J9Frame.j9menu.add(this);
	}
	/**
	 * @param menu
	 * @param menuItem
	 *Smart add Menu item as <strong>String array</strong>.<br><br>
	 *You can add the main menu by adding them in the array string.<br>
	 *For example : Adding the item of <strong>File</strong>  menu named <i>New, Open, Save.</i>
	 *<pre><code>
	 *addSmartMenu("File",new String[]{"New","Open","Save" });</code><br>
	 *<i>Another way</i>,
	 *<code>
	 *String mainMenu="File";
	 *String[] menu={
	 *	"File","Edit","Setting"
	 *};
	 *addSmartMenu(mainMenu,menu);
	 *</code>
	 *</pre>
	 *<pre style='color: orange;'>
	 *Note : The menu must not doublicate. If the doublicate menu 
	 *has occoured it will throw the menuException as error.
	 *</pre>
	 *This will return the added menu item on your <code>J9Menu</code>.<br>
	 * See more <a href='http://J9Frame/Menu'>https://J9Frame/Menu</a>
	 * @throws MenuException 
	 */
	public J9Menu(String menu,String[] menuItem){
		// TODO Auto-generated constructor stub
		this.setToolTipText(menu);
		try {
			sepcifyMenu(menu);
		} catch (MenuException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			AnalysisMenu(this.getText());
		} catch (MenuException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	//	this.setBorder(new LineBorder(new Color(0,0,30)));
		this.setMargin(new Insets(3, 3, 3, 3));
		this.setBackground(new Color(0,0,30));
		this.setForeground(Color.WHITE);
		J9Frame.j9menu.add(this);
			for(String i:menuItem) {
				if(i.equalsIgnoreCase("<separator>") || i.equalsIgnoreCase("<s>")) {
					JSeparator sp=new JSeparator();
					this.add(sp);
				}else {
					String itemSep="";
					if(i.length()>5) {
						itemSep=i.substring(0, 6);
					}
					if(itemSep.equalsIgnoreCase("<item>")) {
						String name=i.substring(6, i.length());
						J9Menu m = new J9Menu(name);
						m.setBackground(new Color(0,0,30));
						this.add(m);
					}else {
						J9MenuItem item = new J9MenuItem(i);
						this.add(item);
					}
				}
				
			}
	}
	
	/**
	 * Set the internal item.
	 * @param item - the array of the item.
	 * @throws MenuException throws when error occoured.
	 */
	public void addInternalItem(String[] item) {
		for (String a : item) {
			if(a.equalsIgnoreCase("<separator>") || a.equalsIgnoreCase("<s>")) {
				JSeparator sp=new JSeparator();
				this.add(sp);
			}else {
				J9MenuItem menuItem = new J9MenuItem(a);
				
				this.add(menuItem);
			}
			
		}
	}
	/**
	 * Analyse the doublicate menu.
	 * @param menu - the corrent menu.
	 * @throws MenuException throw the doublicate exception.
	 */
	private void AnalysisMenu(String menu) throws MenuException {
		// TODO Auto-generated method stub
		for(int i=0;i<J9Frame.j9menu.size();i++) {
			if(J9Frame.j9menu.get(i).getText().equalsIgnoreCase(menu)) {
				throw new MenuException("The Menu has dublicate!");
			}
		}
	}
	/**
	 * Sepecify the menu if the menu has the icon tag.
	 * <pre style='color: orange;'>
	 * Note : We use the icon tag 
	 * <span style='color:green;'><<span>i src(resources of the icon) size(width,height)</span>></span> or
	 * <span style='color:green;'><<span>i size(width,height) src(resources of the icon) </span>></span>.
	 * The tag <span style='color:green;'>i</span> must not missing, if the tag missing the menu
	 * will not set the icon correctly!
	 * </pre>
	 * @param menu - the string with the property of icon.
	 * @throws MenuException  throws the missing tag.
	 */
	private void sepcifyMenu(String menu) throws MenuException{
	String iconSpecifyText="";
		if(menu.charAt(0)=='<' && menu.charAt(1)=='i') {
			iconSpecifyText=menu.substring(menu.indexOf('<')+1, menu.lastIndexOf('>')+1);
		}else {
			this.setText(menu);
		}
		if(iconSpecifyText !="") {
			if(iconSpecifyText.charAt(0) !='i') {
				throw new MenuException("Missing tag! cannot set Icon!");
			}else {
				if (iconSpecifyText.charAt(1)!=' ') {
					throw new MenuException("Undefined tag! cannot set Icon!");
				}
				else
				{
					String src=iconSpecifyText.substring(iconSpecifyText.indexOf(' ')+1,iconSpecifyText.lastIndexOf('>'));
					String iconS="";
					int iconW=0;
					int iconH=0;
					if(src.charAt(0)=='s' && src.charAt(1)=='r' && src.charAt(2)=='c' && src.charAt(3)=='(') {
						String gsa=src.substring(src.indexOf('(')+1,src.length());
						
						String parseSrc=gsa.substring(0,gsa.indexOf(')'));
						iconS=parseSrc;
						
						gsa=gsa.replaceAll(" ", "");
						String srcWH=gsa.substring(gsa.indexOf(')')+1,gsa.lastIndexOf(')')+1);
						if(srcWH.charAt(0)=='s' && srcWH.charAt(1)=='i' && srcWH.charAt(2)=='z' && srcWH.charAt(3)=='e' && srcWH.charAt(4)=='(') {
							String gha=srcWH.substring(srcWH.indexOf('(')+1,srcWH.length());
							String parseW=gha.substring(0,gha.indexOf(')'));
							String sw=parseW.substring(0, parseW.indexOf(','));
							String sh=parseW.substring(parseW.indexOf(',')+1, parseW.length());
							
							iconW=Integer.parseInt(sw);
							iconH=Integer.parseInt(sh);
							
						}
						
						this.setMenuIcon(iconS, iconW, iconH);
						menu=menu.substring(menu.lastIndexOf('>')+1, menu.length());
						this.setText(menu);
						
					}
					if(src.charAt(0)=='s' && src.charAt(1)=='i' && src.charAt(2)=='z' && src.charAt(3)=='e' && src.charAt(4)=='(') {
						String gha=src.substring(src.indexOf('(')+1,src.length());
						String parseW=gha.substring(0,gha.indexOf(')'));
						String sw=parseW.substring(0, parseW.indexOf(','));
						String sh=parseW.substring(parseW.indexOf(',')+1, parseW.length());
						
						iconW=Integer.parseInt(sw);
						iconH=Integer.parseInt(sh);
						
						
						String srcWH=gha.substring(gha.indexOf(')')+1,gha.lastIndexOf(')')+1);
						srcWH=srcWH.substring(srcWH.indexOf('s'),srcWH.length());
						if(srcWH.charAt(0) == ' ') {
							srcWH=srcWH.replaceFirst(" ", "");
						}
						if(srcWH.charAt(0)=='s' && srcWH.charAt(1)=='r' && srcWH.charAt(2)=='c' && srcWH.charAt(3)=='(') {
							String gsa=srcWH.substring(srcWH.indexOf('(')+1,srcWH.length());
							
							String parseSrc=gsa.substring(0,gsa.indexOf(')'));
							iconS=parseSrc;
						}
						this.setMenuIcon(iconS, iconW, iconH);
						menu=menu.substring(menu.lastIndexOf('>')+1, menu.length());
						this.setText(menu);
					}
					
				}
			}
		}
		
		
		
		
		
	}
	
	static String Iconsource;
	static int iconWidth,iconHeight;
	
	
	/**
	 * Set the custom icon for this menu.
	 * 
	 * @param iconImage - the resources of the icon.
	 * @param width - the width of the icon.
	 * @param height - the height of the icon.
	 */
	public void setMenuIcon(String iconImage,int width,int height) {
		ImageIcon icon=new ImageIcon(iconImage);
		this.setIcon(new Icon() {
			
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				// TODO Auto-generated method stub
				Graphics2D g2= (Graphics2D) g;
				x=5;
				y=3;
				if(height<15) {
					int z=15-height;
					if(z<2) {
						y+=z;
					}else {
						y=z;
					}
				}
				g2.drawImage(icon.getImage(), x, y, width, height, null);
			}
			
			@Override
			public int getIconWidth() {
				// TODO Auto-generated method stub
				return width;
			}
			
			@Override
			public int getIconHeight() {
				// TODO Auto-generated method stub
				return height;
			}
		});
	}
	public void setMenuIcon(ImageIcon icon,int width,int height) {
		this.setIcon(new Icon() {
			
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				// TODO Auto-generated method stub
				Graphics2D g2= (Graphics2D) g;
				x=5;
				y=3;
				if(height<15) {
					int z=15-height;
					if(z<2) {
						y+=z;
					}else {
						y=z;
					}
				}
				g2.drawImage(icon.getImage(), x, y, width, height, null);
			}
			
			@Override
			public int getIconWidth() {
				// TODO Auto-generated method stub
				return width;
			}
			
			@Override
			public int getIconHeight() {
				// TODO Auto-generated method stub
				return height;
			}
		});
	}
	
}
