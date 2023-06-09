package autobatch.gui.student;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.FormularStudentActionListener;
import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class StudentenFormularePanel extends JPanel {
	private JTextField tf_Semester;
	private JTextField tf_Telefon;

	private boolean ja = false;
	private boolean clicked = true;

	public StudentenFormularePanel(PanelManager panelManager, PanelSwitcher panelSwitcher, Student student) {

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelManager, panelSwitcher, student);

		JLabel lblNewLabel = new JLabel("Anmeldeformular:");

		JLabel lblNewLabel_1 = new JLabel("Name:");

		JLabel lblNewLabel_2 = new JLabel("Matrikelnummer:");

		JLabel lblNewLabel_3 = new JLabel("Semester:");

		JLabel lblNewLabel_5 = new JLabel("Email:");

		JLabel lblNewLabel_6 = new JLabel("Telefonnummer:");

		tf_Semester = new JTextField();
		tf_Semester.setColumns(10);

		tf_Telefon = new JTextField();
		tf_Telefon.setColumns(10);

		JLabel lblName = new JLabel("New label");

		JLabel lblMNR = new JLabel("New label");

		JLabel lblMail = new JLabel("New label");

		lblName.setText(student.getVorname() + " " + student.getNachname());
		lblMNR.setText(student.getMnr() + "");
		lblMail.setText(student.getEmail());

		if (student.getTelefonnummer() != 0) {
			tf_Telefon.setText(student.getTelefonnummer() + "");
		}

		JLabel lblNewLabel_4 = new JLabel(
				"Die HFT darf den Titel meiner Bachelor-Arbeit und meinen Namen veröffentlichen");

		JRadioButton rb_Ja = new JRadioButton("Ja");

		JRadioButton rb_Nein = new JRadioButton("Nein");

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rb_Nein);
		buttonGroup.add(rb_Ja);

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton selectedRadioButton = (JRadioButton) e.getSource();
				if (selectedRadioButton.isSelected() && selectedRadioButton.getText().equals("Ja")) {
					ja = true;
				} else if (selectedRadioButton.isSelected() && selectedRadioButton.getText().equals("Nein")) {
					ja = false;
				} else {
					clicked = false;
				}
			}
		};
		rb_Ja.addActionListener(actionListener);
		rb_Nein.addActionListener(actionListener);
		
		JLabel lblPopUp = new JLabel("Eingabe überprüfen.");
		lblPopUp.setVisible(false);

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

		Arbeit arbeit = datenbankabfrage.getArbeitByID(student.getArbeit());

		JButton btnNewButton = new JButton("bestätigen");
		btnNewButton.addActionListener(new FormularStudentActionListener(student, arbeit, ja, clicked, tf_Telefon, tf_Semester, lblPopUp));
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_6))
							.addGap(48)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMail)
								.addComponent(lblMNR)
								.addComponent(lblName)
								.addComponent(tf_Telefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tf_Semester, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_4))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(144)
							.addComponent(rb_Ja)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rb_Nein))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(lblPopUp))
								.addComponent(btnNewButton))))
					.addGap(16))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblMNR))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(tf_Semester, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(lblMail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(tf_Telefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rb_Ja)
						.addComponent(rb_Nein))
					.addGap(47)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPopUp)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
