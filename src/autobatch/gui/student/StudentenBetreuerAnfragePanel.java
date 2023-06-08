package autobatch.gui.student;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.AnfragenActionListener;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.businessobjects.Betreuer;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class StudentenBetreuerAnfragePanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Student student;
	private JTextField tf_Unternehmen;
	private JTextField tf_Thema;
	private JTextField tf_beschreibung;

	private Betreuer betreuer;

	public StudentenBetreuerAnfragePanel(PanelManager panelManager, PanelSwitcher panelSwitcher, Student student) {
		this.student = student;
		this.panelSwitcher = panelSwitcher;

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		this.betreuer = datenbankabfrage.getBetreuerByMail(panelSwitcher.getData("1") + "");

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelManager, panelSwitcher, student);

		System.out.println(panelSwitcher.getData("1"));

		JLabel lblUnternehmen = new JLabel("Unternehmen:");

		tf_Unternehmen = new JTextField();
		tf_Unternehmen.setColumns(10);

		JLabel lblThema = new JLabel("Thema:");

		tf_Thema = new JTextField();
		tf_Thema.setColumns(10);

		JLabel lblBeschreibung = new JLabel("Beschreibung:");

		tf_beschreibung = new JTextField();
		tf_beschreibung.setColumns(10);

		JLabel lbl_Betreuer = new JLabel("Betreuer Informationen:");

		JLabel lbl_Name = new JLabel("Name:");

		JLabel lbl_NameIn = new JLabel(this.betreuer.getNachname() + ", " + this.betreuer.getVorname());

		JLabel lbl_Email = new JLabel("Email:");

		JLabel lbl_EmailIn = new JLabel(this.betreuer.getEmail());

		JLabel lbl_error = new JLabel("Überprüfe die Eingabe");
		lbl_error.setVisible(false);
		lbl_error.setForeground(new Color(255, 5, 17));

		JLabel lblPopUp = new JLabel("Anfrage gesendet");
		lblPopUp.setVisible(false);

		JButton btnAnfragen = new JButton("Anfragen");
		btnAnfragen.addActionListener(
				new AnfragenActionListener(tf_Unternehmen, tf_Thema, tf_beschreibung, student, betreuer, lbl_error, lblPopUp));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblBeschreibung))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING, false).addComponent(tf_beschreibung)
								.addComponent(tf_Thema)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblUnternehmen).addComponent(lblThema))
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(tf_Unternehmen,
												GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)))
								.addGap(171)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lbl_Betreuer)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lbl_Email).addComponent(lbl_Name))
												.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lbl_NameIn).addComponent(lbl_EmailIn)))))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnAnfragen)
										.addGroup(groupLayout.createSequentialGroup().addGap(6)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblPopUp).addComponent(lbl_error))))))
				.addGap(43)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblUnternehmen)
								.addComponent(tf_Unternehmen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_Betreuer))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblThema)
								.addComponent(lbl_Name).addComponent(lbl_NameIn))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(tf_Thema, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_Email).addComponent(lbl_EmailIn))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblBeschreibung)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(tf_beschreibung, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(btnAnfragen).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lbl_error).addPreferredGap(ComponentPlacement.RELATED).addComponent(lblPopUp)
						.addContainerGap(102, Short.MAX_VALUE)));
		setLayout(groupLayout);
	}
}
