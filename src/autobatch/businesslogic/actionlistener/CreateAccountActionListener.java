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

	/**
	 * Dienst zum Wechseln zwischen Panels.
	 */
	private PanelSwitcher panelSwitcher;

	/**
	 * Dienst zum Verwalten von Panels.
	 */
	private PanelManager panelManager;

	/**
	 * Textfeld zur Eingabe der Matrikelnummer.
	 */
	private JTextField tf_Mnr;

	/**
	 * Textfeld zur Eingabe des Vornamens.
	 */
	private JTextField tf_vorname;

	/**
	 * Textfeld zur Eingabe des Nachnamens.
	 */
	private JTextField tf_nachname;

	/**
	 * Textfeld zur Eingabe der E-Mail.
	 */
	private JTextField tf_Email;

	/**
	 * Textfeld zur Eingabe der Telefonnummer.
	 */
	private JTextField tf_telefonnummer;

	/**
	 * Label zur Anzeige des Studiengangs.
	 */
	private JLabel lblStudiengang;

	/**
	 * Textfeld zur Eingabe des Ortes.
	 */
	private JTextField tf_Ort;

	/**
	 * Textfeld zur Eingabe der Postleitzahl.
	 */
	private JTextField tf_Postleitzahl;

	/**
	 * Textfeld zur Eingabe der Straße.
	 */
	private JTextField tf_Strasse;

	/**
	 * Textfeld zur Eingabe des Benutzernamens.
	 */
	private JTextField tf_benutzername;

	/**
	 * Textfeld zur Eingabe des Passworts.
	 */
	private JPasswordField tf_Passwort;

	/**
	 * Label zur Anzeige der Registrierungsnachricht.
	 */
	private JLabel lbl_registrationAnzeige;


	/**
	 * ActionListener zum Erstellen eines neuen Benutzerkontos.
	 *
	 * @param panelSwitcher     Dienst zum Wechseln zwischen Panels.
	 * @param panelManager      Dienst zum Verwalten von Panels.
	 * @param tf_Mnr            Textfeld zur Eingabe der Matrikelnummer.
	 * @param tf_vorname        Textfeld zur Eingabe des Vornamens.
	 * @param tf_nachname       Textfeld zur Eingabe des Nachnamens.
	 * @param tf_Email          Textfeld zur Eingabe der E-Mail.
	 * @param tf_telefonnummer  Textfeld zur Eingabe der Telefonnummer.
	 * @param lblStudiengang    Label zur Anzeige des Studiengangs.
	 * @param tf_Ort            Textfeld zur Eingabe des Ortes.
	 * @param tf_Postleizahl   Textfeld zur Eingabe der Postleitzahl.
	 * @param tf_Strasse        Textfeld zur Eingabe der Straße.
	 * @param tf_benutzername   Textfeld zur Eingabe des Benutzernamens.
	 * @param tf_Passwort       Textfeld zur Eingabe des Passworts.
	 * @param lbl_registrationAnzeige Label zur Anzeige der Registrierungsnachricht.
	 */
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
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Button zum Erstellen eines neuen Kontos drückt. Sie 
	 * übernimmt die vom Benutzer eingegebenen Informationen, überprüft sie auf Gültigkeit und erstellt dann ein 
	 * neues Studentenobjekt und ein Benutzerkonto in der Datenbank. Wenn die Registrierung erfolgreich ist, 
	 * wird das Panel auf den Studentenbildschirm umgeschaltet. Wenn der Benutzername bereits existiert, 
	 * wird eine Fehlermeldung angezeigt.
	 * @param e Das ausgelöste ActionEvent.
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
	 *
	 * @param password Das Passwort des Benutzers als String.
	 * @return true, wenn alle benötigten Informationen vorhanden sind, sonst false.
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
