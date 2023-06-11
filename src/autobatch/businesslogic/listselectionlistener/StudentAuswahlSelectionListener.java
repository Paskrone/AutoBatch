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

/**
 * Listener zur Verarbeitung von Ereignissen bei der Auswahl von Studenten in einer Tabelle.
 */
public class StudentAuswahlSelectionListener implements ListSelectionListener {

    // Instanzvariablen
    private PanelSwitcher panelSwitcher;
    private PanelManager panelManager;
    private JTable table;

    /**
     * Konstruktor
     * @param panelSwitcher Dienst zum Wechseln zwischen Panels
     * @param panelManager Dienst zum Verwalten von Panels
     * @param table Die Tabelle, die die Auswahl enthält
     */
    public StudentAuswahlSelectionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTable table) {
        this.panelManager = panelManager;
        this.panelSwitcher = panelSwitcher;
        this.table = table;
    }

    /**
     * Diese Methode wird ausgeführt, wenn eine Auswahl in der Tabelle getroffen wird.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Überprüfen Sie, ob die Auswahl vollständig ist
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // Überprüfen Sie, ob eine Zeile ausgewählt wurde
                // Holen Sie sich die Daten aus der ausgewählten Zeile
                Object mail = table.getValueAt(selectedRow, 1);

                panelSwitcher.storeData("1", mail);

                // Erzeugen Sie ein neues Panel und schalten Sie darauf um
                JPanel studentenBetreuerAnfragePanel = new StudentenBetreuerAnfragePanel(panelManager, panelSwitcher,
                        (Student) SessionManager.getInstance().getAktuellerBenutzer());

                panelManager.updatePanels(studentenBetreuerAnfragePanel, "Studenten_Betreuer_Anfrage");

                panelSwitcher.switchToPanel("Studenten_Betreuer_Anfrage");

            }
        }
    }

}
