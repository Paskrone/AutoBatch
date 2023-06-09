package autobatch.gui.student;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.DatenSpeichernActionListener;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import autobatch.session.SessionManager;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class StudentenDatenPanel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Student student;
	private JTextField tf_ort;
	private JTextField tf_telefon;
	private JTextField tf_postleizahl;
	private JTextField tf_strasse;
	private JTextField tf_Semester;
	private JTextField tf_passwort;

	public StudentenDatenPanel(PanelManager panelManager, PanelSwitcher panelSwitcher, Student student) {

		this.panelSwitcher = panelSwitcher;
		this.student = student;

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelManager, panelSwitcher, student);

		JLabel lblMatrikelnummer = new JLabel("Matrikelnummer:");

		JLabel lblVorname = new JLabel("Vorname:");

		JLabel lblOrt = new JLabel("Wohnort:");

		JLabel lblstudiengang = new JLabel("Studiengang:");

		JLabel lblMail = new JLabel("Email:");

		JLabel lblTelefon = new JLabel("Telefon:");

		JLabel lbl_mnr = new JLabel("hier einfügen!");

		JLabel lbl_studiengang = new JLabel("hier einfügen!");

		JLabel lbl_email = new JLabel("hier einfügen!");

		JLabel lbl_vorname = new JLabel("hier einfügen!");

		JLabel lblnachname = new JLabel("Nachname:");

		JLabel lbl_nachname = new JLabel("hier einfügen!");

		JLabel lblBenutzername = new JLabel("Benutzername:");

		tf_ort = new JTextField();
		tf_ort.setColumns(10);

		tf_telefon = new JTextField();
		tf_telefon.setColumns(10);

		tf_postleizahl = new JTextField();
		tf_postleizahl.setColumns(10);

		JLabel lblStrasse = new JLabel("Straße:");

		tf_strasse = new JTextField();
		tf_strasse.setColumns(10);

		JLabel lblNewLabel = new JLabel("Semseter:");

		tf_Semester = new JTextField();
		tf_Semester.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Passwort:");

		JLabel lbl_benutzername = new JLabel("New label");

		tf_passwort = new JTextField();
		tf_passwort.setColumns(10);

		if (student != null) {

			lbl_vorname.setText(student.getVorname());
			lbl_nachname.setText(student.getNachname());
			lbl_studiengang.setText(student.getStudiengang());
			if (student.getOrt() != null) {
				tf_ort.setText(student.getOrt());
			}
			if (student.getPostleizahl() != 0) {
				tf_postleizahl.setText(student.getPostleizahl() + "");
			}
			if (student.getStrasse() != null) {
				tf_strasse.setText(student.getStrasse());
			}
			lbl_email.setText(student.getEmail());
			if (student.getTelefonnummer() != 0) {
				tf_telefon.setText(student.getTelefonnummer() + "");
			}
			if (student.getSemester() != 0) {
				tf_Semester.setText(student.getSemester() + "");
			}
			lbl_mnr.setText(student.getMnr() + "");
			lbl_benutzername.setText(student.getBenutzername());
			tf_passwort.setText(student.getPasswort());

		} else {
			System.out.println("error");
		}
		JLabel lblPopUp = new JLabel("Daten gespeichert");
		lblPopUp.setVisible(false);

		JButton btnDatenSpeichern = new JButton("Speichern");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
														.createParallelGroup(Alignment.LEADING).addGroup(
																groupLayout.createSequentialGroup().addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblTelefon)
																		.addComponent(lblMatrikelnummer)).addGap(23))
														.addGroup(
																groupLayout
																		.createSequentialGroup().addComponent(lblMail)
																		.addPreferredGap(ComponentPlacement.RELATED))
														.addGroup(groupLayout.createSequentialGroup().addGroup(
																groupLayout.createParallelGroup(Alignment.LEADING)
																		.addGroup(groupLayout.createSequentialGroup()
																				.addGroup(groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(groupLayout
																								.createParallelGroup(
																										Alignment.LEADING)
																								.addComponent(lblOrt)
																								.addComponent(
																										lblVorname)
																								.addComponent(
																										lblnachname,
																										GroupLayout.DEFAULT_SIZE,
																										82,
																										Short.MAX_VALUE))
																						.addComponent(lblstudiengang))
																				.addGap(23))
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(lblStrasse).addGap(62)))
																.addGap(25)))
												.addGroup(groupLayout.createSequentialGroup().addComponent(lblNewLabel)
														.addGap(69)))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblBenutzername, GroupLayout.PREFERRED_SIZE, 107,
														GroupLayout.PREFERRED_SIZE)
												.addGap(23)))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(tf_postleizahl, GroupLayout.PREFERRED_SIZE, 67,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(tf_ort,
														GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
										.addComponent(lbl_nachname, GroupLayout.PREFERRED_SIZE, 87,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_vorname, GroupLayout.PREFERRED_SIZE, 87,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_studiengang).addComponent(lbl_mnr).addComponent(lbl_email)
										.addComponent(tf_telefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(tf_strasse))
										.addComponent(tf_Semester, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_benutzername).addComponent(tf_passwort,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(966)).addComponent(btnDatenSpeichern)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblPopUp)
										.addContainerGap(870, Short.MAX_VALUE))))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(360, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblNewLabel_1)
						.addContainerGap(1293, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(32)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
						.createSequentialGroup().addComponent(lblVorname).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblnachname).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblstudiengang).addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblOrt)
								.addComponent(tf_postleizahl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(tf_ort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblStrasse)
								.addComponent(tf_strasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup().addComponent(lbl_vorname)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lbl_nachname)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lbl_studiengang).addGap(64)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(lbl_email).addComponent(lblMail))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblTelefon).addComponent(
						tf_telefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(lblMatrikelnummer).addComponent(lbl_mnr))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(
						tf_Semester, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblBenutzername)
						.addComponent(lbl_benutzername))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1).addComponent(
						tf_passwort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(53).addComponent(btnDatenSpeichern).addGap(18).addComponent(lblPopUp)
				.addContainerGap(26, Short.MAX_VALUE)));
		setLayout(groupLayout);
	}
}