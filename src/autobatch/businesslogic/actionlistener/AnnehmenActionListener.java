package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Thema;
import autobatch.dbaccess.Datenbankabfrage;

public class AnnehmenActionListener implements ActionListener {

	private Student student;
	private Betreuer betreuer;

	private Thema thema;

	private JLabel lblPopUp;

	public AnnehmenActionListener(Student student, Betreuer betreuer, Thema thema, JLabel lblPopUp) {
		super();
		this.student = student;
		this.betreuer = betreuer;
		this.thema = thema;
		this.lblPopUp = lblPopUp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		
		datenbankabfrage.updateDataStudentString(student, betreuer.getEmail(), "betreuer");

		datenbankabfrage.updateDataThemaInt(thema, 1, "angenommen");

		datenbankabfrage.updateDataStudentInt(student, thema.getIdThema(), "thema");


		lblPopUp.setVisible(true);

	}

}
