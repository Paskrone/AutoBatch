package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelSwitcher;

/**
 * ActionListener zum Speichern der Studentendaten.
 */
public class DatenSpeichernStudentActionListener implements ActionListener{
	
	// Instanzvariablen
	private Student student; // Student, dessen Daten gespeichert werden sollen
	
	private JTextField tf_ort; // Textfeld zur Eingabe des neuen Wohnorts
	private JTextField tf_postleizahl; // Textfeld zur Eingabe der neuen Postleitzahl
	private JTextField tf_strasse; // Textfeld zur Eingabe der neuen Straße
	private JTextField tf_telefon; // Textfeld zur Eingabe der neuen Telefonnummer
	private JTextField tf_passwort; // Textfeld zur Eingabe des neuen Passworts
	
	private JLabel lblPopUp; // Label zur Anzeige von Benachrichtigungen
	
	// Konstruktor
	public DatenSpeichernStudentActionListener(Student student, PanelSwitcher panelSwitcher, JTextField tf_ort, JTextField tf_postleizahl,
			JTextField tf_strasse, JTextField tf_telefon, JTextField tf_passwort, JLabel lblPopUp) {
		super();
		this.student = student;
		this.tf_ort = tf_ort;
		this.tf_postleizahl = tf_postleizahl;
		this.tf_strasse = tf_strasse;
		this.tf_telefon = tf_telefon;
		this.tf_passwort = tf_passwort;
		
		this.lblPopUp = lblPopUp;
	}

	
	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Button zum Speichern der Daten drückt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Abrufen der neuen Daten aus den Textfeldern
		String ort = tf_ort.getText();
		String strasse = tf_strasse.getText();
		int postleitzahl = Integer.parseInt(tf_postleizahl.getText());
		long telefon = Long.parseLong(tf_telefon.getText());
		String passwort = tf_passwort.getText();
		
		// Erzeugen eines neuen Datenbankzugriffs und aktualisieren der Daten in der Datenbank
		Datenbankabfrage dbaccess = new Datenbankabfrage();
		// Überprüfen und aktualisieren des Orts
		if (dbaccess.updateDataStudentString(student, ort, "ort")) {
			student.setOrt(ort);
		}
		// Überprüfen und aktualisieren der Straße
		if (dbaccess.updateDataStudentString(student, strasse, "strasse")) {
			student.setStrasse(strasse);
		}
		// Überprüfen und aktualisieren der Postleitzahl
		if (dbaccess.updateDataStudentInt(student, postleitzahl, "postleizahl")) {
			student.setPostleizahl(postleitzahl);
		}
		// Überprüfen und aktualisieren der Telefonnummer
		if (dbaccess.updateDataStudentLong(student, telefon, "telefonnummer")) {
			student.setTelefonnummer(telefon);
		}
		// Überprüfen und aktualisieren des Passworts
		if (dbaccess.updateDataStudentString(student, passwort, "passwort")) {
			student.setPasswort(passwort);
		}
		

		lblPopUp.setVisible(true);

	}
	
	

}
