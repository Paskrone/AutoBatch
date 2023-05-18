package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;

public class SaveDataActionListener implements ActionListener{

	private JTextField tf_unternehmen;
	private JTextField tf_thema;
	private JTextField tf_Datum;
	private JTextField tf_beschreibung;
	
	 private JButton btnAnfragen;

	private Student student;
	private Betreuer betreuer;
	private Arbeit arbeit;
	
	public SaveDataActionListener(JTextField tf_unternehmen, JTextField tf_beschreibung, JTextField tf_thema, JTextField tf_Datum,
			Student student, Betreuer betreuer, Arbeit arbeit, JButton btnAnfragen) {
		super();
		this.tf_unternehmen = tf_unternehmen;
		this.tf_thema = tf_thema;
		this.tf_Datum = tf_Datum;
		this.tf_beschreibung=tf_beschreibung;
		
		this.btnAnfragen=btnAnfragen;
		
		this.student = student;
		this.betreuer = betreuer;
		this.arbeit=arbeit;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		boolean pruefe=true;
		
			
			if (tf_unternehmen.getText().equals("")||tf_Datum.getText().equals("")||tf_thema.getText().equals("")) {
				pruefe=false;
				 JFrame frame = new JFrame();
				 JOptionPane.showMessageDialog(frame, "Füllen Sie die Textfelder aus",
			               "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				
				arbeit.setThema(tf_thema.getText());
				arbeit.setUnternehmen(tf_unternehmen.getText());
				arbeit.setBeschreibung(tf_beschreibung.getText());
				
				btnAnfragen.addActionListener(new IPAnfragenActionListener(arbeit, student, betreuer));
				
				JFrame frame = new JFrame();
				 JOptionPane.showMessageDialog(frame, "Sie können die Anfrage jetzt schicken",
			               "Infomation", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	
		
	
}
