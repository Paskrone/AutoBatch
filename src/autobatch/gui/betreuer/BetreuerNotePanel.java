package autobatch.gui.betreuer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.NoteEintragenActionListener;
import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Betreuer;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BetreuerNotePanel extends JPanel {

	private JTextField textField;

	public BetreuerNotePanel(PanelManager panelmanager, PanelSwitcher panelSwitcher, Betreuer betreuer, Arbeit arbeit) {

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		BetreuerNavigationBar betreuerNavigationBar = new BetreuerNavigationBar(panelSwitcher);

		JLabel lblNewLabel = new JLabel("Note:");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblPopUp = new JLabel("Note gespeichert");
		lblPopUp.setVisible(false);

		JButton btnSave = new JButton("Speichern");
		btnSave.addActionListener(new NoteEintragenActionListener(arbeit, textField, lblPopUp));

		JButton btnBack = new JButton("Zurück");
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel betreuerStudenten_1Panel = new BetreuerStudenten_1Panel(panelmanager, panelSwitcher, betreuer);
				panelmanager.updatePanels(betreuerStudenten_1Panel, "Betreuer_Studenten_1");
				panelSwitcher.switchToPanel("Betreuer_Studenten_1");
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(betreuerNavigationBar, GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(btnBack))
						.addGroup(groupLayout.createSequentialGroup().addGap(64).addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING).addComponent(lblPopUp)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblNewLabel).addGap(18)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 95,
												GroupLayout.PREFERRED_SIZE)
										.addGap(44).addComponent(btnSave)))))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(63)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSave))
						.addGap(18).addComponent(lblPopUp)
						.addPreferredGap(ComponentPlacement.RELATED, 212, Short.MAX_VALUE).addComponent(btnBack)
						.addGap(73)));
		setLayout(groupLayout);

	}
}