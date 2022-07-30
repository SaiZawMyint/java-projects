package com.J93D.tools;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class Calculator {

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static enum Display{
		TOP,LEFT,BOTTOM,RIGHT,CENTER
	}
	
	public static Point centerScreen(Component c) {
		
		Point s = new Point();
		
		int width = c.getWidth();
		int height = c.getHeight();
		
		int locX = CenterViewX(screenSize.width, width);
		int locY = CenterViewY(screenSize.height, height);
		
		s.x = locX;
		s.y = locY;
		
		return s;
		
	}
	public static int CenterViewX(int Parentwidth,int childWidth) {
		int centerx=0;
		
		centerx=(Parentwidth/2)-(childWidth/2);
		return centerx;
	}
	
	public static int CenterViewY(int Parentheight,int childHeight) {
		int centery=0;
		
		centery=(Parentheight/2)-(childHeight/2);

		return centery;	
	}
}
