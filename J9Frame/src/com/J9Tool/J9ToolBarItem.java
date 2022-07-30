package com.J9Tool;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.J9Frame.event.StyleHover;
import com.J9Tool.StyleTool.STYLE_ATTRIBUTE;

import ErrorLogs.CodeException;
import ErrorLogs.StyleException;

public class J9ToolBarItem extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6375283275364681745L;

	Color bgColor=Color.white,fgColor=Color.BLACK;
	
	public J9ToolBarItem(String name) {
		this(name,name, null, 0, 0);
	}
	
	public J9ToolBarItem(String id,String name)  {
		this(id,name,null,0,0);
	}
	
	public J9ToolBarItem(String id,String icon,int iconwidth,int iconheight) {
		this(id, null, icon, iconwidth, iconheight);
	}
	public J9ToolBarItem(String id,String name,String icon,int iconwidth,int iconheight) {
		// TODO Auto-generated constructor stub
		if(id!=null) {
			this.setName(analyseId(id));
		}
			
		if(name!=null) {
			try {
				analyseName(name);
			} catch (CodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		if(icon!=null)
			this.setToolIcon(icon, iconwidth, iconheight);
		this.setBorder(null);
		this.setBackground(bgColor);
		this.setForeground(fgColor);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);
		
		J9ToolBar.items.add(this);
	}
	
	public void Hover(String hoverCode){
		try {
			analyseCode(StyleTool.tempCode(hoverCode),false,true);
		} catch (StyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Style(String style) {
		try {
			analyseCode(StyleTool.tempCode(style),true,false);
		} catch (StyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void analyseCode(String styleCode,boolean style,boolean hover) throws StyleException {
		// TODO Auto-generated method stub
		
		int codeCount=0;
		int countS=0;
		for(int i=0;i<styleCode.length();i++) {
			if(styleCode.charAt(i) == ':') {
				if(styleCode.charAt(i-2) != '(')
					countS++;
			}
			if(styleCode.charAt(i)==';') {
				codeCount++;
			}
		
		}
		if(countS != codeCount) {
			if(countS > codeCount) {
				throw new StyleException("Missing ';' ! Please check the code.");
			}
			if(countS < codeCount) {
				throw new StyleException("Missing ':' ! Please check the code.");
			}
		}
		
		String[] code=new String[codeCount];
		String originalCode=styleCode;
		for(int i=0;i<codeCount;i++) {
			String eachCode=originalCode.substring(0,originalCode.indexOf(';'));
			code[i]=eachCode;
			originalCode=originalCode.substring(originalCode.indexOf(';')+1,originalCode.length());
		}
		
		for (String sCode : code) {
			String attribute=sCode.substring(0,sCode.indexOf(':')).toLowerCase().replaceAll(" ", "").replaceAll(" ", "");
			
			String value=sCode.substring(sCode.indexOf(':')+1,sCode.length());
			int checkPrototype=0;
			for(int i=0;i<value.length();i++) {
				if(value.charAt(i)=='(') {
					checkPrototype++;
				}
			}
			if(checkPrototype == 0) {
				value=value.replaceAll(" ", "");
			}
			int checkAtr=0;
			for(String atr:StyleTool.StyleAttribute) {
				if(attribute.equalsIgnoreCase(atr)) {
					checkAtr++;
				}
			}
			if(checkAtr == 0) {
				throw new StyleException("Undefined Attribute : "+attribute);
			}
			if(attribute.equalsIgnoreCase("padding")) {
				generateStyle(STYLE_ATTRIBUTE.PADDING,value,style,hover);
			}
			if(attribute.equalsIgnoreCase("align")) {
				generateStyle(STYLE_ATTRIBUTE.ALIGN,value,style,hover);
			}
			if(attribute.equalsIgnoreCase("background-color")) {
				generateStyle(STYLE_ATTRIBUTE.BACKGROUND_COLOR, value,style,hover);
			}
			if(attribute.equalsIgnoreCase("color")) {
				generateStyle(STYLE_ATTRIBUTE.COLOR, value,style,hover);
			}
			if(attribute.equalsIgnoreCase("width")) {
				generateStyle(STYLE_ATTRIBUTE.WIDTH, value,style,hover);
			}
			if(attribute.equalsIgnoreCase("height")) {
				generateStyle(STYLE_ATTRIBUTE.HEIGHT, value,style,hover);
			}
			if(attribute.equalsIgnoreCase("icon")) {
				generateStyle(STYLE_ATTRIBUTE.ICON, value,style,hover);
			}
			if(attribute.equalsIgnoreCase("border")) {
				generateStyle(STYLE_ATTRIBUTE.BORDER, value,style,hover);
			}
			if(attribute.equalsIgnoreCase("cursor")) {
				generateStyle(STYLE_ATTRIBUTE.CURSOR, value,style,hover);
			}
			if(attribute.equalsIgnoreCase("hover")) {
				generateStyle(STYLE_ATTRIBUTE.HOVER, value,style,hover);
			}
			if(attribute.equalsIgnoreCase("size")) {
				generateStyle(STYLE_ATTRIBUTE.SIZE, value,style,hover);
			}
			
		}
		
	}
	
	public static int defWidth=0,defheight=0;
	Dimension defSize=new Dimension();
	Rectangle defr = new Rectangle();
	Color borderColor=Color.BLACK;

	public void addHover(StyleHover s) {
		this.addMouseListener(s);
	}
	
	private void generateStyle(STYLE_ATTRIBUTE s,String value,boolean style,boolean hover) throws StyleException {
		
		if(s == STYLE_ATTRIBUTE.BACKGROUND_COLOR) {
			if(style) {
				this.bgColor=StyleTool.analyseColor(value);
				this.setBackground(StyleTool.analyseColor(value));
			}
			if(hover) {
				this.addHover(new StyleHover() {
					@Override
					public void mouseReleased(MouseEvent e) {}	
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {
						setBackground(bgColor);
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						setBackground(StyleTool.analyseColor(value));
					}
					@Override
					public void mouseClicked(MouseEvent e) {}
				});
			}
			
		}
		if(s == STYLE_ATTRIBUTE.ALIGN) {
			String v=value.replaceAll(" ", "");
			if(v.equalsIgnoreCase("center")) {
				this.setHorizontalAlignment(SwingConstants.CENTER);
			}
			if(v.equalsIgnoreCase("left")) {
				this.setHorizontalAlignment(SwingConstants.LEFT);
			}
			if(v.equalsIgnoreCase("right")) {
				this.setHorizontalAlignment(SwingConstants.RIGHT);
			}
		}
		if(s == STYLE_ATTRIBUTE.COLOR) {
			if(style) {
				this.fgColor=StyleTool.analyseColor(value);
				this.setForeground(StyleTool.analyseColor(value));
			}
			if(hover) {
				this.addHover(new StyleHover() {
					@Override
					public void mouseReleased(MouseEvent e) {}	
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {
						setForeground(fgColor);
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						setForeground(StyleTool.analyseColor(value));
					}
					@Override
					public void mouseClicked(MouseEvent e) {}
				});
			}
		}
		if(s == STYLE_ATTRIBUTE.SIZE) {
			if(style) {
				Dimension dem=StyleTool.getSize(value);
				this.defSize=dem;
				this.setPreferredSize(dem);
			}
			if(hover) {
				Dimension dh=StyleTool.getSize(value);
				this.addHover(new StyleHover() {
					@Override
					public void mouseReleased(MouseEvent e) {}	
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {
						setPreferredSize(defSize);
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						setPreferredSize(dh);
					}
					@Override
					public void mouseClicked(MouseEvent e) {}
				});
			}
			
		}
		if(s == STYLE_ATTRIBUTE.ICON) {
			String src=StyleTool.getIconSource(value);
			Dimension iconSize=new Dimension();
			iconSize=StyleTool.getIconSize(value);
			this.setToolIcon(src, iconSize.width, iconSize.height);
		}
		if(s == STYLE_ATTRIBUTE.BORDER) {
			if(style) {
				Rectangle r=StyleTool.getBorderThickness(value);
				Color c=StyleTool.getBorderColor(value);
				this.defr=r;
				this.borderColor=c;
				this.setBorder(new MatteBorder(r.x, r.y, r.width, r.height, c));
			}
			if(hover) {
				Rectangle r=StyleTool.getBorderThickness(value);
				Color c=StyleTool.getBorderColor(value);
				this.addHover(new StyleHover() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
					}
					@Override
					public void mousePressed(MouseEvent e) {
					}
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						setBorder(new MatteBorder(defr.x, defr.y, defr.width, defr.height, borderColor));
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						setBorder(new MatteBorder(r.x, r.y, r.width, r.height, c));
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		}
		if(s== STYLE_ATTRIBUTE.CURSOR) {
			Cursor c=StyleTool.getCursor(value);
			this.setCursor(c);
		}
		
	}

	private void analyseName(String name) throws CodeException {
		// TODO Auto-generated method stub
		String codeTypeText="";
		if(name.charAt(0)=='<' && name.charAt(1)=='i') {
			codeTypeText=name.substring(name.indexOf('<'),name.indexOf('>')+1);
		}else {
			this.setText(name);
		}
		if(codeTypeText != "") {
			if(codeTypeText.charAt(0) !='i') {
				throw new CodeException("Missing tag! cannot set Icon!");
			}else {
				if (codeTypeText.charAt(1)!=' ') {
					throw new CodeException("Undefined tag! cannot set Icon!");
				}
				else
				{
					String src=codeTypeText.substring(codeTypeText.indexOf(' ')+1,codeTypeText.lastIndexOf('>'));
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
						
						this.setToolIcon(iconS, iconW, iconH);
						name=name.substring(name.lastIndexOf('>')+1, name.length());
						this.setText(name);
						
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
						this.setToolIcon(iconS, iconW, iconH);
						name=name.substring(name.lastIndexOf('>')+1, name.length());
						this.setText(name);
					}
					
				}
			}
		}
	}

	private String analyseId(String id) {
		// TODO Auto-generated method stub
		String analysed="";
		if(id.charAt(0) == '<' && id.charAt(1)=='i') {
			analysed=id.substring(id.indexOf('>')+1);
		}else {
			analysed=id;
		}
		return analysed;
	}

	
	public void setToolIcon(String icon,int iconwidth,int iconheight) {
		ImageIcon imgIcon=new ImageIcon(icon);
		this.setIcon(new Icon() {
			
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				// TODO Auto-generated method stub
				Graphics2D g2= (Graphics2D) g;
				x=3;
				y=3;
				if(iconheight<15) {
					int z=15-iconheight;
					if(z<2) {
						y+=z;
					}else {
						y=z;
					}
				}
				
					y=Calculator.CenterViewY(defSize.height, iconheight);
				
				g2.drawImage(imgIcon.getImage(), x, y, iconheight, iconheight, null);
			}
			
			@Override
			public int getIconWidth() {
				// TODO Auto-generated method stub
				return iconwidth;
			}
			
			@Override
			public int getIconHeight() {
				// TODO Auto-generated method stub
				return iconheight;
			}
		});
	}
	public void setToolIcon(ImageIcon imgIcon,int iconwidth,int iconheight) {
		this.setIcon(new Icon() {
			
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				// TODO Auto-generated method stub
				Graphics2D g2= (Graphics2D) g;
				x=5;
				y=3;
				if(iconheight<15) {
					int z=15-iconheight;
					if(z<2) {
						y+=z;
					}else {
						y=z;
					}
				}
					y=Calculator.CenterViewY(defSize.height, iconheight);
				
				g2.drawImage(imgIcon.getImage(), x, y, iconheight, iconheight, null);
			}
			
			@Override
			public int getIconWidth() {
				// TODO Auto-generated method stub
				return iconwidth;
			}
			
			@Override
			public int getIconHeight() {
				// TODO Auto-generated method stub
				return iconheight;
			}
		});
	}

	
}
