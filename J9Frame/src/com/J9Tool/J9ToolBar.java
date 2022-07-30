package com.J9Tool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.J9FrameWork.J9Frame;

import ErrorLogs.CodeException;

public class J9ToolBar extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3466422298765638200L;
	
	static ArrayList<J9ToolBarItem> items=new ArrayList<J9ToolBarItem>();
	
	
	public static enum TOOL_DISPLAY{
		LEFT_TO_RIGHT,
		RIGHT_TO_LEFT,
		CENTER,
		
	}
	int hgap=1,vgap=1;
	public J9ToolBar(String name){
		this(name, null);
	}
	public J9ToolBar(String name,TOOL_DISPLAY d){
		if(name!=null)
			this.setName(name);
		if(d!=null)
			toolDisplay(d);
		//this.setBorder(new LineBorder(Color.BLACK, 1));
		this.setBackground(new Color(105, 105, 105));
		J9Frame.toolBar.add(this);
	}
	
	public void toolPadding(int h,int v) {
		this.hgap=h;
		this.vgap=v;
	}
	
	public void toolBarSize(int width,int height) {
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public void toolDisplay(TOOL_DISPLAY d) {
		if(d==TOOL_DISPLAY.LEFT_TO_RIGHT) {
			this.setLayout(new FlowLayout(FlowLayout.LEFT,this.hgap,this.vgap));
		}
		if(d==TOOL_DISPLAY.RIGHT_TO_LEFT) {
			this.setLayout(new FlowLayout(FlowLayout.RIGHT,this.hgap,this.vgap));
		}
		if(d==TOOL_DISPLAY.CENTER) {
			this.setLayout(new FlowLayout(FlowLayout.CENTER,this.hgap,this.vgap));
		}
	}
	
	public J9ToolBarItem getTool(String tool) {
		J9ToolBarItem tbi=null;
		
		for(int i=0;i<J9ToolBar.items.size();i++) {
			if(J9ToolBar.items.get(i).getName().equalsIgnoreCase(tool)) {
				tbi=J9ToolBar.items.get(i);
			}
		}
		
		if(tbi==null) {
			try {
				J9Frame.throwCodeException("No tool found!");
			} catch (CodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return tbi;
	}
	
	public J9ToolBarItem getTool(int index){
		J9ToolBarItem tbi=null;
		
		if(index < J9ToolBar.items.size()) {
			tbi=J9ToolBar.items.get(index);
		}
		
		if(tbi==null) {
			try {
				J9Frame.throwCodeException("No tool found!");
			} catch (CodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return tbi;
	}
	
	
	
	public void addTool(String[] tools) {
		for (String tool : tools) {
			J9ToolBarItem item=new J9ToolBarItem(tool);
			this.add(item);
		}
	}
	public void toolStyle(String styleCode) {
		for(int i=0;i<J9ToolBar.items.size();i++) {
			J9ToolBar.items.get(i).Style(styleCode);
		}
	}
	
	public void toolHover(String hover){
		for(int i=0;i<J9ToolBar.items.size();i++) {
			J9ToolBar.items.get(i).Hover(hover);
		}
	}

}
