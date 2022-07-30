package com.J93D;
import java.awt.Color;

public class Cube {
	double x, y, z, width, length, height, rotation = Math.PI*0.75;
	double[] RotAdd = new double[4];
	Color c;
	double x1, x2, x3, x4, y1, y2, y3, y4;
	double xx1,xx2,xx3,xx4,yy1,yy2,yy3,yy4;
	J3DPolygon[] Polys = new J3DPolygon[6];
	double[] angle;
	
	public Cube(double x, double y, double z, double width, double length, double height, Color c)
	{
		Polys[0] = new J3DPolygon(new double[]{x, x+width, x+width, x}, new double[]{y, y, y+length, y+length},  new double[]{z, z, z, z}, c, false,true);
		J3DScreen.DPolygons.add(Polys[0]);
		Polys[1] = new J3DPolygon(new double[]{x, x+width, x+width, x}, new double[]{y, y, y+length, y+length},  new double[]{z+height, z+height, z+height, z+height}, c, false,true);
		J3DScreen.DPolygons.add(Polys[1]);
		Polys[2] = new J3DPolygon(new double[]{x, x, x+width, x+width}, new double[]{y, y, y, y},  new double[]{z, z+height, z+height, z}, c, false,true);
		J3DScreen.DPolygons.add(Polys[2]);
		Polys[3] = new J3DPolygon(new double[]{x+width, x+width, x+width, x+width}, new double[]{y, y, y+length, y+length},  new double[]{z, z+height, z+height, z}, c, false,true);
		J3DScreen.DPolygons.add(Polys[3]);
		Polys[4] = new J3DPolygon(new double[]{x, x, x+width, x+width}, new double[]{y+length, y+length, y+length, y+length},  new double[]{z, z+height, z+height, z}, c, false,true);
		J3DScreen.DPolygons.add(Polys[4]);
		Polys[5] = new J3DPolygon(new double[]{x, x, x, x}, new double[]{y, y, y+length, y+length},  new double[]{z, z+height, z+height, z}, c, false,true);
		J3DScreen.DPolygons.add(Polys[5]);
		
		this.c = c;
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.length = length;
		this.height = height;
		
		setRotAdd();
		updatePoly();
		
	}
	
	void setRotAdd()
	{
		angle = new double[4];
		
		double xdif = - width/2 + 0.00001;
		double ydif = - length/2 + 0.00001;
		
		angle[0] = Math.atan(ydif/xdif);
		
		if(xdif<0)
			angle[0] += Math.PI;
		
/////////
		xdif = width/2 + 0.00001;
		ydif = - length/2 + 0.00001;
		
		angle[1] = Math.atan(ydif/xdif);
		
		if(xdif<0)
			angle[1] += Math.PI;
/////////
		xdif = width/2 + 0.00001;
		ydif = length/2 + 0.00001;
		
		angle[2] = Math.atan(ydif/xdif);
		
		if(xdif<0)
			angle[2] += Math.PI;
		
/////////
		xdif = - width/2 + 0.00001;
		ydif = length/2 + 0.00001;
		
		angle[3] = Math.atan(ydif/xdif);
		
		if(xdif<0)
			angle[3] += Math.PI;	
		
		RotAdd[0] = angle[0] + 0.25 * Math.PI;
		RotAdd[1] =	angle[1] + 0.25 * Math.PI;
		RotAdd[2] = angle[2] + 0.25 * Math.PI;
		RotAdd[3] = angle[3] + 0.25 * Math.PI;

	}
	
	void UpdateDirection(double toX, double toY)
	{
		double xdif = toX - (x + width/2) + 0.00001;
		double ydif = toY - (y + length/2) + 0.00001;
		
		double anglet = Math.atan(ydif/xdif) + 0.75 * Math.PI;

		if(xdif<0)
			anglet += Math.PI;

		rotation = anglet;
		updatePoly();		
	}

