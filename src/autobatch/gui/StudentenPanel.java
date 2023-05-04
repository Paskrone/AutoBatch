package autobatch.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StudentenPanel extends JPanel {

	Main main;
	
    public StudentenPanel(Main main) {
    	this.main = main;
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));
    }
}