package autobatch.gui.studiendekan;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.IPAnnhmenActionListener;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Studiendekan;
import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;


/**
 * Das StudiendekanIpAnfragenPanel ist ein JPanel, das eine Anfrage für die Annahme einer Arbeit durch den Studiendekan darstellt.
 * Es zeigt Informationen über das Unternehmen, das Thema und die Beschreibung der Arbeit sowie Informationen über den Studenten an.
 * Der Studiendekan kann die Anfrage annehmen.
 */
public class StudiendekanIpAnfragenPanel extends JPanel {

    /**
     * Der Student, für den die Anfrage gestellt wurde.
     */
    private Student student;

    /**
     * Die Arbeit, für die die Anfrage gestellt wurde.
     */
    private Arbeit arbeit;

    /**
     * Der Betreuer der Arbeit.
     */
    private Betreuer betreuer;

    /**
     * Erstellt ein neues StudiendekanIpAnfragenPanel.
     *
     * @param panelmanager Der PanelManager, der für die Navigation zwischen den Panels zuständig ist.
     * @param panelSwitcher Der PanelSwitcher, der für das Umschalten zwischen den Panels verantwortlich ist.
     * @param dekan Der Studiendekan, für den das Panel angezeigt wird.
     */
	public StudiendekanIpAnfragenPanel(PanelManager panelmanager, PanelSwitcher panelSwitcher, Studiendekan dekan) {

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		int mnr = Integer.parseInt(panelSwitcher.getData("2") + "");
		this.student = datenbankabfrage.getStudentByMNR(mnr);

		int idArbeit= Integer.parseInt(panelSwitcher.getData("3") + "");
		this.arbeit = datenbankabfrage.getArbeitByID(idArbeit);

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudiendekanNavigationBar studiendekanNavBar = new StudiendekanNavigationBar(panelmanager, panelSwitcher, dekan);

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

		JLabel lblPopUp = new JLabel("Anfrage angenommen");
		lblPopUp.setVisible(false);

		JButton btnAnnehmen = new JButton("Annehmen");
		btnAnnehmen.addActionListener(new IPAnnhmenActionListener(dekan,panelSwitcher, panelmanager, student, betreuer, arbeit, lblPopUp));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblUnternehmen)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblUnternehmenIN, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblThema))
									.addGap(226)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblName)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblNameIN))
										.addComponent(lblStudent)))
								.addComponent(lblBeschreibung)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblThemaIN, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblBeschreibungIN))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(lblPopUp))
								.addComponent(btnAnnehmen)))
						.addComponent(studiendekanNavBar, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(studiendekanNavBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUnternehmen)
						.addComponent(lblUnternehmenIN)
						.addComponent(lblStudent))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblThema)
						.addComponent(lblName)
						.addComponent(lblNameIN))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblThemaIN)
					.addGap(16)
					.addComponent(lblBeschreibung)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBeschreibungIN)
					.addGap(100)
					.addComponent(btnAnnehmen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPopUp)
					.addContainerGap(172, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
