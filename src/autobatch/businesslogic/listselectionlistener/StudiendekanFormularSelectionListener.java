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
    public StudiendekanFormularSelectionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTable table) {
        this.panelSwitcher = panelSwitcher;
        this.panelManager = panelManager;
        this.table = table;
    }

    /**
     * Diese Methode wird ausgeführt, wenn eine Auswahl in der Tabelle getroffen wird.
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
