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
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelSwitcher;
import javax.swing.JTextField;

public class StudentenDatenPanel extends JPanel{
	
	private PanelSwitcher panelSwitcher;
	private JTextField textFieldName;
	private JTextField txtHierEinfgen;
	private JTextField textFieldTelefon;
	
	public StudentenDatenPanel(PanelSwitcher panelSwitcher) {
		
		
		
		this.panelSwitcher = panelSwitcher;
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        
        this.panelSwitcher = panelSwitcher;
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        
        JLabel jLabelHead = new JLabel("AutoBatch | Student");
        
        JButton btn_abmelden = new JButton("abmelden");
        //abmelden
		btn_abmelden.addActionListener(new AbmeldenActionListener(panelSwitcher));
        
        JButton btnIP = new JButton("IP");
        
        JButton btnBetreuer = new JButton("Betreuer");
        
        JButton btnAbgaben = new JButton("Abgaben");
        
        JButton btnFormulare = new JButton("Formulare");
        
        JButton btnDaten = new JButton("Daten");
        
        JLabel lblMatrikelnummer = new JLabel("Matrikelnummer:");
        
        JLabel lblName = new JLabel("Name:");
        
        JLabel lblAdresse = new JLabel("Adresse:");
        
        JLabel lblSemester = new JLabel("Semester:");
        
        JLabel lblMail = new JLabel("Email:");
        
        JLabel lblTelefon = new JLabel("Telefon:");
        
        textFieldName = new JTextField();
        textFieldName.setText("hier einfügen!");
        if (Datenbankabfrage.aktuellerStudent != null) {
            textFieldName.setText(Datenbankabfrage.aktuellerStudent.getNachname());
		} else {
            textFieldName.setText("funkt nicht");

		}
        
		
        textFieldName.setColumns(10);
        
        JLabel lblMNRInput = new JLabel("hier einfügen!");
        
        JLabel lblSemsterIn = new JLabel("hier einfügen!");
        
        txtHierEinfgen = new JTextField();
        txtHierEinfgen.setText("hier einfügen!");
        txtHierEinfgen.setColumns(10);
        
        JLabel lblMailInput = new JLabel("hier einfügen!");
        
        textFieldTelefon = new JTextField();
        textFieldTelefon.setText("(optional)");
        textFieldTelefon.setColumns(10);
		
		//test

        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(jLabelHead)
        					.addPreferredGap(ComponentPlacement.RELATED, 754, Short.MAX_VALUE)
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
        					.addComponent(lblName))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblSemester))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblAdresse))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblMail))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblMatrikelnummer)
        						.addComponent(lblTelefon))
        					.addGap(18)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(textFieldTelefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblMNRInput)
        						.addComponent(lblSemsterIn)
        						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtHierEinfgen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblMailInput))
        					.addGap(723)))
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
        				.addComponent(lblName)
        				.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblMNRInput)
        				.addComponent(lblMatrikelnummer))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblSemester)
        				.addComponent(lblSemsterIn))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblAdresse)
        				.addComponent(txtHierEinfgen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblMail)
        				.addComponent(lblMailInput))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblTelefon)
        				.addComponent(textFieldTelefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(262, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }
}