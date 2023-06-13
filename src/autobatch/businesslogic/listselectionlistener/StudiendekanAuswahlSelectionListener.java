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
     * panel: Eine Zeichenkette, die den Namen des Panels darstellt, auf das umgeschaltet werden soll.
     */
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
     * Diese Methode wird ausgeführt, wenn eine Auswahl in der Tabelle getroffen wird. Sie liest die Daten aus 
     * der ausgewählten Zeile aus, speichert diese Daten und wechselt dann zu einem neuen Panel, das auf der 
     * Auswahl basiert. Wenn das Panel "studiendekanIpAnfragen" ist, wird ein neues Panel dieses Typs erstellt 
     * und angezeigt.
     * @param e: Das ListSelectionEvent, das die Änderung der Auswahl in der Tabelle repräsentiert.
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
