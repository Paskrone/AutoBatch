package autobatch.gui.studiendekan;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import autobatch.businesslogic.tablemodellistener.NotenTableModelListener;
import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Studiendekan;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;

public class StudiendekanNotenPanel extends JPanel {

	private JTable table;
	private static DefaultTableModel model;

	public StudiendekanNotenPanel(PanelManager panelmanager, PanelSwitcher panelSwitcher, Studiendekan studiendekan) {

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudiendekanNavigationBar studiendekanNavBar = new StudiendekanNavigationBar(panelmanager, panelSwitcher,
				studiendekan);

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

		List<Arbeit> a = datenbankabfrage.getAllArbeiten();
		List<Arbeit> arbeiten = new ArrayList<>();
		List<Student> studenten = new ArrayList<>();

		for (Arbeit arbeit : a) {
			if (arbeit.getNoteArbeit() != 0) {
				studenten.add(datenbankabfrage.getStudentByMNR(arbeit.getStudentMNR()));
				arbeiten.add(arbeit);
			}
		}

		String[] columnNames = { "Name", "Matrikelnummer", "Arbeit", "Vortrag", "Gesamt", "idArbeit" };

		Object[][] data = new Object[studenten.size()][6];
		for (int i = 0; i < studenten.size(); i++) {
			data[i][0] = studenten.get(i).getVorname() + " " + studenten.get(i).getNachname();
			data[i][1] = studenten.get(i).getMnr();
			data[i][2] = arbeiten.get(i).getNoteArbeit();
			System.out.println(arbeiten.get(i).getNoteVortrag());
			if (arbeiten.get(i).getNoteVortrag() != 0) {
				data[i][3] = arbeiten.get(i).getNoteVortrag();
				data[i][4] = arbeiten.get(i).getGesamtnote();
			} else {
				data[i][3] = "";
				data[i][4] = "";
			}
			data[i][5] = arbeiten.get(i).getIdArbeit();

		}

		model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == getColumnCount() - 3; // Nur die letzte Spalte ist bearbeitbar
			}
		};

		JScrollPane scrollPane = new JScrollPane((Component) null);

		table = new JTable(model);

		table.getColumnModel().getColumn(3).setCellRenderer(new CustomCellRenderer());

		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column = columnModel.getColumn(5);
		column.setMinWidth(0);
		column.setMaxWidth(0);
		column.setPreferredWidth(0);

		model.addTableModelListener(new NotenTableModelListener(model, panelmanager, panelSwitcher, studiendekan));

		scrollPane.setViewportView(table);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(studiendekanNavBar, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING,
						groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 976, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(8, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(studiendekanNavBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addGap(212)));

		setLayout(groupLayout);

	}

	
	// Spalte Vortrag LIGHT_GRAY
	private static class CustomCellRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			// Check if the column index matches the desired column to be colored
			int desiredColumnIndex = 3;
			if (column == desiredColumnIndex) {
				// Set the desired background color for the column
				component.setBackground(Color.LIGHT_GRAY);
			} else {
				// Set the default background color for other columns
				component.setBackground(table.getBackground());
			}

			return component;
		}
	}
}
