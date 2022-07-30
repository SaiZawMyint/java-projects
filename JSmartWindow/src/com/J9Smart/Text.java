package com.J9Smart;

import java.awt.Font;

import com.J9Smart.JLoaderFrame.Direction;

public class Text {

	int x,y;
	String text;
	Font font;
	Direction d;
	
	public Text(String text,Direction d) {
		this.text=text;
		this.d=d;
	}
	
	public void setText(String text) {
		this.text=text;
	}
	public void setDirection(Direction d) {
		this.d=d;
	}
	
	public String getText() {
		return this.text;
	}
	public Direction getDirection() {
		return this.d;
	}
	
	public Text(String text,Font font,Direction d) {
		this.text=text;
		this.font=font;
		this.d=d;
	}
	public Text(String text,int locationX,int locationY) {
		this.text=text;
		this.x=locationX;
		this.y=locationY;
	}
	public Text(String text,int locationX,int locationY,Font font) {
		this.text=text;
		this.font=font;
		this.x=locationX;
		this.y=locationY;
	}
	public void setTextLocation(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public int getTextLocX() {
		return this.x;
	}
	public int getTextLocY() {
		return this.y;
	}
	public Font getTextFont() {
		return this.font;
	}
}
