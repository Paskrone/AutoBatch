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

public class NotenTableModelListener implements TableModelListener {

	private DefaultTableModel model;
	
	private PanelManager panelmanager;
	private PanelSwitcher panelSwitcher;
	
	private Studiendekan studiendekan;
	
	
	
	public NotenTableModelListener(DefaultTableModel model, PanelManager panelmanager, PanelSwitcher panelSwitcher,
			Studiendekan studiendekan) {
		super();
		this.model = model;
		this.panelmanager = panelmanager;
		this.panelSwitcher = panelSwitcher;
		this.studiendekan = studiendekan;
	}



	@Override
	public void tableChanged(TableModelEvent e) {
		if (e.getType() == TableModelEvent.UPDATE) {

			int column = e.getColumn();
			int lastColumnIndex = model.getColumnCount() - 1;

			if (column != TableModelEvent.ALL_COLUMNS) {
				int row = e.getFirstRow();
				Object value = model.getValueAt(row, column);
				String s = value.toString();
				float noteVortrag = Float.parseFloat(s);
				System.out.println("Neuer Wert in Spalte " + column + ": " + noteVortrag);

				Object lastRowColumnValue = model.getValueAt(row, lastColumnIndex);
				System.out.println("Neuer Wert in Spalte " + lastColumnIndex + ": " + lastRowColumnValue);

				s = lastRowColumnValue.toString();
				int idArbeit = Integer.parseInt(s);

				Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
				
				Arbeit arbeit = datenbankabfrage.getArbeitByID(idArbeit);

				arbeit.setNoteVortrag(noteVortrag);

				datenbankabfrage.updateDataArbeitFloat(arbeit, noteVortrag, "noteVortrag");

				JPanel panel = new StudiendekanNotenPanel(panelmanager, panelSwitcher, studiendekan);
				panelmanager.updatePanels(panel, "StudiendekanNotenPanel");
				panelSwitcher.switchToPanel("StudiendekanNotenPanel");

			}
		}
	}
}
