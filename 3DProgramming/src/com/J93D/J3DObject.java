package com.J93D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class J3DObject {
	Polygon P;
	Color c;
	boolean draw = true, visible = true, seeThrough,outline;
	double lighting = 5;
	
	public J3DObject(double[] x, double[] y, Color c, int n, boolean seeThrough,boolean outline)
	{
		P = new Polygon();
		for(int i = 0; i<x.length; i++)
			P.addPoint((int)x[i], (int)y[i]);
		this.c = c;
		this.seeThrough = seeThrough;
		this.outline=outline;
	}
	
	void updatePolygon(double[] x, double[] y)
	{
		P.reset();
		for(int i = 0; i<x.length; i++)
		{
			P.xpoints[i] = (int) x[i];
			P.ypoints[i] = (int) y[i];
			P.npoints = x.length;
		}
	}
	
	void drawPolygon(Graphics g)
	{
		if(draw && visible)
		{
			g.setColor(new Color((int)(c.getRed() * lighting), (int)(c.getGreen() * lighting), (int)(c.getBlue() * lighting)));
			if(seeThrough)
				g.drawPolygon(P);
			else
				g.fillPolygon(P);
			if(J3DScreen.OutLines)
			{
				g.setColor(new Color(0, 0, 0));
				g.drawPolygon(P);
			}

			if(J3DScreen.PolygonOver == this)
			{
				g.setColor(new Color(255, 255, 255, 100));
				g.fillPolygon(P);
			}
		}
	}
	
	boolean MouseOver()
	{
		return P.contains(J3DFrame.ScreenSize.getWidth()/2, J3DFrame.ScreenSize.getHeight()/2);
	}
}
