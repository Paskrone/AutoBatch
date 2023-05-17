package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Studiendekan;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import autobatch.session.SessionManager;

public class LoginActionListener implements ActionListener {
	private JTextField tf_username;
	private JPasswordField tf_password;
	private JLabel lbl_error;
	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;

	public LoginActionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTextField tf_username,
			JPasswordField tf_password, JLabel lbl_error) {
		this.panelSwitcher = panelSwitcher;
		this.tf_username = tf_username;
		this.tf_password = tf_password;
		this.lbl_error = lbl_error;
		this.panelManager = panelManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = tf_username.getText();
		char[] passwordChars = tf_password.getPassword();
		String password = new String(passwordChars);

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		boolean checkData = datenbankabfrage.searchAllTablesByUsernameAndPassword(username, password);
		if (checkData) {

			// Benutzer speichern
			Benutzer aktuellerBenutzer = datenbankabfrage.getBenutzer(username);
			SessionManager.getInstance().setAktuellerBenutzer(aktuellerBenutzer);

			// Textfields leeren
			tf_password.setText("");
			tf_username.setText("");
			lbl_error.setVisible(false);

			panelManager.initializePanels();

		} else {
			lbl_error.setVisible(true);
			tf_password.setText("");
		}
	}

}
