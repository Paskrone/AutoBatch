package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import autobatch.dbaccess.Datenbankabfrage;
import autobatch.session.SessionManager;

public class AbgabeHinzufuegenActionListener implements ActionListener{
	
	Datenbankabfrage dbaccess = new Datenbankabfrage();
	String currentUsername = SessionManager.getInstance().getAktuellerBenutzer().getBenutzername();

	@Override
	public void actionPerformed(ActionEvent e) {
	    // Datei-Auswahl-Dialog erstellen
	    JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "pdf");
	    fileChooser.setFileFilter(filter);

	    int returnValue = fileChooser.showOpenDialog(null);
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        dbaccess.saveFileToDatabase(selectedFile, currentUsername);
	    }
	    
	}



}
