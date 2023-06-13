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

/**
 * Listener zur Verarbeitung von Ereignissen bei der Auswahl von Formularen in einer Tabelle.
 */
public class StudiendekanFormularSelectionListener implements ListSelectionListener {

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
    public StudiendekanFormularSelectionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTable table) {
        this.panelSwitcher = panelSwitcher;
        this.panelManager = panelManager;
        this.table = table;
    }

    /**
     * Diese Methode wird ausgeführt, wenn eine Auswahl in der Tabelle getroffen wird. Sie liest die Daten aus 
     * der ausgewählten Zeile aus, speichert diese Daten und wechselt dann zu einem neuen Panel "StudiendekanFormular_1Panel". 
     * Dieses Panel wird erzeugt und zur Anzeige gebracht.
     * @param e: Das ListSelectionEvent, das die Änderung der Auswahl in der Tabelle repräsentiert.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Überprüfen Sie, ob die Selektion vollständig ist
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // Überprüfen Sie, ob eine Zeile ausgewählt wurde
                // Holen Sie sich die Daten aus der ausgewählten Zeile
                Object idArbeit = table.getValueAt(selectedRow, 2);

                // Speichern Sie die ausgewählten Daten
                panelSwitcher.storeData("3", idArbeit);

                // Erzeugen Sie ein neues Panel und schalten Sie darauf um
                JPanel panel = new StudiendekanFormular_1Panel(panelManager, panelSwitcher, 
                              (Studiendekan) SessionManager.getInstance().getAktuellerBenutzer());

                panelManager.updatePanels(panel, "StudiendekanFormular_1Panel");

                panelSwitcher.switchToPanel("StudiendekanFormular_1Panel");
            }
        }
    }
}
