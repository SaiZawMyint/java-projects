package com.J93D;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class J3DScreen extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2404168257677300241L;

	//ArrayList of all the 3D polygons - each 3D polygon has a 2D 'PolygonObject' inside called 'DrawablePolygon'
	static ArrayList<J3DPolygon> DPolygons = new ArrayList<J3DPolygon>();
	
	static ArrayList<Cube> Cubes = new ArrayList<Cube>();
	static ArrayList<Prism> Prisms = new ArrayList<Prism>();
	static ArrayList<Pyramid> Pyramids = new ArrayList<Pyramid>();
	static ArrayList<SurroundPoly> cylinder = new ArrayList<SurroundPoly>();
	
	//The polygon that the mouse is currently over
	static J3DObject PolygonOver = null;

	//Used for keeping mouse in centre
	Robot r;

	static double[] ViewFrom = new double[] { -1, 47, 23},	
					ViewTo = new double[] {-1, 47, 23},
					LightDir = new double[] {0, 0, 1};

	
	//The smaller the zoom the more zoomed out you are and visa versa, although altering too far from 1000 will make it look pretty weird
	static double zoom = 1000, MinZoom = 500, MaxZoom = 2500, MouseX = 0, MouseY = 0, MovementSpeed = 0.5;
	
	//FPS is a bit primitive, you can set the MaxFPS as high as u want
	double drawFPS = 0, MaxFPS = 1000, SleepTime = 1000.0/MaxFPS, LastRefresh = 0, StartTime = System.currentTimeMillis(), LastFPSCheck = 0, Checks = 0;
	//VertLook goes from 0.999 to -0.999, minus being looking down and + looking up, HorLook takes any number and goes round in radians
	//aimSight changes the size of the centre-cross. The lower HorRotSpeed or VertRotSpeed, the faster the camera will rotate in those directions
	double VertLook = -0.9, HorLook = 0, aimSight = 4, HorRotSpeed = 900, VertRotSpeed = 2200, SunPos = 0;

	//will hold the order that the polygons in the ArrayList DPolygon should be drawn meaning DPolygon.get(NewOrder[0]) gets drawn first
	int[] NewOrder;
	
	
	int count=0;
	
	
	static boolean OutLines,Focus = true;
	boolean[] Keys = new boolean[4];
	boolean userinterface=false,newCube=false,cubeRelease=false;
	String type3D="";
	double new3DlocationX=0,new3DlocationY=0,new3DZ=2;
	
	long repaintTime = 0;
	
	static double x,y,z,width,height,length;	
	
	JButton toolBtn;
	
	public J3DScreen()
	{		
		GenerateSimpleTerrian(150, Color.BLUE);
		Cube cube=new Cube(4, 47, 0, 2, 2, 2, Color.GREEN);
		Cubes.add(cube);
		
		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 3));
		
		toolBtn = new JButton("Tool");
		this.add(toolBtn);
		
		
		this.addKeyListener(this);
		
		setFocusable(true);		
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		
		updateFocus(Focus);
		this.setVisible(true);
	}	
	//hill like
	public void GenerateTerrian(double roughtness,int mapSize,Color c) {
		new GenerateTerrain(roughtness,mapSize,c);
	}
	
	//simple
	public void GenerateSimpleTerrian(int mapSize,Color c) {
		new GenerateSimpleTerrain(mapSize,c);
	}
	
	//cube
	public void addCube(double x,double y,double z,double width,double length,double height,Color c) {
		
		Cube cube=new Cube(x, y, z, width, length, height, c);
		Cubes.add(cube);
	}
	
	//prisms
	public void addPrisms(double x,double y,double z,double width,double length,double height,Color c) {
		Prisms.add(new Prism(x, y, z, width, length, height, c));
	}
	
	//pyramid
	public void addPyramids(double x,double y,double z,double width,double length,double height,Color c) {
		Pyramids.add(new Pyramid(x, y, z, width, length, height, c));
	}
	
	//cylinder
	public void addCylinder(double x,double y,double z,double width,double height,Color c) {
		cylinder.add(new SurroundPoly(x, y, z, width, height, c));
	}
	
	
	
	public void paintComponent(Graphics g)
	{
		//Clear screen and draw background color
		g.setColor(new Color(100, 130, 180));
		g.fillRect(0, 0, (int)J3DFrame.ScreenSize.getWidth(), (int)J3DFrame.ScreenSize.getHeight());

		CameraMovement();
		
		//Calculated all that is general for this camera position
		Calculator.SetPrederterminedInfo();
		ControlSunAndLight();

		
		//Updates each polygon for this camera position
		for(int i = 0; i < DPolygons.size(); i++)
			DPolygons.get(i).updatePolygon();

		//rotate and update shape examples
	//	Cubes.get(0).Run();

		/**		Prisms.get(0).rotation+=.01;
		Prisms.get(0).updatePoly();
		
		Pyramids.get(0).rotation+=.01;
		Pyramids.get(0).updatePoly();
*/
		//Set drawing order so closest polygons gets drawn last
		setOrder();
			
		//Set the polygon that the mouse is currently over
		setPolygonOver();
			
		//draw polygons in the Order that is set by the 'setOrder' function
		for(int i = 0; i < NewOrder.length; i++)
			DPolygons.get(NewOrder[i]).DrawablePolygon.drawPolygon(g);
			
		//draw the cross in the centre of the screen
		drawMouseAim(g);			
		
		//FPS display
		g.drawString("J93D : " + (int)drawFPS + " (View Space)", 40, 40);
		if(!userinterface) {
		g.drawString("Press V to see User interface.", 40, 80);
		}else {
			g.drawString("Press V to hide User interface.", 40, 80);
		}
		
		if(userinterface) {
			g.setColor(Color.white);
			g.fillRect(40, 100, 520,400);
			g.setColor(Color.black);
			g.setFont(new Font("Consolas",Font.BOLD,20));
			g.drawString("User Interfaces", 200, 120);
			//keys
			g.setFont(new Font("Arial Rounded MT",Font.BOLD,15));
			g.drawString("keys", 50, 140);
			ImageIcon wkey=new ImageIcon("res/wkey.png");
			ImageIcon akey=new ImageIcon("res/A key.png");
			ImageIcon skey=new ImageIcon("res/s key.png");
			ImageIcon dkey=new ImageIcon("res/D key.png");
			ImageIcon vkey=new ImageIcon("res/v key.png");
			ImageIcon mouse=new ImageIcon("res/mouse.png");
			
			//W 
			g.drawImage(wkey.getImage(), 80, 150, 30,30,null);
			g.setFont(new Font("Consolas",Font.BOLD,13));
			g.drawString("W key - Zoom greater per press.", 80, 170);
			
			//S
			g.drawImage(skey.getImage(), 80, 190, 30,30,null);
			g.setFont(new Font("Consolas",Font.BOLD,13));
			g.drawString("S key - Zoom smaller per press.", 80, 210);
			
			//A
			g.drawImage(akey.getImage(), 80, 230, 30,30,null);
			g.setFont(new Font("Consolas",Font.BOLD,13));
			g.drawString("A key - Look Horizintally (left-size) per press.", 80, 250);
			
			//D
			g.drawImage(dkey.getImage(), 80, 270, 30,30,null);
			g.setFont(new Font("Consolas",Font.BOLD,13));
			g.drawString("D key - Look Horizintally (right-size) per press.", 80, 290);
			
			//V
			g.drawImage(vkey.getImage(), 80, 310, 30,30,null);
			g.setFont(new Font("Consolas",Font.BOLD,13));
			g.drawString("V key - User Interface.", 80, 330);
			
			//Mouse
			g.setFont(new Font("Arial Rounded MT",Font.BOLD,15));
			g.drawString("Mouse", 50, 360);
			
			//Mouse motions
			g.drawImage(mouse.getImage(), 80, 380, 30,30,null);
			g.setFont(new Font("Consolas",Font.BOLD,13));
			g.drawString("Mouse Motion(all directions) - Aims camera's sight to all direction",80, 400);
			g.drawString("of zoom. Super, press immediately with keys.", 80, 415);
			g.drawString("Right-click + Object on screen : See throught that obj.", 80, 430);
			g.drawString("Left-click + Object on screen : If that obj is see throunght, redraw ", 80, 445);
			g.drawString("to original.", 80, 460);	
			g.drawString("Mouse Wheels - Zoom(-/+).",80, 475);
			
		}
		if(newCube) {
			Cubes.add(new Cube(new3DlocationX, new3DlocationY, new3DZ, 2, 2, 2, Color.red));
			
			cubeRelease=true;
		}
			g.setColor(Color.white);
			g.drawString(""+newCube+"/"+new3DlocationX+"/"+new3DlocationY, 20, 100);
		if(Keys[0] && Keys[1]) {
			count++;
		}
		g.drawString(""+count, 40, 100);
//		repaintTime = System.currentTimeMillis() - repaintTime; 
//		System.out.println(repaintTime);
		SleepAndRefresh();
	}
	
	protected void new3D() {
		// TODO Auto-generated method stub
		if(type3D.equalsIgnoreCase("Cube")) {
			
		}
	}
	void setOrder()
	{
		double[] k = new double[DPolygons.size()];
		NewOrder = new int[DPolygons.size()];
		
		for(int i=0; i<DPolygons.size(); i++)
		{
			k[i] = DPolygons.get(i).AvgDist;
			NewOrder[i] = i;
		}
		
	    double temp;
	    int tempr;	    
		for (int a = 0; a < k.length-1; a++)
			for (int b = 0; b < k.length-1; b++)
				if(k[b] < k[b + 1])
				{
					temp = k[b];
					tempr = NewOrder[b];
					NewOrder[b] = NewOrder[b + 1];
					k[b] = k[b + 1];
					   
					NewOrder[b + 1] = tempr;
					k[b + 1] = temp;
				}
	}
		
	void invisibleMouse(boolean ins)
	{
		if(ins) {
			 Toolkit toolkit = Toolkit.getDefaultToolkit();
			 BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT); 
			 Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, new Point(0,0), "InvisibleCursor");        
			 setCursor(invisibleCursor);
		}else {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	void drawMouseAim(Graphics g)
	{
		g.setColor(Color.black);
		g.drawLine((int)(J3DFrame.ScreenSize.getWidth()/2 - aimSight), (int)(J3DFrame.ScreenSize.getHeight()/2), (int)(J3DFrame.ScreenSize.getWidth()/2 + aimSight), (int)(J3DFrame.ScreenSize.getHeight()/2));
		g.drawLine((int)(J3DFrame.ScreenSize.getWidth()/2), (int)(J3DFrame.ScreenSize.getHeight()/2 - aimSight), (int)(J3DFrame.ScreenSize.getWidth()/2), (int)(J3DFrame.ScreenSize.getHeight()/2 + aimSight));			
	}

	void SleepAndRefresh()
	{
		long timeSLU = (long) (System.currentTimeMillis() - LastRefresh); 

		Checks ++;			
		if(Checks >= 15)
		{
			drawFPS = Checks/((System.currentTimeMillis() - LastFPSCheck)/1000.0);
			LastFPSCheck = System.currentTimeMillis();
			Checks = 0;
		}
		
		if(timeSLU < 1000.0/MaxFPS)
		{
			try {
				Thread.sleep((long) (1000.0/MaxFPS - timeSLU));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
				
		LastRefresh = System.currentTimeMillis();
		
		repaint();
	}
	
	void ControlSunAndLight()
	{
		SunPos += 0.005;		
		double mapSize = GenerateTerrain.mapSize * GenerateTerrain.Size;
		LightDir[0] = mapSize/2 - (mapSize/2 + Math.cos(SunPos) * mapSize * 10);
		LightDir[1] = mapSize/2 - (mapSize/2 + Math.sin(SunPos) * mapSize * 10);
		LightDir[2] = -200;
	}
	
	void CameraMovement()
	{
		Vector ViewVector = new Vector(ViewTo[0] - ViewFrom[0], ViewTo[1] - ViewFrom[1], ViewTo[2] - ViewFrom[2]);
		
		double xMove = 0, yMove = 0, zMove = 0;
		Vector VerticalVector = new Vector (0, 0, 1);
		Vector SideViewVector = ViewVector.CrossProduct(VerticalVector);
		
		if(Keys[0])
		{
		//	Cubes.get(0).RunFont();
			xMove += ViewVector.x ;
			yMove += ViewVector.y ;
			zMove += ViewVector.z ;
		}
		
		if(Keys[2])
		{
		//	Cubes.get(0).RunBack();
			xMove -= ViewVector.x ;
			yMove -= ViewVector.y ;
			zMove -= ViewVector.z ;
		}
			
		if(Keys[1])
		{
		//	Cubes.get(0).RunLeft();
			xMove += SideViewVector.x ;
			yMove += SideViewVector.y ;
			zMove += SideViewVector.z ;
		}

		if(Keys[3])
		{
		//	Cubes.get(0).RunRight();
			xMove -= SideViewVector.x ;
			yMove -= SideViewVector.y ;
			zMove -= SideViewVector.z ;
		}
		
		Vector MoveVector = new Vector(xMove, yMove, zMove);
		MoveTo(ViewFrom[0] + MoveVector.x * MovementSpeed, ViewFrom[1] + MoveVector.y * MovementSpeed, ViewFrom[2] + MoveVector.z * MovementSpeed);
	}

	void MoveTo(double x, double y, double z)
	{
		ViewFrom[0] = x;
		ViewFrom[1] = y;
		ViewFrom[2] = z;
		updateView();
	}

	void setPolygonOver()
	{
		PolygonOver = null;
		for(int i = NewOrder.length-1; i >= 0; i--)
			if(DPolygons.get(NewOrder[i]).DrawablePolygon.MouseOver() && DPolygons.get(NewOrder[i]).draw 
					&& DPolygons.get(NewOrder[i]).DrawablePolygon.visible)
			{
				PolygonOver = DPolygons.get(NewOrder[i]).DrawablePolygon;
				break;
			}
	}

	void MouseMovement(double NewMouseX, double NewMouseY)
	{		
			double difX = (NewMouseX - J3DFrame.ScreenSize.getWidth()/2);
			double difY = (NewMouseY - J3DFrame.ScreenSize.getHeight()/2);
			difY *= 6 - Math.abs(VertLook) * 5;
			VertLook -= difY  / VertRotSpeed;
			HorLook += difX / HorRotSpeed;
	
			if(VertLook>0.999)
				VertLook = 0.999;
	
			if(VertLook<-0.999)
				VertLook = -0.999;
			
			updateView();
	}
	
	void updateView()
	{
		double r = Math.sqrt(1 - (VertLook * VertLook));
		ViewTo[0] = ViewFrom[0] + r * Math.cos(HorLook);
		ViewTo[1] = ViewFrom[1] + r * Math.sin(HorLook);		
		ViewTo[2] = ViewFrom[2] + VertLook;
	}
	
	void CenterMouse() 
	{
			try {
				r = new Robot();
				r.mouseMove((int)J3DFrame.ScreenSize.getWidth()/2, (int)J3DFrame.ScreenSize.getHeight()/2);
			} catch (AWTException e) {
				e.printStackTrace();
			}
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
			Keys[0] = true;
		if(e.getKeyCode() == KeyEvent.VK_A)
			Keys[1] = true;
		if(e.getKeyCode() == KeyEvent.VK_S)
			Keys[2] = true;
		if(e.getKeyCode() == KeyEvent.VK_D)
			Keys[3] = true;
		if(e.getKeyCode() == KeyEvent.VK_O)
			OutLines = !OutLines;
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(JOptionPane.showConfirmDialog(null, "Exit Project?","Notification",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		
		}
		if(e.getKeyCode()==KeyEvent.VK_V) {
			if(!userinterface) {
			userinterface=true;
			}else {
				userinterface=false ;
			}
		}
		
		
		
		if(e.getKeyCode()==KeyEvent.VK_C)
			newCube=true;
	}

	void updateFocus(boolean focus) {
		
		if(focus) {
			invisibleMouse(true);
			this.addMouseMotionListener(this);
		}else {
			invisibleMouse(false);
			this.removeMouseMotionListener(this);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
			Keys[0] = false;
		if(e.getKeyCode() == KeyEvent.VK_A)
			Keys[1] = false;
		if(e.getKeyCode() == KeyEvent.VK_S)
			Keys[2] = false;
		if(e.getKeyCode() == KeyEvent.VK_D)
			Keys[3] = false;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void mouseDragged(MouseEvent arg0) {
		MouseMovement(arg0.getX(), arg0.getY());
		MouseX = arg0.getX();
		MouseY = arg0.getY();
		CenterMouse();
	}
	
	public void mouseMoved(MouseEvent arg0) {
		MouseMovement(arg0.getX(), arg0.getY());
		MouseX = arg0.getX();
		MouseY = arg0.getY();
		CenterMouse();
		
		
			new3DlocationX=arg0.getX();
			new3DlocationY=arg0.getY();
		
			setPolygonOver();
			
		
		
	}
	
	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON1)
			if(PolygonOver != null)
				PolygonOver.seeThrough = false;

		if(arg0.getButton() == MouseEvent.BUTTON3)
			if(PolygonOver != null)
				PolygonOver.seeThrough = true;
		
		
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public void mouseWheelMoved(MouseWheelEvent arg0) {
		if(arg0.getUnitsToScroll()>0)
		{
			if(zoom > MinZoom)
				zoom -= 25 * arg0.getUnitsToScroll();
		}
		else
		{
			if(zoom < MaxZoom)
				zoom -= 25 * arg0.getUnitsToScroll();
		}	
	}
}
