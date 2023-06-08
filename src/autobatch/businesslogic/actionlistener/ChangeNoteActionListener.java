package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Betreuer;
import autobatch.gui.betreuer.BetreuerNotePanel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

public class ChangeNoteActionListener implements ActionListener {

	private PanelManager panelManager;
	private PanelSwitcher panelSwitcher;

	private Betreuer betreuer;
	private Arbeit arbeit;

	public ChangeNoteActionListener(PanelManager panelManager, PanelSwitcher panelSwitcher, Betreuer betreuer,
			Arbeit arbeit) {
		super();
		this.panelManager = panelManager;
		this.panelSwitcher = panelSwitcher;
		this.betreuer = betreuer;
		this.arbeit = arbeit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel betreuerNotePanel = new BetreuerNotePanel(panelManager, panelSwitcher, betreuer, arbeit);
		panelManager.updatePanels(betreuerNotePanel, "Betreuer_Note");
		panelSwitcher.switchToPanel("Betreuer_Note");
	}

}
