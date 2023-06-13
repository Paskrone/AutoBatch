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

	/**
	 * Boolean-Wert, der bestimmt, ob die Arbeit vom Betreuer angenommen wurde oder nicht.
	 */
	private boolean ja; 
	/**
	 * Boolean-Wert, der bestimmt, ob der Button zum Absenden des Formulars geklickt wurde oder nicht.
	 */
	private boolean clicked; 
	/**
	 * Textfeld zur Eingabe des Themas der Arbeit.
	 */
	private JTextField tf_Thema; 
	/**
	 * Textfeld zur Eingabe des Ausgabetermins der Arbeit.
	 */
	private JTextField tf_Ausgabetermin; 
	/**
	 * Textfeld zur Eingabe des Abgabetermins der Arbeit.
	 */
	private JTextField tf_Abgabetermin; 
	/**
	 * Label zur Anzeige von Benachrichtigungen, ob das Formular erfolgreich abgesendet wurde.
	 */
	private JLabel lblPopUp; 
	/**
	 * Die Arbeit, deren Daten im Formular eingegeben und anschließend aktualisiert werden.
	 */
	private Arbeit arbeit;
	
	
	/**
	 * ActionListener zur Verarbeitung des Betreuerformulars.
	 *
	 * @param ja                 Boolean-Wert, der bestimmt, ob die Arbeit vom Betreuer angenommen wurde oder nicht.
	 * @param clicked            Boolean-Wert, der bestimmt, ob der Button zum Absenden des Formulars geklickt wurde oder nicht.
	 * @param tf_Thema           Das Textfeld zur Eingabe des Themas der Arbeit.
	 * @param tf_Ausgabetermin   Das Textfeld zur Eingabe des Ausgabetermins der Arbeit.
	 * @param tf_Abgabetermin    Das Textfeld zur Eingabe des Abgabetermins der Arbeit.
	 * @param lblPopUp           Das Label zur Anzeige von Benachrichtigungen, ob das Formular erfolgreich abgesendet wurde.
	 * @param arbeit             Die Arbeit, deren Daten im Formular eingegeben und anschließend aktualisiert werden.
	 */
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
	 * Sie überprüft zunächst, ob der Button geklickt wurde und ob keines der Textfelder leer ist.
	 * Wenn diese Bedingungen erfüllt sind, holt sie die Daten aus den Textfeldern und verwandelt sie in geeignete Formate (LocalDate für die Termine, String für das Thema).
	 * Anschließend aktualisiert sie die Instanzvariablen des Arbeit-Objekts und führt verschiedene Datenbank-Update-Operationen durch, um die Daten in der Datenbank zu aktualisieren.
	 * Wenn der Vorgang abgeschlossen ist, wird die Benachrichtigung auf "Abgeschickt." gesetzt und sichtbar gemacht.
	 * @param e Das ausgelöste ActionEvent.
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
