package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;

public class NoteEintragenActionListener implements ActionListener {

	private Arbeit arbeit;
	private JTextField textField;
	private JLabel lblPopUp;

	public NoteEintragenActionListener(Arbeit arbeit, JTextField textField, JLabel lblPopUp) {
		super();
		this.arbeit = arbeit;
		this.textField = textField;
		this.lblPopUp = lblPopUp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		float noteArbeit = Float.parseFloat(textField.getText());

		if (noteArbeit <= 5 && noteArbeit >= 1) {
			datenbankabfrage.updateDataArbeitFloat(arbeit, noteArbeit, "noteArbeit");
			arbeit.setNoteArbeit(noteArbeit);
		} else {
			lblPopUp.setText("Überprüfe die Eingabe.");
		}
		lblPopUp.setVisible(true);

	}

}
