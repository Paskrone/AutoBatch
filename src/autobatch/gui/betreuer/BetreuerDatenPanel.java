package autobatch.gui.betreuer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.DatenSpeichernBetreuerActionListener;
import autobatch.businessobjects.Betreuer;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BetreuerDatenPanel extends JPanel {
	private JTextField tf_Passwort;

	public BetreuerDatenPanel(PanelManager panelManager, PanelSwitcher panelSwitcher, Betreuer betreuer) {

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		BetreuerNavigationBar betreuerNavigationBar = new BetreuerNavigationBar(panelManager, panelSwitcher, betreuer);

		JLabel lblNewLabel = new JLabel("Name:");

		JLabel lblNewLabel_1 = new JLabel("Email:");

		JLabel lblNewLabel_2 = new JLabel("Benutzername:");

		JLabel lblNewLabel_3 = new JLabel("Passwort:");

		JLabel lblBenutzername = new JLabel(betreuer.getBenutzername());

		JLabel lblEmail = new JLabel(betreuer.getEmail());

		JLabel lblName = new JLabel(betreuer.getVorname() + " " + betreuer.getNachname());

		tf_Passwort = new JTextField();
		tf_Passwort.setColumns(10);

		tf_Passwort.setText(betreuer.getPasswort());

		JLabel lblPopUp = new JLabel("Eingabe überprüfen");
		lblPopUp.setVisible(false);

		JButton btnSaveData = new JButton("Speichern");
		btnSaveData.addActionListener(new DatenSpeichernBetreuerActionListener(betreuer, tf_Passwort, lblPopUp));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_2)
										.addComponent(lblNewLabel_1).addComponent(lblNewLabel)
										.addComponent(lblNewLabel_3))
								.addGap(60)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(tf_Passwort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblName).addComponent(lblEmail).addComponent(lblBenutzername)))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(lblPopUp))
										.addComponent(btnSaveData))))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(
						groupLayout
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel).addComponent(lblName))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_1).addComponent(lblEmail))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_2).addComponent(lblBenutzername))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_3).addComponent(tf_Passwort,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSaveData)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblPopUp)
										.addContainerGap(273, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

}
