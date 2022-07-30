package com.J9Tool;

import java.awt.Color;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.SSC.SSDecoder;
import com.SSC.SSEncoder;

public class PreviewConsole extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5627419848519681329L;

	JInternalFrame f;
	
	public PreviewConsole(String code) {
		// TODO Auto-generated constructor stub
		update(code);
	}
	ImageIcon logo=new ImageIcon(getClass().getClassLoader().getResource("appLogo.png"));
	void update(String code){
		this.setLayout(null);
		this.removeAll();
		code = code.replaceAll("\n", "");
		SSEncoder.Code(code);
		SSEncoder.ExecuteCode(true);
		if(!SSDecoder.compnonets.isEmpty()) {
			f=(JInternalFrame) SSDecoder.compnonets.get(0);
			f.setResizable(true);
			try {
				f.setMaximum(true);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			f.setMaximizable(true);
			f.setBorder(null);
			f.setClosable(true);
			f.setVisible(true);
			this.add(f);
		}
		
		this.setBackground(new Color(0,0,30));
	}
	
}
