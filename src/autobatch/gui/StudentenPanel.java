package autobatch.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.AbmeldenActionListener;
import autobatch.businesslogic.LoginActionListener;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StudentenPanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	
    public StudentenPanel(PanelSwitcher panelSwitcher) {
    	this.panelSwitcher = panelSwitcher;
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        
        JLabel jLabelHead = new JLabel("AutoBatch | Student");
        
        JButton btn_abmelden = new JButton("abmelden");
        //abmelden
		btn_abmelden.addActionListener(new AbmeldenActionListener(panelSwitcher));

        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(jLabelHead)
        			.addPreferredGap(ComponentPlacement.RELATED, 754, Short.MAX_VALUE)
        			.addComponent(btn_abmelden)
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabelHead)
        				.addComponent(btn_abmelden))
        			.addContainerGap(456, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }
}