package autobatch.businesslogic.tablemodellistener;

import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Studiendekan;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.studiendekan.StudiendekanNotenPanel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

/**
 * Diese Klasse implementiert TableModelListener und definiert das Verhalten, wenn Änderungen in einem
 * Datenmodell einer Tabelle vorgenommen werden, speziell für Notenupdates.
 */
public class NotenTableModelListener implements TableModelListener {


    /**
     * Das DefaultTableModel, das überwacht wird.
     */
    private DefaultTableModel model;

    /**
     * Der PanelManager, der für die Aktualisierung des Panels verwendet wird.
     */
    private PanelManager panelmanager;

    /**
     * Der PanelSwitcher, der für den Panelwechsel verwendet wird.
     */
    private PanelSwitcher panelSwitcher;

    /**
     * Der Studiendekan, dessen Notenpanel aktualisiert wird.
     */
    private Studiendekan studiendekan;

    /**
     * Konstruktor
     * @param model Das DefaultTableModel, das überwacht wird
     * @param panelmanager Der PanelManager, der für die Aktualisierung des Panels verwendet wird
     * @param panelSwitcher Der PanelSwitcher, der für den Panelwechsel verwendet wird
     * @param studiendekan Der Studiendekan, dessen Notenpanel aktualisiert wird
     */
	public NotenTableModelListener(DefaultTableModel model, PanelManager panelmanager, PanelSwitcher panelSwitcher,
			Studiendekan studiendekan) {
		super();
		this.model = model;
		this.panelmanager = panelmanager;
		this.panelSwitcher = panelSwitcher;
		this.studiendekan = studiendekan;
	}

	/**
     * Diese Methode wird aufgerufen, wenn eine Änderung in dem Datenmodell der Tabelle auftritt.
     * Sie prüft speziell auf Updates und verarbeitet diese.
     */
	@Override
	public void tableChanged(TableModelEvent e) {
		if (e.getType() == TableModelEvent.UPDATE) {

			int column = e.getColumn();
			int lastColumnIndex = model.getColumnCount() - 1;

			// Prüft, ob die Aktualisierung für eine spezifische Spalte oder für alle Spalten gilt.
			if (column != TableModelEvent.ALL_COLUMNS) {
				int row = e.getFirstRow();
				Object value = model.getValueAt(row, column);
				String s = value.toString();
				float noteVortrag = Float.parseFloat(s);

				Object lastRowColumnValue = model.getValueAt(row, lastColumnIndex);
				int idArbeit = Integer.parseInt(lastRowColumnValue.toString());

				// Holen die Arbeit aus der Datenbank, aktualisieren sie und speichern sie dann erneut in der Datenbank.
				Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
				Arbeit arbeit = datenbankabfrage.getArbeitByID(idArbeit);
				arbeit.setNoteVortrag(noteVortrag);
				datenbankabfrage.updateDataArbeitFloat(arbeit, noteVortrag, "noteVortrag");

				// Aktualisieren das Panel, um die Änderungen widerzuspiegeln.
				JPanel panel = new StudiendekanNotenPanel(panelmanager, panelSwitcher, studiendekan);
				panelmanager.updatePanels(panel, "StudiendekanNotenPanel");
				panelSwitcher.switchToPanel("StudiendekanNotenPanel");
			}
		}
	}
}
