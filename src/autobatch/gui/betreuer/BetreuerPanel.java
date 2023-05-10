package autobatch.gui.betreuer;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businessobjects.Betreuer;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class BetreuerPanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Betreuer betreuer;
	
	public BetreuerPanel(PanelSwitcher panelSwitcher, Betreuer betreuer) {
		
		this.panelSwitcher = panelSwitcher;
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        
        BetreuerNavigationBar betreuerNavigationBar = new BetreuerNavigationBar(panelSwitcher);
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addComponent(betreuerNavigationBar, GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(440, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
		
		
		
		
	}

}
