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
/**
 * Die StudiendekanNavigationBar ist ein JPanel, das eine Navigationsleiste für den Studiendekan darstellt.
 * Es ermöglicht dem Studiendekan, zwischen verschiedenen Panels zu wechseln und Aktionen auszuführen.
 */
public class StudiendekanNavigationBar extends JPanel {

    /**
     * Erstellt eine neue StudiendekanNavigationBar.
     *
     * @param panelManager Der PanelManager, der für die Navigation zwischen den Panels zuständig ist.
     * @param panelSwitcher Der PanelSwitcher, der für das Umschalten zwischen den Panels verantwortlich ist.
     * @param studiendekan Der Studiendekan, für den die Navigationsleiste angezeigt wird.
     */
	public StudiendekanNavigationBar(PanelManager panelManager, PanelSwitcher panelSwitcher,
			Studiendekan studiendekan) {

		setPreferredSize(new Dimension(1000, 50));

		JLabel lbl_Ip = new JLabel("IP");
		lbl_Ip.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {
				JPanel panel = new StudiendekanIpPanel(panelSwitcher, panelManager, studiendekan);
				panelManager.updatePanels(panel, "Studiendekan_Ip");
				panelSwitcher.switchToPanel("Studiendekan_Ip");
			}
		});

		JLabel lbl_Formulare = new JLabel("BA-Anmeldformulare");
		lbl_Formulare.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {
				JPanel panel = new StudiendekanFormularPanel(panelManager, panelSwitcher, studiendekan);
				panelManager.updatePanels(panel, "StudiendekanFormularPanel");
				panelSwitcher.switchToPanel("StudiendekanFormularPanel");
			}
		});

		JLabel lbl_abmelden = new JLabel("abmelden");
		lbl_abmelden.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {

				panelSwitcher.switchToPanel("Login");

			}
		});

		JLabel lblNoten = new JLabel("Noten");
		lblNoten.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {

				JPanel panel = new StudiendekanNotenPanel(panelManager, panelSwitcher, studiendekan);
				panelManager.updatePanels(panel, "StudiendekanNotenPanel");
				panelSwitcher.switchToPanel("StudiendekanNotenPanel");
			}
		});

		JLabel lblDaten = new JLabel("Daten");
		lblDaten.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {

				JPanel panel = new StudiendekanDatenPanel(panelManager, panelSwitcher, studiendekan);
				panelManager.updatePanels(panel, "StudiendekanDatenPanel");
				panelSwitcher.switchToPanel("StudiendekanDatenPanel");

			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(66)
						.addComponent(lbl_Ip, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 810, Short.MAX_VALUE)
										.addComponent(lbl_abmelden, GroupLayout.PREFERRED_SIZE, 67,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(
										groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lbl_Formulare).addGap(46).addComponent(lblNoten)
												.addGap(40).addComponent(lblDaten).addGap(559)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lbl_abmelden)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lbl_Ip)
								.addComponent(lbl_Formulare).addComponent(lblNoten).addComponent(lblDaten))
						.addGap(14)));
		setLayout(groupLayout);

	}

}
