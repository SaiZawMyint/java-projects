package com.J9Tool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.J9FrameWork.J9Frame;
import com.J9FrameWork.J9MenuItem;
import com.SSC.SSEncoder;

public class EditorBodyObject extends JPanel implements KeyListener,MouseListener,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4197642013472931162L;
	
	
	JPanel cons,contit,consoleItemfield,consBody,textConsoleTitle,preP,textConIcons;
	JScrollPane scr;
	JEditorPane textPane;
	public JTextArea consolePanel,javaCode;
	SplitPanel splitor,console,cj;
	JLabel consoleMenu,textConsTitle;
	JLabel closeConsole,closeTextCon,revJava;
	JLabel ref,textview;
	ImageIcon consoleIcon=new ImageIcon(getClass().getClassLoader().getResource("console.png"));
	ImageIcon consoleIconG=new ImageIcon(getClass().getClassLoader().getResource("consoleG.png"));
	ImageIcon refresh=new ImageIcon(getClass().getClassLoader().getResource("refresh.png"));
	ImageIcon reviewJavaCodeIcon=new ImageIcon(getClass().getClassLoader().getResource("filemenu.png"));
	boolean typing=false,onetimeSet=true,consoleView=true,consoleAdd=true;
	JScrollPane consolePane,textcp;
	PreviewConsole pre;
	JPopupMenu pop ;
	public EditorBodyObject(String title) {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setOpaque(true);
		textPane=new JTextPane();
		textPane.setCaretColor(Color.WHITE);
		textPane.setBackground(new Color(0, 0, 30));
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("Arial Rounded MT", Font.PLAIN, 15));
		textPane.addKeyListener(this);
		textPane.setMargin(new Insets(10, 10, 10, 10));
		
		pop = new JPopupMenu();
		pop.setBorderPainted(false);
		addPopup(textPane, pop);
		
		generateMenu();
		
		recOV();
		scr=new JScrollPane(textPane);
		
		cons=new JPanel();
		cons.setLayout(new BorderLayout());
		
		consoleItemfield = new JPanel();
		consoleItemfield.setLayout(new FlowLayout(FlowLayout.LEFT,5,1));
		consoleItemfield.setBackground(null);
		contit=new JPanel();
		contit.setBackground(this.getBackground());
		contit.setLayout(new BorderLayout());
		contit.setPreferredSize(new Dimension(this.getWidth(), 25));
		consoleMenu=new JLabel(title);
		consoleMenu.setForeground(Color.WHITE);
		consoleMenu.setBorder(new LineBorder(null,0));
		consoleMenu.setBackground(null);
		consoleMenu.setIcon(StyleTool.drawSmallIcon(consoleIcon, 15, 15));
		closeConsole=new JLabel("X");
		closeConsole.setFont(new Font("Arial", Font.BOLD, 13));
		closeConsole.setForeground(Color.LIGHT_GRAY);
		closeConsole.setHorizontalAlignment(SwingConstants.CENTER);
		closeConsole.setVerticalAlignment(SwingConstants.BOTTOM);
		closeConsole.setCursor(new Cursor(Cursor.HAND_CURSOR));
		closeConsole.setBackground(null);
		closeConsole.setPreferredSize(new Dimension(15, 20));
		closeConsole.addMouseListener(this);
		ref=new JLabel();
		ref.setBorder(null);
		ref.setForeground(Color.LIGHT_GRAY);
		ref.setHorizontalAlignment(SwingConstants.CENTER);
		ref.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ref.setBackground(null);
		ref.setPreferredSize(new Dimension(20, 20));
		ref.setIcon(StyleTool.drawSmallIcon(refresh, 15, 15));
		ref.addMouseListener(this);
		
		textview=new JLabel();
		textview.setBorder(null);
		textview.setForeground(Color.LIGHT_GRAY);
		textview.setHorizontalAlignment(SwingConstants.CENTER);
		textview.setCursor(new Cursor(Cursor.HAND_CURSOR));
		textview.setBackground(null);
		textview.setPreferredSize(new Dimension(20, 20));
		textview.setIcon(StyleTool.drawSmallIcon(consoleIconG, 15, 15));
		textview.addMouseListener(this);
		
		consoleItemfield.add(textview);
		consoleItemfield.add(ref);
		consoleItemfield.add(closeConsole);
		contit.add(consoleMenu,BorderLayout.CENTER);
		contit.add(consoleItemfield,BorderLayout.EAST);
		
		
		cons.add(contit,BorderLayout.NORTH);
		
		splitor=new SplitPanel(Calculator.percentageWidth(J9Frame.j9Frames.get(0), 80),2,scr, cons);
		
		pre = new PreviewConsole(textPane.getText());
		preP=new JPanel();
		preP.setLayout(new BorderLayout());
		preP.add(pre,BorderLayout.CENTER);
		consolePane = new JScrollPane(preP);
		
		textConsoleTitle = new JPanel();
		textConsoleTitle.setLayout(new BorderLayout());
		textConsoleTitle.setBackground(Color.DARK_GRAY);
		
		textConsTitle = new JLabel(" Console");
		textConsTitle.setBackground(null);
		textConsTitle.setForeground(Color.WHITE);
		textConsTitle.setPreferredSize(new Dimension(0, 23));
		textConsTitle.setBorder(new LineBorder(null, 0));
		textConsTitle.setIcon(StyleTool.drawSmallIcon(consoleIconG, 15, 15));
		
		textConIcons = new JPanel();
		textConIcons.setBackground(null);
		textConIcons.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
		
		revJava = new JLabel();
		revJava.setIcon(StyleTool.drawSmallIcon(reviewJavaCodeIcon,1,1, 15, 15));
		revJava.setBackground(null);
		revJava.setCursor(new Cursor(Cursor.HAND_CURSOR));
		revJava.addMouseListener(this);
		
		closeTextCon = new JLabel("X");
		closeTextCon.setBackground(null);
		closeTextCon.setForeground(Color.LIGHT_GRAY);
		closeTextCon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		closeTextCon.addMouseListener(this);
		
		textConIcons.add(revJava);
		textConIcons.add(closeTextCon);
		
		textConsoleTitle.add(textConsTitle,BorderLayout.CENTER);
		textConsoleTitle.add(textConIcons,BorderLayout.EAST);
		
		consolePanel = new JTextArea();
		consolePanel.setLineWrap(true);
		consolePanel.setCaretColor(Color.ORANGE);
		consolePanel.setBackground(new Color(0,0,30));
		consolePanel.setForeground(Color.GREEN);
		consolePanel.setMargin(new Insets(10, 10, 10, 10));
		consolePanel.setEditable(false);
		consolePanel.setCursor(new Cursor(Cursor.TEXT_CURSOR));

		javaCode = new JTextArea();
		javaCode.setEditable(false);
		javaCode.setLineWrap(true);
		javaCode.setVisible(true);
		javaCode.setBackground(new Color(10,10,30));
		javaCode.setForeground(new Color(64,224,208));
		javaCode.setMargin(new Insets(10, 10, 10, 10));
		javaCode.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		cj = new SplitPanel();
		cj.setLeftComponent(consolePanel);
		cj.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		cj.setVisible(true);
		textcp = new JScrollPane(cj);
		textcp.setColumnHeaderView(textConsoleTitle);
		consBody = new JPanel();
		consBody.setLayout(new BoxLayout(consBody, BoxLayout.Y_AXIS));
				
		console=new SplitPanel(J9Frame.j9Frames.get(0).getHeight()/2, 2, consolePane, textcp);
		console.setDividerLocation(J9Frame.j9Frames.get(0).getHeight()/2);
		console.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		cons.add(console,BorderLayout.CENTER);
		add(splitor,BorderLayout.CENTER);
		setEditorTitle(title);
		J9TextEditorPane.bodies.add(this);
	}
	JMenuItem items;
	ImageIcon fileMenu=new ImageIcon(getClass().getClassLoader().getResource("filemenu.png"));
	ImageIcon appLogo=new ImageIcon(getClass().getClassLoader().getResource("appLogo.png"));
	public ImageIcon runMenu=new ImageIcon(getClass().getClassLoader().getResource("runMenu.png"));
	ImageIcon settingMenu=new ImageIcon(getClass().getClassLoader().getResource("setting.png"));
	ImageIcon open=new ImageIcon(getClass().getClassLoader().getResource("open.png"));
	ImageIcon save=new ImageIcon(getClass().getClassLoader().getResource("save.png"));
	ImageIcon saveas=new ImageIcon(getClass().getClassLoader().getResource("saveas.png"));
	ImageIcon exit=new ImageIcon(getClass().getClassLoader().getResource("exit.png"));
	ImageIcon news=new ImageIcon(getClass().getClassLoader().getResource("new.png"));
	@SuppressWarnings("deprecation")
	private void generateMenu() {
		// TODO Auto-generated method stub
		items = new J9MenuItem("Application");
		items.setBorder(new EmptyBorder(3, 3, 3, 3));
		items.setIcon(StyleTool.drawSmallIcon(runMenu, 15, 15));
		items.setBackground(Color.DARK_GRAY);
		items.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		this.pop.add(items);
		
		items = new J9MenuItem("Console");
		items.setBorder(new EmptyBorder(3, 3, 3, 3));
		items.setIcon(StyleTool.drawSmallIcon(runMenu, 15, 15));
		items.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		items.setBackground(Color.DARK_GRAY);
		this.pop.add(items);
		
		items = new J9MenuItem("Copy");
		items.setBorder(new EmptyBorder(3, 3, 3, 3));
		items.setIcon(StyleTool.drawSmallIcon(fileMenu, 15, 15));
		items.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		items.setBackground(Color.DARK_GRAY);
		items.addActionListener(this);
		this.pop.add(items);
		
		this.pop.setBorder(new MatteBorder(0, 0, 0, 0, this.getBackground()));
		this.pop.setBorderPainted(false);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private void Console(boolean consoleAdd2) {
		// TODO Auto-generated method stub
		if(consoleAdd2) {
			addConsole();
		}else {
			removeConsole();
		}
	}
	public void updateTextConsole(String text,boolean error) {
		// TODO Auto-generated method stub
		if(error)
			this.consolePanel.setForeground(Color.ORANGE);
		else
			this.consolePanel.setForeground(Color.GREEN);
		this.consolePanel.setText(text);
	}
	int rcOV=0;
	protected int getOldValue() {
		return this.rcOV;
	}
	protected void recOV() {
		rcOV=getOldValue();
	}
	
	public void logConsole(String message) {
		this.consolePanel.setForeground(new Color(130,30,180));
		this.consolePanel.append("\n"+message);
	}

	public void setEditorTitle(String title) {
		
		this.setName(title);
		this.setBorder(new TitledBorder(new MatteBorder(1, 0, 0, 0, Color.DARK_GRAY), title, TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		String type="";
		if(title.equalsIgnoreCase("Untitle *")) {
			type="Unknown";
		}else {
			type="Application";
		}
		Date d=new Date();
		this.consoleMenu.setText(" "+title+" ["+type+"] - "+d.toString());
		this.textConsTitle.setText(" Console [ "+title+" ] - "+d.toString());
		
	}
	
	public void visibaleConsole() {
		if(!this.consoleView) {
			this.splitor.setDividerLocation(Calculator.percentageWidth(J9Frame.j9Frames.get(0), 70));
			this.splitor.setRightComponent(this.cons);
			this.consoleView=true;
		}else {
			this.splitor.setRightComponent(null);
			this.closeConsole.setForeground(Color.LIGHT_GRAY);
			this.consoleView=false;
		}
		
	}
	
	public void appRun() {
		this.javaCode.setText("");
		updateConsole(this.textPane.getText());
		SSEncoder.Code(this.textPane.getText());
		SSEncoder.ExecuteCode(false);
	}
	
	public void addConsole() {
		this.console.setDividerLocation(J9Frame.j9Frames.get(0).getHeight()/2);
		this.closeConsole.setForeground(Color.LIGHT_GRAY);
		this.console.setRightComponent(this.textcp);
	}
	public void removeConsole() {
		this.closeConsole.setForeground(Color.LIGHT_GRAY);
		this.console.setRightComponent(null);
	}
	
	protected void propertyChange(Object oldValue,Object newValue) {
		if(oldValue!=newValue) {
			
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this.textPane && e.getKeyCode()!=KeyEvent.CTRL_DOWN_MASK) {
			
			int rec=0;
			for(int i=0;i<J9TextEditorPane.bodies.size();i++) {
				if(J9TextEditorPane.bodies.get(i)==this) {
					if(!this.typing) {
						this.typing=true;
					}
					rec=i;
				}
			}
			if(this.typing && !this.onetimeSet) {
				this.setEditorTitle(this.getName()+"*");
				J9TextEditorPane.titles.get(rec).title.setText(J9TextEditorPane.titles.get(rec).title.getText()+"*");
				J9Frame.updateFrameTitle(this.getName());
				this.onetimeSet=true;
			}
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	protected void drawIcon(ImageIcon icon,int width, int height) {
		
	}

	public void updateConsole(String code) {
		code=code.trim();
		String s=this.textPane.getText();
		this.javaCode.setText("");
		s=s.trim();
		if(s.length() !=0 && s!="")
			updateTextConsole("Runed with no error.", false);
		else
			updateTextConsole("Warning : no element to update.", true);
		this.pre.update(code);
		
	}
	
	boolean viewC=false;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this.closeConsole) {
			this.splitor.setRightComponent(null);
			this.closeConsole.setForeground(Color.LIGHT_GRAY);
			this.consoleView=false;
		}
		if(e.getComponent()==this.ref) {
			
			updateConsole(this.textPane.getText());
		}
		if(e.getComponent() == this.textview || e.getComponent() == this.closeTextCon) {
			this.closeTextCon.setForeground(Color.LIGHT_GRAY);
			if(this.consoleAdd) {
			this.consoleAdd = false;
			Console(false);
			}else {
				this.consoleAdd=true;
				Console(true);
			}
		}
		if(e.getComponent() == this.revJava) {
			if(!viewC) {
				this.cj.setLeftComponent(null);
				this.cj.setRightComponent(this.javaCode);
				viewC=true;
			}else {
				this.cj.setLeftComponent(this.consolePanel);
				this.cj.setRightComponent(null);
				viewC=false;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this.closeConsole) {
			this.closeConsole.setForeground(Color.MAGENTA);
		}
		if(e.getComponent()==this.closeTextCon) {
			this.closeTextCon.setForeground(Color.MAGENTA);
		}
		if(e.getComponent()==this.textview) {
			this.textview.setBorder(new MatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
		}
		if(e.getComponent()==this.ref) {
			this.ref.setBorder(new MatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
		}
		if(e.getComponent()==this.revJava) {
			Color c=null;
			this.revJava.setBorder(new MatteBorder(1, 1, 1, 1, c));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this.closeConsole) {
			this.closeConsole.setForeground(Color.LIGHT_GRAY);
		}
		if(e.getComponent()==this.closeTextCon) {
			this.closeTextCon.setForeground(Color.LIGHT_GRAY);
		}
		if(e.getComponent()==this.textview) {
			this.textview.setBorder(null);
		}
		if(e.getComponent()==this.ref) {
			this.ref.setBorder(null);
		}
		if(e.getComponent()==this.revJava) {
			this.revJava.setBorder(null);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.ref) {
			updateConsole(this.textPane.getText());
		}
	}
	
}