	void updatePoly()
	{
		for(int i = 0; i < 6; i++)
		{
			J3DScreen.DPolygons.add(Polys[i]);
			J3DScreen.DPolygons.remove(Polys[i]);
		}
		
		double radius = Math.sqrt(width*width + length*length);
		
			   x1 = x+width*0.5+radius*0.5*Math.cos(rotation + RotAdd[0]);
			   x2 = x+width*0.5+radius*0.5*Math.cos(rotation + RotAdd[1]);
			   x3 = x+width*0.5+radius*0.5*Math.cos(rotation + RotAdd[2]);
			   x4 = x+width*0.5+radius*0.5*Math.cos(rotation + RotAdd[3]);
			   
			   y1 = y+length*0.5+radius*0.5*Math.sin(rotation + RotAdd[0]);
			   y2 = y+length*0.5+radius*0.5*Math.sin(rotation + RotAdd[1]);
			   y3 = y+length*0.5+radius*0.5*Math.sin(rotation + RotAdd[2]);
			   y4 = y+length*0.5+radius*0.5*Math.sin(rotation + RotAdd[3]);
   
		Polys[0].x = new double[]{x1, x2, x3, x4};
		Polys[0].y = new double[]{y1, y2, y3, y4};
		Polys[0].z = new double[]{z, z, z, z};

		Polys[1].x = new double[]{x4, x3, x2, x1};
		Polys[1].y = new double[]{y4, y3, y2, y1};
		Polys[1].z = new double[]{z+height, z+height, z+height, z+height};
			   
		Polys[2].x = new double[]{x1, x1, x2, x2};
		Polys[2].y = new double[]{y1, y1, y2, y2};
		Polys[2].z = new double[]{z, z+height, z+height, z};

		Polys[3].x = new double[]{x2, x2, x3, x3};
		Polys[3].y = new double[]{y2, y2, y3, y3};
		Polys[3].z = new double[]{z, z+height, z+height, z};

		Polys[4].x = new double[]{x3, x3, x4, x4};
		Polys[4].y = new double[]{y3, y3, y4, y4};
		Polys[4].z = new double[]{z, z+height, z+height, z};

		Polys[5].x = new double[]{x4, x4, x1, x1};
		Polys[5].y = new double[]{y4, y4, y1, y1};
		Polys[5].z = new double[]{z, z+height, z+height, z};
		
	}

	void removeCube()
	{
		for(int i = 0; i < 6; i ++)
			J3DScreen.DPolygons.remove(Polys[i]);
		J3DScreen.Cubes.remove(this);
	}
	void RunFont() {
		for(int i = 0; i < 6; i++)
		{
			J3DScreen.DPolygons.add(Polys[i]);
			J3DScreen.DPolygons.remove(Polys[i]);
		}
		xx1=this.x+=0.5;
		Polys[0].x=new double[] {xx1, xx1+width, xx1+width, xx1};
		Polys[1].x=new double[] {xx1, xx1+width, xx1+width, xx1};
		Polys[2].x=new double[] {xx1, xx1, xx1+width, xx1+width};
		Polys[3].x=new double[] {xx1+width, xx1+width, xx1+width, xx1+width};
		Polys[4].x=new double[] {xx1, xx1, xx1+width, xx1+width};
		Polys[5].x=new double[] {xx1, xx1, xx1, xx1};
		
		
	}
	void RunBack() {
		for(int i = 0; i < 6; i++)
		{
			J3DScreen.DPolygons.add(Polys[i]);
			J3DScreen.DPolygons.remove(Polys[i]);
		}
		xx1=this.x-=0.5;
		Polys[0].x=new double[] {xx1, xx1+width, xx1+width, xx1};
		Polys[1].x=new double[] {xx1, xx1+width, xx1+width, xx1};
		Polys[2].x=new double[] {xx1, xx1, xx1+width, xx1+width};
		Polys[3].x=new double[] {xx1+width, xx1+width, xx1+width, xx1+width};
		Polys[4].x=new double[] {xx1, xx1, xx1+width, xx1+width};
		Polys[5].x=new double[] {xx1, xx1, xx1, xx1};
		
	}
	void RunRight() {
		for(int i = 0; i < 6; i++)
		{
			J3DScreen.DPolygons.add(Polys[i]);
			J3DScreen.DPolygons.remove(Polys[i]);
		}
		yy1=this.y+=0.5;
		Polys[0].y=new double[] {yy1, yy1, yy1+length, yy1+length};
		Polys[1].y=new double[] {yy1, yy1, yy1+length, yy1+length};
		Polys[2].y=new double[] {yy1, yy1, yy1, yy1};
		Polys[3].y=new double[] {yy1, yy1, yy1+length, yy1+length};
		Polys[4].y=new double[] {yy1+length, yy1+length, yy1+length, yy1+length};
		Polys[5].y=new double[] {yy1, yy1, yy1+length, yy1+length};
		
	}
	void RunLeft() {
		for(int i = 0; i < 6; i++)
		{
			J3DScreen.DPolygons.add(Polys[i]);
			J3DScreen.DPolygons.remove(Polys[i]);
		}
		yy1=this.y-=0.5;
		Polys[0].y=new double[] {yy1, yy1, yy1+length, yy1+length};
		Polys[1].y=new double[] {yy1, yy1, yy1+length, yy1+length};
		Polys[2].y=new double[] {yy1, yy1, yy1, yy1};
		Polys[3].y=new double[] {yy1, yy1, yy1+length, yy1+length};
		Polys[4].y=new double[] {yy1+length, yy1+length, yy1+length, yy1+length};
		Polys[5].y=new double[] {yy1, yy1, yy1+length, yy1+length};
		
	}
}
