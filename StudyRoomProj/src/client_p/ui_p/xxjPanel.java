package client_p.ui_p;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class xxjPanel extends JPanel {
	ArrayList<JPanel> jPanelArrl = new ArrayList<JPanel>();
	
	@Override
	public void setVisible(boolean aFlag) {
		
		setLayout(new GridLayout(2, jPanelArrl.size()/2+2));
		super.setVisible(aFlag);
		
	}
	void addToXX(JPanel jp) {
		jPanelArrl.add(jp);
		add(jp);
	}

}
