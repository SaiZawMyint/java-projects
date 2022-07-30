import java.awt.Color;

import com.J93D.J3DFrame;
import com.J93D.J3DScreen;


public class Home {

	static J3DFrame frame;
	static J3DScreen Screen;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 frame=new J3DFrame();
		 Screen=new J3DScreen();
		 frame.add(Screen);
		 frame.setVisible(true);
		
	}

}
