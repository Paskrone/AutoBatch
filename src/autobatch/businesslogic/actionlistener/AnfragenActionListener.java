package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;

public class AnfragenActionListener implements ActionListener {

	private JTextField tf_unternehmen;
	private JTextField tf_thema;
	private JTextField tf_beschreibung;

	private Student student;
	private Betreuer betreuer;
	
	private JLabel lblPopUp;
	private JLabel lbl_error;

	public AnfragenActionListener(JTextField tf_unternehmen, JTextField tf_thema, JTextField tf_beschreibung,
			Student student, Betreuer betreuer, JLabel lbl_error, JLabel lblPopUp) {
		super();
		this.tf_unternehmen = tf_unternehmen;
		this.tf_thema = tf_thema;
		this.tf_beschreibung = tf_beschreibung;
		this.student = student;
		this.betreuer = betreuer;
		
		this.lblPopUp = lblPopUp;
		this.lbl_error = lbl_error;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String unternehmen = tf_unternehmen.getText();
		String thema = tf_thema.getText();
		String beschreibung = tf_beschreibung.getText();

		Datenbankabfrage dbaccess = new Datenbankabfrage();
		
		
		if (dbaccess.deleteDataArbeit(student.getMnr(), betreuer.getEmail()) && !dbaccess.setDataArbeit(student, betreuer, thema, unternehmen, beschreibung)) {
			lbl_error.setVisible(true);
		} else {
			lblPopUp.setVisible(true);
		}

	}

}
