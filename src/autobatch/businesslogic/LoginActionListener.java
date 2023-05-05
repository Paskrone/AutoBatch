package autobatch.businesslogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.Main;
import autobatch.navigation.PanelSwitcher;

public class LoginActionListener implements ActionListener {
	private JTextField tf_username;
	private JPasswordField tf_password;
	private JLabel lbl_error;
	private PanelSwitcher panelSwitcher;

	public LoginActionListener(PanelSwitcher panelSwitcher, JTextField tf_username, JPasswordField tf_password,
			JLabel lbl_error) {
		this.panelSwitcher = panelSwitcher;
		this.tf_username = tf_username;
		this.tf_password = tf_password;
		this.lbl_error = lbl_error;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = tf_username.getText();
		char[] passwordChars = tf_password.getPassword();
		String password = new String(passwordChars);

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		boolean checkData = datenbankabfrage.searchAllTablesByUsernameAndPassword(username, password);
		if (checkData) {
			
			//Student speichern
			Datenbankabfrage.aktuellerStudent = datenbankabfrage.getStudent(username);
			System.out.println(Datenbankabfrage.aktuellerStudent.getNachname());

			// Textfields leeren
			tf_password.setText("");
			tf_username.setText("");

			panelSwitcher.switchToPanel("Studenten");
		} else {
			lbl_error.setVisible(true);
			tf_password.setText("");
		}
	}

}
