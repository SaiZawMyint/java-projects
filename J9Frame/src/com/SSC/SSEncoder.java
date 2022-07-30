package com.SSC;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.J9FrameWork.J9Frame;
import com.J9Tool.J9System;
import com.J9Tool.J9TextEditorPane.CONSOLE;

import ErrorLogs.CodeException;

/**
 * @author <strong style='color:#00b4c3;'>JNine <i style='color:silver'>i</i>-TECH</strong><br>
 * 
 * String to Swing encoder ( SSE ) : the relationship between the java and html coding are very close toady.
 * the SSE code processes the hyper code to the Swing code.
 *
 */
public class SSEncoder {

	/**
	 * @author <strong style='color:#00b4c3;'>JNine <i style='color:silver'>i</i>-TECH</strong><br>
	 * 
	 * Default <i>High Quality Tags<i>( HQT )
	 *
	 */
	static enum HQ_TAGS {
		FRAME, PANEL, INPUT, BUTTON, LABEL, BR, TEXT
	}

	static String[] HQTAGS = { "frame", "panel", "input", "button", "label", "br", "text" , "jshi" };

	/**
	 * @author <strong style='color:#00b4c3;'>JNine <i style='color:silver'>i</i>-TECH</strong><br>
	 * 
	 * Default Menus used.
	 *
	 */
	static enum MENU {
		MENU, MENUITME, MENUBAR
	}

	String[] MENUS = { "menu", "menuitem", "menubar" };

	/**
	 * @author <strong style='color:#00b4c3;'>JNine <i style='color:silver'>i</i>-TECH</strong><br>
	 * 
	 * Default Attributes used.
	 *
	 */
	static enum ATTRIBUTES {
		TYPE, ID, STYLE, CLASS, EVENT, TITLE
	}

	static String[] ATTRIBUTE_ARY = { "type", "id", "style", "class", "event", "title" };

	/**
	 * Rectangle used for the encode concept.
	 */
	public static ArrayList<Rectangle> rect = new ArrayList<Rectangle>();
	/**
	 * The code named by the encode concept.
	 */
	public static ArrayList<String> nameRec = new ArrayList<String>();
	/**
	 * X-axis used in the theory of encode.
	 */
	public static ArrayList<Integer> Xaxis = new ArrayList<Integer>();
	/**
	 * Y-axis used in the theory of encode.
	 */
	public static ArrayList<Integer> Yaxis = new ArrayList<Integer>();
	/**
	 * Integer of getting update elements count.
	 */
	public static ArrayList<Integer> GET = new ArrayList<Integer>();
	/**
	 * Temporary used integer in the theory.
	 */
	public static ArrayList<Integer> TempY = new ArrayList<Integer>();
	/**
	 * The main parent of the attributes used in the decoding.
	 */
	public static ArrayList<String> Parent = new ArrayList<String>();
	/**
	 * <i>Code With Attribute ( CWA ) <i> - using in the encode process. 
	 */
	public static ArrayList<String> CWA = new ArrayList<String>();
	/**
	 * The attribute parameters of code. 
	 */
	public static ArrayList<String> AttributeParam = new ArrayList<String>();

	/**
	 * Text parameters from the code.
	 */
	public static ArrayList<String> textParam = new ArrayList<String>();
	/**
	 * Human understant text from the code.
	 */
	public static ArrayList<String> textValue = new ArrayList<String>();
	/**
	 * Temporary use of each starting point of code parameter.
	 */
	public static ArrayList<Integer> startTempPos = new ArrayList<Integer>();
	/**
	 * Temporary use of each ending point of code parameter.
	 */
	public static ArrayList<Integer> endTempPos = new ArrayList<Integer>();
	/**
	 * Number use for encode concept.
	 */
	public static ArrayList<String> PCNumerically = new ArrayList<String>();
	public static int heightest = 0;

	/**
	 * Swing to String code Encoder (SSE)
	 * @param code - SSE code.
	 */
	public SSEncoder(String code) {
		// TODO Auto-generated constructor stub
		clear();
		if (code.length() != 0) {
			Encode(code);
		}

	}

	/**
	 * clear and reset all record.
	 */
	public static void clear() {
		rect.clear();
		nameRec.clear();
		Xaxis.clear();
		Yaxis.clear();
		GET.clear();
		CWA.clear();
		Parent.clear();
		AttributeParam.clear();
		TempY.clear();
		J9System.record.clear();
	}

