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
 * ActionListener zur Verarbeitung der IP-Anfrage.
 */
public class IPAnfrageActionListener implements ActionListener {

	// Instanzvariablen
	private Arbeit arbeit; // Arbeit, die verarbeitet werden soll
	private Student student; // Student, der die Anfrage stellt
	private JTextField txtDatum; // Textfeld für das Datum
	private JTextField txtThema; // Textfeld für das Thema
	private JTextField txtUN; // Textfeld für das Unternehmen
	private JTextField txtBeschreibung; // Textfeld für die Beschreibung
	private JLabel lblPopUp; // Label zur Anzeige von Benachrichtigungen

	// Konstruktor
	public IPAnfrageActionListener(Arbeit arbeit, Student student, JTextField txtDatum, JTextField txtThema,
			JTextField txtUN, JTextField txtBeschreibung, JLabel lblPopUp) {
		super();
		this.arbeit = arbeit;
		this.student = student;
		this.txtDatum = txtDatum;
		this.txtThema = txtThema;
		this.txtUN = txtUN;
		this.txtBeschreibung = txtBeschreibung;
		this.lblPopUp = lblPopUp;
	}

	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Button zum Absenden der Anfrage drückt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// Erzeugen eines neuen Datenbankzugriffs
		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		String date = txtDatum.getText();
		String thema = txtThema.getText();
		String unternehmen = txtUN.getText();
		String beschreibung = txtBeschreibung.getText();

		// Prüfen, ob die Textfelder nicht leer sind
		if (!date.equals("") && thema != null && unternehmen != null && beschreibung != null) {
			try {
				// Umwandlung des Datums in ein LocalDate-Objekt
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate datum = LocalDate.parse(date, formatter);
				
				// Prüfen und Aktualisieren der einzelnen Arbeit-Attribute
				if (!thema.equals(arbeit.getThema())) {
					arbeit.setThema(thema);
					datenbankabfrage.updateDataArbeitString(arbeit, thema, "thema");
				}
				if (!unternehmen.equals(arbeit.getUnternehmen())) {
					arbeit.setUnternehmen(unternehmen);
					datenbankabfrage.updateDataArbeitString(arbeit, unternehmen, "unternehmen");
				}
				if (!beschreibung.equals(arbeit.getBeschreibung())) {
					arbeit.setBeschreibung(beschreibung);
					datenbankabfrage.updateDataArbeitString(arbeit, beschreibung, "beschreibung");
				}
				arbeit.setIpStart(datum);
				datenbankabfrage.updateDataArbeitDate(arbeit, datum, "ipStart");

				// Anzeigen einer Nachricht für den Benutzer
				lblPopUp.setText("Anfrage wurde geschickt!");
				lblPopUp.setVisible(true);
			} catch (Exception ex) {
				// Fehlerbehandlung
				ex.printStackTrace();
				lblPopUp.setVisible(true);
			}

		} else {
			lblPopUp.setVisible(true);
		}

	}

}
