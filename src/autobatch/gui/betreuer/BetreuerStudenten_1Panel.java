package autobatch.gui.betreuer;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businesslogic.itemlistener.NdaItemListener;
import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;

public class BetreuerStudenten_1Panel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Betreuer betreuer;
	private final ButtonGroup buttonGroupNDA = new ButtonGroup();

	public BetreuerStudenten_1Panel(PanelSwitcher panelSwitcher, Betreuer betreuer) {

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

		if (student != null) {
			lblNameIn.setText(student.getVorname() + " " + student.getNachname());
			lblThemaIN.setText(arbeit.getThema());
		}

		JLabel lblNewLabel = new JLabel("Matrikelnummer:");

		JLabel lblNewLabel_1 = new JLabel("Abgabetermin:");

		JLabel lblNewLabel_2 = new JLabel("NDA benötigt:");

		JLabel lblNewLabel_3 = new JLabel("Semester:");

		JLabel lblNewLabel_4 = new JLabel("IP-Ende:");

		JLabel lblNewLabel_5 = new JLabel("Note:");

		JLabel lblmnrIN = new JLabel("New label");

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

		JLabel lblNewLabel_7 = new JLabel("New label");

		JLabel lblNewLabel_8 = new JLabel("New label");

		JLabel lblNewLabel_9 = new JLabel("New label");

		JLabel lblNewLabel_10 = new JLabel("New label");

		JLabel lblNewLabel_11 = new JLabel("New label");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(betreuerNavigationBar, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblStarttermin)
						.addContainerGap(910, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblEmail)
						.addContainerGap(946, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblName)
								.addComponent(lblThema).addComponent(lblIPAnfang).addComponent(lblNewLabel_5))
						.addGap(74)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_9)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_8).addComponent(lblNameIn)
												.addComponent(lblThemaIN).addComponent(lblNewLabel_6)
												.addComponent(lblNewLabel_7))
										.addGap(227)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel).addComponent(lblNewLabel_1)
												.addComponent(lblNewLabel_2).addComponent(lblNewLabel_3)
												.addComponent(lblNewLabel_4))
										.addGap(79)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_11).addComponent(lblNewLabel_10)
												.addGroup(groupLayout.createSequentialGroup().addComponent(chckbxNDAJa)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(chckbxNDANein))
												.addComponent(lblAbgabeterminIN).addComponent(lblmnrIN))))
						.addContainerGap(258, Short.MAX_VALUE)));
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
						.addComponent(chckbxNDAJa).addComponent(chckbxNDANein).addComponent(lblNewLabel_7))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblThema)
						.addComponent(lblThemaIN).addComponent(lblNewLabel_3).addComponent(lblNewLabel_10))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblIPAnfang)
						.addComponent(lblNewLabel_4).addComponent(lblNewLabel_8).addComponent(lblNewLabel_11))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_9))
				.addContainerGap(296, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}
}
