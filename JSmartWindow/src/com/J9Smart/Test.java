package com.J9Smart;


import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

import com.J9Smart.Background.BGProperty;
import com.J9Smart.JLoaderFrame.Direction;
import com.J9Smart.JLoaderFrame.Display;

public class Test {

	static ImageIcon ic=new ImageIcon("C:\\Users\\Acer\\Pictures\\Saved Pictures\\sexy_warrior_3-wallpaper-1366x768.jpg");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JLoaderFrame l=new JLoaderFrame(600, 500);
		l.Display(Display.CENTRE);
		l.defaultCMenu(ic, "JLoader Frame", "Eras ITC");
		l.setFrameBackground(Color.red,Color.orange, BGProperty.BACKGROUND_BOTTOM);
		l.drawText("Hello", Direction.CENTRE);
		l.drawText("Welcome to JLoaderFrame", Direction.TOP);
		l.setVisible(true);
	}

}
