import java.awt.Color;

import javax.swing.JFrame;

import com.J93D.Cube;
import com.J93D.GenerateSimpleTerrain;
import com.J93D.J3DFrame;
import com.J93D.J3DScreen;


public class Home{
	
	
	public static void main(String args[]) {
		J3DFrame d=new J3DFrame();
		d.setVisible(true);
		J3DScreen s=new J3DScreen();
		s.GenerateSimpleTerrian(110, Color.DARK_GRAY);
		d.add(s);
	}

}
