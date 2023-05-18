package autobatch.gui.student;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import autobatch.businessobjects.Student;
import autobatch.navigation.PanelSwitcher;

public class StudentenAbgabenPanel extends JPanel {
	
	private PanelSwitcher panelSwitcher;
	private Student student;

	public StudentenAbgabenPanel(PanelSwitcher panelSwitcher, Student student) {
		
		this.panelSwitcher = panelSwitcher;
        this.student = student;
        
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        
        StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelSwitcher, student);
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 990, Short.MAX_VALUE)
        		.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 490, Short.MAX_VALUE)
        		.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        setLayout(groupLayout);

	}

}
