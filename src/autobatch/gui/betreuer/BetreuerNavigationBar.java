package autobatch.gui.betreuer;

import java.awt.Dimension;

import javax.swing.JPanel;

import autobatch.businesslogic.mouselistener.NavigationBarMouseListener;
import autobatch.businessobjects.Betreuer;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Eine JPanel-Klasse, die eine Navigationsleiste implementiert, die in den Betreuer-bezogenen Panels angezeigt wird.
 */
public class BetreuerNavigationBar extends JPanel {

	/**
	 * Erzeugt eine neue Navigationsleiste für die Betreuer-Ansicht, die Links zu den Studenten-, Anfragen-, Daten- und Abmelden-Panels enthält.
	 *
	 * @param panelManager Der PanelManager, der zur Anzeige der Panels verwendet wird.
	 * @param panelSwitcher Der PanelSwitcher, der zur Navigation zwischen den Panels verwendet wird.
	 * @param betreuer Der Betreuer, der derzeit angemeldet ist.
	 */
	public BetreuerNavigationBar(PanelManager panelManager, PanelSwitcher panelSwitcher, Betreuer betreuer) {

		setPreferredSize(new Dimension(1000, 50));

		JLabel lbl_Studenten = new JLabel("Studenten");
		// Listener für das "Studenten"-Label. Erzeugt ein neues StudentenPanel und navigiert zu diesem.
		lbl_Studenten.addMouseListener(new NavigationBarMouseListener() {
			@Override
			public void labelClicked() {
				JPanel panel = new BetreuerStudentenPanel(panelSwitcher, panelManager, betreuer);
				panelManager.updatePanels(panel, "Betreuer_Studenten");
				panelSwitcher.switchToPanel("Betreuer_Studenten");

			}
		});

		JLabel lbl_Anfragen = new JLabel("Anfragen");
		// Listener für das "Anfragen"-Label. Erzeugt ein neues AnfragenPanel und navigiert zu diesem.
		lbl_Anfragen.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {
				JPanel panel = new BetreuerAnfragenPanel(panelSwitcher, panelManager, betreuer);
				panelManager.updatePanels(panel, "Betreuer_Anfragen");
				panelSwitcher.switchToPanel("Betreuer_Anfragen");

			}
		});

		JLabel lbl_Daten = new JLabel("Daten");
		// Listener für das "Daten"-Label. Erzeugt ein neues DatenPanel und navigiert zu diesem.
		lbl_Daten.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {
				JPanel panel = new BetreuerDatenPanel(panelManager, panelSwitcher, betreuer);
				panelManager.updatePanels(panel, "Betreuer_Daten");
				panelSwitcher.switchToPanel("Betreuer_Daten");

			}
		});

		JLabel lbl_abmelden = new JLabel("abmelden");
		// Listener für das "Abmelden"-Label. Navigiert zurück zum Login-Panel.
		lbl_abmelden.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {
				panelSwitcher.switchToPanel("Login");

			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(66).addComponent(lbl_Studenten).addGap(61)
						.addComponent(lbl_Anfragen, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addGap(58).addComponent(lbl_Daten, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 512, Short.MAX_VALUE).addComponent(lbl_abmelden)
						.addGap(53)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(18, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lbl_Studenten)
								.addComponent(lbl_Daten).addComponent(lbl_Anfragen).addComponent(lbl_abmelden))
						.addGap(16)));
		setLayout(groupLayout);

	}
}
