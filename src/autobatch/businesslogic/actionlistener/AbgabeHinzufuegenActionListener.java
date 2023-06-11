package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.session.SessionManager;

/**
 * Ein ActionListener, der dazu dient, dem Benutzer das Hinzufügen von Abgaben zu ermöglichen.
 */
public class AbgabeHinzufuegenActionListener implements ActionListener{
	
	Datenbankabfrage dbaccess = new Datenbankabfrage();
	Benutzer currentUser = SessionManager.getInstance().getAktuellerBenutzer();
	String currentUsername = SessionManager.getInstance().getAktuellerBenutzer().getBenutzername();
	private JLabel lbl_success;
	private JLabel lbl_error;
	private Student student;
	
	/**
	 * Konstruktor für den ActionListener
	 * @param lbl_success Label zur Anzeige einer erfolgreichen Operation
	 * @param lbl_error Label zur Anzeige eines Fehlers
	 */
	public AbgabeHinzufuegenActionListener(JLabel lbl_success, JLabel lbl_error) {
		this.lbl_success = lbl_success;
		this.lbl_error = lbl_error;
	}
	
	/**
	 * Konstruktor für den ActionListener
	 * @param lbl_success Label zur Anzeige einer erfolgreichen Operation
	 * @param lbl_error Label zur Anzeige eines Fehlers
	 * @param student Der betroffene Student
	 */
	public AbgabeHinzufuegenActionListener(JLabel lbl_success, JLabel lbl_error, Student student) {
		this.lbl_success = lbl_success;
		this.lbl_error = lbl_error;
		this.student = student;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn eine Aktion ausgeführt wird.
	 * @param e Das ausgelöste ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Prüft, ob der aktuelle Benutzer ein Student ist und fügt entsprechend die Datei hinzu
		if(currentUser instanceof Student) {
			if(((Student) currentUser).getBetreuer() != null) {
				// Erstellt einen Datei-Auswahl-Dialog
			    JFileChooser fileChooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "pdf");
			    fileChooser.setFileFilter(filter);

			    int returnValue = fileChooser.showOpenDialog(null);
			    if (returnValue == JFileChooser.APPROVE_OPTION) {
			        File selectedFile = fileChooser.getSelectedFile();
			        dbaccess.saveFileToDatabase(selectedFile, currentUsername);
			        lbl_success.setVisible(true);
			    }
			} else if(((Student) currentUser).getBetreuer() == null) {
				lbl_error.setVisible(true);
			}
		}
		
		// Prüft, ob der aktuelle Benutzer ein Betreuer ist und fügt entsprechend die Datei hinzu
		else if(currentUser instanceof Betreuer) {
			// Erstellt einen Datei-Auswahl-Dialog
		    JFileChooser fileChooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "pdf");
		    fileChooser.setFileFilter(filter);

		    int returnValue = fileChooser.showOpenDialog(null);
		    if (returnValue == JFileChooser.APPROVE_OPTION) {
		        File selectedFile = fileChooser.getSelectedFile();
		        dbaccess.saveFileToDatabase(selectedFile, currentUsername,student.getBenutzername());
		        lbl_success.setVisible(true);
		    }
		    } else {
		    System.out.println("Aktueller Benutzer hat keine Zugriffsberechtigung");
		    	}
		    		}
							}
