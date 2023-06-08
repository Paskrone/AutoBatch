package autobatch.gui.studiendekan;

import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import autobatch.businessobjects.Studiendekan;
import autobatch.gui.betreuer.BetreuerNavigationBar;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StudiendekanPanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Studiendekan studiendekan;
	
	public StudiendekanPanel(PanelManager panelmanager,PanelSwitcher panelSwitcher, Studiendekan studiendekan) {
		
		this.panelSwitcher = panelSwitcher;
		

		this.panelSwitcher = panelSwitcher;
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        
		StudiendekanNavigationBar studiendekanNavBar = new StudiendekanNavigationBar(panelmanager, panelSwitcher, studiendekan);
        
        //AutoBath Logo anzeigen
        ImageIcon imageIcon = new ImageIcon("src/images/autobatchlogo_klein.png");
        JLabel lbl_Image = new JLabel(imageIcon);
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(studiendekanNavBar, GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(139)
        					.addComponent(lbl_Image)))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(studiendekanNavBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
        			.addComponent(lbl_Image)
        			.addGap(69))
        );
        setLayout(groupLayout);
		
		
		
	}

}
