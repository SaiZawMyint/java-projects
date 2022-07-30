package com.J9Tool;

import java.util.ArrayList;

import com.J9Tool.J9TextEditorPane.CONSOLE;

public class J9System {

	public static ArrayList<String> record = new ArrayList<String>();
	
	public static void Console(CONSOLE c,String message) {
		J9TextEditorPane.console(c, message);
	}
	
	public static void JavaCode(String type,String code,int id) {
		if(type.equalsIgnoreCase("Parent")) {
			String parent = code;
			String name = code.toLowerCase()+"_"+id;
			String full = parent+" "+name+" = new "+parent+"();";
			boolean show=true;
			for(int i=0;i<record.size();i++) {
				if(record.get(i).equalsIgnoreCase(full)) {
					show=false;
				}
			}
			
			if(show) {
				J9System.record.add(full);
			}
			
		}
		Code();
	}
	static void Code() {
		J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).javaCode.setText("");
		for(int i=0;i<record.size();i++) {
			String full= record.get(i);
			J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).javaCode.append(full+"\n");
		}
	}
}
