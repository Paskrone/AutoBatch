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
	
	// Instanzvariablen
	private boolean ja; // Entscheidungsvariable
	private boolean clicked; // Variablenstatus für den Klickzustand

	private JTextField tf_telefon; // Textfeld zur Eingabe der Telefonnummer
	private JTextField tf_semester; // Textfeld zur Eingabe des Semesters

	private JLabel lblPopUp; // Label zur Anzeige von Benachrichtigungen

	private Arbeit arbeit; // Arbeit, die verarbeitet werden soll
	private Student student; // Student, der bearbeitet wird

	// Konstruktor
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
