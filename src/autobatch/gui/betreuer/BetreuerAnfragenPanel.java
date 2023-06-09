package autobatch.gui.betreuer;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import autobatch.businesslogic.listselectionlistener.BetreuerAuswahlSelectionListener;
import autobatch.businesslogic.listselectionlistener.StudentAuswahlSelectionListener;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

public class BetreuerAnfragenPanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Betreuer betreuer;
	private JTable table;

	private PanelManager panelManager;

	public BetreuerAnfragenPanel(PanelSwitcher panelSwitcher, PanelManager panelManager, Betreuer betreuer) {

		this.betreuer = betreuer;
		this.panelSwitcher = panelSwitcher;
		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		BetreuerNavigationBar betreuerNavigationBar = new BetreuerNavigationBar(panelManager,panelSwitcher,betreuer);

		// Erstelle eine neue Tabelle
		String[] columnNames = { "Nachname", "Email", "Matrikelnr.", "idThema" };
		Datenbankabfrage dbQuery = new Datenbankabfrage();

		List<Arbeit> a = dbQuery.getAllArbeiten();
		List<Arbeit> arbeiten = new ArrayList<>();

		List<Student> studenten = new ArrayList<>();

		for (Arbeit arbeit : a) {
			if (!arbeit.getAngenommen() && arbeit.getBetreuerMail() != null
					&& arbeit.getBetreuerMail().equals(betreuer.getEmail())) {
				System.out.println("klappt");
				studenten.add(dbQuery.getStudentByMNR(arbeit.getStudentMNR()));
				arbeiten.add(arbeit);
			}
		}
		Object[][] data = new Object[studenten.size()][4];
		for (int i = 0; i < studenten.size(); i++) {
			data[i][0] = studenten.get(i).getNachname();
			data[i][1] = studenten.get(i).getEmail();
			data[i][2] = studenten.get(i).getMnr();
			data[i][3] = arbeiten.get(i).getIdArbeit();

		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				// Alle Zellen sind nicht editierbar
			}
		};

		JTable table = new JTable(model);

		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column = columnModel.getColumn(3);
		column.setMinWidth(0);
		column.setMaxWidth(0);
		column.setPreferredWidth(0);

		table.getSelectionModel().addListSelectionListener(
				new BetreuerAuswahlSelectionListener(panelSwitcher, panelManager, table, "Betreuer_Anfragen_1"));

		JScrollPane scrollPane = new JScrollPane(table);

		table.setFillsViewportHeight(true);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 976, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(212, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}
}