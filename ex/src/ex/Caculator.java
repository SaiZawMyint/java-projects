package ex;

import java.util.Scanner;

public class Caculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double r=Math.sqrt(1-(-0.9*-0.9));
		double a=-8+r*Math.cos(0);
		double b=11+r*Math.sin(0);
		
		System.out.println(a+" , "+b);
	}

}
