package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Studiendekan;
import autobatch.dbaccess.Datenbankabfrage;
/**
 * ActionListener zum Speichern der Studiendekan-Daten.
 */
public class DatenSpeichernStudiendekanActionListener implements ActionListener {

	// Instanzvariablen
	private Studiendekan studiendekan; // Studiendekan, dessen Daten gespeichert werden sollen

	private JTextField tf_Passwort; // Textfeld zur Eingabe des neuen Passworts
	private JLabel lblPopUp; // Label zur Anzeige von Benachrichtigungen

	// Konstruktor
	public DatenSpeichernStudiendekanActionListener(Studiendekan studiendekan, JTextField tf_Passwort,
			JLabel lblPopUp) {
		super();
		this.studiendekan = studiendekan;
		this.tf_Passwort = tf_Passwort;
		this.lblPopUp = lblPopUp;
	}
	
	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Button zum Speichern der Daten drückt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// Überprüfen, ob das Textfeld für das Passwort nicht leer ist
		if (!tf_Passwort.getText().equals("")) {
			// Abrufen des neuen Passworts aus dem Textfeld
			String passwort = tf_Passwort.getText();
			// Aktualisieren des Passworts im Studiendekan-Objekt
			studiendekan.setPasswort(passwort);

			// Erzeugen eines neuen Datenbankzugriffs und aktualisieren des Passworts in der Datenbank
			Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
			if (datenbankabfrage.updateDataStudiendekanString(studiendekan, passwort, "Passwort")) {
				// Setzen der Benachrichtigung auf "gespeichert!", wenn das Passwort erfolgreich aktualisiert wurde
				lblPopUp.setText("gespeichert!");

			}
		}

		lblPopUp.setVisible(true);

	}

}
