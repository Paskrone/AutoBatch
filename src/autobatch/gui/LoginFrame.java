package autobatch.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.dbaccess.Datenbankabfrage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_username;
	private JTextField tf_password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(55, 127, 218));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 13));
		
		JLabel lblBenutzername = new JLabel("Benutzername:");
		lblBenutzername.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblBenutzername.setBackground(Color.BLACK);
		
		JLabel lblPasswort = new JLabel("Passwort:");
		lblPasswort.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblPasswort.setBackground(Color.BLACK);
		
		tf_username = new JTextField();
		tf_username.setColumns(10);
		
		tf_password = new JTextField();
		tf_password.setColumns(10);
		
		JLabel lbl_error = new JLabel("Ihr Benutzername oder Passwort ist falsch. Bitte erneut versuchen.");
		lbl_error.setForeground(new Color(255, 5, 17));
		lbl_error.setVisible(false);
		
		JButton btn_Login = new JButton("Login");
		btn_Login.setBackground(new Color(85, 133, 212));
		btn_Login.addActionListener(new ActionListener() {
			
			
			//Login Bereich Logik
			public void actionPerformed(ActionEvent e) {
				
				String username = tf_username.getText();
				String password = tf_password.getText();
				
				Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
				boolean checkData = datenbankabfrage.searchStudentsByUsernameAndPassword(username, password);
				if(checkData == true) {
					LoginFrame.this.dispose();
					StudentenFrame studentenFrame = new StudentenFrame();
					studentenFrame.setVisible(true);
				} else {
					lbl_error.setVisible(true);
				}
				
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Du hast noch keinen Account?");
		
		JButton btn_register = new JButton("Registrieren");
		btn_register.setBackground(new Color(85, 133, 212));
		btn_register.setForeground(Color.WHITE);
		
		JLabel label = new JLabel("New label");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBenutzername, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPasswort, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_Login)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(33)
									.addComponent(label)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_register))
						.addComponent(lbl_error))
					.addContainerGap(118, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbl_error)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblBenutzername, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(tf_username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPasswort, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tf_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_Login)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(btn_register)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(165)
							.addComponent(label)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
