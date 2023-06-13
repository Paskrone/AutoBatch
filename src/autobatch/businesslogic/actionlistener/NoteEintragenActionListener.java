package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;

/**
 * ActionListener zur Verarbeitung des Eintragens der Arbeit Note.
 */
public class NoteEintragenActionListener implements ActionListener {
	
	/**
	 * Arbeit, für die die Note eingetragen werden soll.
	 */
	private Arbeit arbeit;

	/**
	 * JTextField zur Eingabe der Note.
	 */
	private JTextField textField;

	/**
	 * JLabel zur Anzeige von Feedback und/oder Fehlermeldungen.
	 */
	private JLabel lblPopUp;

	/**
	 * ActionListener zur Verarbeitung des Eintragens der Arbeit Note.
	 * 
	 * @param arbeit Die Arbeit, für die die Note eingetragen werden soll.
	 * @param textField Das JTextField zur Eingabe der Note.
	 * @param lblPopUp Das JLabel zur Anzeige von Feedback und/oder Fehlermeldungen.
	 */
	public NoteEintragenActionListener(Arbeit arbeit, JTextField textField, JLabel lblPopUp) {
		super();
		this.arbeit = arbeit;
		this.textField = textField;
		this.lblPopUp = lblPopUp;
	}

	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer auf den Button zum Eintragen der Note klickt.
	 * Zuerst wird ein neuer Datenbankzugriff erzeugt. Die Eingabe des Benutzers wird dann aus dem Textfeld
	 * extrahiert und in eine Fließkommazahl umgewandelt. Es wird geprüft, ob die Note innerhalb des gültigen Bereichs 
	 * (1 bis 5) liegt. Falls ja, wird die Note der Arbeit in der Datenbank aktualisiert und in der Arbeit-Instanz gesetzt. 
	 * Andernfalls wird eine Fehlermeldung ausgegeben. Unabhängig vom Ausgang der Prüfung wird das Pop-up-Label angezeigt.
	 * @param e Das ausgelöste ActionEvent.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Erzeugen eines neuen Datenbankzugriffs
		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		// Note aus dem Textfeld extrahieren und in eine Fließkommazahl umwandeln
		float noteArbeit = Float.parseFloat(textField.getText());

		// Prüfen, ob die Note innerhalb des gültigen Bereichs liegt
		if (noteArbeit <= 5 && noteArbeit >= 1) {
			// Aktualisieren der Arbeit Note in der Datenbank
			datenbankabfrage.updateDataArbeitFloat(arbeit, noteArbeit, "noteArbeit");
			// Aktualisieren der Note in der Arbeit Instanz
			arbeit.setNoteArbeit(noteArbeit);
		} else {
			// Anzeigen einer Fehlermeldung, wenn die Note außerhalb des gültigen Bereichs liegt
			lblPopUp.setText("Überprüfe die Eingabe.");
		}
		// Anzeigen des Pop-up Labels
		lblPopUp.setVisible(true);

	}

}
