package autobatch.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;


import autobatch.businesslogic.LoginActionListener;
import autobatch.businesslogic.RegistrationActionListener;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class LoginPanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;
	private JTextField tf_username;
	private JPasswordField tf_password;

	public LoginPanel(PanelSwitcher panelSwitcher, PanelManager panelManager) {
		this.panelSwitcher = panelSwitcher;	
		this.panelManager = panelManager;
		
		setPreferredSize(new Dimension(1000, 500));
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(55, 127, 218));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
		
		JLabel lblBenutzername = new JLabel("Benutzername:");
		lblBenutzername.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenutzername.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblBenutzername.setBackground(Color.BLACK);
		
		JLabel lblPasswort = new JLabel("Passwort:");
		lblPasswort.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblPasswort.setBackground(Color.BLACK);
		
		tf_username = new JTextField();
		tf_username.setColumns(10);
		tf_username.setText("");
		
		tf_password = new JPasswordField();
		tf_password.setText("");
		
		JLabel lbl_error = new JLabel("Ihr Benutzername oder Passwort ist falsch. Bitte erneut versuchen.");
		lbl_error.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_error.setForeground(new Color(255, 5, 17));
		lbl_error.setVisible(false);
		
		JButton btn_Login = new JButton("Login");
		btn_Login.setBackground(new Color(85, 133, 212));
		
		//Login Daten werden hier überprüft
		btn_Login.addActionListener(new LoginActionListener(panelSwitcher, panelManager, tf_username, tf_password, lbl_error));
		
		JLabel lblNewLabel_1 = new JLabel("Du hast noch keinen Account?");
		
		JButton btn_registration = new JButton("Registrieren");
		btn_registration.setBackground(new Color(85, 133, 212));
		btn_registration.setForeground(new Color(0, 0, 0));
		
		//Nutzer wird auf die Registrierungsseite weitergeleitet
		btn_registration.addActionListener(new RegistrationActionListener(panelSwitcher));
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lbl_error, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 975, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(tf_username, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(6)
												.addComponent(lblPasswort, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
											.addComponent(btn_Login, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
											.addComponent(tf_password))
										.addComponent(btn_registration, Alignment.LEADING))
									.addGap(375))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(130)
							.addComponent(lblBenutzername, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(378)
							.addComponent(lblNewLabel_1)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbl_error)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblBenutzername, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(tf_username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPasswort, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tf_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_Login)
					.addGap(36)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_registration)
					.addContainerGap(196, Short.MAX_VALUE))
		);
		this.setLayout(gl_contentPane);
	}
	
}
