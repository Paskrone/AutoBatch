package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Betreuer;
import autobatch.gui.betreuer.BetreuerNotePanel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

/**
 * Ein ActionListener, der dazu dient, den Panel zum Ändern von Noten aufzurufen.
 */
public class ChangeNoteActionListener implements ActionListener {

	private PanelManager panelManager;
	private PanelSwitcher panelSwitcher;

	private Betreuer betreuer;
	private Arbeit arbeit;

	/**
	 * Konstruktor für den ActionListener
	 * @param panelManager Der PanelManager, der zur Verwaltung der Panels verwendet wird
	 * @param panelSwitcher Der PanelSwitcher, der zum Wechseln zwischen Panels verwendet wird
	 * @param betreuer Der betroffene Betreuer
	 * @param arbeit Die betroffene Arbeit
	 */
	public ChangeNoteActionListener(PanelManager panelManager, PanelSwitcher panelSwitcher, Betreuer betreuer,
			Arbeit arbeit) {
		super();
		this.panelManager = panelManager;
		this.panelSwitcher = panelSwitcher;
		this.betreuer = betreuer;
		this.arbeit = arbeit;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn eine Aktion ausgeführt wird.
	 * @param e Das ausgelöste ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Erstellt ein neues BetreuerNotePanel und aktualisiert es im PanelManager
		JPanel betreuerNotePanel = new BetreuerNotePanel(panelManager, panelSwitcher, betreuer, arbeit);
		panelManager.updatePanels(betreuerNotePanel, "Betreuer_Note");

		// Wechselt zum BetreuerNotePanel
		panelSwitcher.switchToPanel("Betreuer_Note");
	}

}
