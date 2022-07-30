package com.J93D;

import java.awt.Color;

public class SurroundPoly {

	/**
	 * Cube[] cube=new Cube[100]; int width=30; double height=2; double z=2;
	 */
	
	

	double[] xx = new double[360];
	double[] yy = new double[360];
	double[] zz = new double[360];
	double[] hh = new double[360];
	double x,y,z,width,height;

	Color c;

	public SurroundPoly(double x,double y,double z,double width,double height,Color c) {
		// TODO Auto-generated constructor stub
		/**
		 * Screen.DPolygons.add(new DPolygon(new double[]
		 * {1+width,1.2+width,2+width,2.7+width,3+width,2.7+width,2+width,1.2+width},
		 * new double[]
		 * {2+width,1.2+width,1+width,1.2+width,2+width,2.7+width,3+width,2.7+width},
		 * new double[] {z,z,z,z,z,z,z,z}, Color.red, false)); //1,2 & 1.2,1.2
		 * Screen.DPolygons.add(new DPolygon(new double[]
		 * {1+width,1.2+width,1.2+width,1+width}, new double[]
		 * {2+width,1.2+width,1.2+width,2+width}, new double[] {z+height,z+height,z,z},
		 * Color.gray, false)); //1.2,1.2 & 2,1 Screen.DPolygons.add(new DPolygon(new
		 * double[] {1.2+width,2+width,2+width,1.2+width}, new double[]
		 * {1.2+width,1+width,1+width,1.2+width}, new double[] {z+height,z+height,z,z},
		 * Color.gray, false)); //2,1 & 2.7,1.2 Screen.DPolygons.add(new DPolygon(new
		 * double[] {2+width,2.7+width,2.7+width,2+width}, new double[]
		 * {1+width,1.2+width,1.2+width,1+width}, new double[] {z+height,z+height,z,z},
		 * Color.gray, false)); //2.7,1.2 & 3,2 Screen.DPolygons.add(new DPolygon(new
		 * double[] {2.7+width,3+width,3+width,2.7+width}, new double[]
		 * {1.2+width,2+width,2+width,1.2+width}, new double[] {z+height,z+height,z,z},
		 * Color.gray, false)); //3,2 & 2.7,2.7 Screen.DPolygons.add(new DPolygon(new
		 * double[] {3+width,2.7+width,2.7+width,3+width}, new double[]
		 * {2+width,2.7+width,2.7+width,2+width}, new double[] {z+height,z+height,z,z},
		 * Color.gray, false)); //2.7,2.7 & 2,3 Screen.DPolygons.add(new DPolygon(new
		 * double[] {2.7+width,2+width,2+width,2.7+width}, new double[]
		 * {2.7+width,3+width,3+width,2.7+width}, new double[] {z+height,z+height,z,z},
		 * Color.gray, false)); //2,3 & 1.2,2.7 Screen.DPolygons.add(new DPolygon(new
		 * double[] {2+width,1.2+width,1.2+width,2+width}, new double[]
		 * {3+width,2.7+width,2.7+width,3+width}, new double[] {z+height,z+height,z,z},
		 * Color.gray, false)); //1.2,2.7 & 1,2 Screen.DPolygons.add(new DPolygon(new
		 * double[] {1.2+width,1+width,1+width,1.2+width}, new double[]
		 * {2.7+width,2+width,2+width,2.7+width}, new double[] {z+height,z+height,z,z},
		 * Color.gray, false));
		 * 
		 * Screen.DPolygons.add(new DPolygon(new double[]
		 * {1+width,1.2+width,2+width,2.7+width,3+width,2.7+width,2+width,1.2+width},
		 * new double[]
		 * {2+width,1.2+width,1+width,1.2+width,2+width,2.7+width,3+width,2.7+width},
		 * new double[]
		 * {z+height,z+height,z+height,z+height,z+height,z+height,z+height,z+height},
		 * Color.red, false));
		 */
		
		this.x=x;
		this.y=y;
		this.z=z;
		this.width=width;
		this.height=height;
		this.c=c;
		double r = width / 2;

		
		for (int i = 0; i < 360; i++) {
			yy[i] = r * Math.sin(i) + y;
			xx[i] = r * Math.cos(i) + x;
			zz[i] = z;
		}
		for (int i = 0; i < zz.length; i++) {

			hh[i] =height;
		}

		J3DScreen.DPolygons.add(new J3DPolygon(xx, yy, zz, c, false,true));
		for (double i = 0; i < 360; i++) {
			double j = i + 1;
			if (j > 359) {
				j = 0;
			}
			J3DScreen.DPolygons.add(new J3DPolygon(new double[] { xx[(int) i], xx[(int) j], xx[(int) j], xx[(int) i] },
					new double[] { yy[(int) (i)], yy[(int) (j)], yy[(int) (j)], yy[(int) (i)] },
					new double[] { height, height, zz[(int) i], zz[(int) i] }, c, false,false));

		}
		J3DScreen.DPolygons.add(new J3DPolygon(xx, yy, hh, c, false,true));
		
	}

}
