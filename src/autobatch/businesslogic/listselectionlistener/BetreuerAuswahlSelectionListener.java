package autobatch.businesslogic.listselectionlistener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import autobatch.businessobjects.Betreuer;
import autobatch.gui.betreuer.BetreuerAnfragen_1Panel;
import autobatch.gui.betreuer.BetreuerStudenten_1Panel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import autobatch.session.SessionManager;

public class BetreuerAuswahlSelectionListener implements ListSelectionListener {

	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;

	private JTable table;

	private String panel;

	public BetreuerAuswahlSelectionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTable table,
			String panel) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.panelManager = panelManager;
		this.table = table;

		this.panel = panel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) { // Überprüfen Sie, ob die Selektion vollständig ist
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) { // Überprüfen Sie, ob eine Zeile ausgewählt wurde
				// Holen Sie sich die Daten aus der ausgewählten Zeile
				Object mnr = table.getValueAt(selectedRow, 2);

				Object idThema = table.getValueAt(selectedRow, 3);

				panelSwitcher.storeData("2", mnr);
				panelSwitcher.storeData("3", idThema);

				if (panel.equals("Betreuer_Anfragen_1")) {
					JPanel betreuerAnfragen_1Panel = new BetreuerAnfragen_1Panel(panelSwitcher, panelManager,
							(Betreuer) SessionManager.getInstance().getAktuellerBenutzer());

					panelManager.updatePanels(betreuerAnfragen_1Panel, "Betreuer_Anfragen_1");

					panelSwitcher.switchToPanel("Betreuer_Anfragen_1");

				} else if (panel.equals("Betreuer_Studenten_1")) {
					JPanel betreuerStudenten_1Panel = new BetreuerStudenten_1Panel(panelSwitcher,
							(Betreuer) SessionManager.getInstance().getAktuellerBenutzer());

					panelManager.updatePanels(betreuerStudenten_1Panel, "Betreuer_Studenten_1");

					panelSwitcher.switchToPanel("Betreuer_Studenten_1");
				}

			}
		}
	}
}
