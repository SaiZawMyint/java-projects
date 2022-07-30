package com.J93D;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import com.J93D.tools.Calculator;
import com.J93D.tools.Calculator.Display;

public class J93DFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4385145215457795019L;
	
	
	public J93DFrame() {
		this(null, null, null, null);
	}
	
	public J93DFrame(String name) {
		this(name, null, null, null);
	}
	
	public J93DFrame(String name,Dimension size) {
		this(name, size, null, null);
	}
	
	public J93DFrame(String name,Display d) {
		this(name, null, d, null);
	}
	
	public J93DFrame(String name,Dimension size,Display d,Type t) {
		// TODO Auto-generated constructor stub
		if(name != null)
			this.setTitle(name);
		else
			this.setTitle("J93D (v.1.0)");
		if(size != null)
			this.setSize(size);
		else
			this.setSize(1000, 550);
		
		displayFrame(d);
			
		if(t != null)
			this.setType(t);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void displayFrame(Display d) {
		// TODO Auto-generated method stub
		Point s = Calculator.centerScreen(this);
		this.setLocation(s);
	}

}
