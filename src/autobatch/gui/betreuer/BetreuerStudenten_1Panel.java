package autobatch.gui.betreuer;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BetreuerStudenten_1Panel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Betreuer betreuer;

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

		JLabel lblThema = new JLabel("Arbeit:");

		JLabel lblIPAnfang = new JLabel("IP-Anfang:");

		JLabel lblNameIn = new JLabel("New label");
		
		JLabel lblThemaIN = new JLabel("New label");

		if (student != null) {
			lblNameIn.setText(student.getVorname() + " " + student.getNachname());
			lblThemaIN.setText(arbeit.getThema());
		}
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(betreuerNavigationBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStarttermin)
					.addContainerGap(910, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEmail)
					.addContainerGap(946, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIPAnfang)
					.addContainerGap(916, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblThema))
					.addGap(96)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblThemaIN)
						.addComponent(lblNameIn))
					.addContainerGap(781, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(lblNameIn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStarttermin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblThema)
						.addComponent(lblThemaIN))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblIPAnfang)
					.addContainerGap(318, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
