package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;

/**
 * ActionListener zur Verarbeitung des Betreuerformulars.
 */
public class FormularBetreuerActionListener implements ActionListener {

	// Instanzvariablen
	private boolean ja; // Entscheidungsvariable
	private boolean clicked; // Variablenstatus für den Klickzustand

	private JTextField tf_Thema; // Textfeld zur Eingabe des Themas
	private JTextField tf_Ausgabetermin; // Textfeld zur Eingabe des Ausgabetermins
	private JTextField tf_Abgabetermin; // Textfeld zur Eingabe des Abgabetermins

	private JLabel lblPopUp; // Label zur Anzeige von Benachrichtigungen

	private Arbeit arbeit; // Arbeit, die verarbeitet werden soll
	
	
	// Konstruktor
	public FormularBetreuerActionListener(boolean ja, boolean clicked, JTextField tf_Thema, JTextField tf_Ausgabetermin,
			JTextField tf_Abgabetermin, JLabel lblPopUp, Arbeit arbeit) {
		super();
		this.ja = ja;
		this.clicked = clicked;
		this.tf_Thema = tf_Thema;
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
		// Prüfen, ob der Button geklickt wurde und keines der Textfelder leer ist
		if (clicked && !tf_Abgabetermin.getText().equals("") && !tf_Ausgabetermin.getText().equals("")
				&& !tf_Thema.getText().equals("")) {
			// Daten aus den Textfeldern holen
			String dateAusgabetermin = tf_Ausgabetermin.getText();
			String dateAbgabetermin = tf_Abgabetermin.getText();
			try {
				// Umwandlung der Textfelddaten in LocalDate-Objekte
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate ausgabetermin = LocalDate.parse(dateAusgabetermin, formatter);
				LocalDate abgabetermin = LocalDate.parse(dateAbgabetermin, formatter);
				
				// Thema aus Textfeld holen
				String thema = tf_Thema.getText();
				
				// Aktualisieren der Arbeit-Instanzvariablen
				arbeit.setThema(thema);
				arbeit.setBaAbgabetermin(abgabetermin);
				arbeit.setBaStart(ausgabetermin);
				arbeit.setIpBestanden(ja);
				arbeit.setBa_Anmeldung_Studiendekan(true);
				
				// Erzeugen eines neuen Datenbankzugriffs und Aktualisieren der Daten in der Datenbank
				Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

				// Ausführen der verschiedenen Update-Methode für verschiedene Datentypen
				datenbankabfrage.updateDataArbeitString(arbeit, thema, "thema");
				
				// ausgabetermin und abgabetermin erweitern
				
				datenbankabfrage.updateDataArbeitDate(arbeit, abgabetermin, "baAbgabetermin");
				datenbankabfrage.updateDataArbeitDate(arbeit, ausgabetermin, "baStart");
				
				datenbankabfrage.updateDataArbeitBoolean(arbeit, ja, "ipBestanden");
				
				datenbankabfrage.updateDataArbeitBoolean(arbeit, true, "ba_Anmeldung_Betreuer");

				lblPopUp.setText("Abgeschickt.");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}

		lblPopUp.setVisible(true);

	}

}
