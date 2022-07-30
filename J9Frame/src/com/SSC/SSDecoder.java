package com.SSC;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.J9FrameWork.J9Frame;
import com.J9Tool.J9System;
import com.J9Tool.StyleTool;

import ErrorLogs.CodeException;

public class SSDecoder {

	public static ArrayList<String> Objects = new ArrayList<String>();
	public static ArrayList<String> ObjectParent = new ArrayList<String>();
	public static ArrayList<String> ObjectAttribute = new ArrayList<String>();

	public static ArrayList<String> ID = new ArrayList<String>();

	public static boolean preview=false;

	public static ArrayList<Component> compnonets = new ArrayList<Component>();
	ImageIcon appLogo=new ImageIcon(getClass().getClassLoader().getResource("appLogo.png"));
	
	public SSDecoder(ArrayList<String> nameRec, ArrayList<String> parent, ArrayList<String> attributeParam,boolean pre) {
		
		SSDecoder.Objects = nameRec;
		SSDecoder.ObjectParent = parent;
		SSDecoder.ObjectAttribute = attributeParam;
		preview=pre;
		generateDefaultId();

	}
	
	public static void clear() {
		Objects.clear();
	}

	private void generateDefaultId() {
		// TODO Auto-generated method stub
		SSDecoder.ID.clear();
		for(int i=0;i<SSDecoder.Objects.size();i++) {
			SSDecoder.ID.add(i+" - "+SSDecoder.Objects.get(i)+" [ "+ i +" ]");
		}
	}

	void execute() {
		writeObject(SSDecoder.Objects);
		
		readObject();
		AnalyseObjects();
		check();
	}
	
	protected void check() {
		for(int i=0;i<SSDecoder.ObjectAttribute.size();i++) {
		}
		for(int i=0;i<SSDecoder.ID.size();i++) {
			
		}
	}

	public static String getID(int index) {
		String id = "";

		String preId = SSDecoder.ID.get(index);
		id = preId.substring(preId.indexOf("[") + 1, preId.indexOf("]"));
		return id;
	}

	public static int getIDI(String id) {
		int idi = 0;
		String pre="";
		for(int i=0;i<SSDecoder.ID.size();i++) {
			String get = getID(i);
			if( get.equalsIgnoreCase(id)) {
				pre=SSDecoder.ID.get(i);
			}
		}
		pre=pre.substring(0, pre.indexOf("-"));
		pre=pre.trim();
		idi = Integer.parseInt(pre);
		return idi;
	}
	
	public static int getIndex(Component com) {
		int index = 0;
		for(int i=0;i<SSDecoder.compnonets.size();i++) {
			if(SSDecoder.compnonets.get(i) == com) {
				index = i;
			}
		}
		return index;
	}
	  
	
	public static Component getComponent(int index) {
		Component c = SSDecoder.compnonets.get(index);		
		return c;
	}

	protected void readObject() {
		// TODO Auto-generated method stub
		generateAttribute();

	}

	private void generateAttribute() {
		// TODO Auto-generated method stub
		if (!SSDecoder.ObjectAttribute.isEmpty()) {
			for (int i = 0; i < SSDecoder.ObjectAttribute.size(); i++) {
				String getatr = tempAttribute(SSDecoder.ObjectAttribute.get(i));
				String obj = getatr.substring(0, getatr.indexOf(":"));
				if (getatr.equalsIgnoreCase("unset")) {

				} else {
					int rec = 0;
					for (int j = 0; j < getatr.length(); j++) {
						if (getatr.charAt(j) == ']') {
							rec++;
						}
					}
					String[] each = new String[rec];

					String analy = getatr.substring(getatr.indexOf(":") + 1, getatr.length());
					analy = analy.trim();
					for (int a = 0; a < rec; a++) {
						each[a] = analy.substring(0, analy.indexOf("]") + 1);
						analy = analy.substring(analy.indexOf("]") + 1, analy.length());
						analy = analy.trim();
						executeAttribute(obj, each[a], i);
					}
				}

			}

		}
	}

