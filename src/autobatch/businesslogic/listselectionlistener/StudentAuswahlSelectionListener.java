package autobatch.businesslogic.listselectionlistener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Student;
import autobatch.gui.student.StudentenBetreuerAnfragePanel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import autobatch.session.SessionManager;

public class StudentAuswahlSelectionListener implements ListSelectionListener {

	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;

	private JTable table;

	public StudentAuswahlSelectionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTable table) {
		super();
		this.panelManager = panelManager;
		this.panelSwitcher = panelSwitcher;
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) { // Überprüfen Sie, ob die Selektion vollständig ist
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) { // Überprüfen Sie, ob eine Zeile ausgewählt wurde
				// Holen Sie sich die Daten aus der ausgewählten Zeile
				Object mail = table.getValueAt(selectedRow, 1);

				panelSwitcher.storeData("1", mail);

				JPanel studentenBetreuerAnfragePanel = new StudentenBetreuerAnfragePanel(panelManager, panelSwitcher,
						(Student) SessionManager.getInstance().getAktuellerBenutzer());

				panelManager.updatePanels(studentenBetreuerAnfragePanel, "Studenten_Betreuer_Anfrage");

				panelSwitcher.switchToPanel("Studenten_Betreuer_Anfrage");

			}
		}
	}

}
