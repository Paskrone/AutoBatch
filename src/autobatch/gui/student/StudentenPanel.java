package autobatch.gui.student;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.AbmeldenActionListener;
import autobatch.businesslogic.StudentenDatenActionListener;
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
        
        JButton btnIP = new JButton("IP");
        
        JButton btnBetreuer = new JButton("Betreuer");
        
        JButton btnAbgaben = new JButton("Abgaben");
        
        JButton btnFormulare = new JButton("Formulare");
        
        JButton btnDaten = new JButton("Daten");
		btnDaten.addActionListener(new StudentenDatenActionListener(panelSwitcher));

		
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(jLabelHead)
        					.addPreferredGap(ComponentPlacement.RELATED, 754, Short.MAX_VALUE)
        					.addComponent(btn_abmelden))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(btnIP)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnBetreuer)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnAbgaben)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnFormulare)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnDaten)))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabelHead)
        				.addComponent(btn_abmelden))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnIP)
        				.addComponent(btnBetreuer)
        				.addComponent(btnAbgaben)
        				.addComponent(btnFormulare)
        				.addComponent(btnDaten))
        			.addContainerGap(414, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }
}