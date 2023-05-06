package autobatch.gui.student;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.AbmeldenActionListener;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelSwitcher;
import autobatch.session.SessionManager;

import javax.swing.JTextField;
import javax.swing.JPasswordField;


public class StudentenDatenPanel extends JPanel{
	
	private PanelSwitcher panelSwitcher;
	private Student student;
	
	public StudentenDatenPanel(PanelSwitcher panelSwitcher, Student student) {
        
        this.panelSwitcher = panelSwitcher;
        this.student = student;
        
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        
        JLabel jLabelHead = new JLabel("AutoBatch | Student");
        
        JButton btn_abmelden = new JButton("abmelden");
        //Abmelden
		btn_abmelden.addActionListener(new AbmeldenActionListener(panelSwitcher));
        
        JButton btnIP = new JButton("IP");
        
        JButton btnBetreuer = new JButton("Betreuer");
        
        JButton btnAbgaben = new JButton("Abgaben");
        
        JButton btnFormulare = new JButton("Formulare");
        
        JButton btnDaten = new JButton("Daten");
        
        JLabel lblMatrikelnummer = new JLabel("Matrikelnummer:");
        
        JLabel lblVorname = new JLabel("Vorname:");
        
        JLabel lblAdresse = new JLabel("Adresse:");
        
        JLabel lblstudiengang = new JLabel("Studiengang:");
        
        JLabel lblMail = new JLabel("Email:");
        
        JLabel lblTelefon = new JLabel("Telefon:");
        
        JLabel lbl_mnr = new JLabel("hier einfügen!");
        
        JLabel lbl_studiengang = new JLabel("hier einfügen!");
        
        JLabel lbl_email = new JLabel("hier einfügen!");
        
        JLabel lbl_adresse = new JLabel("hier einfügen!");
        
        JLabel lbl_telefonnummer = new JLabel("hier einfügen!");
        
        JLabel lbl_vorname = new JLabel("hier einfügen!");

		
        if (student != null) {
            int mnr = student.getMnr();
            String mnrString = "" + mnr;
            lbl_mnr.setText(mnrString);
            lbl_vorname.setText(student.getVorname() + " " + student.getNachname());
            lbl_email.setText(student.getEmail());
        } else {
            System.out.println("error");
        }
        
        JLabel lblnachname = new JLabel("Nachname:");
        
        JLabel lbl_nachname = new JLabel("hier einfügen!");
        
        JLabel lblBenutzername = new JLabel("Benutzername:");
        
        JLabel lbl_benutzername = new JLabel("hier einfügen!");
        
        JLabel lblPasswort = new JLabel("Passwort:");
        
        JLabel lbl_passwort = new JLabel("hier einfügen!");
        
        
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(jLabelHead)
        					.addPreferredGap(ComponentPlacement.RELATED, 759, Short.MAX_VALUE)
        					.addComponent(btn_abmelden))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(btnIP)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnBetreuer)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnAbgaben)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnFormulare)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnDaten))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblMail))
        				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        								.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.LEADING)
        									.addComponent(lblTelefon)
        									.addComponent(lblAdresse)
        									.addComponent(lblVorname)
        									.addComponent(lblMatrikelnummer)
        									.addComponent(lblnachname, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
        								.addComponent(lblstudiengang, Alignment.LEADING))
        							.addComponent(lblBenutzername, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
        						.addComponent(lblPasswort, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
        					.addGap(23)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lbl_benutzername, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lbl_mnr)
        						.addComponent(lbl_nachname, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lbl_vorname, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lbl_adresse, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lbl_studiengang)
        						.addComponent(lbl_email)
        						.addComponent(lbl_telefonnummer, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lbl_passwort, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
        					.addGap(766)))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabelHead)
        				.addComponent(btn_abmelden))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnIP)
        				.addComponent(btnBetreuer)
        				.addComponent(btnAbgaben)
        				.addComponent(btnFormulare)
        				.addComponent(btnDaten))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblVorname)
        				.addComponent(lbl_vorname))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblnachname)
        				.addComponent(lbl_nachname))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblstudiengang)
        				.addComponent(lbl_studiengang))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblAdresse)
        				.addComponent(lbl_adresse))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblMail)
        				.addComponent(lbl_email))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblTelefon)
        				.addComponent(lbl_telefonnummer))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblMatrikelnummer)
        				.addComponent(lbl_mnr))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblBenutzername)
        				.addComponent(lbl_benutzername))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblPasswort)
        				.addComponent(lbl_passwort))
        			.addContainerGap(216, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }
}