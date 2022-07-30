package com.J9FrameWork;




import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;

import com.J9Frame.event.MenuEvent;

import ErrorLogs.MenuException;


public class J9MenuItem extends JMenuItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	String text,iconImage;

	boolean iconSet;
	
	/**
	 * The item for the menu on the frame.
	 * @param item - the name of this item.
	 * @throws MenuException throws if the items have error or null exception.
	 */
	public J9MenuItem(String item){
		// TODO Auto-generated constructor stub
		try {
			sepecifyItem(item);
		} catch (MenuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBackground(new Color(0,0,30));
		this.setForeground(Color.WHITE);
		this.setBorder(new EmptyBorder(2, 2, 2, 2));
		this.setBorderPainted(false);
		this.setToolTipText(item);
		J9Frame.items.add(this);
		
	}
	
	/**
	 * Sepecify the item when the item has set icon.
	 * <pre style='color: orange;'>
	 * Note : We use the icon tag 
	 * <span style='color:green;'><<span>i src(resources of the icon) size(width,height)</span>></span> or
	 * <span style='color:green;'><<span>i size(width,height) src(resources of the icon) </span>></span>.
	 * The tag <span style='color:green;'>i</span> must not missing, if the tag missing the item
	 * will not set the icon correctly!
	 * </pre>
	 * @param menu - the string with the property of icon.
	 * @throws MenuException  throws the missing tag.
	 */
	private void sepecifyItem(String menu) throws MenuException {
		String iconSpecifyText="";
		if(menu.charAt(0)=='<' && menu.charAt(1)=='i' ) {
			iconSpecifyText=menu.substring(menu.indexOf('<')+1, menu.lastIndexOf('>')+1);
		}else {
			this.setText(menu);
			this.text=menu;
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
						
						this.setIcon(iconS, iconW, iconH);
						menu=menu.substring(menu.lastIndexOf('>')+1, menu.length());
						this.setText(menu);
						this.text=menu;
						
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
						this.setIcon(iconS, iconW, iconH);
						menu=menu.substring(menu.lastIndexOf('>')+1, menu.length());
						this.setText(menu);
						this.text=menu;
					}
					
				}
			}
		}
		
		
		
		
		
	}
	
	/**
	 * Set the custom icon with its width and height.
	 * 
	 * @param img
	 * @param width
	 * @param height
	 */
	public void setIcon(String img,int width,int height) {
		ImageIcon icon=new ImageIcon(img);
		
		this.setIcon(new Icon() {
			
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				// TODO Auto-generated method stub
				
				Graphics2D g2= (Graphics2D) g;
				x=3;
				y=2;
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
	
	public void setIcon(ImageIcon icon,int width,int height) {
		
		this.setIcon(new Icon() {
			
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				// TODO Auto-generated method stub
				
				Graphics2D g2= (Graphics2D) g;
				x=3;
				y=2;
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
	
	


	/**
	 * Set the action of menu item.
	 * <pre style='color:orange;'>
	 * Note : If the method is use doublicate on the same menu item,
	 * the action will follow on the last updated!
	 * </pre>
	 * @param menuEvent - the event for this item.
	 */
	
	public void addMenuEvent(MenuEvent menuEvent) {
		// TODO Auto-generated method stub
		int x=0;
		
		for(int i=0;i<J9Frame.items.size();i++) {
			if(this.getText().equalsIgnoreCase(J9Frame.items.get(i).getText())) {
				x++;
			}
		}
		
		int[] countItem=new int[x];
		int y=0;
		for(int i=0;i<J9Frame.items.size();i++) {
			if(this.getText().equalsIgnoreCase(J9Frame.items.get(i).getText())) {
				countItem[y]=i;
				y++;
			}
		}
		for(int i=0;i<countItem.length;i++) {
			J9Frame.items.get(countItem[i]).addActionListener(menuEvent);
		}
		
		
	}
	
	
	
	
}
