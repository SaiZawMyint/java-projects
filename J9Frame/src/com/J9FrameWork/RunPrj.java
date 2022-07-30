package com.J9FrameWork;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.J9Frame.event.MenuEvent;
import com.J9Tool.J9TextEditorPane;
import com.J9Tool.J9TextEditorPane.CONSOLE;
import com.J9Tool.J9ToolBar;
import com.J9Tool.SplitPanel;
import com.J9Tool.J9ToolBar.TOOL_DISPLAY;

import ErrorLogs.CodeException;
import ErrorLogs.MenuException;
import ErrorLogs.StyleException;

public class RunPrj extends J9Frame implements MenuEvent{
	
  /**
	 * 
	 */
	private static final long serialVersionUID = -7467796559807382594L;
	ImageIcon fileMenu=new ImageIcon(getClass().getClassLoader().getResource("filemenu.png"));
	ImageIcon appLogo=new ImageIcon(getClass().getClassLoader().getResource("appLogo.png"));
	public ImageIcon runMenu=new ImageIcon(getClass().getClassLoader().getResource("runMenu.png"));
	ImageIcon settingMenu=new ImageIcon(getClass().getClassLoader().getResource("setting.png"));
	ImageIcon open=new ImageIcon(getClass().getClassLoader().getResource("open.png"));
	ImageIcon save=new ImageIcon(getClass().getClassLoader().getResource("save.png"));
	ImageIcon saveas=new ImageIcon(getClass().getClassLoader().getResource("saveas.png"));
	ImageIcon exit=new ImageIcon(getClass().getClassLoader().getResource("exit.png"));
	ImageIcon news=new ImageIcon(getClass().getClassLoader().getResource("new.png"));
	String[] fileMenuItem= {
		"Open","New","Save","Save as","<s>","Quit"	
	};
	String[] settingMItem= {
		"General","Apperance","Tool","<s>","<item>Help"	
	};
	String[] helpItem= {
		"Community Support","Website","<s>","FAQ"	
	};
	String[] runMenuItem= {
			"Application","Console"
	};
	J9ToolBar tool;
	SplitPanel split;

	@SuppressWarnings("deprecation")
	public RunPrj() {
		// TODO Auto-generated constructor stub
		
	//	DbConnection.GetData("file");
		customFrame("Hyper Text", null, null);
		useDefault(true);
		Display(Display.CENTRE);
		AppLogo(appLogo);
		setJ9Menu("File", fileMenuItem);
		getMenu("File").setMenuIcon(fileMenu, 15, 15);
		setJ9Menu("Run",runMenuItem);
		getMenu("Run").setMenuIcon(runMenu, 15, 15);
		setJ9Menu("Setting", settingMItem);
		getMenu("Setting").setMenuIcon(settingMenu, 15, 15);
		addJ9MenuItem("Help", helpItem);
		setLayout(new BorderLayout());
		getMenuItem("Save").addMenuEvent(this);
		getMenuItem("Open").addMenuEvent(this);
		getMenuItem("New").addMenuEvent(this);
		getMenuItem("Quit").addMenuEvent(this);
		getMenuItem("Console").addMenuEvent(this);
		getMenuItem("Application").addMenuEvent(this);
		getMenuItem("New").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		getMenuItem("Quit").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
		getMenuItem("Open").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		getMenuItem("Save").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		getMenuItem("Save as").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		getMenuItem("Application").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		getMenuItem("Console").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		getMenuItem("New").setIcon(news, 15, 15);
		getMenuItem("Open").setIcon(open, 15, 15);
		getMenuItem("Save").setIcon(save, 15, 15);
		getMenuItem("Save as").setIcon(saveas, 15, 15);
		getMenuItem("Quit").setIcon(exit, 15, 15);
		getMenuItem("Application").setIcon(runMenu, 15, 15);
		getMenuItem("Console").setIcon(runMenu, 15, 15);
		tool=new J9ToolBar("tool1");
		tool.toolPadding(1, 3);
		tool.toolDisplay(TOOL_DISPLAY.LEFT_TO_RIGHT);
		tool.addTool(new String[] {
				"Save","Find","Run"
		});
		
		tool.toolStyle(""
				+ "padding: 1;"
				+ "size: (40,20);"
				+ "align: center;"
				+ "background-color: transparent;"
				+ "color: white;"
				+ "cursor: pointer;");
		tool.toolHover(""
				+ "background-color: dark_gray;"
				+ "border: (1,1,1,1) solid(black);"
				+ "");
		tool.getTool("Run").Style(""
				+ "icon: src(C:\\\\Users\\\\Acer\\\\eclipse-workspace\\\\workspace-2\\\\J9Frame\\\\Icon\\\\runMenu.png) size(15,15);"
				+ "size: (45,20);"
				+ "");
		tool.getTool("Save").addActionListener(this);
		tool.getTool("Run").addActionListener(this);
	//	tool.getTool("Find").setToolIcon("C:\\Users\\Acer\\eclipse-workspace\\workspace-2\\J9Frame\\Icon\\runMenu.png", 15, 15);
		
		split=new SplitPanel();
		split.setBorder(null);
		J9TextEditorPane pane=new J9TextEditorPane();
		
		split.setRightComponent(pane);
		
		addChild(tool, Display.TOP);
		addChild(split, Display.CENTRE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) throws MenuException, StyleException, CodeException {
		
		RunPrj r=new RunPrj();
		r.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s=e.getActionCommand();
		if(s.equalsIgnoreCase("Save")) {
			if(J9TextEditorPane.bodies.size()>0) {
				
					J9TextEditorPane.titles.get(J9TextEditorPane.getSelectedEditor()).save(false);
				
			}
		}
		if(s.equalsIgnoreCase("New")) {
			J9TextEditorPane.addNewEditorTab("Untitle *");
		}
		if(s.equalsIgnoreCase("Open")) {
			J9TextEditorPane.Open();
		}
		if(s.equalsIgnoreCase("Console")) {
			J9TextEditorPane.console(CONSOLE.LOG, "Running !");
			J9TextEditorPane.consoleRun();
		}
		if(s.equalsIgnoreCase("Application") || s.equalsIgnoreCase("Run")) {
			J9TextEditorPane.appRun();
		}
		if(s.equalsIgnoreCase("Quit")) {
			if(JOptionPane.showConfirmDialog(J9Frame.j9Frames.get(0), "Exit?","AXIL",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) {
				String record = "";
				if(J9TextEditorPane.titles.size()>0) {
					if(!J9TextEditorPane.titles.get(J9TextEditorPane.getSelectedEditor()).getName().contains("*"))
						record = J9TextEditorPane.titles.get(J9TextEditorPane.getSelectedEditor()).getName()+"/";
				}
				String filename="recOpen.j9";
				File objFile=new File("src/com/J9File/"+filename);
				try {
	        	   FileWriter objFileWriter = new FileWriter(objFile.getAbsoluteFile());
	               //instnace (object) of BufferedReader with respect of FileWriter
	               BufferedWriter objBW = new BufferedWriter(objFileWriter);
	               //write into file
	               objBW.write(record);
	               objBW.close();
	               
				}catch (Exception c) {
					c.printStackTrace();
				}
				System.exit(0);
			}
		}
	}
	
}
