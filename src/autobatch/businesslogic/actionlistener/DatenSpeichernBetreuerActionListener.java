package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Betreuer;
import autobatch.dbaccess.Datenbankabfrage;

public class DatenSpeichernBetreuerActionListener implements ActionListener {

	private Betreuer betreuer;

	private JTextField tf_Passwort;
	private JLabel lblPopUp;

	public DatenSpeichernBetreuerActionListener(Betreuer betreuer, JTextField tf_Passwort, JLabel lblPopUp) {
		super();
		this.betreuer = betreuer;
		this.tf_Passwort = tf_Passwort;
		this.lblPopUp = lblPopUp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (!tf_Passwort.getText().equals("")) {
			String passwort = tf_Passwort.getText();
			betreuer.setPasswort(passwort);
			
			Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
			datenbankabfrage.updateDataBetreuerString(betreuer, passwort, "Passwort");
			lblPopUp.setText("gespeichert!");
		}
		
		lblPopUp.setVisible(true);

	}

}
