package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelSwitcher;

public class DatenSpeichernActionListener implements ActionListener{
	
	private PanelSwitcher panelSwitcher;
	
	private Student student;
	
	private JTextField tf_ort;
	private JTextField tf_postleizahl;
	private JTextField tf_strasse;
	private JTextField tf_telefon;
	
	private JLabel lblPopUp;

	public DatenSpeichernActionListener(Student student, PanelSwitcher panelSwitcher, JTextField tf_ort, JTextField tf_postleizahl,
			JTextField tf_strasse, JTextField tf_telefon, JLabel lblPopUp) {
		super();
		this.student = student;
		this.panelSwitcher = panelSwitcher;
		this.tf_ort = tf_ort;
		this.tf_postleizahl = tf_postleizahl;
		this.tf_strasse = tf_strasse;
		this.tf_telefon = tf_telefon;
		this.lblPopUp = lblPopUp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ort = tf_ort.getText();
		String strasse = tf_strasse.getText();
		int postleizahl = Integer.parseInt(tf_postleizahl.getText());
		String telefon = tf_telefon.getText();
		
		Datenbankabfrage dbaccess = new Datenbankabfrage();
		dbaccess.updateData(student, ort, strasse, postleizahl, telefon);
		
		lblPopUp.setVisible(true);

	}
	
	

}
