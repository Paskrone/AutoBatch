package autobatch.businesslogic.listselectionlistener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import autobatch.businessobjects.Studiendekan;
import autobatch.gui.studiendekan.StudiendekanFormular_1Panel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import autobatch.session.SessionManager;

public class StudiendekanFormularSelectionListener implements ListSelectionListener {

	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;

	private JTable table;
	
	

	public StudiendekanFormularSelectionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTable table) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.panelManager = panelManager;
		this.table = table;
	}



	@Override
	public void valueChanged(ListSelectionEvent e) {

		if (!e.getValueIsAdjusting()) { // Überprüfen Sie, ob die Selektion vollständig ist
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) { // Überprüfen Sie, ob eine Zeile ausgewählt wurde
				// Holen Sie sich die Daten aus der ausgewählten Zeile

				Object idArbeit = table.getValueAt(selectedRow, 2);

				panelSwitcher.storeData("3", idArbeit);
				
				JPanel panel = new StudiendekanFormular_1Panel(panelManager, panelSwitcher, (Studiendekan) SessionManager.getInstance().getAktuellerBenutzer());

				panelManager.updatePanels(panel, "StudiendekanFormular_1Panel");

				panelSwitcher.switchToPanel("StudiendekanFormular_1Panel");
			}

		}
	}
}
