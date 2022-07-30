package com.J93D;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class J3DFrame extends JFrame implements KeyListener
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static JTextField TF;
	static J3DScreen ScreenObject = new J3DScreen();
	static Tools tool = new Tools();
	public J3DFrame(){
		this.addKeyListener(this);
		setUndecorated(true);
	    setSize(ScreenSize);
	    
	    JSplitPane s=new JSplitPane();
	    s.setLeftComponent(ScreenObject);
	    s.setRightComponent(tool);
	    s.setDividerLocation(ScreenSize.width/2);
	    s.setDividerSize(1);
	    
	    add(s);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		J3DFrame home=new J3DFrame();
		home.setVisible(true);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			if(JOptionPane.showConfirmDialog(null, "Exit this project!","Notifications",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			J3DScreen.Focus = !J3DScreen.Focus;
			ScreenObject.updateFocus(J3DScreen.Focus);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
