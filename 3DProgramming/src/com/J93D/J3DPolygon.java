package com.J93D;
import java.awt.Color;

public class J3DPolygon {
	Color c;
	double[] x, y, z;
	boolean draw = true, seeThrough = false, outline=true;
	double[] CalcPos, newX, newY;
	J3DObject DrawablePolygon;
	double AvgDist;
	
	
	public J3DPolygon(double[] x, double[] y,  double[] z, Color c, boolean seeThrough,boolean outline)
	{
		this.x = x;
		this.y = y;
		this.z = z;		
		this.c = c;
		this.seeThrough = seeThrough; 
		this.outline=outline;
		createPolygon();
	}
	
	void createPolygon()
	{
		DrawablePolygon = new J3DObject(new double[x.length], new double[x.length], c, J3DScreen.DPolygons.size(), seeThrough,outline);
	}
	
	void updatePolygon()
	{		
		newX = new double[x.length];
		newY = new double[x.length];
		draw = true;
		for(int i=0; i<x.length; i++)
		{
			CalcPos = Calculator.CalculatePositionP(J3DScreen.ViewFrom, J3DScreen.ViewTo, x[i], y[i], z[i]);
			newX[i] = (J3DFrame.ScreenSize.getWidth()/2 - Calculator.CalcFocusPos[0]) + CalcPos[0] * J3DScreen.zoom;
			newY[i] = (J3DFrame.ScreenSize.getHeight()/2 - Calculator.CalcFocusPos[1]) + CalcPos[1] * J3DScreen.zoom;			
			if(Calculator.t < 0)
				draw = false;
		}
		
		calcLighting();
		
		DrawablePolygon.draw = draw;
		DrawablePolygon.updatePolygon(newX, newY);
		AvgDist = GetDist();
	}
	
	void calcLighting()
	{
		Plane lightingPlane = new Plane(this);
		double angle = Math.acos(((lightingPlane.NV.x * J3DScreen.LightDir[0]) + 
			  (lightingPlane.NV.y * J3DScreen.LightDir[1]) + (lightingPlane.NV.z * J3DScreen.LightDir[2]))
			  /(Math.sqrt(J3DScreen.LightDir[0] * J3DScreen.LightDir[0] + J3DScreen.LightDir[1] * J3DScreen.LightDir[1] + J3DScreen.LightDir[2] * J3DScreen.LightDir[2])));
		
		DrawablePolygon.lighting = 0.2 + 1 - Math.sqrt(Math.toDegrees(angle)/180);

		if(DrawablePolygon.lighting > 1)
			DrawablePolygon.lighting = 1;
		if(DrawablePolygon.lighting < 0)
			DrawablePolygon.lighting = 0;
	}
		
	double GetDist()
	{
		double total = 0;
		for(int i=0; i<x.length; i++)
			total += GetDistanceToP(i);
		return total / x.length;
	}
	
	double GetDistanceToP(int i)
	{
		return Math.sqrt((J3DScreen.ViewFrom[0]-x[i])*(J3DScreen.ViewFrom[0]-x[i]) + 
						 (J3DScreen.ViewFrom[1]-y[i])*(J3DScreen.ViewFrom[1]-y[i]) +
						 (J3DScreen.ViewFrom[2]-z[i])*(J3DScreen.ViewFrom[2]-z[i]));
	}
}
