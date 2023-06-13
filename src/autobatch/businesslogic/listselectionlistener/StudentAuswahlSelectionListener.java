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

    /**
     * panelSwitcher: Ein Dienst zum Wechseln zwischen verschiedenen Panels.
     */
    private PanelSwitcher panelSwitcher;

    /**
     * panelManager: Ein Dienst zum Verwalten der Panels in der Anwendung.
     */
    private PanelManager panelManager;

    /**
     * table: Ein JTable-Objekt, das eine Tabelle in der GUI repräsentiert.
     */
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
     * Diese Methode wird aufgerufen, wenn eine Auswahl in der Tabelle getroffen wird.
     * Zuerst wird überprüft, ob die Auswahl vollständig ist und ob eine Zeile ausgewählt wurde. 
     * Wenn dies der Fall ist, werden die Daten aus der ausgewählten Zeile abgerufen und 
     * gespeichert. Anschließend wird ein neues Panel erstellt, das die Anfrage eines 
     * Studenten an einen Betreuer darstellt, und dieses Panel wird zur Anzeige gebracht.
     * @param e ist das ListSelectionEvent, das die Änderung der Auswahl in der Tabelle repräsentiert.
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
