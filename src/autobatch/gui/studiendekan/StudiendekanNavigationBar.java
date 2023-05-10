package autobatch.gui.studiendekan;

import java.awt.Dimension;

import javax.swing.JPanel;

import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StudiendekanNavigationBar extends JPanel {

	private PanelSwitcher panelSwitcher;
	
	public StudiendekanNavigationBar(PanelSwitcher panelSwitcher) {
		
		this.panelSwitcher = panelSwitcher;
		setPreferredSize(new Dimension(1000, 50));
		
		JLabel lbl_Studenten = new JLabel("Studenten");
		
		JLabel lbl_Ip = new JLabel("IP");
		
		JLabel lbl_Formulare = new JLabel("Formulare");
		
		JLabel lbl_Anfragen = new JLabel("Anfragen");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addComponent(lbl_Studenten)
					.addGap(36)
					.addComponent(lbl_Anfragen, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 582, Short.MAX_VALUE)
					.addComponent(lbl_Ip, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(lbl_Formulare, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_Studenten)
						.addComponent(lbl_Formulare)
						.addComponent(lbl_Ip)
						.addComponent(lbl_Anfragen))
					.addGap(15))
		);
		setLayout(groupLayout);

	}

}
