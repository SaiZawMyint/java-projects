package com.J9Tool;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.J9FrameWork.J9Frame;
import com.SSC.SSDecoder;

import ErrorLogs.StyleException;

public class StyleTool{

	 
	int comWidth,comHeight,comX,comY;
	 public static enum LayoutType{
		 FLOAT,
		 FLEX,
		 NONE
	 }
	
	 public static enum FloatProperty{
		 LEFT,
		 RIGHT,
	 }
	 
	 public static enum FlexProperty{
		 ROW,
		 COLOUM,
		 ROW_RESERVE,
		 COLORM_RESERVE
	 }
	 
	 public static int VARTICAL_padding = 1;
	 public static int HORIZONTAL_padding = 1;
	
	 public static String[] StyleAttribute={
		"/**/",
		 "float",
		 "flex",
		 "margin",
		 "align",
		 "width",
		 "height",
		 "padding",
		 "background-color",
		 "color",
		 "icon",
		 "border",
		 "cursor",
		 "hover",
		 "size",
		 "layout"
		 
	 };
	 public static String[] cursorValue= {
			 "pointer",
			 "progress",
			 "cross",
			 "default",
			 "e_resize",
			 "move",
			 "n_resize",
			 "ne_resize",
			 "nw_resize",
			 "s_resize",
			 "se_resize",
			 "sw_resize",
			 "text",
			 "w_resize",
			
	 };
	 public static enum STYLE_ATTRIBUTE{
		 FLOAT,
		 FLEX,
		 WIDTH,
		 HEIGHT,
		 PADDING,
		 BACKGROUND_COLOR,
		 ALIGN,
		 ICON,
		 BORDER,
		 CURSOR,
		 HOVER,
		 COLOR,
		 SIZE,
		 MARGIN,
		 LAYOUT
	 };
	 
	 public static enum STYLE_TEXT_POSITION{
		 TOP,
		 LEFT,
		 BOTTOM,
		 RIGHT,
		 CENTRE
	 }
	 
	 public static String[] floatValue= {
		"left",
		"right"
	 };
	
	public static String[] layoutValue= {
			"flow","border","grid","box",""
	};
	J9Frame frame;
	JPanel panel;
	JButton button;
	JLabel label;
	 
	public static ArrayList<String> layoutProperties = new ArrayList<String>();
	
	public static String currentField;
	
	public static void StyleCode(String code) {
		
			analyseCode(tempCode(GetField(code)));
		
	}
	 
	 

	private static String GetField(String code) {
		// TODO Auto-generated method stub
		String fieldId="";
		String coder="";
		if(code.contains("{")) {
			fieldId=code.substring(0, code.indexOf("{"));
			coder=code.substring(code.indexOf("{")+1, code.indexOf("}"));
		}
		if(fieldId!="") {
			currentField=fieldId;
		}
		return coder;
	}

	public static String currentType;
	
	public static String currentId() {
		String id="";
		if(currentField !=null) {
			id=currentField.substring(currentField.indexOf("(")+1, currentField.lastIndexOf(")"));
		}
		return id;
	}
	
	public static Component getCurrentComponent() {
		Component c=null;
		String getCom=currentField.substring(0, currentField.indexOf("("));
		String id=currentField.substring(currentField.indexOf("(")+1,currentField.indexOf(")"));
		int idi=SSDecoder.getIDI(id);
		if(getCom.equalsIgnoreCase("frame")) {
			if(SSDecoder.preview) {
				c = ((JInternalFrame)SSDecoder.compnonets.get(idi));
			}else {
				c = ((J9Frame)SSDecoder.compnonets.get(idi));
			}
			
		}
		if(getCom.equalsIgnoreCase("panel")) {
			c = ((JPanel)SSDecoder.compnonets.get(idi));
		}
		if(getCom.equalsIgnoreCase("text")) {
			c = ((JTextField)SSDecoder.compnonets.get(idi));
		}
		return c;
	}
	public static String getType() {
		String getCom=currentField.substring(0, currentField.indexOf("("));
		return getCom;
	}
	
