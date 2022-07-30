package com.J93D;

import java.awt.BorderLayout;

public class J93D {
	public static void main(String[] args) {
		
		J93DFrame frame = new J93DFrame();
		frame.setLayout(new BorderLayout());
		
		J93DScreen screen = new J93DScreen();
		
		frame.add(screen,BorderLayout.CENTER);
		frame.setVisible(true);
		
	}
}
