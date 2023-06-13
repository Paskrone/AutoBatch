package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;

/**
 * ActionListener zur Verarbeitung des Studentenformulars.
 */
public class FormularStudentActionListener implements ActionListener {
	
	/**
	 * Entscheidungsvariable, die einen booleschen Zustand speichert.
	 */
	private boolean ja;

	/**
	 * Variable, die den Klickzustand speichert. Sie ist 'true', wenn der Button geklickt wurde.
	 */
	private boolean clicked;

	/**
	 * Textfeld zur Eingabe der Telefonnummer des Studenten.
	 */
	private JTextField tf_telefon;

	/**
	 * Textfeld zur Eingabe des Semesters des Studenten.
	 */
	private JTextField tf_semester;

	/**
	 * JLabel, das dazu dient, Pop-up-Nachrichten anzuzeigen.
	 */
	private JLabel lblPopUp;

	/**
	 * Arbeit-Objekt, das die Arbeit des Studenten repräsentiert.
	 */
	private Arbeit arbeit;

	/**
	 * Student-Objekt, das den Studenten repräsentiert, der das Formular ausfüllt.
	 */
	private Student student; 

	/**
	 * Konstruktor
	 *
	 * @param student      Das Student-Objekt, das den Studenten repräsentiert, der das Formular ausfüllt.
	 * @param arbeit       Das Arbeit-Objekt, das die Arbeit des Studenten repräsentiert.
	 * @param ja           Entscheidungsvariable, die einen booleschen Zustand speichert.
	 * @param clicked      Variable, die den Klickzustand speichert. Sie ist 'true', wenn der Button geklickt wurde.
	 * @param tf_telefon   Das Textfeld zur Eingabe der Telefonnummer des Studenten.
	 * @param tf_semester  Das Textfeld zur Eingabe des Semesters des Studenten.
	 * @param lblPopUp     Das JLabel, das dazu dient, Pop-up-Nachrichten anzuzeigen.
	 */
	public FormularStudentActionListener(Student student, Arbeit arbeit, boolean ja, boolean clicked,
			JTextField tf_telefon, JTextField tf_semester, JLabel lblPopUp) {
		super();
		this.student = student;
		this.arbeit = arbeit;
		this.ja = ja;
		this.clicked = clicked;
		this.tf_telefon = tf_telefon;
		this.tf_semester = tf_semester;
		this.lblPopUp = lblPopUp;
	}

	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Button zum Absenden des Formulars drückt.
	 * Sie überprüft zunächst, ob der Button geklickt wurde und ob keines der Textfelder (Telefon und Semester) leer ist.
	 * Wenn diese Bedingungen erfüllt sind, werden die Daten aus den Textfeldern geholt und in geeignete Formate umgewandelt (long für die Telefonnummer, int für das Semester).
	 * Anschließend werden die Instanzvariablen des Student- und Arbeit-Objekts aktualisiert.
	 * Danach werden verschiedene Update-Operationen auf der Datenbank durchgeführt, um die Daten des Studenten und der Arbeit zu aktualisieren.
	 * Zum Schluss wird eine Nachricht für den Benutzer angezeigt, dass das Formular abgeschickt wurde.
	 * @param e Das ausgelöste ActionEvent.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

		// Prüfen, ob der Button geklickt wurde und keines der Textfelder leer ist
		if (clicked && !tf_telefon.getText().equals("") && !tf_semester.getText().equals("")) {
			// Daten aus den Textfeldern holen und umwandeln
			long telefonnummer = Long.parseLong(tf_telefon.getText());
			int semester = Integer.parseInt(tf_semester.getText());

			// Aktualisieren der Student- und Arbeit-Instanzvariablen
			student.setTelefonnummer(telefonnummer);
			student.setSemester(semester);

			arbeit.setVeroeffentlichung(ja);
			arbeit.setBa_Anmeldung_Student(true);

			// Aktualisieren der Daten in der Datenbank
			datenbankabfrage.updateDataStudentLong(student, telefonnummer, "telefonnummer");
			datenbankabfrage.updateDataStudentInt(student, semester, "semester");
			datenbankabfrage.updateDataArbeitBoolean(arbeit, ja, "veroeffentlichung");
			datenbankabfrage.updateDataArbeitBoolean(arbeit, true, "ba_Anmeldung_Student");

			// Anzeigen einer Nachricht für den Benutzer
			lblPopUp.setText("Abgeschickt.");

		}
		
		lblPopUp.setVisible(true);

	}

}
