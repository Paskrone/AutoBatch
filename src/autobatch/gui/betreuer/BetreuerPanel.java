package autobatch.gui.betreuer;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businessobjects.Betreuer;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Die Klasse BetreuerPanel dient zur Darstellung des Hauptpanels für einen Betreuer.
 * Sie beinhaltet eine Navigationsleiste und zeigt das Autobatch-Logo.
 */
public class BetreuerPanel extends JPanel {

	/**
	 * Der Konstruktor für die Klasse BetreuerPanel.
	 * 
	 * @param panelManager Ein PanelManager zur Verwaltung von Panels
	 * @param panelSwitcher Ein PanelSwitcher zur Umschaltung zwischen verschiedenen Panels
	 * @param betreuer Ein Betreuer-Objekt repräsentiert den aktuell eingeloggten Betreuer
	 */
	public BetreuerPanel(PanelManager panelManager, PanelSwitcher panelSwitcher, Betreuer betreuer) {

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		BetreuerNavigationBar betreuerNavigationBar = new BetreuerNavigationBar(panelManager, panelSwitcher, betreuer);

		// AutoBath Logo anzeigen
		ImageIcon imageIcon = new ImageIcon("src/images/autobatchlogo_klein.png");
		JLabel lbl_Image = new JLabel(imageIcon);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(betreuerNavigationBar, GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup().addGap(139).addComponent(lbl_Image)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE).addComponent(lbl_Image)
						.addGap(69)));
		setLayout(groupLayout);

	}
}
