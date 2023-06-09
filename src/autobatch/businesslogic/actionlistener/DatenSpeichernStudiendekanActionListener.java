package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Studiendekan;
import autobatch.dbaccess.Datenbankabfrage;

public class DatenSpeichernStudiendekanActionListener implements ActionListener {

	private Studiendekan studiendekan;

	private JTextField tf_Passwort;
	private JLabel lblPopUp;

	public DatenSpeichernStudiendekanActionListener(Studiendekan studiendekan, JTextField tf_Passwort,
			JLabel lblPopUp) {
		super();
		this.studiendekan = studiendekan;
		this.tf_Passwort = tf_Passwort;
		this.lblPopUp = lblPopUp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (!tf_Passwort.getText().equals("")) {
			String passwort = tf_Passwort.getText();
			studiendekan.setPasswort(passwort);

			Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
			datenbankabfrage.updateDataStudiendekanString(studiendekan, passwort, "Passwort");
			lblPopUp.setText("gespeichert!");
		}

		lblPopUp.setVisible(true);

	}

}
