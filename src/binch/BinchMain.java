package binch;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class BinchMain extends JFrame{
	Binchview binch;
	public BinchMain() {
		binch=new Binchview();
		
		JTabbedPane pane =new JTabbedPane();
		pane.add("물건 관리", binch);
		pane.setSelectedIndex(0);
		add("Center",pane);
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	public static void main(String[] args) {
		new BinchMain();
	}
	

}