	private String tempAttribute(String string) {
		// TODO Auto-generated method stub
		String result = "";

		int count = 0;

		for (int i = 0; i < string.length(); i++) {

			if (string.charAt(i) == '*') {
				count++;
			}

		}

		String[] fuck = new String[count];
		String maintag = string.substring(0, string.indexOf(":") + 1);
		maintag=maintag.trim();
		String analy = string.substring(string.indexOf(":") + 1, string.length());
		analy=analy.trim();
		for (int a = 0; a < count; a++) {
			fuck[a] = analy.substring(0, analy.indexOf("*"));
			analy = analy.substring(analy.indexOf("*") + 1, analy.length());
		}
		String idString = "", typeString = "", styleString = "", eventString = "", titleString = "";
		for (int i = 0; i < fuck.length; i++) {
			String attribute = fuck[i].substring(0, fuck[i].indexOf("["));
			attribute=attribute.trim();
			if (attribute.equalsIgnoreCase("id")) {
				idString = fuck[i];
			}
			if (attribute.equalsIgnoreCase("type")) {
				typeString = fuck[i];
			}
			if (attribute.equalsIgnoreCase("style")) {
				styleString = fuck[i];
			}
			if (attribute.equalsIgnoreCase("event")) {
				eventString = fuck[i];
			}
			if (attribute.equalsIgnoreCase("title")) {
				titleString = fuck[i];
			}

		}
		result = maintag + typeString + " " + idString + " " + styleString +" "+ titleString +" " + eventString;
		return result;
	}

	private void executeAttribute(String obj, String string, int index) {
		// TODO Auto-generated method stub
		if (string.charAt(0) == 'i' && string.charAt(1) == 'd') {
		/**	String id = string.substring(string.indexOf("[") + 1, string.indexOf("]"));
			SSDecoder.ID.set(index,index + " - " + obj + " [ " + id + " ]");*/
		}

		if (string.startsWith("style")) {
			String s = getAttributeValue(string);
			String type = s.substring(0, s.indexOf(":"));
			String value = s.substring(s.indexOf(":") + 1, s.length());
			if (type.equalsIgnoreCase("file")) {
				ATTEncode.Style(obj, value, ATTEncode.ENC_TYPE.FILE, index);
			}
			if (type.equalsIgnoreCase("default")) {

				ATTEncode.Style(obj, value, ATTEncode.ENC_TYPE.DEFAULT, index);
			}
		}
		
		if(string.startsWith("title")) {
			String title=string.substring(string.indexOf("[")+1, string.lastIndexOf("]"));
			title = StyleTool.checkSpecialCharacters(title);
			if(obj.equalsIgnoreCase("frame")) {
				if(SSDecoder.preview) {
					((JInternalFrame) SSDecoder.compnonets.get(index)).setTitle(title);
				}else {
					((J9Frame) SSDecoder.compnonets.get(index)).setTitle(title);
				}
			}else {
				((JComponent) SSDecoder.compnonets.get(index)).setToolTipText(title);
			}
		}

		if (string.startsWith("type")) {

		}

		if (string.startsWith("event")) {

		}

	}

	String getAttributeValue(String attr) {
		String result = "";

		result = attr.substring(attr.indexOf("[") + 1, attr.lastIndexOf("]"));
		if (result.charAt(0) == '{' && result.charAt(result.length() - 1) == '}') {
			result = "file:" + result;
		} else {
			result = "default:" + result;
		}
		return result;
	}


