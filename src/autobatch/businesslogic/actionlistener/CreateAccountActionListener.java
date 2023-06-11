package autobatch.businesslogic.actionlistener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import autobatch.session.SessionManager;

/**
 * ActionListener zum Erstellen eines neuen Benutzerkontos.
 */
public class CreateAccountActionListener implements ActionListener {

	// Instanzvariablen
	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;
	
	// Verschiedene Textfelder zur Eingabe von Benutzerinformationen
	private JTextField tf_Mnr;
	private JTextField tf_vorname;
	private JTextField tf_nachname;
	private JTextField tf_Email;
	private JTextField tf_telefonnummer;

	private JLabel lblStudiengang;

	private JTextField tf_Ort;
	private JTextField tf_Postleitzahl;
	private JTextField tf_Strasse;
	private JTextField tf_benutzername;
	private JPasswordField tf_Passwort;
	private JLabel lbl_registrationAnzeige;

	// Konstruktor
	public CreateAccountActionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTextField tf_Mnr,
			JTextField tf_vorname, JTextField tf_nachname, JTextField tf_Email, JTextField tf_telefonnummer,
			JLabel lblStudiengang, JTextField tf_Ort, JTextField tf_Postleizahl, JTextField tf_Strasse,
			JTextField tf_benutzername, JPasswordField tf_Passwort, JLabel lbl_registrationAnzeige) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.panelManager = panelManager;
		this.tf_Mnr = tf_Mnr;
		this.tf_vorname = tf_vorname;
		this.tf_nachname = tf_nachname;
		this.tf_Email = tf_Email;
		this.tf_telefonnummer = tf_telefonnummer;

		this.lblStudiengang = lblStudiengang;

		this.tf_Ort = tf_Ort;
		this.tf_Postleitzahl = tf_Postleizahl;
		this.tf_Strasse = tf_Strasse;
		this.tf_benutzername = tf_benutzername;
		this.tf_Passwort = tf_Passwort;
		this.lbl_registrationAnzeige = lbl_registrationAnzeige;

	}

	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Button zum Erstellen eines neuen Kontos drückt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Passwort aus dem Passwort-Feld abrufen und in einen String umwandeln
		char[] passwordChars = tf_Passwort.getPassword();
		String password = new String(passwordChars);

		// Überprüfen, ob alle erforderlichen Informationen eingegeben wurden
		if (check(password)) {

			String mnr = tf_Mnr.getText();
			int mnrInt = Integer.parseInt(mnr);

			int telefonnummer = Integer.parseInt(tf_telefonnummer.getText());

			int postleizahl = Integer.parseInt(tf_Postleitzahl.getText());

			Datenbankabfrage dbaccess = new Datenbankabfrage();

			String studiendekan = "";

			switch (lblStudiengang.getText()) {
			case "WI":
				studiendekan = "st1.mail@hft-stuttgart.de";
				break;

			case "BWL":
				studiendekan = "st2.mail@hft-stuttgart.de";
				break;

			default:
				break;
			}
			
			// Erstellen eines neuen Studentenobjekts und Registrierung in der Datenbank
			if (dbaccess.registerStudent(new Student(mnrInt, tf_vorname.getText(), tf_nachname.getText(), password,
					tf_benutzername.getText(), tf_Email.getText(), telefonnummer, 0, lblStudiengang.getText(),
					tf_Ort.getText(), tf_Strasse.getText(), postleizahl, studiendekan, null))) {
				// Benutzer speichern
				Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
				Benutzer aktuellerBenutzer = datenbankabfrage.getBenutzer(tf_benutzername.getText());
				SessionManager.getInstance().setAktuellerBenutzer(aktuellerBenutzer);
				panelManager.initializePanels();
				panelSwitcher.switchToPanel("Studenten");

			} else {

				lbl_registrationAnzeige.setText("Benutzername existiert bereits.");
				lbl_registrationAnzeige.setForeground(Color.red);
				lbl_registrationAnzeige.setVisible(true);
			}

		}

	}

	/**
	 * Diese Methode überprüft, ob alle erforderlichen Informationen für die Registrierung eingegeben wurden.
	 */
	private boolean check(String password) {

		if (tf_Mnr.getText().equals("") || tf_vorname.getText().equals("") || tf_nachname.getText().equals("")
				|| tf_Email.getText().equals("") || password.equals("") || tf_benutzername.getText().equals("")) {
			System.out.println("die scugwdijwfqjopf");

			lbl_registrationAnzeige.setText("Bitte Benutzername ... eingeben");
			lbl_registrationAnzeige.setForeground(Color.red);
			lbl_registrationAnzeige.setVisible(true);

			return false;
		} else {
			return true;
		}
	}

}
