package com.J9Tool;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.J9FrameWork.J9Frame;

import ErrorLogs.EditorException;

public class J9TextEditorPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 789461088273541431L;

	public static ArrayList<EditorTitleObject> titles = new ArrayList<EditorTitleObject>();

	public static ArrayList<EditorBodyObject> bodies = new ArrayList<EditorBodyObject>();

	public static ArrayList<J9TextEditorPane> panes = new ArrayList<J9TextEditorPane>();

	// rec

	public static ArrayList<EditorTitleObject> rect = new ArrayList<EditorTitleObject>();

	public static enum CONSOLE {
		LOG, ERR, DATE, LIST
	}

	EditorTitleObject tab;
	EditorBodyObject textEditor;
	static JPanel p;
	static JToolBar body;
	static CardLayout c = new CardLayout();
	static Box b;

	public J9TextEditorPane() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		J9TextEditorPane.panes.add(this);

		b = Box.createHorizontalBox();
		b.setOpaque(true);
		b.setBackground(Color.DARK_GRAY);
		body = new JToolBar();
		body.setBackground(Color.BLACK);
		body.setLayout(c);
		namedF("AXIL - FLOATABLE EDITOR (v.1.0)");
		body.setBorder(null);
		body.setPreferredSize(new Dimension(1000, 500));
		tempTab();

		p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.add(body);
		this.add(b, BorderLayout.NORTH);
		this.add(p, BorderLayout.CENTER);

	}
	
	public void namedF(String name) {
		body.setName(name);
	}
	
	protected void tempTab() {
		try {
			String filename = "recOpen.j9";
			File objFile = new File("src/com/J9File/" + filename);
			if (objFile.exists() == false) {
				J9System.Console(CONSOLE.LOG, "File does not exit!");
			}

			// reading content from file
			String text;
			FileReader objFR = new FileReader(objFile.getAbsoluteFile());
			BufferedReader objBR = new BufferedReader(objFR);
			// read text from file
			String fileContents = "";
			while ((text = objBR.readLine()) != null) {
				fileContents += text + "\n";
			}
			if(!fileContents.contains("/")) {
				J9TextEditorPane.addNewEditorTab("Untitle *");
			}else {
			int count = 0;
			for (int i = 0; i < fileContents.length(); i++) {
				if (fileContents.charAt(i) == '/') {
					count++;
				}
			}

			if(count > 0) {
			String[] files = new String[count];

			for (int i = 0; i < files.length; i++) {
				files[i] = fileContents.substring(0, fileContents.indexOf("/"));
				fileContents = fileContents.substring(fileContents.indexOf("/") + 1, fileContents.length());
			}

			for (int i = 0; i < files.length; i++) {
				String fn = File.separator + files[i];
				File f = new File("C:\\Users\\Acer\\eclipse-workspace\\workspace-2\\J9Frame\\Project" + fn);
				if (!f.exists()) {
					J9System.Console(CONSOLE.LOG, "File does not exit !");
				}
				String name = f.getName();

				openFile(name, f);
			}
			}
			}
			objBR.close();
		} catch (Exception Ex) {
			System.out.println("Exception : " + Ex.toString());
		}
		
	}

	public static void addNewEditorTab(String tabname) {
		EditorTitleObject t = new EditorTitleObject(tabname,
				"src(C:\\Users\\Acer\\eclipse-workspace\\workspace-2\\J9Frame\\Icon\\filemenu.png) size(15,15)");
		J9TextEditorPane.b.add(t);
		EditorBodyObject b = new EditorBodyObject(tabname);
		tempSelectedEditor(b, t);
	}

	public static void Open() {
		/**
		 * Open p=new Open(); Object[] obj= { "Select file(s) : ",p };
		 * JOptionPane.showConfirmDialog(J9Frame.j9Frames.get(0), obj, "Open file",
		 * JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		 */

		JFileChooser chooser = new JFileChooser("C:\\Users\\Acer\\eclipse-workspace\\workspace-2\\J9Frame\\Project");
		int select = chooser.showOpenDialog(null);
		if (select == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String name = file.getName();
			openFile(name, file);
		}

	}

	protected static void openFile(String name, File f) {
		String filename = File.separator + f;
		int check = 0;
		int record = 0;
		if (J9TextEditorPane.titles.size() > 0) {
			for (int i = 0; i < J9TextEditorPane.titles.size(); i++) {
				if (J9TextEditorPane.titles.get(i).getName().equals(name)) {
					check++;
					record = i;
				}
			}
		}
		if (check == 0) {
			J9TextEditorPane.addNewEditorTab(name);
			try {
				File objFile = new File(filename);
				if (objFile.exists() == false) {
					J9System.Console(CONSOLE.LOG, "File does not exit!");
				}

				// reading content from file
				String text;
				FileReader objFR = new FileReader(objFile.getAbsoluteFile());
				BufferedReader objBR = new BufferedReader(objFR);
				// read text from file
				String fileContents = "";
				while ((text = objBR.readLine()) != null) {
					fileContents += text + "\n";
				}
				J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).textPane.setText(fileContents);
				objBR.close();
			} catch (Exception Ex) {
				System.out.println("Exception : " + Ex.toString());
			}

		} else {
			J9TextEditorPane.setSelectEditor(record);
			J9System.Console(CONSOLE.LOG, "File is already opened !");
		}

	}

	private static void analyseRec(int t) {
		// TODO Auto-generated method stub
		int record = 0;
		int count = 0;
		for (int i = 0; i < J9TextEditorPane.rect.size(); i++) {
			if (J9TextEditorPane.rect.get(i) == J9TextEditorPane.titles.get(t)) {
				record = i;
				count++;
			}
		}
		if (count > 0) {
			J9TextEditorPane.rect.remove(record);
			J9TextEditorPane.rect.add(J9TextEditorPane.titles.get(t));
		}
		if (count == 0) {
			J9TextEditorPane.rect.add(J9TextEditorPane.titles.get(t));
		}
	}

	public void closeEditor(int index) {
		b.remove(index);
		body.remove(J9TextEditorPane.bodies.get(index));
		resetFocus(index);
		repaint();
		setIgnoreRepaint(true);
	}

	protected void resetFocus(int index) {
		int curentR = 0;
		int cR = 0;
		for (int i = 0; i < J9TextEditorPane.rect.size(); i++) {
			if (J9TextEditorPane.rect.get(i).getName().equalsIgnoreCase(J9TextEditorPane.titles.get(index).getName())) {
				curentR = i;
				cR++;
			}
		}
		if (cR > 0) {
			J9TextEditorPane.rect.remove(curentR);
			J9TextEditorPane.titles.remove(index);
			J9TextEditorPane.bodies.remove(index);
		}
		int count = 0;
		int rec = 0;
		for (int i = 0; i < J9TextEditorPane.titles.size(); i++) {
			if (J9TextEditorPane.titles.get(i) == J9TextEditorPane.rect.get(J9TextEditorPane.rect.size() - 1)) {
				count++;
				rec = i;
			}
		}
		if (count > 0)
			setSelectEditor(rec);
	}

	public static void tempSelectedEditor(EditorBodyObject b, EditorTitleObject t) {
		for (int i = J9TextEditorPane.bodies.size() - 1; i >= 0; i--) {
			if (J9TextEditorPane.bodies.get(i) == b) {
				J9TextEditorPane.body.add(J9TextEditorPane.bodies.get(i));

			} else {
				J9TextEditorPane.body.remove(J9TextEditorPane.bodies.get(i));
			}
			if (J9TextEditorPane.titles.get(i) == t) {
				setSelectEditor(i);

			} else {
				removeSelectEditor(i);

			}
		}
	}

	public void tempEditor(int index) {
		body.removeAll();
		body.add(J9TextEditorPane.bodies.get(index));
		body.repaint();
	}

	static int getEditor(String editor) throws EditorException {
		int ed = 0;

		for (int i = 0; i < J9TextEditorPane.bodies.size(); i++) {
			if (J9TextEditorPane.bodies.get(i).getName().equalsIgnoreCase(editor)
					|| J9TextEditorPane.titles.get(i).getName().equalsIgnoreCase(editor)) {
				ed = i;
			}
		}

		if (ed == 0) {
			throw new EditorException("No editor page found !");
		}

		return ed;
	}

	static void setSelectEditor(int index) {
		J9TextEditorPane.titles.get(index).setBackground(Color.BLACK);
		J9TextEditorPane.titles.get(index).selected = true;
		J9TextEditorPane.titles.get(index).focousUpdate();
		J9TextEditorPane.panes.get(0).tempEditor(index);
		J9Frame.updateFrameTitle(J9TextEditorPane.titles.get(index).getName());
		analyseRec(index);
	}

	static void removeSelectEditor(int index) {
		J9TextEditorPane.titles.get(index).setBackground(Color.DARK_GRAY);
		J9TextEditorPane.titles.get(index).selected = false;
		J9TextEditorPane.titles.get(index).focousUpdate();
		
	}

	public static int getSelectedEditor() {
		int index = 0;

		for (int i = 0; i < J9TextEditorPane.titles.size(); i++) {
			if (J9TextEditorPane.titles.get(i).selected)
				index = i;
		}

		return index;
	}

	public void addEditorTabTitleEvent() {

	}

	public static void consoleRun() {
		// TODO Auto-generated method stub
		J9TextEditorPane.bodies.get(getSelectedEditor())
				.updateConsole(J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).textPane.getText());
	}

	public static void appRun() {
		J9TextEditorPane.bodies.get(getSelectedEditor()).appRun();
	}

	public static void console(CONSOLE C, String message) {
		if (C == CONSOLE.LOG) {
			J9TextEditorPane.bodies.get(getSelectedEditor()).logConsole(message);
		}
	}

}
