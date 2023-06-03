package autobatch.businesslogic.mouselistener;

import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;

import autobatch.dbaccess.Datenbankabfrage;

public class downloadFileMouseListener extends MouseAdapter{

	Datenbankabfrage dbaccess = new Datenbankabfrage();
	
	public void downloadFile(String filename) {
	    InputStream fileStream = dbaccess.getFileFromDatabase(filename);
	    if (fileStream != null) {
	        try {
	        	File downloadedFile = new File(System.getProperty("user.home") + "/Downloads/" + filename);
	            Files.copy(fileStream, downloadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            JOptionPane.showMessageDialog(null, "Datei erfolgreich heruntergeladen.");
	        } catch (IOException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Fehler beim Herunterladen der Datei.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Datei konnte nicht gefunden werden.");
	    }
	}
}