	protected void writeObject(ArrayList<String> objects) {
		// TODO Auto-generated method stub
		SSDecoder.compnonets.clear();
		int countCurText = 0;
		for (int i = 0; i < objects.size(); i++) {
			String object = objects.get(i);
			if (object.equalsIgnoreCase("frame")) {
				if(preview) {
					JInternalFrame f = new JInternalFrame();
					f.setFrameIcon(StyleTool.drawSmallIcon(appLogo, 15, 15));
					f.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
					SSDecoder.compnonets.add(f);
				}else {
					J9Frame f = new J9Frame();
					f.useDefault(true);
					f.setVisible(true);
					f.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
					
					SSDecoder.compnonets.add(f);
				}
				J9System.JavaCode("Parent", "J9Frame", i);
			}
			if (object.equalsIgnoreCase("panel")) {
				JPanel p = new JPanel();
				p.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
				J9System.JavaCode("Parent", "JPanel", i);
				SSDecoder.compnonets.add(p);
			}
			if(object.equalsIgnoreCase("jshi")) {
				JLabel lb = new JLabel();
				String text=SSEncoder.getText(countCurText);
				lb.setText(text);
				lb.setBackground(null);
				SSDecoder.compnonets.add(lb);
				String g = lb.getText();
				g=g.replaceAll(" ", "");
				g=g.replaceAll("\n", "");
				if(g!=null || g!="")
					J9System.JavaCode("Parent", "JLabel", i);
				countCurText++;
			}
			if(object.equalsIgnoreCase("Text")) {
				JTextField lb = new JTextField();
				lb.setAutoscrolls(true);
				lb.setPreferredSize(new Dimension(100, 25));
				String text=SSEncoder.getText(countCurText);
				
				lb.setText(text);
				lb.setBackground(null);
				SSDecoder.compnonets.add(lb);
				J9System.JavaCode("Parent", "JTextField", i);
			}
		}
	
	
	}
	public static void AnalyseObjects() {
		for(int i=0;i<SSDecoder.ObjectParent.size();i++) {
			String ap=SSDecoder.ObjectParent.get(i);
			String pId=ap.substring(0, ap.indexOf("-"));
			String parentType=ap.substring(ap.indexOf("-")+1, ap.lastIndexOf("="));
			parentType=parentType.trim();
			String childType=ap.substring(ap.lastIndexOf("-")+1, ap.length());
			childType=childType.trim();
			pId=pId.trim();
			String cId=ap.substring(ap.lastIndexOf("=")+1, ap.lastIndexOf("-"));
			cId=cId.trim();
			int parentId=Integer.parseInt(pId);
			int childId=Integer.parseInt(cId);
			updateAdd(parentType,parentId,childType,childId);
		}
	}

