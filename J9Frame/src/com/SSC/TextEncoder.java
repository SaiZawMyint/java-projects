package com.SSC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.J9FrameWork.J9Frame;
import com.J9FrameWork.J9Frame.Display;

public class TextEncoder {
	
	public static ArrayList<String> text = new ArrayList<String>();
	
	public static ArrayList<String> field= new ArrayList<String>();
	
	public static void encodeText(String code) {
		
	}

	public static void encode(String code) {
		String g = SSEncoder.verifyCode(code);
		String s = "";
		for(int i=0;i<SSEncoder.textParam.size();i++) {
			s += i + " - " + SSEncoder.textParam.get(i);
		}
		JOptionPane.showMessageDialog(null, g+"\n"+s);
	}
	protected void field(String code) {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		J9Frame f=new J9Frame();
		f.useDefault(true);
		JTextArea a=new JTextArea();
		a.setLineWrap(true);
		f.addChild(a, Display.CENTRE);
		
		JButton b=new JButton("encode");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s = a.getText();
			//	s = s.replaceAll("\n", "");
				s = s.trim();
			 TextEncoder.encode(s);
			}
		});
		f.addChild(b, Display.BOTTOM);
		
		f.setVisible(true);
		
		
	}

}
