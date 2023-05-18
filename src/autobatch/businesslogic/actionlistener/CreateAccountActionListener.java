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

public class CreateAccountActionListener implements ActionListener {

	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;

	private JTextField tf_Mnr;
	private JTextField tf_vorname;
	private JTextField tf_nachname;
	private JTextField tf_Email;
	private JTextField tf_telefonnummer;
	private JTextField tf_Studiengang;
	private JTextField tf_Ort;
	private JTextField tf_Postleitzahl;
	private JTextField tf_Strasse;
	private JTextField tf_benutzername;
	private JPasswordField tf_Passwort;
	private JLabel lbl_registrationAnzeige;
	private SessionManager sessionManager;

	public CreateAccountActionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, JTextField tf_Mnr,
			JTextField tf_vorname, JTextField tf_nachname, JTextField tf_Email, JTextField tf_telefonnummer,
			JTextField tf_Studiengang, JTextField tf_Ort, JTextField tf_Postleizahl, JTextField tf_Strasse,
			JTextField tf_benutzername, JPasswordField tf_Passwort, JLabel lbl_registrationAnzeige) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.panelManager = panelManager;
		this.tf_Mnr = tf_Mnr;
		this.tf_vorname = tf_vorname;
		this.tf_nachname = tf_nachname;
		this.tf_Email = tf_Email;
		this.tf_telefonnummer = tf_telefonnummer;
		this.tf_Studiengang = tf_Studiengang;
		this.tf_Ort = tf_Ort;
		this.tf_Postleitzahl = tf_Postleizahl;
		this.tf_Strasse = tf_Strasse;
		this.tf_benutzername = tf_benutzername;
		this.tf_Passwort = tf_Passwort;
		this.lbl_registrationAnzeige = lbl_registrationAnzeige;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		char[] passwordChars = tf_Passwort.getPassword();
		String password = new String(passwordChars);

		if (check(password)) {

			String mnr = tf_Mnr.getText();
			int mnrInt = Integer.parseInt(mnr);

			int telefonnummer = Integer.parseInt(tf_telefonnummer.getText());

			int postleizahl = Integer.parseInt(tf_Postleitzahl.getText());

			Datenbankabfrage dbaccess = new Datenbankabfrage();
			if (dbaccess.registerStudent(new Student(mnrInt, tf_vorname.getText(), tf_nachname.getText(), password,
					tf_benutzername.getText(), tf_Email.getText(), telefonnummer, tf_Studiengang.getText(),
					tf_Ort.getText(), tf_Strasse.getText(), postleizahl, null, null))) {
				//Benutzer speichern
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
