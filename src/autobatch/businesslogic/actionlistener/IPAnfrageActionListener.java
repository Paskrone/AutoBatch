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

public class IPAnfrageActionListener implements ActionListener {

	private Arbeit arbeit;
	private Student student;
	private JTextField txtDatum;
	private JTextField txtThema;
	private JTextField txtUN;
	private JTextField txtBeschreibung;
	private JLabel lblPopUp;

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

	@Override
	public void actionPerformed(ActionEvent e) {

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		String date = txtDatum.getText();
		String thema = txtThema.getText();
		String unternehmen = txtUN.getText();
		String beschreibung = txtBeschreibung.getText();

		if (!date.equals("") && thema != null && unternehmen != null && beschreibung != null) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate datum = LocalDate.parse(date, formatter);
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

				lblPopUp.setText("Anfrage wurde geschickt!");
				lblPopUp.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				lblPopUp.setVisible(true);
			}

		} else {
			lblPopUp.setVisible(true);
		}

	}

}
