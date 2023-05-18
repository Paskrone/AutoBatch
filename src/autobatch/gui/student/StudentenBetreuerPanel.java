package autobatch.gui.student;

import java.awt.Dimension;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import autobatch.businesslogic.listselectionlistener.StudentAuswahlSelectionListener;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StudentenBetreuerPanel extends JPanel {

	private PanelManager panelManager;
	
	private PanelSwitcher panelSwitcher;
	private Student student;

	public StudentenBetreuerPanel(PanelSwitcher panelSwitcher, PanelManager panelManager, Student student) {
		this.panelManager = panelManager;
		
		this.panelSwitcher = panelSwitcher;
        this.student = student;
        
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        
        // Erstelle eine neue Tabelle
        String[] columnNames = {"Nachname", "Email"};
        Datenbankabfrage dbQuery = new Datenbankabfrage();
        List<Betreuer> betreuerList = dbQuery.getAllBetreuer();
        Object[][] data = new Object[betreuerList.size()][2];
        for (int i = 0; i < betreuerList.size(); i++) {
            data[i][0] = betreuerList.get(i).getNachname();
            data[i][1] = betreuerList.get(i).getEmail();
        }
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Alle Zellen sind nicht editierbar
            }
        };
        
        JTable table = new JTable(model);
        
        table.getSelectionModel().addListSelectionListener(new StudentAuswahlSelectionListener(panelSwitcher, panelManager, table));
        
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelSwitcher, student);
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        		.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 984, GroupLayout.PREFERRED_SIZE)
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(74, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
	}
}
