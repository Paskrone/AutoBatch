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

/**
 * Listener zur Verarbeitung von Ereignissen bei der Auswahl von Betreuern in einer Tabelle.
 */
public class BetreuerAuswahlSelectionListener implements ListSelectionListener {

	/**
	 * Dienst zum Wechseln zwischen Panels.
	 */
	private PanelSwitcher panelSwitcher;

	/**
	 * Dienst zum Verwalten von Panels.
	 */
	private PanelManager panelManager;

	/**
	 * Die Tabelle, die die Auswahl enthält.
	 */
	private JTable table;

	/**
	 * Das Panel, auf dem die Tabelle angezeigt wird.
	 */
	private String panel;

    /**
     * Konstruktor
     * @param panelSwitcher Dienst zum Wechseln zwischen Panels
     * @param panelManager Dienst zum Verwalten von Panels
     * @param table Die Tabelle, die die Auswahl enthält
     * @param panel Das Panel, auf dem die Tabelle angezeigt wird
     */
    public BetreuerAuswahlSelectionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTable table, String panel) {
        this.panelSwitcher = panelSwitcher;
        this.panelManager = panelManager;
        this.table = table;
        this.panel = panel;
    }

    /**
     * Diese Methode wird aufgerufen, wenn eine Auswahl in der Tabelle getroffen wird.
     * Es überprüft zunächst, ob die Auswahl vollständig ist und ob eine Zeile ausgewählt wurde.
     * Dann holt es die Daten aus der ausgewählten Zeile und speichert sie.
     * Abhängig vom ausgewählten Panel erstellt es ein neues Panel und wechselt zu diesem.
     * @param e Das ListSelectionEvent, das die Auswahländerung in der Tabelle darstellt.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Überprüfen Sie, ob die Auswahl vollständig ist
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // Überprüfen Sie, ob eine Zeile ausgewählt wurde
                // Holen Sie sich die Daten aus der ausgewählten Zeile
                Object mnr = table.getValueAt(selectedRow, 2);
                Object idThema = table.getValueAt(selectedRow, 3);

                panelSwitcher.storeData("2", mnr);
                panelSwitcher.storeData("3", idThema);

                // Abhängig vom ausgewählten Panel wird ein neues Panel erstellt und zum Anzeigen geschaltet
                if (panel.equals("Betreuer_Anfragen_1")) {
                    JPanel betreuerAnfragen_1Panel = new BetreuerAnfragen_1Panel(panelSwitcher, panelManager, (Betreuer) SessionManager.getInstance().getAktuellerBenutzer());
                    panelManager.updatePanels(betreuerAnfragen_1Panel, "Betreuer_Anfragen_1");
                    panelSwitcher.switchToPanel("Betreuer_Anfragen_1");

                } else if (panel.equals("Betreuer_Studenten_1")) {
                    JPanel betreuerStudenten_1Panel = new BetreuerStudenten_1Panel(panelManager, panelSwitcher, (Betreuer) SessionManager.getInstance().getAktuellerBenutzer());
                    panelManager.updatePanels(betreuerStudenten_1Panel, "Betreuer_Studenten_1");
                    panelSwitcher.switchToPanel("Betreuer_Studenten_1");
                }

            }
        }
    }
}
