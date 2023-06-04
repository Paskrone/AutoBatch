package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.session.SessionManager;

public class AbgabeHinzufuegenActionListener implements ActionListener{
	
	Datenbankabfrage dbaccess = new Datenbankabfrage();
	Benutzer currentUser = SessionManager.getInstance().getAktuellerBenutzer();
	String currentUsername = SessionManager.getInstance().getAktuellerBenutzer().getBenutzername();
	private JLabel lbl_success;
	private JLabel lbl_error;
	
	public AbgabeHinzufuegenActionListener(JLabel lbl_success, JLabel lbl_error) {
		this.lbl_success = lbl_success;
		this.lbl_error = lbl_error;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(currentUser instanceof Student) {
			if(((Student) currentUser).getBetreuer() != null) {
				// Datei-Auswahl-Dialog erstellen
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
		
		else if(currentUser instanceof Betreuer) {
			// Datei-Auswahl-Dialog erstellen
		    JFileChooser fileChooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "pdf");
		    fileChooser.setFileFilter(filter);

		    int returnValue = fileChooser.showOpenDialog(null);
		    if (returnValue == JFileChooser.APPROVE_OPTION) {
		        File selectedFile = fileChooser.getSelectedFile();
		        dbaccess.saveFileToDatabase(selectedFile, currentUsername);
		        lbl_success.setVisible(true);
		    }
		} else {
			System.out.println("Aktueller Benutzer hat keine Zugriffsberechtigung");
		}
		
		
	    
	    
	}

	
	


}
