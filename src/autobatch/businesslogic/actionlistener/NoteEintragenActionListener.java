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
	// Instanzvariablen
	private Arbeit arbeit; // Arbeit, für die die Note eingetragen werden soll
	private JTextField textField; // Textfeld zur Eingabe der Note
	private JLabel lblPopUp; // Label zur Anzeige von Feedback und/oder Fehlermeldungen

	// Konstruktor
	public NoteEintragenActionListener(Arbeit arbeit, JTextField textField, JLabel lblPopUp) {
		super();
		this.arbeit = arbeit;
		this.textField = textField;
		this.lblPopUp = lblPopUp;
	}

	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer auf den Button zum Eintragen der Note klickt.
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
