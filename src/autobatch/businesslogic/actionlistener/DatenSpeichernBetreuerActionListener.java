package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Betreuer;
import autobatch.dbaccess.Datenbankabfrage;

/**
 * ActionListener zum Speichern der Betreuerdaten.
 */
public class DatenSpeichernBetreuerActionListener implements ActionListener {

	// Instanzvariablen
	private Betreuer betreuer; // Betreuer, dessen Daten gespeichert werden sollen
	private JTextField tf_Passwort; // Textfeld zur Eingabe des neuen Passworts
	private JLabel lblPopUp; // Label zur Anzeige von Benachrichtigungen

	// Konstruktor
	public DatenSpeichernBetreuerActionListener(Betreuer betreuer, JTextField tf_Passwort, JLabel lblPopUp) {
		super();
		this.betreuer = betreuer;
		this.tf_Passwort = tf_Passwort;
		this.lblPopUp = lblPopUp;
	}

	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Button zum Speichern der Daten drückt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Überprüfen, ob das Passwort-Feld gefüllt ist
		if (!tf_Passwort.getText().equals("")) {
			String passwort = tf_Passwort.getText();
			betreuer.setPasswort(passwort);

			// Erzeugen eines neuen Datenbankzugriffs und aktualisieren des Passworts in der Datenbank
			Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
			if (datenbankabfrage.updateDataBetreuerString(betreuer, passwort, "Passwort")) {
				lblPopUp.setText("gespeichert!");
			}
		}

		lblPopUp.setVisible(true);

	}

}
