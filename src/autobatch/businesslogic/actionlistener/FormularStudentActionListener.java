package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;

public class FormularStudentActionListener implements ActionListener {

	private boolean ja;
	private boolean clicked;

	private JTextField tf_telefon;
	private JTextField tf_semester;

	private JLabel lblPopUp;

	private Arbeit arbeit;
	private Student student;

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

		if (clicked && !tf_telefon.getText().equals("") && !tf_semester.getText().equals("")) {
			long telefonnummer = Long.parseLong(tf_telefon.getText());
			int semester = Integer.parseInt(tf_semester.getText());
			
			student.setTelefonnummer(telefonnummer);
			student.setSemester(semester);
			
			arbeit.setVeroeffentlichung(ja);
			arbeit.setBa_Anmeldung_Student(true);

			datenbankabfrage.updateDataStudentLong(student, telefonnummer, "telefonnummer");
			datenbankabfrage.updateDataStudentInt(student, semester, "semseter");
			datenbankabfrage.updateDataArbeitBoolean(arbeit, ja, "veroeffentlichung");
			datenbankabfrage.updateDataArbeitBoolean(arbeit, true, "ba_Anmeldung_Student");

			lblPopUp.setText("Abgeschickt.");

		}
		
		lblPopUp.setVisible(true);

	}

}
