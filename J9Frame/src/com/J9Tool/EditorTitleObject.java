package com.J9Tool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.J9FrameWork.J9Frame;


public class EditorTitleObject extends JButton implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5580955633354015219L;
	
	JLabel closebtn;
	JLabel title;
	JMenuItem console;
	boolean selected=false;
	JPanel tit;
	JPanel icons;
	ImageIcon consoleIcon=new ImageIcon(getClass().getClassLoader().getResource("console.png"));
	ImageIcon fileicon=new ImageIcon(getClass().getClassLoader().getResource("filemenu.png"));
	public EditorTitleObject(String titleString,String iconCode) {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		this.setName(titleString);
		title=new JLabel(titleString);
		title.setBackground(null);
		title.setForeground(Color.WHITE);
		title.addMouseListener(this);
		title.setPreferredSize(new Dimension(70,18));
		title.setIcon(StyleTool.drawSmallIcon(fileicon,3,3, 13, 13));
		title.setVerticalAlignment(SwingConstants.BOTTOM);
		
		console=new JMenuItem();
		console.setBackground(null);
		console.setPreferredSize(new Dimension(20, 25));
		console.setBorder(null);
		console.addMouseListener(this);
		console.setIcon(StyleTool.drawSmallIcon(consoleIcon, 13, 13));
		console.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		closebtn=new JLabel("X");
		closebtn.setForeground(Color.LIGHT_GRAY);
		closebtn.setBorder(null);
		closebtn.setBackground(null);
		closebtn.addMouseListener(this);
		closebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		closebtn.setPreferredSize(new Dimension(13, 15));
		closebtn.setHorizontalAlignment(SwingConstants.CENTER);
		
		icons = new JPanel();
		icons.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		icons.setBackground(null);
		icons.add(console);
		icons.add(closebtn);
		
		tit = new JPanel();
		tit.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
		tit.setBackground(null);
		tit.add(title);

		this.add(tit,BorderLayout.CENTER);
		this.add(icons,BorderLayout.EAST);
		this.addMouseListener(this);
		this.setBorder(null);
		this.setMaximumSize(new Dimension(100, 30));
		J9TextEditorPane.titles.add(this);
	}
	
	public void focousUpdate() {
		if(selected)
			this.setBorder(new MatteBorder(0, 0, 0, 1, Color.DARK_GRAY));
		else
			this.setBorder(new MatteBorder(0,0, 0, 1, Color.BLACK));
	}
	
	public void save(boolean close) {
		if(this.title.getText().equalsIgnoreCase("Untitle *")) {
			JLabel l=new JLabel();
			JTextField f=new JTextField();
			
			Object[] obj= {
					l,"Name",f
			};
			
			if(JOptionPane.showConfirmDialog(null, obj, "Save file", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)==
					JOptionPane.OK_OPTION
					) {
				char[] c= {
						'/',' ','"','\'','\\','`','_','(',')','*','&','^','%','$','#','@','!',',','.','/','<','>','[',']','{','}','|' ,'~',':'
				};
				int count=0;
				for (char d : c) {
					for(int i=0;i<f.getText().length();i++) {
						if(f.getText().charAt(i)==d)
							count++;
					}
				}
				if(f.getText().length()>0) {
					if(count==0) {
						analyseSave(f.getText());
						if(close)
							J9TextEditorPane.panes.get(0).closeEditor(J9TextEditorPane.getSelectedEditor());
						
					}else {
						JOptionPane.showMessageDialog(null, "File name cannot contain special character !","Progress failed !",JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "File name cannot be null","Progress failed !",JOptionPane.ERROR_MESSAGE);
				}
					
			}
		}else {
			if(this.title.getText()!="Untitle *" && this.title.getText().contains("*")) {
				String analyse=this.title.getText().replace("*", "");
				analyseSave(analyse);
				if(close)
					J9TextEditorPane.panes.get(0).closeEditor(J9TextEditorPane.getSelectedEditor());
			}
		}
		
	}

	protected void analyseSave(String analyse) {
		
		final String filename=analyse+".j9";
		try
        {
            File objFile=new File("Project/"+filename);
            if(objFile.exists()) {
            	if(JOptionPane.showConfirmDialog(null, "File Already Exit! Open file instead ?","System Information",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)
            			==JOptionPane.YES_OPTION
            			) {
            		
            		
            	}
            }else {
            if(objFile.createNewFile())
            {
               
                J9TextEditorPane.titles.get(J9TextEditorPane.getSelectedEditor()).setName(filename);
        		J9TextEditorPane.titles.get(J9TextEditorPane.getSelectedEditor()).title.setText(filename);
        		J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).setName(filename);
        		J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).setEditorTitle(filename);
        		J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).onetimeSet=false;
        		J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).recOV();
        		J9Frame.updateFrameTitle(J9TextEditorPane.titles.get(J9TextEditorPane.getSelectedEditor()).getName());
            }
            else
            {
                System.out.println("File creation failed!!!");
            }
            
           if(J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).textPane.getText().length()!=0) {
        	   String filetext=J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).textPane.getText();
        	   FileWriter objFileWriter = new FileWriter(objFile.getAbsoluteFile());
               //instnace (object) of BufferedReader with respect of FileWriter
               BufferedWriter objBW = new BufferedWriter(objFileWriter);
               //write into file
               objBW.write(filetext);
               objBW.close();
               
           }
            
            }
        }
        catch (Exception Ex)
        {
            System.out.println("Exception : " + Ex.toString());
        }
		
	}
	
	public void updateEditor(int i) {
		
		if(J9TextEditorPane.titles.get(i)==this) {
			J9TextEditorPane.titles.get(i).selected=true;
			J9TextEditorPane.setSelectEditor(i);
			
		}else {
			J9TextEditorPane.titles.get(i).selected=false;
			J9TextEditorPane.titles.get(i).setBackground(Color.DARK_GRAY);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this || e.getComponent()==this.title) {
			if(!selected) {
				this.setBackground(Color.LIGHT_GRAY);
				for(int i=J9TextEditorPane.titles.size()-1;i>=0;i--) {
					updateEditor(i);
				}
				
			}
			try {
				Thread.sleep(100);
				if(!selected)this.setBackground(Color.DARK_GRAY);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getComponent()==this.closebtn) {
			if(this.title.getText().contains("*")) {
				if(JOptionPane.showConfirmDialog(null, "Save Project","Save Project",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==
						JOptionPane.YES_OPTION) {
					this.save(true);
				}
			}else {
				int j=0;
				for(int i=0;i<J9TextEditorPane.titles.size();i++) {
					if(J9TextEditorPane.titles.get(i)==this) {
						j=i;
					}
				}
				this.title.setText("Closing...");
				J9TextEditorPane.panes.get(0).closeEditor(j);
				
			}
		}
		if(e.getComponent()==this.console) {
			if(this.selected) {
				J9TextEditorPane.bodies.get(J9TextEditorPane.getSelectedEditor()).visibaleConsole();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this || e.getComponent()==this.title ) {
			if(!selected)this.setBackground(Color.LIGHT_GRAY);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this || e.getComponent()==this.title ) {
			if(!selected)this.setBackground(Color.DARK_GRAY);
		}
		if(e.getComponent()==this.closebtn) {
			if(!selected)
				this.setBackground(Color.DARK_GRAY);
			this.closebtn.setForeground(Color.LIGHT_GRAY);
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this || e.getComponent()==this.title) {
			if(!selected)
				this.setBackground(Color.BLACK);
		}
		if(e.getComponent()==this.closebtn) {
			if(!selected)
				this.setBackground(Color.BLACK);
			this.closebtn.setForeground(Color.MAGENTA);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent()==this || e.getComponent()==this.title ) {
			if(!selected)
				this.setBackground(Color.DARK_GRAY);
		}
		if(e.getComponent()==this.closebtn) {
			if(!selected)
				this.setBackground(Color.DARK_GRAY);
			this.closebtn.setForeground(Color.LIGHT_GRAY);
		}
	}

}
