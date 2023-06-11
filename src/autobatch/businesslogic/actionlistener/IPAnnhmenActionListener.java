package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Studiendekan;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.betreuer.BetreuerAnfragenPanel;
import autobatch.gui.betreuer.BetreuerStudentenPanel;
import autobatch.gui.studiendekan.StudiendekanIpAnfragenPanel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

/**
 * ActionListener zur Verarbeitung der Annahme der IP-Anfrage.
 */
public class IPAnnhmenActionListener implements ActionListener {

	// Instanzvariablen
	private PanelSwitcher panelSwitcher; // zur Verwaltung des Panelwechsels
	private PanelManager panelManager; // zur Verwaltung der Panels

	private Student student; // betroffener Student
	private Betreuer betreuer; // betroffener Betreuer
	private Studiendekan dekan; // betroffener Studiendekan

	private Arbeit arbeit; // Arbeit, die verarbeitet wird

	private JLabel lblPopUp; // Label zur Anzeige von Benachrichtigungen

	// Konstruktor
	public IPAnnhmenActionListener(Studiendekan dekan, PanelSwitcher panelSwitcher, PanelManager panelManager,
			Student student, Betreuer betreuer, Arbeit arbeit, JLabel lblPopUp) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.panelManager = panelManager;

		this.student = student;
		this.betreuer = betreuer;
		this.dekan = dekan;
		this.arbeit = arbeit;
		this.lblPopUp = lblPopUp;
	}

	/**
	 * Diese Methode wird ausgeführt, wenn der Benutzer den Button zum Annehmen der IP-Anfrage drückt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// Erzeugen eines neuen Datenbankzugriffs
		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

		// Aktualisieren des Studenten und der Arbeit in der Datenbank
		datenbankabfrage.updateDataStudentString(student, student.getBetreuer(), "betreuer");
		datenbankabfrage.updateDataArbeitBoolean(arbeit, true, "ipAngenommen");

		// Erstellen eines neuen StudiendekanIpAnfragenPanels
		JPanel studiendekanAnfragenPanl = new StudiendekanIpAnfragenPanel(panelManager, panelSwitcher, dekan);

		// Aktualisieren des Panels
		panelManager.updatePanels(studiendekanAnfragenPanl, "studiendekanIpAnfragen");

		// Anzeigen des Pop-Up-Labels
		lblPopUp.setVisible(true);

	}

}
