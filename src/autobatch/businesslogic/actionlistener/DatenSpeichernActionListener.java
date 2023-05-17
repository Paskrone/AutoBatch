package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelSwitcher;

public class DatenSpeichernActionListener implements ActionListener{
	
	
	private Student student;
	
	private JTextField tf_ort;
	private JTextField tf_postleizahl;
	private JTextField tf_strasse;
	private JTextField tf_telefon;
	private JTextField tf_passwort;
	
	private JLabel lblPopUp;

	public DatenSpeichernActionListener(Student student, PanelSwitcher panelSwitcher, JTextField tf_ort, JTextField tf_postleizahl,
			JTextField tf_strasse, JTextField tf_telefon, JTextField tf_passwort, JLabel lblPopUp) {
		super();
		this.student = student;
		this.tf_ort = tf_ort;
		this.tf_postleizahl = tf_postleizahl;
		this.tf_strasse = tf_strasse;
		this.tf_telefon = tf_telefon;
		this.tf_passwort = tf_passwort;
		
		this.lblPopUp = lblPopUp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ort = tf_ort.getText();
		String strasse = tf_strasse.getText();
		int postleitzahl = Integer.parseInt(tf_postleizahl.getText());
		long telefon = Long.parseLong(tf_telefon.getText());
		String passwort = tf_passwort.getText();
		
		Datenbankabfrage dbaccess = new Datenbankabfrage();
		if (dbaccess.updateDataStudentString(student, ort, "ort")) {
			student.setOrt(ort);
		}
		if (dbaccess.updateDataStudentString(student, strasse, "strasse")) {
			student.setStrasse(strasse);
		}
		if (dbaccess.updateDataStudentInt(student, postleitzahl, "postleizahl")) {
			student.setPostleizahl(postleitzahl);
		}
		if (dbaccess.updateDataStudentLong(student, telefon, "telefonnummer")) {
			student.setTelefonnummer(telefon);
		}
		if (dbaccess.updateDataStudentString(student, passwort, "passwort")) {
			student.setPasswort(passwort);
		}
		

		lblPopUp.setVisible(true);

	}
	
	

}
