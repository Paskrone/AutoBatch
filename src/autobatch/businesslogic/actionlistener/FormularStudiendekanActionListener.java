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

	// Instanzvariablen
	private JTextField tf_Ausgabetermin; // Textfeld für das Ausgabedatum
	private JTextField tf_Abgabetermin; // Textfeld für das Abgabedatum

	private JLabel lblPopUp; // Label zur Anzeige von Benachrichtigungen

	private Arbeit arbeit; // Arbeit, die verarbeitet werden soll

	// Konstruktor
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
