package autobatch.gui.student;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.IPAnfrageActionListener;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.businessobjects.Arbeit;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;


import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Die Klasse StudentenIpPanel repräsentiert ein JPanel, das spezifisch für Studenten erstellt wurde.
 * Es stellt verschiedene Informationen zur Anzeige und Interaktion zur Verfügung,
 * einschließlich Daten zur Arbeit des Studenten und zur Beantragung eines interdisziplinären Projekts (IP).
 */
public class StudentenIpPanel extends JPanel {

	/**
     * Das Textfeld für die Eingabe des Datums.
     */
    private JTextField txtDatum;

    /**
     * Das Textfeld für die Anzeige und Bearbeitung des Arbeitsthemas.
     */
    private JTextField txtThema;

    /**
     * Das Textfeld für die Anzeige und Bearbeitung des Unternehmensnamens.
     */
    private JTextField txtUN;

    /**
     * Das Textfeld für die Anzeige und Bearbeitung der Beschreibung.
     */
    private JTextField txtBeschreibung;

    /**
     * Erstellt ein neues StudentenIpPanel.
     *
     * @param panelManager   Der Manager, der die Navigation zwischen den Panels verwaltet.
     * @param panelSwitcher  Ein Helfer zum Wechseln zwischen verschiedenen Panels.
     * @param student        Der Student, für den das Panel angezeigt wird.
     */
	public StudentenIpPanel(PanelManager panelManager, PanelSwitcher panelSwitcher, Student student) {

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		Arbeit arbeit = datenbankabfrage.getArbeitByID(student.getArbeit());

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudentNavigationBar studentNavigationBar = new StudentNavigationBar(panelManager, panelSwitcher, student);

		txtDatum = new JTextField();
		txtDatum.setColumns(10);

		txtThema = new JTextField();
		txtThema.setColumns(10);
		txtThema.setText(arbeit.getThema());

		txtUN = new JTextField();
		txtUN.setColumns(10);
		txtUN.setText(arbeit.getUnternehmen());

		txtBeschreibung = new JTextField();
		txtBeschreibung.setColumns(10);
		txtBeschreibung.setText(arbeit.getBeschreibung());

		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblThema = new JLabel("Arbeit");
		lblThema.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblUnternehmen = new JLabel("Unternehmen");
		lblUnternehmen.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblBeschreibung = new JLabel("Beschreibung");
		lblBeschreibung.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblPopUp = new JLabel("Eingabe überprüfen!");
		lblPopUp.setVisible(false);

		JButton btnAnfragen = new JButton("Anfrage Schicken");
		btnAnfragen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAnfragen.addActionListener(
				new IPAnfrageActionListener(arbeit, student, txtDatum, txtThema, txtUN, txtBeschreibung, lblPopUp));

		JLabel lblNewLabel = new JLabel("Anfrage bereits gestellt.");

		if (arbeit.getIpStart()!=null) {
			lblNewLabel.setVisible(false);
		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup().addGap(50).addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING).addComponent(lblPopUp)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblBeschreibung, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
										.addComponent(lblUnternehmen, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblThema, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup().addGap(2)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel).addComponent(lblDatum))))
								.addGap(26)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, 272,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(txtUN, GroupLayout.PREFERRED_SIZE, 272,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(txtBeschreibung, GroupLayout.PREFERRED_SIZE, 272,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(txtThema, GroupLayout.PREFERRED_SIZE, 272,
																GroupLayout.PREFERRED_SIZE))
												.addGap(179).addComponent(btnAnfragen, GroupLayout.PREFERRED_SIZE, 204,
														GroupLayout.PREFERRED_SIZE)))))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(studentNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblNewLabel).addGap(1)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDatum, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
								.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtThema, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblThema, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGap(27)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtUN, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUnternehmen, GroupLayout.PREFERRED_SIZE, 37,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup().addGap(36)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtBeschreibung, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBeschreibung, GroupLayout.PREFERRED_SIZE, 37,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup().addGap(109).addComponent(btnAnfragen,
										GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
						.addGap(13).addComponent(lblPopUp).addGap(39)));
		setLayout(groupLayout);

	}
}
