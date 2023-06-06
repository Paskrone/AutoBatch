package autobatch.gui.studiendekan;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import autobatch.businesslogic.listselectionlistener.BetreuerAuswahlSelectionListener;
import autobatch.businesslogic.listselectionlistener.StudiendekanAuswahlSelectionListener;
import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.IPAnfragen;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Studiendekan;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.betreuer.BetreuerNavigationBar;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

public class StudiendekanIpPanel extends JPanel {
	
	private PanelSwitcher panelSwitcher;
	private Studiendekan studiendekan;
	private PanelManager panelManager;
	private JTable table;
	private Betreuer betreuer;
	
	public StudiendekanIpPanel(PanelSwitcher panelSwitcher,PanelManager panelmanager, Studiendekan studiendekan) {

		this.panelSwitcher = panelSwitcher;
		this.studiendekan = studiendekan;
		this.panelManager=panelmanager;
		StudiendekanNavigationBar studiendekanNavBar=new StudiendekanNavigationBar(panelSwitcher);
		
		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		String[] columnNames = { "Nachname", "Email", "Matrikelnr.", "idThema" };
		Datenbankabfrage dbQuery = new Datenbankabfrage();

		List<IPAnfragen> a = dbQuery.getAllIpAnfragen();
		List<IPAnfragen> arbeiten = new ArrayList<>();

		List<Student> studenten = new ArrayList<>();

		for (IPAnfragen anfrage : a) {
			
			if (!anfrage.getAngenommen() && anfrage.getBetreuerMail() != null) {
				System.out.println("klappt");
				studenten.add(dbQuery.getStudentByMNR(anfrage.getStudentMNR()));
				arbeiten.add(anfrage);
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
				new StudiendekanAuswahlSelectionListener(panelSwitcher, panelManager, table, "studiendekanIpAnfragen"));

		JScrollPane scrollPane = new JScrollPane(table);

		table.setFillsViewportHeight(true);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(studiendekanNavBar, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 976, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(studiendekanNavBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(212, Short.MAX_VALUE)));
		setLayout(groupLayout);
			
	}

}
