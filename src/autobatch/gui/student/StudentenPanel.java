package autobatch.gui.student;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.AbmeldenActionListener;
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

public class StudentenPanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Student student;
	
    public StudentenPanel(PanelSwitcher panelSwitcher, Student student) {
    	this.student = student;
    	this.panelSwitcher = panelSwitcher;
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelSwitcher);
       
        //AutoBath Logo anzeigen
        ImageIcon imageIcon = new ImageIcon("/Users/pascalgrcic/git/AutoBatch/images/autobatchlogo_klein.png");
        Image image = imageIcon.getImage();
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