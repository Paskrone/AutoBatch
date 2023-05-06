package autobatch.gui.studiendekan;

import javax.swing.JPanel;

import autobatch.businessobjects.Studiendekan;
import autobatch.navigation.PanelSwitcher;
import javax.swing.JLabel;

public class StudiendekanPanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Studiendekan studiendekan;
	
	public StudiendekanPanel(PanelSwitcher panelSwitcher, Studiendekan studiendekan) {
		
		this.panelSwitcher = panelSwitcher;
		this.studiendekan = studiendekan;
		
		JLabel lblNewLabel = new JLabel("Test");
		add(lblNewLabel);
		
	}

}
