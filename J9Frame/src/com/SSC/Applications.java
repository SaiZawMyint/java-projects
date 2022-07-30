package com.SSC;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;

import com.J9Tool.StyleTool;

public class Applications {

	/**
	 * Apply the objects to their parent. 
	 * @param comment : mentions the way to apply between parent and child.
	 * @param parent : current object parent.
	 * @param child : current child.
	 */
	public static void ApplyObjects(String comment,Component parent,Component child) {
		if(comment.equals("I-P")) {
			JInternalFrame f =(JInternalFrame) parent;
			int index = SSDecoder.getIndex(f);
			int ind = SSDecoder.getIndex(child);
			String id = SSDecoder.getID(index);
			
			for(int i=0;i<StyleTool.layoutProperties.size();i++) {
				String mainS = StyleTool.layoutProperties.get(i);
				String compareId = mainS.substring(0, mainS.indexOf("#"));
				String lyTy = mainS.substring(mainS.indexOf("#")+1, mainS.length());
				if(id.equals(compareId)) {
					UpdateLayout(index,ind,lyTy);
				}
			}
		}
	}

	private static void UpdateLayout(int index, int ind, String lyTy) {
		// TODO Auto-generated method stub
		JComponent p=(JComponent) SSDecoder.getComponent(index);
		JComponent c=(JComponent) SSDecoder.getComponent(ind);
		if(lyTy.equalsIgnoreCase("Border")) {
			
		}
	}
	
}
