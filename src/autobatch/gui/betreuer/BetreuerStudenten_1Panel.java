package autobatch.gui.betreuer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businesslogic.actionlistener.ChangeNoteActionListener;
import autobatch.businesslogic.itemlistener.NdaItemListener;
import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class BetreuerStudenten_1Panel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Betreuer betreuer;
	private final ButtonGroup buttonGroupNDA = new ButtonGroup();

	public BetreuerStudenten_1Panel(PanelManager panelManager, PanelSwitcher panelSwitcher, Betreuer betreuer) {

		this.panelSwitcher = panelSwitcher;
		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

		int mnr = Integer.parseInt(panelSwitcher.getData("2") + "");
		Student student = datenbankabfrage.getStudentByMNR(mnr);

		int idThema = Integer.parseInt(panelSwitcher.getData("3") + "");
		Arbeit arbeit = datenbankabfrage.getArbeitByID(idThema);

		BetreuerNavigationBar betreuerNavigationBar = new BetreuerNavigationBar(panelSwitcher);

		JLabel lblName = new JLabel("Name:");

		JLabel lblStarttermin = new JLabel("Starttermin:");

		JLabel lblEmail = new JLabel("Email:");

		JLabel lblThema = new JLabel("Thema:");

		JLabel lblIPAnfang = new JLabel("IP-Anfang:");

		JLabel lblNameIn = new JLabel("New label");

		JLabel lblThemaIN = new JLabel("New label");

		JLabel lblNewLabel = new JLabel("Matrikelnummer:");

		JLabel lblNewLabel_1 = new JLabel("Abgabetermin:");

		JLabel lblNewLabel_2 = new JLabel("NDA benötigt:");

		JLabel lblNewLabel_3 = new JLabel("Semester:");

		JLabel lblNewLabel_4 = new JLabel("IP-Ende:");

		JLabel lblNewLabel_5 = new JLabel("Note Arbeit:");

		JLabel lblmnrIN = new JLabel("");

		JLabel lblAbgabeterminIN = new JLabel("New label");

		JCheckBox chckbxNDAJa = new JCheckBox("Ja");
		buttonGroupNDA.add(chckbxNDAJa);

		JCheckBox chckbxNDANein = new JCheckBox("Nein");
		buttonGroupNDA.add(chckbxNDANein);

		chckbxNDAJa.setSelected(arbeit.getNda_notwenidg());
		chckbxNDANein.setSelected(!arbeit.getNda_notwenidg());

		chckbxNDAJa.addItemListener(new NdaItemListener(arbeit, chckbxNDAJa, true));
		chckbxNDANein.addItemListener(new NdaItemListener(arbeit, chckbxNDANein, false));

		JLabel lblNewLabel_6 = new JLabel("New label");

		JLabel lblNEmailIN = new JLabel("");

		JLabel lblNewLabel_8 = new JLabel("New label");

		JLabel lblSemesterIN = new JLabel("New label");

		if (student != null) {
			lblNameIn.setText(student.getVorname() + " " + student.getNachname());
			lblThemaIN.setText(arbeit.getThema());
			lblmnrIN.setText(student.getMnr() + "");
			lblNEmailIN.setText(student.getEmail());
		}

		JLabel lblNoteVortragIN = new JLabel("New label");

		JLabel lblGesamtnoteIN = new JLabel("New label");

		JLabel lblNoteArbeitIN = new JLabel("");
		if (arbeit.getNoteArbeit() != 0) {
			lblNoteArbeitIN.setText(arbeit.getNoteArbeit() + "");
		}
		if (arbeit.getNoteVortrag()!=0) {
			lblNoteVortragIN.setText(arbeit.getNoteVortrag() + "");
		}
		if (arbeit.getGesamtnote()!=0) {
			lblGesamtnoteIN.setText(arbeit.getGesamtnote()+"");
		}

		JLabel lblNewLabel_11 = new JLabel("New label");

		JButton btnNote = new JButton("Note Arbeit ändern");
		btnNote.addActionListener(new ChangeNoteActionListener(panelManager, panelSwitcher, betreuer, arbeit));

		JLabel lblNewLabel_7 = new JLabel("Note Vortrag:");

		JLabel lblNewLabel_9 = new JLabel("Gesamtnote:");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(betreuerNavigationBar, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblStarttermin)
						.addContainerGap(910, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblEmail)
						.addContainerGap(946, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(btnNote)
						.addContainerGap(821, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblName)
								.addComponent(lblThema).addComponent(lblIPAnfang).addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_7).addComponent(lblNewLabel_9))
						.addGap(66)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblGesamtnoteIN)
								.addComponent(lblNoteVortragIN).addComponent(lblNoteArbeitIN)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_8)
												.addComponent(lblNameIn).addComponent(lblThemaIN)
												.addComponent(lblNewLabel_6).addComponent(lblNEmailIN))
										.addGap(227)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel).addComponent(lblNewLabel_1)
												.addComponent(lblNewLabel_2).addComponent(lblNewLabel_3)
												.addComponent(lblNewLabel_4))
										.addGap(79)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_11).addComponent(lblSemesterIN)
												.addGroup(groupLayout.createSequentialGroup().addComponent(chckbxNDAJa)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(chckbxNDANein))
												.addComponent(lblAbgabeterminIN).addComponent(lblmnrIN))))
						.addContainerGap(250, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblName)
						.addComponent(lblNameIn).addComponent(lblNewLabel).addComponent(lblmnrIN))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblStarttermin)
						.addComponent(lblNewLabel_1).addComponent(lblAbgabeterminIN).addComponent(lblNewLabel_6))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail).addComponent(lblNewLabel_2)
						.addComponent(chckbxNDAJa).addComponent(chckbxNDANein).addComponent(lblNEmailIN))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblThema)
						.addComponent(lblThemaIN).addComponent(lblNewLabel_3).addComponent(lblSemesterIN))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblIPAnfang)
						.addComponent(lblNewLabel_4).addComponent(lblNewLabel_8).addComponent(lblNewLabel_11))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_5)
						.addComponent(lblNoteArbeitIN))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_7)
						.addComponent(lblNoteVortragIN))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_9)
						.addComponent(lblGesamtnoteIN))
				.addGap(15).addComponent(btnNote).addContainerGap(201, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}
}
