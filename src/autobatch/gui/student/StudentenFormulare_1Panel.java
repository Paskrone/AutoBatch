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

/**
 * Die Klasse StudentenFormulare_1Panel stellt ein JPanel dar, das spezifische Informationen zu den von einem Studenten eingereichten Formularen anzeigt.
 */
public class StudentenFormulare_1Panel extends JPanel {

    /**
     * Erstellt ein neues Panel, das Informationen zu den von einem Studenten eingereichten Formularen anzeigt.
     *
     * @param panelmanager   Der Manager, der die Navigation zwischen den Panels verwaltet.
     * @param panelSwitcher  Ein Helfer zum Wechseln zwischen verschiedenen Panels.
     * @param student        Der aktuelle Student, dessen eingereichte Formulare angezeigt werden.
     */
	public StudentenFormulare_1Panel(PanelManager panelmanager, PanelSwitcher panelSwitcher, Student student) {

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelmanager, panelSwitcher, student);
		
		JLabel lblNewLabel = new JLabel("Bereits eingereicht!");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(148)
					.addComponent(lblNewLabel))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(102)
					.addComponent(lblNewLabel)
					.addContainerGap(322, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}