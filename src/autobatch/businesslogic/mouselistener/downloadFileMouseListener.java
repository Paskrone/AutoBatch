package autobatch.businesslogic.mouselistener;

import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;

import autobatch.dbaccess.Datenbankabfrage;

/**
 * Diese Klasse erweitert MouseAdapter und bietet die Funktion, Dateien herunterzuladen.
 */
public class downloadFileMouseListener extends MouseAdapter{
	/**
	*Instanz der Datenbankabfrage
	*/
	Datenbankabfrage dbaccess = new Datenbankabfrage();
	
	/**
	 * Diese Methode wird verwendet, um eine Datei mit gegebenem Dateinamen herunterzuladen.
	 * @param filename Der Name der herunterzuladenden Datei.
	 */
	public void downloadFile(String filename) {
	    // Versuche, die Datei von der Datenbank zu holen
	    InputStream fileStream = dbaccess.getFileFromDatabase(filename);
	    if (fileStream != null) {
	        try {
	        	// Erstelle eine neue Datei im Download-Ordner des Benutzers
	        	File downloadedFile = new File(System.getProperty("user.home") + "/Downloads/" + filename);
	            // Kopiere den Inhalt des Streams in die Datei
	            Files.copy(fileStream, downloadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            // Informiere den Benutzer, dass der Download erfolgreich war
	            JOptionPane.showMessageDialog(null, "Datei erfolgreich heruntergeladen.");
	        } catch (IOException e) {
	            // Falls etwas schief geht, drucke den Stack Trace und informiere den Benutzer
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Fehler beim Herunterladen der Datei.");
	        }
	    } else {
	        // Wenn die Datei nicht in der Datenbank gefunden wurde, informiere den Benutzer
	        JOptionPane.showMessageDialog(null, "Datei konnte nicht gefunden werden.");
	    }
	}
}
