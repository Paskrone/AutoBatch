package autobatch.gui.betreuer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.AblehnenActionListener;
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

/**
 * Eine JPanel-Klasse, die das Panel zur Darstellung der Betreueranfragen implementiert.
 */
public class BetreuerAnfragen_1Panel extends JPanel {

	/**
	 * Verwaltet die Navigation zwischen den Panels.
	 */
	private PanelSwitcher panelSwitcher;
	
	/**
	 * Verwaltet die Anzeige der Panels.
	 */
	private PanelManager panelManager;
	
	/**
	 * Der Student, für den die Anfragen dargestellt werden sollen.
	 */
	private Student student;

	/**
	 * Die Arbeit, für die die Anfragen dargestellt werden sollen.
	 */
	private Arbeit arbeit;

	/**
	 * Der Betreuer, der die Anfragen einsehen kann.
	 */
	private Betreuer betreuer;

	/**
	 * Erzeugt ein neues BetreuerAnfragen_1Panel, das die Anfragen eines bestimmten Betreuers anzeigt.
	 *
	 * @param panelSwitcher der PanelSwitcher, der zur Navigation zwischen den Panels verwendet wird.
	 * @param panelManager der PanelManager, der zur Anzeige der Panels verwendet wird.
	 * @param betreuer der Betreuer, der die Anfragen einsehen kann.
	 */
	public BetreuerAnfragen_1Panel(PanelSwitcher panelSwitcher, PanelManager panelManager, Betreuer betreuer) {
		this.panelSwitcher = panelSwitcher;

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		int mnr = Integer.parseInt(panelSwitcher.getData("2") + "");
		this.student = datenbankabfrage.getStudentByMNR(mnr);

		int idThema = Integer.parseInt(panelSwitcher.getData("3") + "");
		this.arbeit = datenbankabfrage.getArbeitByID(idThema);

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		BetreuerNavigationBar betreuerNavigationBar = new BetreuerNavigationBar(panelManager, panelSwitcher, betreuer);

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

		JLabel lblNewLabel = new JLabel("Matrikelnummer:");

		JLabel lblMNR = new JLabel(student.getMnr() + "");

		JLabel lblPopUp = new JLabel("Anfrage angenommen");
		lblPopUp.setVisible(false);

		JButton btnAnnehmen = new JButton("Annehmen");
		btnAnnehmen.addActionListener(
				new AnnehmenActionListener(panelSwitcher, panelManager, student, betreuer, arbeit, lblPopUp));

		JButton btnAblehnen = new JButton("Ablehnen");
		btnAblehnen.addActionListener(
				new AblehnenActionListener(panelSwitcher, panelManager, student, betreuer, lblPopUp));
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		
		JLabel lblEmail = new JLabel(student.getEmail());

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUnternehmen)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblUnternehmenIN, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblThema)
								.addComponent(lblBeschreibung))
							.addGap(226)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStudent)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(lblName)
										.addComponent(lblNewLabel_1))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEmail)
										.addComponent(lblNameIN)
										.addComponent(lblMNR)))))
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
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAnnehmen)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAblehnen)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblThemaIN)
						.addComponent(lblNewLabel)
						.addComponent(lblMNR))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(lblBeschreibung)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBeschreibungIN)
							.addGap(100)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAnnehmen)
								.addComponent(btnAblehnen))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPopUp))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(lblEmail))))
					.addContainerGap(156, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