	public static String[] StyleEncode;
	
	public static String tempCode(String styleCode){
		
		String temp="";
		int codeCount=0;
		int countS=0;
		String marString="",floatString="",widthString="",heightString="",flexString="",alignString="",bgc="",iconString="",borderString=""
				,cursorString="",hoverString="",colorString="",sizeString="",marginString="",layoutString=""
				;
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
				try {
					J9Frame.throwStyleException("Missing ';' ! Please check the code.");
				} catch (StyleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(countS < codeCount) {
				try {
					J9Frame.throwStyleException("Missing ':' ! Please check the code.");
				} catch (StyleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String[] code=new String[codeCount];
		String originalCode=styleCode.replaceAll(" ", "");
		for(int i=0;i<codeCount;i++) {
			String eachCode=originalCode.substring(0,originalCode.indexOf(';'));
			code[i]=eachCode;
			originalCode=originalCode.substring(originalCode.indexOf(';')+1,originalCode.length());
		}
		for (String sCode : code) {
			String attribute=sCode.substring(0,sCode.indexOf(':')).toLowerCase();
			attribute=attribute.trim();
			String value=sCode.substring(sCode.indexOf(':')+1,sCode.length());
			int checkAtr=0;
			for(String atr:StyleAttribute) {
				if(attribute.equalsIgnoreCase(atr)) {
					checkAtr++;
					
				}
			}
			if(checkAtr == 0) {
				try {
					J9Frame.throwStyleException("Undefined Attribute : "+attribute);
				} catch (StyleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(attribute.equalsIgnoreCase("align")) {
				alignString=attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("padding")) {
				marString=attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("float")) {
				floatString=attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("flex")) {
				flexString = attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("width")) {
				widthString = attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("height")) {
				heightString = attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("background-color")) {
				bgc = attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("icon")) {
				iconString = attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("border")) {
				borderString = attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("cursor")) {
				cursorString = attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("hover")) {
				hoverString = attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("color")) {
				colorString = attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("size")) {
				sizeString = attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("margin")) {
				marginString = attribute+" : "+value+";";
			}
			if(attribute.equalsIgnoreCase("layout")) {
				layoutString = attribute+" : "+value+";";
			}
			
		}
		temp=sizeString+bgc+colorString+alignString+widthString+heightString+marString+floatString+flexString+iconString+borderString+cursorString+
				hoverString+marginString+layoutString;
		return temp;
	}
	
	
	static void analyseCode(String styleCode) {
		
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
				try {
					J9Frame.throwStyleException("Missing ';' ! Please check the code.");
				} catch (StyleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(countS < codeCount) {
				try {
					J9Frame.throwStyleException("Missing ':' ! Please check the code.");
				} catch (StyleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String[] code=new String[codeCount];
		String originalCode=styleCode.replaceAll(" ", "");
		for(int i=0;i<codeCount;i++) {
			String eachCode=originalCode.substring(0,originalCode.indexOf(';'));
			code[i]=eachCode;
			originalCode=originalCode.substring(originalCode.indexOf(';')+1,originalCode.length());
		}
		for (String sCode : code) {
			String attribute=sCode.substring(0,sCode.indexOf(':')).toLowerCase();	
			attribute=attribute.trim();
			String value=sCode.substring(sCode.indexOf(':')+1,sCode.length());
			int checkAtr=0;
			for(String atr:StyleAttribute) {
				if(attribute.equalsIgnoreCase(atr)) {
					checkAtr++;
					
				}
			}
			if(checkAtr == 0) {
				try {
					J9Frame.throwStyleException("Undefined Attribute : "+attribute);
				} catch (StyleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(attribute.equalsIgnoreCase("align")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.ALIGN,value);
			}
			if(attribute.equalsIgnoreCase("padding")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.PADDING,value);
			}
			if(attribute.equalsIgnoreCase("float")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.FLOAT,value);
			}
			if(attribute.equalsIgnoreCase("flex")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.FLEX,value);
			}
			if(attribute.equalsIgnoreCase("width")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.WIDTH,value);
			}
			if(attribute.equalsIgnoreCase("height")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.HEIGHT,value);
			}
			if(attribute.equalsIgnoreCase("background-color")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.BACKGROUND_COLOR,value);
			}
			if(attribute.equalsIgnoreCase("icon")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.ICON,value);
			}
			if(attribute.equalsIgnoreCase("border")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.BORDER,value);
			}
			if(attribute.equalsIgnoreCase("cursor")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.CURSOR,value);
			}
			
			if(attribute.equalsIgnoreCase("color")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.COLOR,value);
			}
			if(attribute.equalsIgnoreCase("size")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.SIZE,value);
			}
			if(attribute.equalsIgnoreCase("layout")) {
				generateStyle(StyleTool.STYLE_ATTRIBUTE.LAYOUT,value);
			}
			
		}
		
	}
	
	private static void generateStyle(STYLE_ATTRIBUTE attr, String value) {
		// TODO Auto-generated method stub
		
		if(attr == STYLE_ATTRIBUTE.ALIGN) {
			
		}
		if(attr == STYLE_ATTRIBUTE.BACKGROUND_COLOR) {
			Color color = StyleTool.analyseColor(value);
			if(StyleTool.getType().equalsIgnoreCase("frame")) {
				if(SSDecoder.preview) {
					((JInternalFrame) StyleTool.getCurrentComponent()).getContentPane().setBackground(color);
				}else {
					((J9Frame) StyleTool.getCurrentComponent()).getContentPane().setBackground(color);
				}
				
			}else {
				StyleTool.getCurrentComponent().setBackground(color);
			}
			
		}
		if(attr == STYLE_ATTRIBUTE.BORDER) {
			Rectangle r=StyleTool.getBorderThickness(value);
			Color c=StyleTool.getBorderColor(value);
			
			if(StyleTool.getType().equalsIgnoreCase("Frame")) {
				
			}
			if(StyleTool.getType().equalsIgnoreCase("panel"))
			{
				
				((JPanel) StyleTool.getCurrentComponent()).setBorder(new MatteBorder(r.x, r.y, r.width, r.height, c));
			}
			if(StyleTool.getType().equalsIgnoreCase("text"))
			{
				
				((JTextField) StyleTool.getCurrentComponent()).setBorder(new MatteBorder(r.x, r.y, r.width, r.height, c));
			}
			
		}
		if(attr == STYLE_ATTRIBUTE.COLOR) {
			Color c = StyleTool.analyseColor(value);
			StyleTool.getCurrentComponent().setForeground(c);
			if(StyleTool.getType().equalsIgnoreCase("text")) {
				((JTextField) StyleTool.getCurrentComponent()).setCaretColor(c);
			}
		}
		if(attr == STYLE_ATTRIBUTE.CURSOR) {
			Cursor c=StyleTool.getCursor(value);
			StyleTool.getCurrentComponent().setCursor(c);
		}
		if(attr == STYLE_ATTRIBUTE.FLEX) {
			
		}
		if(attr == STYLE_ATTRIBUTE.FLOAT) {
					
		}
		
		if(attr == STYLE_ATTRIBUTE.ICON) {
			String icon=StyleTool.getIconSource(value);
			ImageIcon icons=new ImageIcon(icon);
			Dimension iconSize=StyleTool.getIconSize(value);
			if(StyleTool.getType().equalsIgnoreCase("frame")) {
				if(SSDecoder.preview) {
					((JInternalFrame) StyleTool.getCurrentComponent()).setFrameIcon(StyleTool.drawSmallIcon(icons, iconSize.width, iconSize.height));
				}else {
					((J9Frame) StyleTool.getCurrentComponent()).AppLogo(icons);
				}
				
			}else {
				if(StyleTool.getType().equalsIgnoreCase("panel")) {
					JLabel b = new JLabel();
					b.setIcon(StyleTool.drawSmallIcon(icons,0,0, iconSize.width, iconSize.height));
					b.setVisible(true);
					((JPanel)StyleTool.getCurrentComponent()).add(b);
				}
			}
			
		}
		if(attr == STYLE_ATTRIBUTE.PADDING) {
			Dimension s=StyleTool.getPadding(value);
			int hgap = s.width;
			int vgap = s.height;
			if(StyleTool.getType().equalsIgnoreCase("frame")) {
				if(SSDecoder.preview) {
					((JInternalFrame) StyleTool.getCurrentComponent()).setLayout(new FlowLayout(FlowLayout.LEADING, hgap, vgap));
				}else {
					((J9Frame) StyleTool.getCurrentComponent()).setLayout(new FlowLayout(FlowLayout.LEADING, hgap, vgap));
				}
				
			}else {
					((JComponent) StyleTool.getCurrentComponent()).setLayout(new FlowLayout(FlowLayout.LEADING, hgap, vgap));
			}
		}
		if(attr == STYLE_ATTRIBUTE.SIZE) {
			Dimension s=StyleTool.getSize(value);
			if(StyleTool.getType().equalsIgnoreCase("frame")) {
				StyleTool.getCurrentComponent().setSize(s);
			}else {
				StyleTool.getCurrentComponent().setPreferredSize(s);
				StyleTool.getCurrentComponent().setSize(s);
			}
			
		}
		
		if(attr == STYLE_ATTRIBUTE.LAYOUT) {
			doStyleLayout(value);
		}
		
	}


	private static void doStyleLayout(String value) {
		// TODO Auto-generated method stub
		value = value.trim();
		String getml = "";
		String getlv = "";
		if(value.contains("(")) {
			getml = value.substring(0, value.indexOf('('));
			getlv = value.substring(value.indexOf('(')+1,value.lastIndexOf(')'));
		}else {
			getml = value;
			getlv = "";
		}
		
		checkLayout(getml,getlv);
		
				
	}



	private static void checkLayout(String getml,String getlv) {
		// TODO Auto-generated method stub
		int record = 0;
		for (String check : layoutValue) {
			if(getml.equalsIgnoreCase(check)) {
				record++;
			}
		}
		if(record == 0) {
			try {
				J9Frame.throwStyleException("Undefined layout! : "+getml);
			} catch (StyleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			generateLayout(getml,getlv);
		}
		

	}



	private static void generateLayout(String main, String value) {
		// TODO Auto-generated method stub
		if(value == "") {
			if(main.equalsIgnoreCase("flow")) {
				if(StyleTool.getType().equalsIgnoreCase("frame")) {
					if(SSDecoder.preview) {
						((JInternalFrame) StyleTool.getCurrentComponent()).setLayout(new FlowLayout());
						String id = StyleTool.currentId();
						StyleTool.layoutProperties.add(id+"#Flow");
					}else {
						((J9Frame) StyleTool.getCurrentComponent()).setLayout(new FlowLayout());
						String id = StyleTool.currentId();
						StyleTool.layoutProperties.add(id+"#Flow");
					}
				}else {
					((JComponent) StyleTool.getCurrentComponent()).setLayout(new FlowLayout());
					String id = StyleTool.currentId();
					StyleTool.layoutProperties.add(id+"#Flow");
				}
				
			}
			if(main.equalsIgnoreCase("border")) {
				if(StyleTool.getType().equalsIgnoreCase("frame")) {
					if(SSDecoder.preview) {
						((JInternalFrame) StyleTool.getCurrentComponent()).setLayout(new BorderLayout());
						String id = StyleTool.currentId();
						id.trim();
						StyleTool.layoutProperties.add(id+"#Border");
					}else {
						((J9Frame) StyleTool.getCurrentComponent()).setLayout(new BorderLayout());
						String id = StyleTool.currentId();
						id.trim();
						StyleTool.layoutProperties.add(id+"#Border");
					}
				}else {
					((JComponent) StyleTool.getCurrentComponent()).setLayout(new BorderLayout());
					String id = StyleTool.currentId();
					id.trim();
					StyleTool.layoutProperties.add(id+"#Border");
				}
			}
		}
	}



	public static Dimension getPadding(String padding) {
		Dimension c=new Dimension();
		padding=padding.trim();
		
		padding=padding.substring(padding.indexOf("(")+1, padding.lastIndexOf(")"));
		int count=0;
		for(int i=0;i<padding.length();i++) {
			if(padding.charAt(i)==',') {
				count++;
			}
		}
		
		if(count >= 2) {
			try {
				J9Frame.throwStyleException("Undefined padding values !\nDefault value of padding are (top&left&bottom&right) or"
						+ " (top&bottom , left&right).");
			} catch (StyleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(count == 0) {
			padding=padding.replaceAll(" ", "");
			int x = 0,y=0;
			if(padding.contains("%")) {
				padding=padding.trim();
				String a=padding.substring(0, padding.length()-1);
				int s=Integer.parseInt(a);
				x=Calculator.percentageWidth(StyleTool.getCurrentComponent(), s);
				y=Calculator.percentageHeight(StyleTool.getCurrentComponent(), s);
			}else {
				x = Integer.parseInt(padding);
				y = x;
			}
			c.width=x;
			c.height=y;
		} 
		if(count == 1){
			padding=padding.replaceAll(" ", "");
			int x = Integer.parseInt(padding.substring(0, padding.indexOf(",")));
			
			int y = Integer.parseInt(padding.substring(padding.indexOf(",")+1, padding.length()));
			c.width=x;
			c.height=y;
		}
		return c;
	}
	
	public static String[] colors= {
			"white","black","red","green","blue","pink","gray","cyan","yellow"
	};

	
	
	static Color analyseColor(String color) {
		color=color.trim();
		Color c=null;
		
		if(color.equalsIgnoreCase("red")) {
			c=Color.RED;
		}
		
		if(color.equalsIgnoreCase("black")) {
			c=Color.BLACK;
		}
		
		if(color.equalsIgnoreCase("blue")) {
			c=Color.BLUE;
		}
		if(color.equalsIgnoreCase("cyan")) {
			c=Color.CYAN;
		}
		if(color.equalsIgnoreCase("dark_gray")) {
			c=Color.DARK_GRAY;
		}
		if(color.equalsIgnoreCase("gray")) {
			c=Color.GRAY;
		}
		if(color.equalsIgnoreCase("green")) {
			c=Color.GREEN;
		}
		if(color.equalsIgnoreCase("light_gray")) {
			c=Color.LIGHT_GRAY;
		}
		if(color.equalsIgnoreCase("magenta")) {
			c=Color.MAGENTA;
		}
		if(color.equalsIgnoreCase("orange")) {
			c=Color.ORANGE;
		}
		if(color.equalsIgnoreCase("pink")) {
			c=Color.PINK;
		}
		if(color.equalsIgnoreCase("white")) {
			c=Color.WHITE;
		}
		if(color.equalsIgnoreCase("yellow")) {
			c=Color.YELLOW;
		}
		if(color.equalsIgnoreCase("transparent")) {
			c=null;
		}
		
		return c;
		
	}
	
	static String getIconSource(String iconCode) {
		String iconsrc="";
		
		iconCode=iconCode.trim();
		
		if(iconCode.charAt(0)=='s' && iconCode.charAt(1)=='r' && iconCode.charAt(2)=='c' && iconCode.charAt(3)=='(') {
			String gsa=iconCode.substring(iconCode.indexOf('(')+1,iconCode.length());
			
			String parseSrc=gsa.substring(0,gsa.indexOf(')'));
			iconsrc=parseSrc;
			
		}
		
		return iconsrc;
	}
	
	static Dimension getIconSize(String iconCode) {
		Dimension iconSize=new Dimension();
		iconCode=iconCode.trim();
		String srcWH=iconCode.substring(iconCode.indexOf(')')+1,iconCode.lastIndexOf(')')+1);
		srcWH=srcWH.trim();
		if(srcWH.charAt(0)=='s' && srcWH.charAt(1)=='i' && srcWH.charAt(2)=='z' && srcWH.charAt(3)=='e' && srcWH.charAt(4)=='(') {
			String gha=srcWH.substring(srcWH.indexOf('(')+1,srcWH.length());
			String parseW=gha.substring(0,gha.indexOf(')'));
			String sw=parseW.substring(0, parseW.indexOf(','));
			String sh=parseW.substring(parseW.indexOf(',')+1, parseW.length());
			
			int iconW=Integer.parseInt(sw);
			int iconH=Integer.parseInt(sh);
			
			iconSize.width=iconW;
			iconSize.height=iconH;
			
		}else {
			try {
				J9Frame.throwStyleException("Undifined attribute : "+srcWH);
			} catch (StyleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return iconSize;
	}
	
	static Dimension getSize(String sizeCode){
		String id = StyleTool.currentId();
		Dimension size=new Dimension();
		sizeCode=sizeCode.trim();
		sizeCode=sizeCode.substring(sizeCode.indexOf('(')+1, sizeCode.lastIndexOf(')'));
		sizeCode.replaceAll(" ", "");
		int count=0;
		for(int i=0;i<sizeCode.length();i++) {
			if(sizeCode.charAt(i)==',') {
				count++;
			}
		}
		
		if(count == 0) {
			try {
				J9Frame.throwStyleException("Undefined size value! Default size value is ((int)width,(int)height) !");
			} catch (StyleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String width=sizeCode.substring(0,sizeCode.indexOf(","));
		String height=sizeCode.substring(sizeCode.indexOf(",")+1, sizeCode.length());
		int w = 0;
		int h = 0 ;
		
		if(!width.contains("%")) {
			w=Integer.parseInt(width);
		}else {
			width=width.trim();
			String a=width.substring(0, width.length()-1);
			int s=Integer.parseInt(a);
			w=Calculator.percentageWidth(SSDecoder.getParent(id), s);
			
		}
		
		if(!height.contains("%")) {
			h = Integer.parseInt(height);
		}else {
			height=height.trim();
			String a=height.substring(0, height.length()-1);
			int s=Integer.parseInt(a);
			h=Calculator.percentageWidth(SSDecoder.getParent(id), s);
		}
		
		size.width=w;
		size.height=h;
		return size;
	}
	
	static int getAlign(String alignCode) {
		int x=0;
		alignCode=alignCode.trim();
		alignCode=alignCode.replaceAll(" ", "");
		
		if(alignCode.equalsIgnoreCase("center")) {
			x=SwingConstants.CENTER;
		}
		if(alignCode.equalsIgnoreCase("left")) {
			x=SwingConstants.LEFT;
		}
		if(alignCode.equalsIgnoreCase("right")) {
			x=SwingConstants.RIGHT;
		}
		return x;
	}
	
	static Rectangle getBorderThickness(String borderCode){
		Rectangle t=new Rectangle();
		borderCode=borderCode.trim();
		borderCode=borderCode.substring(borderCode.indexOf('(')+1, borderCode.indexOf(')'));
		borderCode=borderCode.replaceAll(" ", "");
		int countse=0;
		for(int i=0;i<borderCode.length();i++) {
			if(borderCode.charAt(i)==',') {
				countse++;
			}
		}
		
		if(countse!=3) {
			try {
				J9Frame.throwStyleException("Undefined border thickness value! Default border value is ((int)top,(int)left,(int)bottom,(int)right) !");
			} catch (StyleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		borderCode=borderCode+",";
		int[] get=new int[4];
		for(int i=0;i<4;i++) {
			get[i]=Integer.parseInt(borderCode.substring(0, borderCode.indexOf(',')));
		borderCode=	borderCode.substring(borderCode.indexOf(',')+1, borderCode.length());
			
		}
		
		t.x=get[0];
		t.y=get[1];
		t.width=get[2];
		t.height=get[3];
		
		return t;
	}
	
	static Color getBorderColor(String borderCode) {
		Color c=null;
		borderCode=borderCode.trim();
		borderCode=borderCode.substring(borderCode.indexOf('s'), borderCode.lastIndexOf(')')+1);
		borderCode=borderCode.substring(borderCode.indexOf('(')+1, borderCode.lastIndexOf(')'));
		
		c=StyleTool.analyseColor(borderCode);
		
		
		return c;
	}
	
	public static void checkCursor(String cur) {
		cur=cur.trim();
		int check=0;
		for(String c:cursorValue) {
			if(c.equalsIgnoreCase(cur)) {
				check++;
			}
		}
		if(check == 0) {
			try {
				J9Frame.throwStyleException("Unknown cursor : "+cur+"!\nThe cursor will return the default cursor.");
			} catch (StyleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static Cursor getCursor(String cursorCode) {
		Cursor c=null;
		
		
		cursorCode=cursorCode.replaceAll(" ", "");
		
		checkCursor(cursorCode);
		
		if(cursorCode.equalsIgnoreCase("pointer")) {
			c=new Cursor(Cursor.HAND_CURSOR);
		}
		
		if(cursorCode.equalsIgnoreCase("progress")) {
			c=new Cursor(Cursor.WAIT_CURSOR);
		}
		
		if(cursorCode.equalsIgnoreCase("default")) {
			c=new Cursor(Cursor.DEFAULT_CURSOR);
		}
		
		if(cursorCode.equalsIgnoreCase("move")) {
			c=new Cursor(Cursor.MOVE_CURSOR);
		}
		
		if(cursorCode.equalsIgnoreCase("cross")) {
			c=new Cursor(Cursor.CROSSHAIR_CURSOR);
		}
		
		if(cursorCode.equalsIgnoreCase("text")) {
			c=new Cursor(Cursor.TEXT_CURSOR);
		}
		if(cursorCode.equalsIgnoreCase("e_resize")) {
			c=new Cursor(Cursor.E_RESIZE_CURSOR);
		}
		if(cursorCode.equalsIgnoreCase("n_resize")) {
			c=new Cursor(Cursor.N_RESIZE_CURSOR);
		}
		if(cursorCode.equalsIgnoreCase("ne_resize")) {
			c=new Cursor(Cursor.NE_RESIZE_CURSOR);
		}
		if(cursorCode.equalsIgnoreCase("nw_resize")) {
			c=new Cursor(Cursor.NW_RESIZE_CURSOR);
		}
		if(cursorCode.equalsIgnoreCase("s_resize")) {
			c=new Cursor(Cursor.S_RESIZE_CURSOR);
		}
		if(cursorCode.equalsIgnoreCase("se_resize")) {
			c=new Cursor(Cursor.SE_RESIZE_CURSOR);
		}
		if(cursorCode.equalsIgnoreCase("sw_resize")) {
			c=new Cursor(Cursor.SW_RESIZE_CURSOR);
		}
		if(cursorCode.equalsIgnoreCase("w_resize")) {
			c=new Cursor(Cursor.W_RESIZE_CURSOR);
		}
		
		return c;
	}
	
	static interface Hover extends MouseListener{
		
	}
	public static Icon drawSmallIcon(ImageIcon icon,int width,int height) {
		Icon c=new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				// TODO Auto-generated method stub
				Graphics2D g2=(Graphics2D) g;
				y=Calculator.CenterViewY(23, height);
				g2.drawImage(icon.getImage(), 3, y, width, height, null);
				
			}
			
			@Override
			public int getIconWidth() {return width;}
			@Override
			public int getIconHeight() {return height;}
		};
		return c;
	}
	public static Icon drawSmallIcon(ImageIcon icon,int xl,int yl,int width,int height) {
		Icon c=new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				// TODO Auto-generated method stub
				Graphics2D g2=(Graphics2D) g;
				
				g2.drawImage(icon.getImage(), xl, yl, width, height, null);
				
			}
			
			@Override
			public int getIconWidth() {return width;}
			@Override
			public int getIconHeight() {return height;}
		};
		return c;
	}
	static void Hover(Component c,Color oldbg,Color oldfg,Color bgColor,Color fgColor) {
		c.addMouseListener(new Hover() {	
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				c.setBackground(oldbg);
				c.setForeground(oldfg);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				c.setBackground(bgColor);
				c.setForeground(fgColor);
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}
	
	public void generateValue(String value,STYLE_ATTRIBUTE f) throws StyleException {
		value=value.replaceAll(" ", "");
		if(f==STYLE_ATTRIBUTE.PADDING) {
			int length=value.length();
			boolean containSign=false;
			int x=0;
			for(int i=0;i<length;i++) {
				if(value.charAt(i) == '%') {
					containSign=true;
					x++;
				}
			}
			
			if(x==1) {
				if(containSign) {
					if(value.charAt(length-1) != '%') {
						throw new StyleException("Undefined value : "+value);
					}
					value=value.substring(0, value.indexOf('%'));
					int padding=Integer.parseInt(value);
					padding(Calculator.percentageWidth(J9Frame.j9Frames.get(0), padding));
					
				}else {
					int padding=Integer.parseInt(value);
					padding(padding);
				}
				
			}
			
			
			if(x==2) {
				String hgap=value.substring(0, value.indexOf('%')+1);
				String vgap=value.substring(value.indexOf('%')+1,value.length());
				
				if(containSign) {
				
					if(hgap.charAt(hgap.length()-1) != '%') {
						throw new StyleException("Undefined value : "+hgap);
					}
					
					if(vgap.charAt(vgap.length()-1) != '%') {
						throw new StyleException("Undefined value : "+vgap);
					}
					
					hgap=hgap.substring(0, hgap.indexOf('%'));
					vgap=vgap.substring(0, vgap.indexOf('%'));
					
					int hpadding=Integer.parseInt(hgap);
					int vpadding=Integer.parseInt(vgap);
					
					padding(Calculator.percentageHeight(J9Frame.j9Frames.get(0), vpadding),Calculator.percentageWidth(J9Frame.j9Frames.get(0), hpadding));
					
					
				}else {
					int hmar=Integer.parseInt(hgap);
					int vmar=Integer.parseInt(vgap);
					padding(hmar, vmar);
				}
			}
			
				
			
			
		}
		
		if(f == STYLE_ATTRIBUTE.FLOAT) {
			int countval=0;
			for(String val:floatValue) {
				if(value.equalsIgnoreCase(val)) {
					countval++;
				}
			}
			if(countval == 0) {
				throw new StyleException("Undefined value : "+value);
			}
			if(value.equalsIgnoreCase("left")) {
				FloatDisplay(FloatProperty.LEFT);
			}
		}
		
		
		
	}

	public void FloatDisplay(FloatProperty property) throws StyleException {
		
	}
	
	public void CentreDisplay() {
		
	}
	
	public void FlexDisplay(FlexProperty property) {
		if(property == FlexProperty.ROW) {
			
		}
		if(property == FlexProperty.COLOUM) {
			
		}
		if(property == FlexProperty.ROW_RESERVE) {
			
		}
		if(property == FlexProperty.COLORM_RESERVE) {
			
		}
	}
	
	public void padding(int padding) {
		StyleTool.VARTICAL_padding=padding;
		StyleTool.HORIZONTAL_padding=padding;	
	}
	
	public void padding(int hpadding,int vpadding) {
		StyleTool.VARTICAL_padding=vpadding;
		StyleTool.HORIZONTAL_padding=hpadding;	
	}
	
	////////////
	//String formatting.
	
	public static String checkSpecialCharacters(String originalString) {
		String result="";
		int countSpec=0;
		if(originalString.contains("\\")) {
			for(int i=0;i<originalString.length();i++) {
				if(originalString.charAt(i) == '\\') {
					countSpec++;
				}
			}
				
			String[] str = new String[countSpec];
			
			for(int i=0;i<str.length;i++) {
				str[i]=originalString.substring(0,originalString.indexOf("\\"));
				originalString = originalString.substring(originalString.indexOf("\\")+1, originalString.length());
				
			}
			for(int i=0;i<str.length;i++) {
				result +=str[i];
			}
			result +=originalString;
		}
		if(result==null || result=="")
			result=originalString;
		
		return result;
	}
	
}
