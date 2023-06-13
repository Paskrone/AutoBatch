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

	/**
	 * Arbeit-Objekt, das die Arbeit repräsentiert, die vom Studenten verwaltet wird.
	 */
	private Arbeit arbeit;

	/**
	 * Student-Objekt, das den Studenten repräsentiert, der die Anfrage stellt.
	 */
	private Student student;

	/**
	 * Textfeld zur Eingabe des Datums der Anfrage.
	 */
	private JTextField txtDatum;

	/**
	 * Textfeld zur Eingabe des Themas der Anfrage.
	 */
	private JTextField txtThema;

	/**
	 * Textfeld zur Eingabe des Unternehmens der Anfrage.
	 */
	private JTextField txtUN;

	/**
	 * Textfeld zur Eingabe der Beschreibung der Anfrage.
	 */
	private JTextField txtBeschreibung;

	/**
	 * JLabel, das dazu dient, Pop-up-Nachrichten anzuzeigen.
	 */
	private JLabel lblPopUp;


	/**
	 * Konstruktor
	 *
	 * @param arbeit          Das Arbeit-Objekt, das die Arbeit repräsentiert, die vom Studenten verwaltet wird.
	 * @param student         Das Student-Objekt, das den Studenten repräsentiert, der die Anfrage stellt.
	 * @param txtDatum        Das Textfeld zur Eingabe des Datums der Anfrage.
	 * @param txtThema        Das Textfeld zur Eingabe des Themas der Anfrage.
	 * @param txtUN           Das Textfeld zur Eingabe des Unternehmens der Anfrage.
	 * @param txtBeschreibung Das Textfeld zur Eingabe der Beschreibung der Anfrage.
	 * @param lblPopUp        Das JLabel, das dazu dient, Pop-up-Nachrichten anzuzeigen.
	 */
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
	 * Sie überprüft zunächst, ob die Textfelder für das Datum, das Thema, das Unternehmen und die Beschreibung nicht leer sind.
	 * Wenn diese Bedingungen erfüllt sind, werden die Daten aus den Textfeldern geholt und das Datum wird in ein LocalDate-Objekt umgewandelt.
	 * Anschließend werden die Instanzvariablen des Arbeit-Objekts aktualisiert, wenn sie sich von den aktuellen Werten unterscheiden.
	 * Danach wird eine neue Datenbankabfrage erzeugt und die Daten in der Datenbank aktualisiert.
	 * Zum Schluss wird eine Nachricht für den Benutzer angezeigt, dass die Anfrage abgeschickt wurde.
	 * @param e Das ausgelöste ActionEvent.
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
