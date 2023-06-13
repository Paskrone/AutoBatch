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

	/**
	 * PanelSwitcher-Objekt, das für die Verwaltung des Wechsels zwischen verschiedenen Panels in der GUI verwendet wird.
	 */
	private PanelSwitcher panelSwitcher;

	/**
	 * PanelManager-Objekt, das zur Verwaltung der Panels in der GUI verwendet wird.
	 */
	private PanelManager panelManager;

	/**
	 * Student-Objekt, das den betroffenen Studenten repräsentiert.
	 */
	private Student student;

	/**
	 * Betreuer-Objekt, das den betroffenen Betreuer repräsentiert.
	 */
	private Betreuer betreuer;

	/**
	 * Studiendekan-Objekt, das den betroffenen Studiendekan repräsentiert.
	 */
	private Studiendekan dekan;

	/**
	 * Arbeit-Objekt, das die Arbeit repräsentiert, die verarbeitet wird.
	 */
	private Arbeit arbeit;

	/**
	 * JLabel, das dazu dient, Pop-up-Nachrichten anzuzeigen.
	 */
	private JLabel lblPopUp;


	/**
	 * Konstruktor
	 *
	 * @param dekan          Das Studiendekan-Objekt, das den betroffenen Studiendekan repräsentiert.
	 * @param panelSwitcher  Das PanelSwitcher-Objekt, das für die Verwaltung des Wechsels zwischen verschiedenen Panels in der GUI verwendet wird.
	 * @param panelManager   Das PanelManager-Objekt, das zur Verwaltung der Panels in der GUI verwendet wird.
	 * @param student        Das Student-Objekt, das den betroffenen Studenten repräsentiert.
	 * @param betreuer       Das Betreuer-Objekt, das den betroffenen Betreuer repräsentiert.
	 * @param arbeit         Das Arbeit-Objekt, das die Arbeit repräsentiert, die verarbeitet wird.
	 * @param lblPopUp       Das JLabel, das dazu dient, Pop-up-Nachrichten anzuzeigen.
	 */
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
	 * Zunächst wird ein neuer Datenbankzugriff erstellt, danach werden der Betreuer des betroffenen Studenten und der Status der IP-Anfrage 
	 * in der Datenbank aktualisiert. Anschließend wird ein neues Panel für die Anfragen des Studiendekans erstellt und im PanelManager aktualisiert.
	 * Schließlich wird eine Nachricht für den Benutzer angezeigt, dass die Anfrage angenommen wurde.
	 * @param e Das ausgelöste ActionEvent.
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
