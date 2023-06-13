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
	
	/**
	 * JTextField, das zur Eingabe des Benutzernamens verwendet wird.
	 */
	private JTextField tf_username;

	/**
	 * JPasswordField, das zur Eingabe des Passworts verwendet wird.
	 */
	private JPasswordField tf_password;

	/**
	 * JLabel, das zur Anzeige von Fehlermeldungen verwendet wird.
	 */
	private JLabel lbl_error;

	/**
	 * PanelSwitcher-Objekt, das für die Verwaltung des Wechsels zwischen verschiedenen Panels in der GUI verwendet wird.
	 */
	private PanelSwitcher panelSwitcher;

	/**
	 * PanelManager-Objekt, das zur Verwaltung der Panels in der GUI verwendet wird.
	 */
	private PanelManager panelManager;


	/**
	 * Konstruktor
	 *
	 * @param panelSwitcher  Das PanelSwitcher-Objekt, das für die Verwaltung des Wechsels zwischen verschiedenen Panels in der GUI verwendet wird.
	 * @param panelManager   Das PanelManager-Objekt, das zur Verwaltung der Panels in der GUI verwendet wird.
	 * @param tf_username    Das JTextField, das zur Eingabe des Benutzernamens verwendet wird.
	 * @param tf_password    Das JPasswordField, das zur Eingabe des Passworts verwendet wird.
	 * @param lbl_error      Das JLabel, das zur Anzeige von Fehlermeldungen verwendet wird.
	 */
	public LoginActionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTextField tf_username,
			JPasswordField tf_password, JLabel lbl_error) {
		this.panelSwitcher = panelSwitcher;
		this.tf_username = tf_username;
		this.tf_password = tf_password;
		this.lbl_error = lbl_error;
		this.panelManager = panelManager;
	}

	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Login-Button drückt. Zunächst werden die Benutzereingaben (Benutzername und Passwort) ermittelt. 
	 * Anschließend wird mit Hilfe eines Datenbankzugriffs geprüft, ob die eingegebenen Daten gültig sind. 
	 * Falls ja, wird der aktuelle Benutzer in der Session gespeichert, die Eingabefelder werden zurückgesetzt, die Fehlermeldung wird ausgeblendet und die Panels initialisiert.
	 * Falls die Daten ungültig sind, wird eine Fehlermeldung angezeigt und das Passwortfeld zurückgesetzt.
	 * @param e Das ausgelöste ActionEvent.
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
