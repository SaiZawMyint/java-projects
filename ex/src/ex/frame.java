package ex;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class frame extends JFrame{
panel p;
JSplitPane sp;
Container c;
	public frame() {
		// TODO Auto-generated constructor stub
		setSize(500, 500);
		setVisible(true);
		
		c=getContentPane();
		p=new panel();
		sp=new JSplitPane();
		sp.setLeftComponent(null);
		sp.setRightComponent(p);
		c.add(sp);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]) {
		frame f=new frame();
		
	}
}
