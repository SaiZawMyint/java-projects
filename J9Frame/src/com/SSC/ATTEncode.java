package com.SSC;

import com.J9Tool.StyleTool;

public class ATTEncode {

	static enum ENC_TYPE{
		FILE,DEFAULT
	}
	
	public static void Style(String tag,String value,ENC_TYPE type,int index) {
	
		String id=SSDecoder.getID(index);
		if(type == ENC_TYPE.DEFAULT) {
			OptamizeStyle(tag,value,id,"default");
		}
		if(type == ENC_TYPE.FILE) {
			
		}
	}
	

	private static void OptamizeStyle(String tag, String value, String id, String type) {
		// TODO Auto-generated method stub
		String styleCode=tag+"("+id+"){"+value+"}";
		if(type.equalsIgnoreCase("default")) {
				StyleTool.StyleCode(styleCode);
		
			
		}
	}


	public static void Event(String tag,String value,ENC_TYPE type,int index) {
		
	}
	
}
