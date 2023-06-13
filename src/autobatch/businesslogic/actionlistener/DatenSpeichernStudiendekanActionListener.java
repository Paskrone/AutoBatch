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

	/**
	 * Der Studiendekan, dessen Daten aktualisiert werden sollen.
	 */
	private Studiendekan studiendekan; 
	/**
	 * Textfeld zur Eingabe des neuen Passworts des Studiendekans.
	 */
	private JTextField tf_Passwort; 
	/**
	 * Label zur Anzeige von Benachrichtigungen, ob die Datenaktualisierung erfolgreich war.
	 */
	private JLabel lblPopUp; 

	/**
	 * Konstruktor
	 *
	 * @param studiendekan  Der Studiendekan, dessen Daten aktualisiert werden sollen.
	 * @param tf_Passwort   Das Textfeld zur Eingabe des neuen Passworts des Studiendekans.
	 * @param lblPopUp      Das Label zur Anzeige von Benachrichtigungen, ob die Datenaktualisierung erfolgreich war.
	 */
	public DatenSpeichernStudiendekanActionListener(Studiendekan studiendekan, JTextField tf_Passwort,
			JLabel lblPopUp) {
		super();
		this.studiendekan = studiendekan;
		this.tf_Passwort = tf_Passwort;
		this.lblPopUp = lblPopUp;
	}
	
	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Button zum Speichern der Daten drückt.
	 * Sie überprüft zunächst, ob das Textfeld für das Passwort nicht leer ist.
	 * Wenn es nicht leer ist, wird das neue Passwort aus dem Textfeld abgerufen und das Passwort des Studiendekan-Objekts aktualisiert.
	 * Anschließend wird ein neuer Datenbankzugriff erzeugt und das Passwort in der Datenbank aktualisiert.
	 * Wenn die Aktualisierung erfolgreich ist, wird die Benachrichtigung auf "gespeichert!" gesetzt und sichtbar gemacht.
	 * @param e Das ausgelöste ActionEvent.
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
