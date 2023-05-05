package autobatch.businesslogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
	
	
	public CreateAccountActionListener(PanelSwitcher panelSwitcher, JTextField tf_Mnr, JTextField tf_vorname,
			JTextField tf_nachname, JTextField tf_Email, JTextField tf_telefonnummer, JTextField tf_Studiengang,
			JTextField tf_benutzername, JPasswordField tf_Passwort) {
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
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
