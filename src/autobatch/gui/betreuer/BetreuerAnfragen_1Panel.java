package autobatch.gui.betreuer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.AnfragenActionListener;
import autobatch.businesslogic.actionlistener.AnnehmenActionListener;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.student.StudentNavigationBar;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BetreuerAnfragen_1Panel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;
	
	private Student student;

	private Arbeit arbeit;

	private Betreuer betreuer;

	public BetreuerAnfragen_1Panel(PanelSwitcher panelSwitcher, PanelManager panelManager, Betreuer betreuer) {
		this.panelSwitcher = panelSwitcher;

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		int mnr = Integer.parseInt(panelSwitcher.getData("2") + "");
		this.student = datenbankabfrage.getStudentByMNR(mnr);

		int idThema = Integer.parseInt(panelSwitcher.getData("3") + "");
		this.arbeit = datenbankabfrage.getArbeitByID(idThema);

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		BetreuerNavigationBar betreuerNavigationBar = new BetreuerNavigationBar(panelSwitcher);

		System.out.println(panelSwitcher.getData("1"));

		JLabel lblUnternehmen = new JLabel("Unternehmen:");

		JLabel lblThema = new JLabel("Arbeit:");

		JLabel lblBeschreibung = new JLabel("Beschreibung:");

		JLabel lblUnternehmenIN = new JLabel(arbeit.getUnternehmen());

		JLabel lblStudent = new JLabel("Student Informationen:");

		JLabel lblThemaIN = new JLabel(arbeit.getThema());

		JLabel lblBeschreibungIN = new JLabel("");

		if (arbeit.getBeschreibung() != null) {
			lblBeschreibungIN.setText(arbeit.getBeschreibung());
		}

		JLabel lblName = new JLabel("Name:");

		JLabel lblNameIN = new JLabel(student.getVorname() + " " + student.getNachname());

		JLabel lblNewLabel = new JLabel("New label");

		JLabel lblPopUp = new JLabel("Anfrage angenommen");
		lblPopUp.setVisible(false);

		JButton btnAnnehmen = new JButton("Annehmen");
		btnAnnehmen.addActionListener(new AnnehmenActionListener(panelSwitcher, panelManager, student, betreuer, arbeit, lblPopUp));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addComponent(lblUnternehmen)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblUnternehmenIN, GroupLayout.PREFERRED_SIZE, 181,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(lblThema)).addGap(226)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addGroup(groupLayout.createSequentialGroup().addComponent(lblName)
														.addPreferredGap(ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblNameIN))
												.addComponent(lblStudent).addComponent(lblNewLabel)))
								.addComponent(lblBeschreibung)))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblThemaIN,
								GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblBeschreibungIN))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(lblPopUp))
										.addComponent(btnAnnehmen))))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblUnternehmen)
								.addComponent(lblUnternehmenIN).addComponent(lblStudent))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblThema)
								.addComponent(lblName).addComponent(lblNameIN))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblThemaIN)
								.addComponent(lblNewLabel))
						.addGap(16).addComponent(lblBeschreibung).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblBeschreibungIN).addGap(100).addComponent(btnAnnehmen)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblPopUp)
						.addContainerGap(156, Short.MAX_VALUE)));
		setLayout(groupLayout);
	}
}
