package com.J9FrameWork;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends J9Frame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 665015658078117491L;
	
	public Test() {
		// TODO Auto-generated constructor stub
		useDefault(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Test t=new Test();
		t.setVisible(true);
		
		
		JPanel p = new JPanel();
		p.setBackground(Color.black);
		
		JPanel c=new JPanel();
		c.setBackground(Color.green);
		
	}
	
}
