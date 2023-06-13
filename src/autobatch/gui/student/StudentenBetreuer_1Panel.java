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
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Eine Klasse, die ein JPanel repräsentiert, in dem die Informationen eines Betreuers und des zugewiesenen Themas eines Studenten angezeigt werden.
 */
public class StudentenBetreuer_1Panel extends JPanel {

	/**
     * Hilft beim Wechseln zwischen verschiedenen Panels.
     */
    private PanelSwitcher panelSwitcher;

    /**
     * Der aktuelle Student, dessen Betreuer- und Themeninformationen angezeigt werden.
     */
    private Student student;

    /**
     * Der Betreuer des aktuellen Studenten.
     */
    private Betreuer betreuer;

    /**
     * Erstellt ein neues Panel, das die Betreuer- und Themeninformationen eines Studenten anzeigt.
     *
     * @param panelManager Der Manager, der die Navigation zwischen den Panels verwaltet.
     * @param panelSwitcher Ein Helfer zum Wechseln zwischen verschiedenen Panels.
     * @param student Der aktuelle Student, dessen Betreuer- und Themeninformationen angezeigt werden.
     */
	public StudentenBetreuer_1Panel(PanelManager panelManager, PanelSwitcher panelSwitcher, Student student) {
		this.student = student;
		this.panelSwitcher = panelSwitcher;

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		betreuer = datenbankabfrage.getBetreuerByMail(student.getBetreuer());
		Arbeit arbeit = datenbankabfrage.getArbeitByID(student.getArbeit());

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelManager, panelSwitcher, student);

		JLabel lblBetreuerInfos = new JLabel("Betreuer Informationen:");

		JLabel lblName = new JLabel("Name:");

		JLabel lblEmail = new JLabel("Email:");

		JLabel lblThema = new JLabel("Thema:");

		JLabel lblNameIN = new JLabel("");
		JLabel lblEmailIN = new JLabel("");
		JLabel lblThemaIN = new JLabel("");

		if (betreuer != null && arbeit != null) {
			lblNameIN.setText(betreuer.getVorname() + " " + betreuer.getNachname());
			lblEmailIN.setText(betreuer.getEmail());
			lblThemaIN.setText(arbeit.getThema());

		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblBetreuerInfos))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblName)
								.addComponent(lblEmail).addComponent(lblThema))
						.addGap(105)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblThemaIN)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNameIN, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblEmailIN)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGap(693)));
		groupLayout
				.setVerticalGroup(
						groupLayout
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblBetreuerInfos)
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblName).addComponent(lblNameIN))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblEmail).addComponent(lblEmailIN))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblThema).addComponent(lblThemaIN))
										.addContainerGap(322, Short.MAX_VALUE)));
		setLayout(groupLayout);
	}
}