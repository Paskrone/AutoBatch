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

/**
 * Listener zur Verarbeitung von Ereignissen bei der Auswahl von Studiendekan in einer Tabelle.
 */
public class StudiendekanAuswahlSelectionListener implements ListSelectionListener {

    // Instanzvariablen
    private PanelSwitcher panelSwitcher;
    private PanelManager panelManager;
    private JTable table;
    private String panel;

    /**
     * Konstruktor
     * @param panelSwitcher Dienst zum Wechseln zwischen Panels
     * @param panelManager Dienst zum Verwalten von Panels
     * @param table Die Tabelle, die die Auswahl enthält
     * @param panel Der Name des Panels, auf das umgeschaltet werden soll
     */
    public StudiendekanAuswahlSelectionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTable table,
                                                String panel) {
        this.panelSwitcher = panelSwitcher;
        this.panelManager = panelManager;
        this.table = table;
        this.panel = panel;
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
                Object mnr = table.getValueAt(selectedRow, 2);
                Object idThema = table.getValueAt(selectedRow, 3);

                // Speichern Sie die ausgewählten Daten
                panelSwitcher.storeData("2", mnr);
                panelSwitcher.storeData("3", idThema);

                // Erzeugen Sie ein neues Panel und schalten Sie darauf um, wenn das gewählte Panel "studiendekanIpAnfragen" ist
                if (panel.equals("studiendekanIpAnfragen")) {
                    JPanel studiendekanIPAnfragen = new StudiendekanIpAnfragenPanel(panelManager, panelSwitcher,
                            (Studiendekan) SessionManager.getInstance().getAktuellerBenutzer());

                    panelManager.updatePanels(studiendekanIPAnfragen, "studiendekanIpAnfragen");

                    panelSwitcher.switchToPanel("studiendekanIpAnfragen");
                }
            }
        }
    }
}
