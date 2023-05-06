package autobatch.gui.betreuer;

import javax.swing.JPanel;

import autobatch.businessobjects.Betreuer;
import autobatch.navigation.PanelSwitcher;

public class BetreuerPanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Betreuer betreuer;
	
	public BetreuerPanel(PanelSwitcher panelSwitcher, Betreuer betreuer) {
		
		this.panelSwitcher = panelSwitcher;
		this.betreuer = betreuer;
		
	}

}
