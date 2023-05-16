package autobatch.gui.betreuer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import autobatch.businesslogic.mouselistener.NavigationBarMouseListener;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BetreuerNavigationBar extends JPanel {
	
	private PanelSwitcher panelSwitcher;

	public BetreuerNavigationBar(PanelSwitcher panelSwitcher) {
		
		this.panelSwitcher = panelSwitcher;
		setPreferredSize(new Dimension(1000, 50));
		
		JLabel lbl_Studenten = new JLabel("Studenten");
		lbl_Studenten.addMouseListener(new NavigationBarMouseListener() {
			@Override
			public void labelClicked() {
				
				panelSwitcher.switchToPanel("Betreuer_Studenten");
				
			}
		});
		
		JLabel lbl_Anfragen = new JLabel("Anfragen");
		lbl_Anfragen.addMouseListener(new NavigationBarMouseListener() {
			
			@Override
			public void labelClicked() {
				
				panelSwitcher.switchToPanel("Betreuer_Anfragen");
				
			}
		});
		
		JLabel lbl_Daten = new JLabel("Daten");
		lbl_Daten.addMouseListener(new NavigationBarMouseListener() {
			
			@Override
			public void labelClicked() {
				
				panelSwitcher.switchToPanel("Betreuer_Daten");
				
			}
		});
		
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
					.addGap(66)
					.addComponent(lbl_Studenten)
					.addGap(61)
					.addComponent(lbl_Anfragen, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addComponent(lbl_Daten, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 512, Short.MAX_VALUE)
					.addComponent(lbl_abmelden)
					.addGap(53))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_Studenten)
						.addComponent(lbl_Daten)
						.addComponent(lbl_Anfragen)
						.addComponent(lbl_abmelden))
					.addGap(16))
		);
		setLayout(groupLayout);
		
	}
}
