package autobatch.gui.studiendekan;

import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.DatenSpeichernBetreuerActionListener;
import autobatch.businesslogic.actionlistener.DatenSpeichernStudiendekanActionListener;
import autobatch.businessobjects.Studiendekan;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class StudiendekanDatenPanel extends JPanel {
	private JTextField tf_Passwort;

	public StudiendekanDatenPanel(PanelManager panelmanager, PanelSwitcher panelSwitcher, Studiendekan studiendekan) {

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudiendekanNavigationBar studiendekanNavBar = new StudiendekanNavigationBar(panelmanager, panelSwitcher,
				studiendekan);

		JLabel lblNewLabel = new JLabel("Name:");

		JLabel lblNewLabel_1 = new JLabel("Email:");

		JLabel lblNewLabel_2 = new JLabel("Studiengang");

		JLabel lblNewLabel_3 = new JLabel("Benutzername:");

		JLabel lblNewLabel_4 = new JLabel("Passwort:");

		JLabel lblBenutzername = new JLabel(studiendekan.getBenutzername());

		JLabel lblStudeiengang = new JLabel(studiendekan.getStudiengang());

		JLabel lblEmail = new JLabel(studiendekan.getEmail());

		JLabel lblName = new JLabel(studiendekan.getVorname()+" "+studiendekan.getNachname());

		tf_Passwort = new JTextField();
		tf_Passwort.setColumns(10);
		
		tf_Passwort.setText(studiendekan.getPasswort());

		JLabel lblPopUp = new JLabel("Eingabe überprüfen!");
		lblPopUp.setVisible(false);

		JButton btnSaveData = new JButton("Speichern");
		btnSaveData.addActionListener(new DatenSpeichernStudiendekanActionListener(studiendekan, tf_Passwort, lblPopUp));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(studiendekanNavBar, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_2).addComponent(lblNewLabel_1).addComponent(lblNewLabel)
								.addComponent(lblNewLabel_4))
						.addGap(54)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(tf_Passwort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblName).addComponent(lblEmail).addComponent(lblStudeiengang)
								.addComponent(lblBenutzername))
						.addContainerGap(708, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(lblPopUp))
								.addComponent(btnSaveData))
						.addContainerGap(879, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(
						groupLayout
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(studiendekanNavBar, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel).addComponent(lblName))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_1).addComponent(lblEmail))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_2).addComponent(lblStudeiengang))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_3).addComponent(lblBenutzername))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_4).addComponent(tf_Passwort,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGap(18).addComponent(btnSaveData)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblPopUp)
										.addGap(239)));
		setLayout(groupLayout);

	}

}
