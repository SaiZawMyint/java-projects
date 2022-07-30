package com.J9Tool;

import java.awt.Component;
import java.util.Random;

public class Calculator {

	public static int percentageWidth(Component parent,int percent) {
		double calculateR=0;
		if(percent < 100) {
			if(percent<10) {
				percent=percent*100;
				int c=percent/100;
				calculateR=c*0.01;
			}else {
				percent=percent*10;
				int c=percent/100;
				calculateR=c*0.1;
			}
		}else {
		calculateR=percent/100;
		}
		int result=(int) (calculateR*(parent.getWidth()));
		
		return result;
	}
	
	public static int percentageHeight(Component parent,int percent) {
		double calculateR=0;
		if(percent < 100) {
			if(percent<10) {
				percent=percent*100;
				int c=percent/100;
				calculateR=c*0.01;
			}else {
				percent=percent*10;
				int c=percent/100;
				calculateR=c*0.1;
			}
		}else {
		calculateR=percent/100;
		}
		int result=(int) (calculateR*(parent.getHeight()));
		return result;
	}
	
	public static int CenterViewX(int Parentwidth,int childWidth) {
		int centerx=0;
		
		centerx=(Parentwidth/2)-(childWidth/2);
		return centerx;
	}
	
	public static int CenterViewY(int Parentheight,int childHeight) {
		int centery=0;
		
		centery=(Parentheight/2)-(childHeight/2);

		return centery;	
	}
	
	public static int NGCenterXText(int main) {
		int result=0;
		if(main<10) {
			result=2;
		}
		if(main>=10 && main<100) {
			result=6;
		}
		if(main>=100) {
			result=10;
		}
		return result;
	}
	
	public static int random() {
		Random ran = new Random();
		int c = ran.nextInt(250);
		return c;
	}
}