	private static void updateAdd(String parentType, int parentId, String childType, int childId) {
		// TODO Auto-generated method stub
		J9Frame ft,cf;
		JPanel fp,cp;
		JLabel lb;
		JTextField ar,ap;
		JInternalFrame in,ic;
		
		if(parentType.equalsIgnoreCase("frame")) {
			if(preview) {
				in=(JInternalFrame) SSDecoder.compnonets.get(parentId);
				
				in.setVisible(true);
				if(childType.equalsIgnoreCase("frame")) {
					if(SSDecoder.preview) {
						ic=(JInternalFrame) SSDecoder.compnonets.get(childId);
						ic.setVisible(true);
						in.add(ic);
					}else {
						cf=(J9Frame) SSDecoder.compnonets.get(childId);
						in.add(cf);
					}
				}
				if(childType.equalsIgnoreCase("panel")) {
					cp=(JPanel) SSDecoder.compnonets.get(childId);
					cp.setVisible(true);
					Applications.ApplyObjects("I-P", in, cp);
				}
				if(childType.equalsIgnoreCase("jshi")) {
					lb=(JLabel) SSDecoder.compnonets.get(childId);
					lb.setForeground(in.getForeground());
					
					String gett = lb.getText();
					gett = gett.replaceAll("\n", "");
					gett = gett.replaceAll(" ", "");
					if(gett.length() > 0 && gett != "" && gett !=null)
						lb.setVisible(true);
					else
						lb.setVisible(false);
					in.add(lb);
				}
				if(childType.equalsIgnoreCase("text")) {
					ar=(JTextField) SSDecoder.compnonets.get(childId);
					ar.setVisible(true);
					in.add(ar);
				}
				
			}else {
				ft=(J9Frame) SSDecoder.compnonets.get(parentId);
				ft.setVisible(true);
				if(childType.equalsIgnoreCase("frame")) {
					cf=(J9Frame) SSDecoder.compnonets.get(childId);
					cf.setVisible(true);
					ft.add(cf);
				}
				if(childType.equalsIgnoreCase("panel")) {
					cp=(JPanel) SSDecoder.compnonets.get(childId);
					cp.setVisible(true);
					checkBeforeAdd("F-P",ft, cp);
				}
				if(childType.equalsIgnoreCase("jshi")) {
					lb=(JLabel) SSDecoder.compnonets.get(childId);
					lb.setForeground(ft.getForeground());
					lb.setVisible(true);
					String gett = lb.getText();
					gett = gett.replaceAll("\n", "");
					gett = gett.replaceAll(" ", "");
					if(gett.length() > 0 && gett != "" && gett !=null)
						lb.setVisible(true);
					else
						lb.setVisible(false);
					
					ft.add(lb);
				}
				if(childType.equalsIgnoreCase("text")) {
					ar=(JTextField) SSDecoder.compnonets.get(childId);
					ar.setVisible(true);
					ft.add(ar);
				}
				
			}
			
			
		}
		
		if(parentType.equalsIgnoreCase("panel")) {
			fp=(JPanel) SSDecoder.compnonets.get(parentId);
			if(childType.equalsIgnoreCase("frame")) {
				try {
					J9Frame.throwCodeException("Panel cannot become the frame parent!");
				} catch (CodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(childType.equalsIgnoreCase("panel")) {
				cp=(JPanel) SSDecoder.compnonets.get(childId);
				cp.setVisible(true);
				fp.add(cp);
			}
			if(childType.equalsIgnoreCase("jshi")) {
				lb=(JLabel) SSDecoder.compnonets.get(childId);
				lb.setForeground(fp.getForeground());
				lb.setVisible(true);
				String gett = lb.getText();
				gett = gett.replaceAll("\n", "");
				gett = gett.replaceAll(" ", "");
				if(gett.length() > 0 && gett != "" && gett !=null)
					lb.setVisible(true);
				else
					lb.setVisible(false);
					
				fp.add(lb);
			}
			if(childType.equalsIgnoreCase("text")) {
				ar=(JTextField) SSDecoder.compnonets.get(childId);
				ar.setVisible(true);
				fp.add(ar);
			}
		}
		
		if(parentType.equalsIgnoreCase("text")) {
			ap = (JTextField) SSDecoder.compnonets.get(parentId);
			if(childType.equalsIgnoreCase("frame")) {
				try {
					J9Frame.throwCodeException("Text cannot become the frame parent!");
				} catch (CodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(childType.equalsIgnoreCase("panel")) {
				cp=(JPanel) SSDecoder.compnonets.get(childId);
				cp.setVisible(true);
				ap.add(cp);
			}
			
			if(childType.equalsIgnoreCase("text")) {
				ar=(JTextField) SSDecoder.compnonets.get(childId);
				ar.setVisible(true);
				ap.add(ar);
			}
		}
		
		
	}
	private static void checkBeforeAdd(String way,Component parent, Component child) {
		// TODO Auto-generated method stub
		if(way.equalsIgnoreCase("I-P")) {
			JInternalFrame f = (JInternalFrame) parent;
		//	int index = SSDecoder.getIndex(f);
			
		}
	}

	public static Component getParent(String id) {
		Component c = null;
		id=id.trim();
		
		int idi=0;
		for(int i=0;i<SSDecoder.ObjectParent.size();i++) {
			String a=SSDecoder.ObjectParent.get(i);
			String cId=a.substring(a.lastIndexOf("=")+1, a.lastIndexOf("-"));
			cId=cId.trim();
			if(cId.equalsIgnoreCase(id)) {
				String pId=a.substring(0, a.indexOf("-"));
				pId=pId.trim();
				idi=Integer.parseInt(pId);
			}
		}
		
		c = SSDecoder.compnonets.get(idi);
		return c;
	}
}
