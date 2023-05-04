package autobatch.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import autobatch.navigation.PanelSwitcher;

import javax.swing.JButton;

public class RegistrationPanel extends JPanel {
	
	private PanelSwitcher panelSwitcher;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	public RegistrationPanel(PanelSwitcher panelSwitcher) {
		
		this.panelSwitcher = panelSwitcher;
		
		setPreferredSize(new Dimension(1000, 500));
		
		JLabel lblNewLabel = new JLabel("Studenten Account erstellen");
		lblNewLabel.setForeground(new Color(92, 160, 255));
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 19));
		
		JLabel lblNewLabel_1 = new JLabel("Matrikelnummer:");
		lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Vorname:");
		lblNewLabel_1_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nachname:");
		lblNewLabel_1_1_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("E-Mail:");
		lblNewLabel_1_1_2.setFont(new Font("Poppins", Font.PLAIN, 13));
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Telefonnummer:");
		lblNewLabel_1_1_2_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Studiengang:");
		lblNewLabel_1_2.setFont(new Font("Poppins", Font.PLAIN, 13));
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Benutzername:");
		lblNewLabel_1_1_2_2.setFont(new Font("Poppins", Font.PLAIN, 13));
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_1_1_2_2_1 = new JLabel("Passwort:");
		lblNewLabel_1_1_2_2_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		JButton btn_AccountErstellen = new JButton("Account erstellen");
		btn_AccountErstellen.setBackground(new Color(92, 160, 255));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_AccountErstellen)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_2_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE))
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_2_2_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_2_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(172, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_2_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btn_AccountErstellen)
					.addContainerGap(147, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
}
