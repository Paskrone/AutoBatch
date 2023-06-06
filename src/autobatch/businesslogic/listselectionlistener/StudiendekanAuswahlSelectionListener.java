package autobatch.businesslogic.listselectionlistener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Studiendekan;
import autobatch.gui.betreuer.BetreuerAnfragen_1Panel;
import autobatch.gui.betreuer.BetreuerStudenten_1Panel;
import autobatch.gui.studiendekan.StudiendekanIpAnfragenPanel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import autobatch.session.SessionManager;

public class StudiendekanAuswahlSelectionListener implements ListSelectionListener {

	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;

	private JTable table;

	private String panel;

	public StudiendekanAuswahlSelectionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTable table,
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

				if (panel.equals("studiendekanIpAnfragen")) {
					JPanel studiendekanIPAnfragen = new StudiendekanIpAnfragenPanel (panelSwitcher, panelManager,
							(Studiendekan) SessionManager.getInstance().getAktuellerBenutzer());

					panelManager.updatePanels(studiendekanIPAnfragen, "studiendekanIpAnfragen");

					panelSwitcher.switchToPanel("studiendekanIpAnfragen");

				}

			}
		}
	}
}
