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
import autobatch.navigation.PanelSwitcher;

public class StudentenAbgabenPanel extends JPanel {

    private PanelSwitcher panelSwitcher;
    private Student student;
    private JButton downloadButton;
    private DefaultListModel<String> listModel;
    private JList<String> fileList;
    private Datenbankabfrage dbaccess = new Datenbankabfrage();

    public StudentenAbgabenPanel(PanelSwitcher panelSwitcher, Student student) {
        this.panelSwitcher = panelSwitcher;
        this.student = student;

        setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelSwitcher, student);

        JLabel lblBisherigeAbagben = new JLabel("Bisherige Abgaben:");

        JLabel lblBetreuer = new JLabel("Betreuer:");

        JButton btnAbgabeHinzufuegen = new JButton("Abgabe hinzufügen");
        btnAbgabeHinzufuegen.addActionListener(new AbgabeHinzufuegenActionListener());

        listModel = new DefaultListModel<>();
        ArrayList<String> submissions = (ArrayList<String>) dbaccess.getStudentSubmissions(student.getBenutzername());
        for(String submission : submissions) {
            listModel.addElement(submission);
        }
        
        fileList = new JList<>(listModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
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

        JButton btnEigeneAbgaben = new JButton("Herunterladen");
        btnEigeneAbgaben.addActionListener(new EigeneAbgabenHerunterladenActionListener());
        
        JScrollPane fileListScrollPane = new JScrollPane(fileList);
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(23)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblBetreuer)
        				.addComponent(lblBisherigeAbagben)
        				.addComponent(fileListScrollPane)))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(15)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnEigeneAbgaben)
        				.addComponent(btnAbgabeHinzufuegen))
        			.addGap(819))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(lblBisherigeAbagben)
        			.addGap(80)
        			.addComponent(fileListScrollPane)
        			.addGap(30)
        			.addComponent(btnEigeneAbgaben)
        			.addGap(33)
        			.addComponent(lblBetreuer)
        			.addPreferredGap(ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
        			.addComponent(btnAbgabeHinzufuegen)
        			.addGap(67))
        );
        setLayout(groupLayout);

	}
}
