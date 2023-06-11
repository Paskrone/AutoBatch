package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;

/**
 * Ein ActionListener, der dazu dient, Anfragen zu bearbeiten.
 */
public class AnfragenActionListener implements ActionListener {

	private JTextField tf_unternehmen;
	private JTextField tf_thema;
	private JTextField tf_beschreibung;

	private Student student;
	private Betreuer betreuer;
	
	private JLabel lblPopUp;
	private JLabel lbl_error;

	/**
	 * Konstruktor für den ActionListener
	 * @param tf_unternehmen Textfeld für das Unternehmen
	 * @param tf_thema Textfeld für das Thema
	 * @param tf_beschreibung Textfeld für die Beschreibung
	 * @param student Der betroffene Student
	 * @param betreuer Der betroffene Betreuer
	 * @param lbl_error Label zur Anzeige eines Fehlers
	 * @param lblPopUp Label zur Anzeige einer Nachricht
	 */
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

	/**
	 * Diese Methode wird aufgerufen, wenn eine Aktion ausgeführt wird.
	 * @param e Das ausgelöste ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String unternehmen = tf_unternehmen.getText();
		String thema = tf_thema.getText();
		String beschreibung = tf_beschreibung.getText();

		Datenbankabfrage dbaccess = new Datenbankabfrage();
		
		// Überprüft, ob das Löschen und Setzen der Daten erfolgreich war und zeigt entsprechend eine Fehlermeldung oder eine Bestätigungsnachricht an
		if (dbaccess.deleteDataArbeit(student.getMnr(), betreuer.getEmail()) && !dbaccess.setDataArbeit(student, betreuer, thema, unternehmen, beschreibung)) {
			lbl_error.setVisible(true);
		} else {
			lblPopUp.setVisible(true);
		}

	}

}
