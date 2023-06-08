package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.IPAnfragen;
import autobatch.businessobjects.Student;

public class SaveDataActionListener implements ActionListener{

	private JTextField tf_unternehmen;
	private JTextField tf_thema;
	private JTextField tf_beschreibung;
	private JTextField tf_termin;
	
	 private JButton btnAnfragen;

	private Student student;
	private Betreuer betreuer;
	private Arbeit arbeit;
	private IPAnfragen anfrage;
	
	public SaveDataActionListener(JTextField tf_unternehmen, JTextField tf_beschreibung, JTextField tf_thema, JTextField tf_termin,
			Student student, Betreuer betreuer, Arbeit arbeit, JButton btnAnfragen , IPAnfragen anfrage) {
		super();
		this.tf_unternehmen = tf_unternehmen;
		this.tf_thema = tf_thema;
		this.tf_beschreibung=tf_beschreibung;
		this.tf_termin=tf_termin;
		
		this.btnAnfragen=btnAnfragen;
		
		this.student = student;
		this.betreuer = betreuer;
		this.arbeit=arbeit;
		this.anfrage=anfrage;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		boolean pruefe=true;
		
			
			if (tf_unternehmen.getText().equals("")||tf_termin.getText().equals("")||tf_thema.getText().equals("")) {
				pruefe=false;
				 JFrame frame = new JFrame();
				 JOptionPane.showMessageDialog(frame, "Füllen Sie die Textfelder aus",
			               "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				Date termin=Date.valueOf(LocalDate.parse(tf_termin.getText(),DateTimeFormatter.ofPattern("dd.MM.yyyy")));
				
				anfrage.setThema(tf_thema.getText());
				anfrage.setUnternehmen(tf_unternehmen.getText());
				anfrage.setBeschreibung(tf_beschreibung.getText());
				anfrage.setIptermin(termin);
				
			
				
				btnAnfragen.addActionListener(new IPAnfragenActionListener(arbeit, student, betreuer,anfrage));
				
				JFrame frame = new JFrame();
				 JOptionPane.showMessageDialog(frame, "Sie können die Anfrage jetzt schicken",
			               "Infomation", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	
		
	
}

