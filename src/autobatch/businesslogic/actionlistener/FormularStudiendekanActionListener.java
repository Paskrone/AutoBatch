package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;

public class FormularStudiendekanActionListener implements ActionListener {

	private JTextField tf_Ausgabetermin;
	private JTextField tf_Abgabetermin;

	private JLabel lblPopUp;

	private Arbeit arbeit;

	public FormularStudiendekanActionListener(JTextField tf_Ausgabetermin, JTextField tf_Abgabetermin, JLabel lblPopUp,
			Arbeit arbeit) {
		super();
		this.tf_Ausgabetermin = tf_Ausgabetermin;
		this.tf_Abgabetermin = tf_Abgabetermin;
		this.lblPopUp = lblPopUp;
		this.arbeit = arbeit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (!tf_Abgabetermin.getText().equals("") && !tf_Ausgabetermin.getText().equals("")) {
			String dateAusgabetermin = tf_Ausgabetermin.getText();
			String dateAbgabetermin = tf_Abgabetermin.getText();
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate ausgabetermin = LocalDate.parse(dateAusgabetermin, formatter);
				LocalDate abgabetermin = LocalDate.parse(dateAbgabetermin, formatter);

				arbeit.setBaAbgabetermin(abgabetermin);
				arbeit.setBaStart(ausgabetermin);
				arbeit.setBa_Anmeldung_Studiendekan(true);

				Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

				datenbankabfrage.updateDataArbeitDate(arbeit, abgabetermin, "baAbgabetermin");
				datenbankabfrage.updateDataArbeitDate(arbeit, ausgabetermin, "baStart");
				datenbankabfrage.updateDataArbeitBoolean(arbeit, true, "ba_Anmeldung_Studiendekan");

				lblPopUp.setText("Abgeschickt.");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		lblPopUp.setVisible(true);

	}

}
