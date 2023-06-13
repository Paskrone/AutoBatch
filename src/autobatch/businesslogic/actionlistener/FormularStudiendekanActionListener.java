package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;

/**
 * ActionListener zur Verarbeitung des Formulars des Studiendekans.
 */
public class FormularStudiendekanActionListener implements ActionListener {

	/**
	 * Textfeld zur Eingabe des Ausgabetermins der Arbeit.
	 */
	private JTextField tf_Ausgabetermin;

	/**
	 * Textfeld zur Eingabe des Abgabetermins der Arbeit.
	 */
	private JTextField tf_Abgabetermin;

	/**
	 * JLabel, das dazu dient, Pop-up-Nachrichten anzuzeigen.
	 */
	private JLabel lblPopUp;

	/**
	 * Arbeit-Objekt, das die Arbeit repräsentiert, die vom Studiendekan verwaltet wird.
	 */
	private Arbeit arbeit;

	/**
	 * ActionListener zur Verarbeitung des Formulars des Studiendekans.
	 *
	 * @param tf_Ausgabetermin   Das Textfeld zur Eingabe des Ausgabetermins der Arbeit.
	 * @param tf_Abgabetermin    Das Textfeld zur Eingabe des Abgabetermins der Arbeit.
	 * @param lblPopUp           Das JLabel, das dazu dient, Pop-up-Nachrichten anzuzeigen.
	 * @param arbeit             Das Arbeit-Objekt, das die Arbeit repräsentiert, die vom Studiendekan verwaltet wird.
	 */
	public FormularStudiendekanActionListener(JTextField tf_Ausgabetermin, JTextField tf_Abgabetermin, JLabel lblPopUp,
			Arbeit arbeit) {
		super();
		this.tf_Ausgabetermin = tf_Ausgabetermin;
		this.tf_Abgabetermin = tf_Abgabetermin;
		this.lblPopUp = lblPopUp;
		this.arbeit = arbeit;
	}


	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Button zum Absenden des Formulars drückt.
	 * Sie überprüft zunächst, ob die Textfelder für das Abgabe- und das Ausgabedatum nicht leer sind.
	 * Wenn diese Bedingungen erfüllt sind, werden die Daten aus den Textfeldern geholt und in LocalDate-Objekte umgewandelt.
	 * Anschließend werden die Instanzvariablen des Arbeit-Objekts aktualisiert.
	 * Danach wird eine neue Datenbankabfrage erzeugt und die Daten in der Datenbank aktualisiert.
	 * Zum Schluss wird eine Nachricht für den Benutzer angezeigt, dass das Formular abgeschickt wurde.
	 * @param e Das ausgelöste ActionEvent.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// Prüfen, ob die Textfelder nicht leer sind
		if (!tf_Abgabetermin.getText().equals("") && !tf_Ausgabetermin.getText().equals("")) {
			String dateAusgabetermin = tf_Ausgabetermin.getText();
			String dateAbgabetermin = tf_Abgabetermin.getText();
			try {
				// Umwandlung der Datumseingaben in LocalDate-Objekte
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate ausgabetermin = LocalDate.parse(dateAusgabetermin, formatter);
				LocalDate abgabetermin = LocalDate.parse(dateAbgabetermin, formatter);

				// Aktualisieren der Arbeit-Instanzvariablen
				arbeit.setBaAbgabetermin(abgabetermin);
				arbeit.setBaStart(ausgabetermin);
				arbeit.setBa_Anmeldung_Studiendekan(true);

				// Erzeugen eines neuen Datenbankzugriffs
				Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

				// Aktualisieren der Daten in der Datenbank
				datenbankabfrage.updateDataArbeitDate(arbeit, abgabetermin, "baAbgabetermin");
				datenbankabfrage.updateDataArbeitDate(arbeit, ausgabetermin, "baStart");
				datenbankabfrage.updateDataArbeitBoolean(arbeit, true, "ba_Anmeldung_Studiendekan");

				// Anzeigen einer Nachricht für den Benutzer
				lblPopUp.setText("Abgeschickt.");

			} catch (Exception ex) {
				// Fehlerbehandlung
				ex.printStackTrace();
			}

		}

		// Anzeigen des Pop-Up-Labels
		lblPopUp.setVisible(true);

	}

}
