import java.awt.Color;

import com.J93D.J3DFrame;
import com.J93D.J3DScreen;

public class Draw3D {
	
	//get 3D frame
	static J3DFrame frame;
	
	//get 3D panel
	static J3DScreen screen;

	public static void main(String[] args) {
		//let test how J3DFrame works
		frame=new J3DFrame();
		frame.setVisible(true);
		
		//add 3d screen
		screen=new J3DScreen();
		
		//now we can draw 3D in this screen
		//i have four 3D objects cube,prisms,pyramid and cylinder.
		//i also have terrians
		//let generate terrians
		screen.GenerateSimpleTerrian(50, Color.darkGray);
		
		//adding Cubes
		screen.addCube(10, 10, 0, 2, 2, 2, Color.green);
		//press v to see user interface
		//adding prisms
		screen.addPrisms(10, 10, 2, 2, 2, 2, Color.pink);
		
		//adding pyramid
		screen.addPyramids(10, 14, 0, 2, 2, 2, Color.blue);
		
		//last object cylinder
		screen.addCylinder(10,20, 0, 2, 4, Color.cyan);
		
		frame.add(screen);
		
	}

}
