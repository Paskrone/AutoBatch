package autobatch.gui.student;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.LoginActionListener;
import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.betreuer.BetreuerStudenten_1Panel;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StudentenBetreuer_1Panel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Student student;
	private Betreuer betreuer;

	public StudentenBetreuer_1Panel(PanelSwitcher panelSwitcher, Student student) {
		this.student = student;
		this.panelSwitcher = panelSwitcher;
		
		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		betreuer = datenbankabfrage.getBetreuerByMail(student.getBetreuer());
		Arbeit arbeit = datenbankabfrage.getArbeitByID(student.getArbeit());
		
		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelSwitcher, student);
		
		JLabel lblBetreuerInfos = new JLabel("Betreuer Informationen:");
		
		JLabel lblName = new JLabel("Name:");
		
		JLabel lblEmail = new JLabel("Email:");
		
		JLabel lblThema = new JLabel("Thema:");
		
		JLabel lblNameIN = new JLabel(betreuer.getVorname() + " " + betreuer.getNachname());
		
		JLabel lblEmailIN = new JLabel(betreuer.getEmail());
		
		JLabel lblThemaIN = new JLabel(arbeit.getThema());

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBetreuerInfos))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblEmail)
						.addComponent(lblThema))
					.addGap(105)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblThemaIN)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNameIN, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEmailIN)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(693))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblBetreuerInfos)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(lblNameIN))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(lblEmailIN))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblThema)
						.addComponent(lblThemaIN))
					.addContainerGap(322, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}