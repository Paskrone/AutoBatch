package autobatch.gui.student;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.LoginActionListener;
import autobatch.businessobjects.Student;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Die Klasse StudentenPanel repräsentiert ein JPanel spezifisch für Studenten.
 * Es ist der Startpunkt des Studenten, nach erfolgreichen Login.
 */
public class StudentenPanel extends JPanel {

	private PanelManager panelmanager;
	private PanelSwitcher panelSwitcher;
	private Student student;
	
    /**
     * Erstellt ein neues StudentenPanel.
     *
     * @param panelmanager   Der Manager, der die Navigation zwischen den Panels verwaltet.
     * @param panelSwitcher  Ein Helfer zum Wechseln zwischen verschiedenen Panels.
     * @param student        Der Student, für den das Panel angezeigt wird.
     */
    public StudentenPanel(PanelManager panelmanager, PanelSwitcher panelSwitcher, Student student) {
    	this.student = student;
    	this.panelSwitcher = panelSwitcher;
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelmanager, panelSwitcher, student);
       
        //AutoBath Logo anzeigen
        ImageIcon imageIcon = new ImageIcon("src/images/autobatchlogo_klein.png");
        JLabel lbl_image = new JLabel(imageIcon);
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(129)
        			.addComponent(lbl_image))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(64)
        			.addComponent(lbl_image, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(77, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }
}