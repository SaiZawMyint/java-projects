package com.J9Tool;

import java.awt.Component;

import javax.swing.JSplitPane;

public class SplitPanel extends JSplitPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5009235796568025089L;
	
	
	
	public SplitPanel() {
		this(0, 0, null, null);
	}
	
	public SplitPanel(int divLocation,int divSize) {
		this(divLocation, divSize, null, null);
	}
	
	public SplitPanel(Component left,Component right) {
		this(0, 0, left, right);
		repaint();
		
	}
	
	public SplitPanel(int divLocation,int divSize,Component left,Component right) {
		// TODO Auto-generated constructor stub
		if(divLocation !=0)
			this.setDividerLocation(divLocation);
		this.setBorder(null);
		this.setDividerSize(divSize);
		this.setLeftComponent(left);
		this.setRightComponent(right);
	}
	
	

}
