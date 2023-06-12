package autobatch.gui.student;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businessobjects.Student;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Die Klasse StudentenIP_1Panel repräsentiert ein JPanel, das spezifisch für Studenten erstellt wurde.
 * Es zeigt an, dass kein Betreuer für den Studenten zugewiesen ist.
 */
public class StudentenIP_1Panel extends JPanel {

    /**
     * Erstellt ein neues Panel für Studenten ohne Betreuer.
     *
     * @param panelManager   Der Manager, der die Navigation zwischen den Panels verwaltet.
     * @param panelSwitcher  Ein Helfer zum Wechseln zwischen verschiedenen Panels.
     * @param student        Der Student, für den das Panel angezeigt wird.
     */
	public StudentenIP_1Panel(PanelManager panelManager, PanelSwitcher panelSwitcher, Student student) {

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelManager, panelSwitcher, student);

		JLabel lblNewLabel = new JLabel("Noch kein Betreuer!!");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup().addGap(129).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNewLabel)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(64).addGroup(
										groupLayout.createSequentialGroup().addGap(137).addComponent(lblNewLabel))))));
		setLayout(groupLayout);
	}
}