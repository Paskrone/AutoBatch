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

public class FormularBetreuerActionListener implements ActionListener {

	private boolean ja;
	private boolean clicked;

	private JTextField tf_Thema;
	private JTextField tf_Ausgabetermin;
	private JTextField tf_Abgabetermin;

	private JLabel lblPopUp;

	private Arbeit arbeit;
	
	

	public FormularBetreuerActionListener(boolean ja, boolean clicked, JTextField tf_Thema, JTextField tf_Ausgabetermin,
			JTextField tf_Abgabetermin, JLabel lblPopUp, Arbeit arbeit) {
		super();
		this.ja = ja;
		this.clicked = clicked;
		this.tf_Thema = tf_Thema;
		this.tf_Ausgabetermin = tf_Ausgabetermin;
		this.tf_Abgabetermin = tf_Abgabetermin;
		this.lblPopUp = lblPopUp;
		this.arbeit = arbeit;
	}



	@Override
	public void actionPerformed(ActionEvent e) {

		if (clicked && !tf_Abgabetermin.getText().equals("") && !tf_Ausgabetermin.getText().equals("")
				&& !tf_Thema.getText().equals("")) {
			String dateAusgabetermin = tf_Ausgabetermin.getText();
			String dateAbgabetermin = tf_Abgabetermin.getText();
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate ausgabetermin = LocalDate.parse(dateAusgabetermin, formatter);
				LocalDate abgabetermin = LocalDate.parse(dateAbgabetermin, formatter);
				
				String thema = tf_Thema.getText();
				
				arbeit.setThema(thema);
				arbeit.setBaAbgabetermin(abgabetermin);
				arbeit.setBaStart(ausgabetermin);
				arbeit.setIpBestanden(ja);
				arbeit.setBa_Anmeldung_Studiendekan(true);
				
				
				Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

				datenbankabfrage.updateDataArbeitString(arbeit, thema, "thema");
				
				// ausgabetermin und abgabetermin erweitern
				
				datenbankabfrage.updateDataArbeitDate(arbeit, abgabetermin, "baAbgabetermin");
				datenbankabfrage.updateDataArbeitDate(arbeit, ausgabetermin, "baStart");
				
				datenbankabfrage.updateDataArbeitBoolean(arbeit, ja, "ipBestanden");
				
				datenbankabfrage.updateDataArbeitBoolean(arbeit, true, "ba_Anmeldung_Betreuer");

				lblPopUp.setText("Abgeschickt.");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}

		lblPopUp.setVisible(true);

	}

}