	/**
	 * Encoder
	 * @param code - SSE code
	 */
	public static void Code(String code) {
		clear();
		if (code.length() != 0) {
			Encode(code);
		}
	}

	/**
	 * Code Closer Reader (CCR) - get all the the closer tag from the code (<i><</>/<i>></>).
	 * @param code - SSE code.
	 * @return - the int array of all CCR positions encode.
	 */
	protected static int[] CCR(String code) {
		code = code.trim();  
		int countOpenner = 0, countCloser = 0;
		int[] CCR = null;

		if(code.contains("<")) {
		
		for (int i = 0; i < code.length(); i++) {
			if (code.charAt(i) == '<') {
				if (i == 0) {
					countOpenner++;
				} else {
					if (code.charAt(i - 1) != '\\' && code.charAt(i) == '<') {
						countOpenner++;
					}
				}

			}
			if (code.charAt(i) == '>') {
				if (i == 0) {
					countCloser++;
				} else {
					if (code.charAt(i - 1) != '\\' && code.charAt(i) == '>') {
						countCloser++;
					}
				}
			}
		}
		code = code.substring(code.indexOf('<'), code.lastIndexOf('>') + 1);
		if (countOpenner != countCloser) {
			if (countOpenner > countCloser) {
				try {
					// J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).console.setText("Missing
					// tag closer ! Check Out");
					J9Frame.throwCodeException("Missing tag closer ! Check Out");
				} catch (CodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (countCloser > countOpenner) {
				try {
					// J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).console.setText("Missing
					// tag openner ! Check Out");
					J9Frame.throwCodeException("Missing tag openner ! Check Out");
				} catch (CodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			CCR = new int[countCloser];
			int o = 0;
			for (int i = 0; i < code.length(); i++) {
				if (code.charAt(i) == '<') {
					if (i == 0) {

					} else {
						if (code.charAt(i - 1) != '\\' && code.charAt(i) == '<') {

						}
					}

				}
				if (code.charAt(i) == '>') {

					if (i == 0) {
						CCR[o] = i;
						o++;
					} else {
						if (code.charAt(i - 1) != '\\' && code.charAt(i) == '>') {
							CCR[o] = i;
							o++;
						}
					}
				}
			}

		}
		}
		return CCR;
	}

	/**
	 * Getting whitespace text from the code.
	 * @param i - index of whitespace.
	 * @return - whitespace text.
	 */
	public static String getText(int i) {
		String s="";
		s=SSEncoder.textValue.get(i);
		
		return s;
	}
	
	/**
	 * Code Openner Reader ( COR ) - get all openner tag from the code(<i><</>HQA<i>></>)
	 * @param code - SSE code.
	 * @return the int array of all COR positions encode.
	 */
	protected static int[] COR(String code) {
		code = code.trim();  
		int countOpenner = 0, countCloser = 0;
		int[] COR = null;

		if(code.contains("<")) {
		
		for (int i = 0; i < code.length(); i++) {
			if (code.charAt(i) == '<') {
				if (i == 0) {
					countOpenner++;
				} else {
					if (code.charAt(i - 1) != '\\' && code.charAt(i) == '<') {
						countOpenner++;
					}
				}

			}
			if (code.charAt(i) == '>') {
				if (i == 0) {
					countCloser++;
				} else {
					if (code.charAt(i - 1) != '\\' && code.charAt(i) == '>') {
						countCloser++;
					}
				}
			}
		}
		code = code.substring(code.indexOf('<'), code.lastIndexOf('>') + 1);
		if (countOpenner != countCloser) {
			if (countOpenner > countCloser) {
				try {
				
					J9Frame.throwCodeException("Missing tag closer ! Check Out");
				} catch (CodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (countCloser > countOpenner) {
				try {
					
					J9Frame.throwCodeException("Missing tag openner ! Check Out");
				} catch (CodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			COR = new int[countOpenner];
			int c = 0;
			for (int i = 0; i < code.length(); i++) {
				if (code.charAt(i) == '<') {
					if (i == 0) {
						COR[c] = i;
						c++;
					} else {
						if (code.charAt(i - 1) != '\\' && code.charAt(i) == '<') {
							COR[c] = i;
							c++;
						}
					}

				}
			}

		}
		}
		return COR;
	}

	
	/**
	 * Code verification is very important in checking and searching the code issue. It can pick them to their represent form and encode easily. 
	 * If the code contain error, it may check them and fix them. They can fix the system code error but not the human made error. 
	 * @param code - SSE code
	 * @return - the verified and purify code.
	 */
	protected static String verifyCode(String code) {
		code = code.trim();
		int[] COR = COR(code);
		int[] CCR = CCR(code);
		
		if (COR.length == CCR.length && COR.length > 0 && CCR.length > 0) {
			SSEncoder.textParam.clear();
			SSEncoder.textValue.clear();
			SSEncoder.startTempPos.clear();
			SSEncoder.endTempPos.clear();
			for (int i = 0; i < COR.length; i++) {
				if (i == COR.length - 1) {
					break;
				} else {
					SSEncoder.startTempPos.add(COR[i]);
					SSEncoder.endTempPos.add(CCR[i]+1);
					String cc=code.substring(CCR[i] + 1, COR[i + 1]);
										
					SSEncoder.textParam.add(cc);
				}
			}

			for (int i = 0; i < SSEncoder.textParam.size(); i++) {
				String text = SSEncoder.textParam.get(i);
				String temp = code.substring(SSEncoder.startTempPos.get(i)+1, SSEncoder.endTempPos.get(i));
				if(text.length() !=0 && text !="" && text!=null && text !="\n" 
						|| temp.length() > 2 && temp.charAt(0) == 't' && temp.charAt(1) == 'e' && temp.charAt(2) == 'x' && temp.charAt(3) == 't') {
					text = "<jshi>" + SSEncoder.textParam.get(i) + "</>";
					text=text.substring(text.indexOf("<"), text.length());
					SSEncoder.textParam.set(i, text);
					
				}
				
			}

			int reclen = 0;
			for (int i = 0; i < COR.length; i++) {
				if (i == COR.length - 1) {
					break;
				} else {
					String first = code.substring(0, CCR[i] + reclen + 1);
					first=first.trim();
					String middle = SSEncoder.textParam.get(i);
					String last = code.substring(COR[i + 1] + reclen, code.length());
					last=last.trim();
					if(middle.length() !=0 && middle !="" && middle!=null && middle!="\n") 
						reclen+=9;
					code = first.concat(middle).concat(last);
				}
			}
		}
		
		for(int i=0;i<code.length();i++) {
			if(i > 5) {
				if(code.charAt(i)=='>' && code.charAt(i-1)=='i' && code.charAt(i-2)=='h' && code.charAt(i-3)=='s' && code.charAt(i-4)=='j' && code.charAt(i-5)=='<') {
					String pre = code.substring(i+1, code.length());
					String tv="";
					if(pre.charAt(0) !='<') {
						tv = pre.substring(0, pre.indexOf("<"));
					}else {
						tv = "";
					}
					SSEncoder.textValue.add(tv);
				}
			}
		}

		return code;
	}

	/**
	 * SSE Encoding process.
	 * @param code - SSE Code.
	 */
	protected static void Encode(String code) {
		code=code.trim();
		code = verifyCode(code);
		int[] newCOR = COR(code);
		int[] newCCR = CCR(code);
		String[] codeParam = new String[newCOR.length];
		for (int i = 0; i < newCOR.length; i++) {
			codeParam[i] = code.substring(newCOR[i], newCCR[i] + 1);
		}

		String[] getTag = new String[codeParam.length];

		for (int i = 0; i < getTag.length; i++) {
			if (codeParam[i].contains(" ")) {
				getTag[i] = codeParam[i].substring(codeParam[i].indexOf("<") + 1, codeParam[i].indexOf(" "));
			} else {
				getTag[i] = codeParam[i].substring(codeParam[i].indexOf("<") + 1, codeParam[i].indexOf(">"));
			}
		}

		int countO = 0, countC = 0;
		String[] Names = new String[getTag.length / 2];

		for (int i = 0; i < getTag.length; i++) {
			if (getTag[i].contains("/")) {
				countC++;
			} else {
				countO++;
			}
		}
		if (countO != countC) {
			if (countO > countC) {
				try {
					J9Frame.throwCodeException("Missing tag closer ! Check the openner and closer !");
				} catch (CodeException e) {
					e.printStackTrace();
				}
			}
			if (countO < countC) {
				try {
					J9Frame.throwCodeException("Missing tag openner ! Check the openner and closer !");
				} catch (CodeException e) {
					e.printStackTrace();
				}
			}
		} else {
			int cc = 0;
			for (int i = 0; i < getTag.length; i++) {
				if (!getTag[i].contains("/")) {
					Names[cc] = getTag[i];
					cc++;
				}
			}

			String[] codeWA = new String[codeParam.length / 2];
			int cwa = 0;
			for (int i = 0; i < codeParam.length; i++) {
				if (!codeParam[i].contains("</")) {
					if (codeParam.length != 0) {
						codeWA[cwa] = codeParam[i];
						cwa++;
					}
				}
			}

			String frame = getTag[0];

			if (frame.equalsIgnoreCase("frame")) {
				int x = 0, y = 0, rc = 0;
				int[] Xa = new int[codeParam.length];
				int[] Ya = new int[codeParam.length];
				int[] FY = new int[codeParam.length];
				for (int i = 0; i < getTag.length; i++) {
					if (i == 0) {
						x++;
						y++;
						Xa[rc] = x;
						Ya[rc] = y;
						FY[rc] = y;
						heightest = y;
						rc++;
					} else {
						if (!getTag[i].contains("/") && !getTag[i - 1].contains("/")) {
							x++;
							y++;

							Xa[rc] = x;
							Ya[rc] = y;
							FY[rc] = y;
							heightest = y;
							rc++;
						}
						if (getTag[i].contains("/") && !getTag[i - 1].contains("/")) {
							x++;

							Xa[rc] = x;
							Ya[rc] = y;
							FY[rc] = y;
							rc++;

						}
						if (getTag[i - 1].contains("/") && !getTag[i].contains("/")) {
							Xa[rc] = x;
							Ya[rc] = y;
							FY[rc] = y;
							rc++;
						}
						if (getTag[i].contains("/") && getTag[i - 1].contains("/")) {
							x++;
							y--;
							Xa[rc] = x;
							Ya[rc] = y;
							FY[rc] = y;
							rc++;
						}
					}
				}

				for (int i = 0; i < Xa.length; i++) {
					SSEncoder.Xaxis.add(Xa[i]);
					SSEncoder.Yaxis.add(Ya[i]);
					SSEncoder.TempY.add(Ya[i]);
				}

				tempParents(TempY);
				int width = 0, slow = 25;
				for (int i = 0; i < SSEncoder.GET.size(); i += 2) {
					int X = SSEncoder.Xaxis.get(SSEncoder.GET.get(i));
					int Xx = SSEncoder.Xaxis.get(SSEncoder.GET.get(i + 1));
					int Y = SSEncoder.Yaxis.get(SSEncoder.GET.get(i));

					width = Xx - X;
					Rectangle rectangle = new Rectangle(X * 20, Y * 20, width * 20, slow);
					rectangle.setFrame(rectangle);
					SSEncoder.rect.add(rectangle);

				}
				NamedCode(Names);
				// analyseRec();
				analyseAP(codeWA);
			} else {
				try {
					J9Frame.throwCodeException("Unstandable type ! Cannot run itself");
				} catch (CodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Pick and analyse the Attribute Param with the corrected code with attribute ( CWA ).
	 * @param codeWA - Code With Attribute ( CWA )
	 */
	protected static void analyseAP(String[] codeWA) {
		// TODO Auto-generated method stub
		for (int i = 0; i < codeWA.length; i++) {
			String tag = codeWA[i].trim();
			String codeA = "";
			String getConA = "";
			if (tag.contains(" ")) {
				codeA = codeWA[i].trim();
				tag = tag.substring(tag.indexOf("<") + 1, tag.indexOf(" "));
			} else {
				tag = tag.substring(tag.indexOf("<") + 1, tag.lastIndexOf(">"));
			}
			checkHQ(tag);
			if (codeA != "") {
				String[] attrs = getAttributes(codeA);

				for (int j = 0; j < attrs.length; j++) {
					getConA += getCA(tag, attrs[j]);
					int checkAttr = 0;
					String getAttr = attrs[j].substring(0, attrs[j].indexOf("["));
					getAttr = getAttr.trim();
					for (String or : ATTRIBUTE_ARY) {
						if (getAttr.equalsIgnoreCase(or)) {
							checkAttr++;
						}
					}
					if (checkAttr == 0) {
						try {
							J9Frame.throwCodeException("Unknown attribute : " + getAttr);
						} catch (CodeException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {

					}
				}

			} else {
				getConA = "unset";
			}

			SSEncoder.AttributeParam.add(tag + ":" + getConA);
		}

	}

	/**
	 * Getting the code attribute from the code.
	 * @param tag
	 * @param attrs
	 * @return
	 */
	protected static String getCA(String tag, String attrs) {
		// TODO Auto-generated method stub
		String s = "";
		if (attrs.startsWith("id")) {
			s += attrs + "*";
		}
		if (attrs.startsWith("style")) {
			s += attrs + "*";
		}
		if (attrs.startsWith("title")) {
			s += attrs + "*";
		}
		if (attrs.startsWith("type")) {
			s += attrs + "*";
		}
		if (attrs.startsWith("event")) {
			s += attrs + "*";
		}

		return s;
	}

	/**
	 * Get the named attribute from CWA.
	 * @param codeA - code attribute.
	 * @return the string array of named attribute.
	 */
	static String[] getAttributes(String codeA) {
		String[] a = null;

		String attr = codeA.substring(codeA.indexOf(" "), codeA.lastIndexOf(">"));
		attr = attr.trim();
		int countAttr = 0;
		for (int i = 0; i < attr.length(); i++) {
			if (attr.charAt(i) == ']') {
				countAttr++;
			}
		}
		a = new String[countAttr];
		String analyse = attr;
		for (int i = 0; i < countAttr; i++) {
			a[i] = analyse.substring(0, analyse.indexOf("]") + 1);
			a[i] = a[i].trim();
			analyse = analyse.substring(analyse.indexOf("]") + 1, analyse.length());
			analyse = analyse.trim();
		}

		return a;
	}

	/**
	 * Checking the Hight Quality(HQ) tags from the tag code parameter.
	 * @param tag - the tag of code parameter.
	 */
	protected static void checkHQ(String tag) {
		// TODO Auto-generated method stub
		int check = 0;
		for (String checktag : HQTAGS) {
			if (tag.equalsIgnoreCase(checktag)) {
				check++;
			}
		}
		if (check == 0) {
			try {
				String recom = "";
				for (String rec : HQTAGS) {
					recom += rec + ", ";
				}
				recom = recom.substring(0, recom.lastIndexOf(","));
				J9Frame.throwCodeException("Undefined tag : " + tag + " !\n Default tag are : " + recom + ".");
			} catch (CodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Name and Record the code in the encoding process.
	 * @param names
	 */
	protected static void NamedCode(String[] names) {
		// TODO Auto-generated method stub
		for (String name : names) {
			SSEncoder.nameRec.add(name);
		}
		
		for (int i = 0; i < SSEncoder.rect.size(); i++) {
			int c=0;
			for (int j = i + 1; j < SSEncoder.rect.size(); j++) {
				if (SSEncoder.rect.get(i).intersects(SSEncoder.rect.get(j))) {
					SSEncoder.Parent
							.add(i + " - " + SSEncoder.nameRec.get(i) + "=" + (j) + " - " + SSEncoder.nameRec.get(j));
					c++;
				}
			}
			SSEncoder.PCNumerically.add(i+"-"+c);
		}
	}


	/**
	 * Get the outest code field and it's inner code field and recode them with thier parent and child type.
	 *  The process help the code grow and strong to redecode.
	 * @param list - the integer list of temp points.
	 */
	protected static void tempParents(ArrayList<Integer> list) {
		for (int a = 0; a < list.size(); a++) {
			if (list.get(a) != 0010) {
				int record = list.get(a);
				int recordJ = 0, first = 0, second = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) != 0010) {
						if (list.get(i) == record) {
							recordJ++;
							if (recordJ == 1) {
								first = i;
							}
							if (recordJ == 2) {
								second = i;
								SSEncoder.GET.add(first);
								SSEncoder.GET.add(second);
								list.set(first, 0010);
								list.set(second, 0010);
								break;
							}
						}
					}
				}
			} else {
				continue;
			}
		}
	}

	/**
	 * Get <i>High Quality Attriubte</i> ( <span style='color:orange;'>HQA</span> ) by its specified index.
	 * @param index - the specified inedx of <span style='color:orange;'>HQA</span>
	 * @return <span style='color:orange;'>HQA</span>
	 */
	public static String getHQA(int index) {
		String type = "";
		if(type !="jshi") {
			type =  SSEncoder.nameRec.get(index);
		}else {
			type = "N/A";
		}
		return type;
	}
	
	public static void ExecuteCode(boolean pre) {
		SSDecoder ssd = new SSDecoder(SSEncoder.nameRec, SSEncoder.Parent, SSEncoder.AttributeParam, pre);
		
		ssd.execute();
	}

}
