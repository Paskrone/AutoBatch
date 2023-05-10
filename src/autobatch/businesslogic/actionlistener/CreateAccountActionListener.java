package autobatch.businesslogic.actionlistener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelSwitcher;

public class CreateAccountActionListener implements ActionListener{
	
	private PanelSwitcher panelSwitcher;
	private JTextField tf_Mnr;
	private JTextField tf_vorname;
	private JTextField tf_nachname;
	private JTextField tf_Email;
	private JTextField tf_telefonnummer;
	private JTextField tf_Studiengang;
	private JTextField tf_benutzername;
	private JPasswordField tf_Passwort;	
	private JLabel lbl_registrationAnzeige;
	
	
	public CreateAccountActionListener(PanelSwitcher panelSwitcher, JTextField tf_Mnr, JTextField tf_vorname,
			JTextField tf_nachname, JTextField tf_Email, JTextField tf_telefonnummer, JTextField tf_Studiengang,
			JTextField tf_benutzername, JPasswordField tf_Passwort, JLabel lbl_registrationAnzeige) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.tf_Mnr = tf_Mnr;
		this.tf_vorname = tf_vorname;
		this.tf_nachname = tf_nachname;
		this.tf_Email = tf_Email;
		this.tf_telefonnummer = tf_telefonnummer;
		this.tf_Studiengang = tf_Studiengang;
		this.tf_benutzername = tf_benutzername;
		this.tf_Passwort = tf_Passwort;
		this.lbl_registrationAnzeige = lbl_registrationAnzeige;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		char[] passwordChars = tf_Passwort.getPassword();
        String password = new String(passwordChars);
        
        String mnr = tf_Mnr.getText();
        int mnrInt = Integer.parseInt(mnr);
        
		Datenbankabfrage dbaccess = new Datenbankabfrage();
		if(dbaccess.registerStudent(new Student(mnrInt, tf_vorname.getText(), tf_nachname.getText(), password,
				tf_benutzername.getText(), tf_Email.getText(), tf_telefonnummer.getText(), tf_Studiengang.getText(), null, null))) {
			
		} else {
			lbl_registrationAnzeige.setText("Benutzername existiert bereits.");
			lbl_registrationAnzeige.setForeground(Color.red);
			lbl_registrationAnzeige.setVisible(true);
		}
		
	}

}
