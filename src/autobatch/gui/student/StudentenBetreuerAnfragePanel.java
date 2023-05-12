package autobatch.gui.student;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.LoginActionListener;
import autobatch.businesslogic.actionlistener.StudentenDatenActionListener;
import autobatch.businessobjects.Student;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StudentenBetreuerAnfragePanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Student student;
	
    public StudentenBetreuerAnfragePanel(PanelSwitcher panelSwitcher, Student student) {
    	this.student = student;
    	this.panelSwitcher = panelSwitcher;
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelSwitcher);
       
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(129))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(64)
        			.addContainerGap(77, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }
	
}
