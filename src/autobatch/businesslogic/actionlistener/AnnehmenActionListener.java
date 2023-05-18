package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;

public class AnnehmenActionListener implements ActionListener {

	private Student student;
	private Betreuer betreuer;

	private Arbeit arbeit;

	private JLabel lblPopUp;

	public AnnehmenActionListener(Student student, Betreuer betreuer, Arbeit arbeit, JLabel lblPopUp) {
		super();
		this.student = student;
		this.betreuer = betreuer;
		this.arbeit = arbeit;
		this.lblPopUp = lblPopUp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		
		datenbankabfrage.updateDataStudentString(student, betreuer.getEmail(), "betreuer");

		datenbankabfrage.updateDataArbeitInt(arbeit, 1, "angenommen");

		datenbankabfrage.updateDataStudentInt(student, arbeit.getIdArbeit(), "arbeit");


		lblPopUp.setVisible(true);

	}

}
