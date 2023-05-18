package autobatch.gui.student;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.IPAnfragenActionListener;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Arbeit;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentenIpPanel extends JPanel {
	
	private PanelSwitcher panelSwitcher;
	private Student student;
	private Betreuer betreuer;
	private JTextField txtDatum;
	private JTextField txtThema;
	private JTextField txtUN;
	private JTextField txtBeschreibung;
	private boolean pruefe;
	private Arbeit arbeit =new Arbeit(0, "", "", "", 0, 0,"");

	public StudentenIpPanel(PanelSwitcher panelSwitcher, Student student) {
		
		this.panelSwitcher = panelSwitcher;
        this.student = student;
        
    	setPreferredSize(new Dimension(1000, 500));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        
        StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelSwitcher, student);
        
        txtDatum = new JTextField();
        txtDatum.setColumns(10);
        
        txtThema = new JTextField();
        txtThema.setColumns(10);
        
        txtUN = new JTextField();
        txtUN.setColumns(10);
        
        txtBeschreibung = new JTextField();
        txtBeschreibung.setColumns(10);
        
        JLabel lblDatum = new JLabel("Datum");
        lblDatum.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        JLabel lblThema = new JLabel("Arbeit");
        lblThema.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        JLabel lblUnternehmen = new JLabel("Unternehmen");
        lblUnternehmen.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        JLabel lblBeschreibung = new JLabel("Beschreibung");
        lblBeschreibung.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        JButton btnSave = new JButton("Daten Speichern");
        JButton btnAnfragen = new JButton("Anfrage Schicken");       
        btnAnfragen.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (txtUN.getText().equals("")&&txtDatum.getText().equals("")&&txtThema.getText().equals("")) {
					pruefe=false;
					 JFrame frame = new JFrame();
					 JOptionPane.showMessageDialog(frame, "Füllen Sie die Textfelder aus",
				               "Error", JOptionPane.ERROR_MESSAGE);
				}
        		else {
					pruefe=true;
					 JFrame frame = new JFrame();
					arbeit.setThema(txtThema.getText());
					arbeit.setUnternehmen(txtUN.getText());
					arbeit.setBeschreibung(txtBeschreibung.getText());
					System.out.println(arbeit.getBeschreibung());
					btnAnfragen.addActionListener(new IPAnfragenActionListener(arbeit, student, betreuer));
					 JOptionPane.showMessageDialog(frame, "Sie können die Anfrage jetzt schicken",
				               "Infomation", JOptionPane.INFORMATION_MESSAGE);
					
				}
        	}
        });
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(50)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(lblBeschreibung, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        				.addComponent(lblUnternehmen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(lblThema, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(lblDatum))
        			.addGap(26)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        							.addComponent(txtUN, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
        							.addComponent(txtBeschreibung, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))
        						.addComponent(txtThema, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))
        					.addGap(179)
        					.addComponent(btnSave))))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(544)
        			.addComponent(btnAnfragen, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(29)
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lblDatum, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        				.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
        			.addGap(26)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtThema, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblThema, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
        					.addGap(27)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtUN, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblUnternehmen, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
        					.addGap(36)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtBeschreibung, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblBeschreibung, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
        				.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
        			.addGap(43)
        			.addComponent(btnAnfragen, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        setLayout(groupLayout);
        

	}
}
