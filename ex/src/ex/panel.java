package ex;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class panel extends JPanel{
	double width=3;
	double r=width/2;
	double[] x=new double[10000];
	double[] y=new double[10000];
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	panel() {
		// TODO Auto-generated constructor stub
		 this.setFocusable(true);
		 this.setBackground(Color.black);
	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
		Polygon p=new Polygon();
		
		for(int i=0;i<360;i++) {
			x[i]=r*Math.cos(i);
			y[i]=r*Math.cos(i);
			p.addPoint((int) x[i], (int) y[i]);
		}
		g.fillPolygon(p);
		repaint();
		
	}
	
}
