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

	/**
	 * Betreuer-Objekt, dessen Daten gespeichert werden sollen.
	 */
	private Betreuer betreuer; 
	/**
	 * Textfeld, in dem das Passwort des Betreuers eingegeben wird.
	 */
	private JTextField tf_Passwort; 
	/**
	 * Label, das als Pop-Up-Benachrichtigung dient, um den Benutzer zu informieren, ob das Speichern erfolgreich war.
	 */
	private JLabel lblPopUp; 

	/**
	 * Konstruktor
	 *
	 * @param betreuer       Das Betreuer-Objekt, dessen Daten gespeichert werden sollen.
	 * @param tf_Passwort    Das Textfeld, in dem das Passwort des Betreuers eingegeben wird.
	 * @param lblPopUp       Das Label, das als Pop-Up-Benachrichtigung dient, um den Benutzer zu informieren, ob das Speichern erfolgreich war.
	 */
	public DatenSpeichernBetreuerActionListener(Betreuer betreuer, JTextField tf_Passwort, JLabel lblPopUp) {
		super();
		this.betreuer = betreuer;
		this.tf_Passwort = tf_Passwort;
		this.lblPopUp = lblPopUp;
	}

	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Button zum Speichern der Daten drückt. Es überprüft, ob das
	 * Passwort-Feld gefüllt ist. Wenn ja, wird das Passwort des Betreuers aktualisiert und in der Datenbank gespeichert.
	 * Ein Pop-Up-Label wird verwendet, um den Benutzer zu informieren, ob das Speichern erfolgreich war.
	 * @param e Das ausgelöste ActionEvent.
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
