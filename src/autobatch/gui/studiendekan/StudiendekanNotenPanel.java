package autobatch.gui.studiendekan;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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

		String[] columnNames = { "Matrikelnummer", "Arbeit", "Vortrag", "Gesamt", "idArbeit" };

		Object[][] data = new Object[studenten.size()][5];
		for (int i = 0; i < studenten.size(); i++) {
			data[i][0] = studenten.get(i).getMnr();
			data[i][1] = arbeiten.get(i).getNoteArbeit();
			System.out.println(arbeiten.get(i).getNoteVortrag());
			if (arbeiten.get(i).getNoteVortrag() != 0) {
				data[i][2] = arbeiten.get(i).getNoteVortrag();
				data[i][3] = arbeiten.get(i).getGesamtnote();
			} else {
				data[i][2] = "";
				data[i][3] = "";
			}
			data[i][4] = arbeiten.get(i).getIdArbeit();

		}

		model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == getColumnCount() - 3; // Nur die letzte Spalte ist bearbeitbar
			}
		};

		JScrollPane scrollPane = new JScrollPane((Component) null);

		table = new JTable(model);

		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column = columnModel.getColumn(4);
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

}
