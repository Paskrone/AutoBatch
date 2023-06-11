package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.betreuer.BetreuerAnfragenPanel;
import autobatch.gui.betreuer.BetreuerStudentenPanel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

/**
 * Ein ActionListener, der dazu dient, Anfragen anzunehmen.
 */
public class AnnehmenActionListener implements ActionListener {

	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;
	
	private Student student;
	private Betreuer betreuer;

	private Arbeit arbeit;

	private JLabel lblPopUp;

	/**
	 * Konstruktor für den ActionListener
	 * @param panelSwitcher Der PanelSwitcher, der zum Wechseln zwischen Panels verwendet wird
	 * @param panelManager Der PanelManager, der zur Verwaltung der Panels verwendet wird
	 * @param student Der betroffene Student
	 * @param betreuer Der betroffene Betreuer
	 * @param arbeit Die betroffene Arbeit
	 * @param lblPopUp Label zur Anzeige einer Nachricht
	 */
	public AnnehmenActionListener(PanelSwitcher panelSwitcher,PanelManager panelManager, Student student, Betreuer betreuer, Arbeit arbeit, JLabel lblPopUp) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.panelManager = panelManager;
		
		this.student = student;
		this.betreuer = betreuer;
		this.arbeit = arbeit;
		this.lblPopUp = lblPopUp;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn eine Aktion ausgeführt wird.
	 * @param e Das ausgelöste ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Setzt das Thema als angenommen
		arbeit.setThemaAngenommen(true);

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		
		// Aktualisiert die Datenbank mit den neuen Informationen
		datenbankabfrage.updateDataStudentString(student, betreuer.getEmail(), "betreuer");
		datenbankabfrage.updateDataArbeitBoolean(arbeit, true, "angenommen");
		datenbankabfrage.updateDataStudentInt(student, arbeit.getIdArbeit(), "arbeit");
		
		// Erstellt neue Panels und aktualisiert sie im PanelManager
		JPanel betreuerAnfragenPanel = new BetreuerAnfragenPanel(panelSwitcher, panelManager, betreuer);
		JPanel betreuerStudentenPanel = new BetreuerStudentenPanel(panelSwitcher, panelManager, betreuer);

		panelManager.updatePanels(betreuerAnfragenPanel, "Betreuer_Anfragen");
		panelManager.updatePanels(betreuerStudentenPanel, "Betreuer_Studenten");

		// Zeigt eine Nachricht an
		lblPopUp.setVisible(true);

	}

}
