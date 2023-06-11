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

/**
 * ActionListener zur Verarbeitung des Anmeldevorgangs.
 */
public class LoginActionListener implements ActionListener {
	// Instanzvariablen
	private JTextField tf_username; // Textfeld für den Benutzernamen
	private JPasswordField tf_password; // Passwortfeld für das Passwort
	private JLabel lbl_error; // Label zur Anzeige von Fehlermeldungen

	private PanelSwitcher panelSwitcher; // zur Verwaltung des Panelwechsels
	private PanelManager panelManager; // zur Verwaltung der Panels

	// Konstruktor
	public LoginActionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTextField tf_username,
			JPasswordField tf_password, JLabel lbl_error) {
		this.panelSwitcher = panelSwitcher;
		this.tf_username = tf_username;
		this.tf_password = tf_password;
		this.lbl_error = lbl_error;
		this.panelManager = panelManager;
	}

	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Login-Button drückt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Ermitteln der Eingaben des Benutzers
		String username = tf_username.getText();
		char[] passwordChars = tf_password.getPassword();
		String password = new String(passwordChars);

		// Erzeugen eines neuen Datenbankzugriffs
		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

		// Prüfen, ob die Benutzerdaten gültig sind
		boolean checkData = datenbankabfrage.searchAllTablesByUsernameAndPassword(username, password);
		if (checkData) {

			// Aktuellen Benutzer speichern
			Benutzer aktuellerBenutzer = datenbankabfrage.getBenutzer(username);
			SessionManager.getInstance().setAktuellerBenutzer(aktuellerBenutzer);

			// Eingabefelder zurücksetzen
			tf_password.setText("");
			tf_username.setText("");

			// Fehlermeldung ausblenden
			lbl_error.setVisible(false);

			// Panels initialisieren
			panelManager.initializePanels();

		} else {
			// Fehlermeldung anzeigen und Passwortfeld zurücksetzen
			lbl_error.setVisible(true);
			tf_password.setText("");
		}
	}

}
