package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import autobatch.dbaccess.Datenbankabfrage;
import autobatch.session.SessionManager;

public class EigeneAbgabenHerunterladenActionListener implements ActionListener {
    
    private JList<String> fileList;
    private DefaultListModel<String> listModel;
    private Datenbankabfrage dbaccess = new Datenbankabfrage();
    private String currentUsername = SessionManager.getInstance().getAktuellerBenutzer().getBenutzername();
    
    public EigeneAbgabenHerunterladenActionListener() {
        listModel = new DefaultListModel<>();
        List<String> submissions = dbaccess.getStudentSubmissions(currentUsername);
        for (String submission : submissions) {
            listModel.addElement(submission);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        fileList = new JList<>(listModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        String selectedFile = fileList.getSelectedValue();
        if (selectedFile != null) {
            try {
                InputStream input = dbaccess.getFileFromDatabase(selectedFile);
                
                // Datei-Auswahl-Dialog erstellen
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedPath = fileChooser.getSelectedFile();
                    File file = new File(selectedPath + File.separator + selectedFile);
                    OutputStream output = new FileOutputStream(file);

                    byte[] buffer = new byte[1024];
                    while (input.read(buffer) > 0) {
                        output.write(buffer);
                    }
                    

                    output.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

