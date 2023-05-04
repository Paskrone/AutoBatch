package autobatch.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.navigation.PanelSwitcher;

public class StudentenPanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	
    public StudentenPanel(PanelSwitcher panelSwitcher) {
    	this.panelSwitcher = panelSwitcher;
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));
    }
}