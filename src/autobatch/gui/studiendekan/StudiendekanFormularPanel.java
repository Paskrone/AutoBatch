package autobatch.gui.studiendekan;

import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import autobatch.businesslogic.listselectionlistener.StudiendekanFormularSelectionListener;
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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;


/**
 * Das StudiendekanFormularPanel ist ein JPanel, das eine Liste von Arbeiten anzeigt, für die der Studiendekan Ausgabetermin und Abgabetermin festlegen kann.
 */
public class StudiendekanFormularPanel extends JPanel {

    /**
     * Die Tabelle, die die Liste der Arbeiten anzeigt.
     */
    private JTable table;

    /**
     * Das DefaultTableModel, das die Daten für die Tabelle enthält.
     */
    private DefaultTableModel model;

    /**
     * Erstellt ein neues StudiendekanFormularPanel.
     *
     * @param panelmanager Der PanelManager, der für die Navigation zwischen den Panels zuständig ist.
     * @param panelSwitcher Der PanelSwitcher, der für das Umschalten zwischen den Panels verantwortlich ist.
     * @param studiendekan Der Studiendekan, für den das Formular angezeigt wird.
     */
	public StudiendekanFormularPanel(PanelManager panelmanager, PanelSwitcher panelSwitcher,
			Studiendekan studiendekan) {

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudiendekanNavigationBar studiendekanNavBar = new StudiendekanNavigationBar(panelmanager, panelSwitcher,
				studiendekan);

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

		List<Arbeit> a = datenbankabfrage.getAllArbeiten();
		List<Arbeit> arbeiten = new ArrayList<>();
		List<Student> studenten = new ArrayList<>();

		for (Arbeit arbeit : a) {
			if (arbeit.getStudiendekanMail() != null && arbeit.getStudiendekanMail().equals(studiendekan.getEmail())
					&& arbeit.getBa_Anmeldung_Betreuer() && !arbeit.getBa_Anmeldung_Studiendekan()) {
				studenten.add(datenbankabfrage.getStudentByMNR(arbeit.getStudentMNR()));
				arbeiten.add(arbeit);
			}
		}

		String[] columnNames = { "Name", "Matrikelnummer", "idArbeit" };

		Object[][] data = new Object[studenten.size()][3];

		for (int i = 0; i < studenten.size(); i++) {
			data[i][0] = studenten.get(i).getVorname() + " " + studenten.get(i).getNachname();
			data[i][1] = studenten.get(i).getMnr();
			data[i][2] = arbeiten.get(i).getIdArbeit();
		}

		model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);

		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column = columnModel.getColumn(2);
		column.setMinWidth(0);
		column.setMaxWidth(0);
		column.setPreferredWidth(0);

		table.getSelectionModel().addListSelectionListener(
				new StudiendekanFormularSelectionListener(panelSwitcher, panelmanager, table));

		JScrollPane scrollPane = new JScrollPane(table);

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
