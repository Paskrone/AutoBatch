package autobatch.gui.student;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import autobatch.businesslogic.actionlistener.*;
import autobatch.businesslogic.mouselistener.downloadFileMouseListener;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

/**
 * Eine Klasse, die ein JPanel repräsentiert, in dem Studenten ihre Einreichungen anzeigen und neue Einreichungen hinzufügen können.
 * Es zeigt auch die Einreichungen an, die von ihrem Betreuer gemacht wurden.
 */
public class StudentenAbgabenPanel extends JPanel {

	private PanelManager panelManager;  // Verwaltet die Navigation zwischen Panels
    private PanelSwitcher panelSwitcher;  // Hilft beim Wechseln zwischen verschiedenen Panels
    private Student student;  // Der aktuelle Student, der die Einreichungen vornimmt
    private JButton downloadButton;  // Ein Button zum Herunterladen von Einreichungen
    private DefaultListModel<String> listModel;  // Ein Modell für die Liste der Einreichungen des Studenten
    private JList<String> fileList;  // Eine Liste der Einreichungen des Studenten
    private DefaultListModel<String> listModel_1;  // Ein Modell für die Liste der Einreichungen des Betreuers
    private JList<String> fileList_1;  // Eine Liste der Einreichungen des Betreuers
    private Datenbankabfrage dbaccess = new Datenbankabfrage();  // Eine Instanz zur Durchführung von Datenbankabfragen

    /**
     * Erstellt ein neues Panel, in dem Studenten ihre Einreichungen anzeigen und neue Einreichungen hinzufügen können.
     * @param panelManager Der Manager, der die Navigation zwischen den Panels verwaltet.
     * @param panelSwitcher Ein Helfer zum Wechseln zwischen verschiedenen Panels.
     * @param student Der aktuelle Student, der die Einreichungen vornimmt.
     */
    public StudentenAbgabenPanel(PanelManager panelManager, PanelSwitcher panelSwitcher, Student student) {
    	this.panelManager = panelManager;
        this.panelSwitcher = panelSwitcher;
        this.student = student;

        setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelManager, panelSwitcher, student);

        JLabel lblBisherigeAbagben = new JLabel("Bisherige Abgaben:");

        JLabel lblBetreuer = new JLabel("Betreuer:");

        JButton btnAbgabeHinzufuegen = new JButton("Abgabe hinzufügen");
        
        JLabel lbl_success = new JLabel("Dokument wurde erfolgreich hochgeladen!");
        lbl_success.setVisible(false);
        lbl_success.setForeground(new Color(86, 251, 18));
        JLabel lbl_error = new JLabel("Sie müssen zuerst einen Betreuer anfragen, um Dokumente hochladen zu können!");
        lbl_error.setVisible(false);
        lbl_error.setForeground(new Color(247, 19, 25));
        btnAbgabeHinzufuegen.addActionListener(new AbgabeHinzufuegenActionListener(lbl_success, lbl_error));

        listModel = new DefaultListModel<>();
        ArrayList<String> submissions = (ArrayList<String>) dbaccess.getSubmissions(student.getBenutzername());
        for(String submission : submissions) {
            listModel.addElement(submission);
        }
        
        
        
        fileList = new JList<>(listModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane fileListScrollPane = new JScrollPane(fileList);
        fileList.addMouseListener(new downloadFileMouseListener() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) { // Double-click
                    int index = list.locationToIndex(evt.getPoint());
                    String filename = listModel.getElementAt(index);
                    downloadFile(filename);
                }
            }
        });
        
        listModel_1 = new DefaultListModel<>();
        if (student.getBetreuer()!=null) {
			        ArrayList<String> submissions_1 = (ArrayList<String>) dbaccess.getSubmissionsFromBetreuerWithStudent(dbaccess.getBetreuerByMail(student.getBetreuer()).getBenutzername(), student.getBenutzername());
					for (String submission : submissions_1) {
						listModel_1.addElement(submission);
					}
		}
      
        
        JList<String> fileList_1 = new JList<String>(listModel_1);
        fileList_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane fileListScrollPane_1 = new JScrollPane(fileList_1);
        fileList_1.addMouseListener(new downloadFileMouseListener() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) { // Double-click
                    int index = list.locationToIndex(evt.getPoint());
                    String filename = listModel.getElementAt(index);
                    downloadFile(filename);
                }
            }
        });
        
        
        
        
    
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(23)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblBisherigeAbagben)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addComponent(fileListScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addGap(32)
        							.addComponent(lbl_error))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addComponent(btnAbgabeHinzufuegen)
        							.addGap(18)
        							.addComponent(lbl_success))
        						.addComponent(lblBetreuer)
        						.addComponent(fileListScrollPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        	        					)))
        			.addGap(6))
        ));
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(18)
        					.addComponent(lblBisherigeAbagben)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(fileListScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED))
        				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(lbl_error)
        					.addGap(73)))
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnAbgabeHinzufuegen)
        				.addComponent(lbl_success))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblBetreuer)
        			.addGap(18)
        			.addComponent(fileListScrollPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(49, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            				)
        ));
        setLayout(groupLayout);

	}
}
