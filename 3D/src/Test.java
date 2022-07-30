import java.awt.Color;

import com.J93D.J3DFrame;
import com.J93D.J3DScreen;

public class Test {

	//get 3D frame
	static J3DFrame frame;
	
	//get 3D Screen
	static J3DScreen screen;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame=new J3DFrame();
		screen=new J3DScreen();
		//this is a specify floor to seen other 3D clearly
		
		
		//in the project press key v to see how to use this
		//floor
		screen.GenerateSimpleTerrian(50, new Color(100,120,89));
		//cube
		screen.addCube(10, 10, 0, 2, 2, 2, Color.green);
		//
		frame.add(screen);
	}

}
