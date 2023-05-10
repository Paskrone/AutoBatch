package autobatch.gui.student;


import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import autobatch.businesslogic.mouselistener.NavigationBarMouseListener;
import autobatch.navigation.PanelSwitcher;

public class StudentNavigationBar extends JPanel {
	
	private PanelSwitcher panelSwitcher;
	
	public StudentNavigationBar(PanelSwitcher panelSwitcher) {
		
		this.panelSwitcher = panelSwitcher;
		setPreferredSize(new Dimension(1000, 50));
		
		JLabel lbl_Ip = new JLabel("IP");
		lbl_Ip.addMouseListener(new NavigationBarMouseListener() {
			@Override
			public void labelClicked() {
				
				panelSwitcher.switchToPanel("Studenten_Ip");
				
			}
		});
		
		JLabel lbl_betreuer = new JLabel("Betreuer");
		lbl_betreuer.addMouseListener(new NavigationBarMouseListener() {
			
			@Override
			public void labelClicked() {
				
				panelSwitcher.switchToPanel("Studenten_Betreuer");
				
			}
		});
		
		JLabel lbl_abgaben = new JLabel("Abgaben");
		lbl_abgaben.addMouseListener(new NavigationBarMouseListener() {
			
			@Override
			public void labelClicked() {
				
				panelSwitcher.switchToPanel("Studenten_Abgaben");
				
			}
		});
		
		JLabel lbl_formulare = new JLabel("Formulare");
		lbl_formulare.addMouseListener(new NavigationBarMouseListener() {
			
			@Override
			public void labelClicked() {
				
				panelSwitcher.switchToPanel("Studenten_Formulare");
				
			}
		});
		
		JLabel lbl_daten = new JLabel("Daten");
		lbl_daten.addMouseListener(new NavigationBarMouseListener() {
			
			@Override
			public void labelClicked() {
				
				panelSwitcher.switchToPanel("Studenten_Daten");
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(63)
					.addComponent(lbl_Ip, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(lbl_betreuer, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(lbl_abgaben, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(lbl_formulare)
					.addGap(64)
					.addComponent(lbl_daten)
					.addContainerGap(453, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_Ip)
						.addComponent(lbl_betreuer)
						.addComponent(lbl_formulare)
						.addComponent(lbl_abgaben)
						.addComponent(lbl_daten))
					.addGap(15))
		);
		setLayout(groupLayout);
		
		

	}

}