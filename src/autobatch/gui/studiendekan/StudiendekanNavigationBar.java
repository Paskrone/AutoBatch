package autobatch.gui.studiendekan;

import java.awt.Dimension;

import javax.swing.JPanel;

import autobatch.businesslogic.mouselistener.NavigationBarMouseListener;
import autobatch.businessobjects.Studiendekan;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StudiendekanNavigationBar extends JPanel {

	
	public StudiendekanNavigationBar(PanelManager panelManager, PanelSwitcher panelSwitcher, Studiendekan studiendekan) {
		
		
		setPreferredSize(new Dimension(1000, 50));
		
		JLabel lbl_Studenten = new JLabel("Studenten");
	
		
		JLabel lbl_Ip = new JLabel("IP");
		lbl_Ip.addMouseListener(new NavigationBarMouseListener() {
			
			@Override
			public void labelClicked() {
				JPanel panel = new StudiendekanIpPanel(panelSwitcher, panelManager, studiendekan);
				panelManager.updatePanels(panel, "Studiendekan_Ip");
				panelSwitcher.switchToPanel("Studiendekan_Ip");
			}
		});
		
		
		JLabel lbl_Formulare = new JLabel("Formulare");
		
		JLabel lbl_Anfragen = new JLabel("Anfragen");
		
		
		JLabel lbl_abmelden = new JLabel("abmelden");
		lbl_abmelden.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {

				panelSwitcher.switchToPanel("Login");

			}
		});
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
					.addGap(18)
					.addComponent(lbl_Formulare, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(71))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(923, Short.MAX_VALUE)
					.addComponent(lbl_abmelden, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_abmelden)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_Studenten)
						.addComponent(lbl_Ip)
						.addComponent(lbl_Anfragen)
						.addComponent(lbl_Formulare))
					.addGap(14))
		);
		setLayout(groupLayout);

	}

}
