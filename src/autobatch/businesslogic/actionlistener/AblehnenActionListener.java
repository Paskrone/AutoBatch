package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.betreuer.BetreuerAnfragenPanel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

/**
 * Ein ActionListener, der dazu dient, Anfragen abzulehnen.
 */
public class AblehnenActionListener implements ActionListener {

	/**
	 * Der PanelSwitcher, der für das Umschalten der Panels in der Anwendung zuständig ist.
	 */
	private PanelSwitcher panelSwitcher;

	/**
	 * Der PanelManager, der für das Verwalten der Panels in der Anwendung zuständig ist.
	 */
	private PanelManager panelManager;

	/**
	 * Der Student, der die Anfrage gestellt hat, die abgelehnt werden soll.
	 */
	private Student student;

	/**
	 * Der Betreuer, der die Anfrage ablehnt.
	 */
	private Betreuer betreuer;

	/**
	 * Ein JLabel, das zur Anzeige einer Popup-Nachricht verwendet wird, wenn die Anfrage abgelehnt wird.
	 */
	private JLabel lblPopUp;

	/**
	 * Konstruktor für den ActionListener
	 * @param panelSwitcher Der PanelSwitcher der Anwendung
	 * @param panelManager Der PanelManager der Anwendung
	 * @param student Der betroffene Student
	 * @param betreuer Der betroffene Betreuer
	 * @param lblPopUp Label zur Anzeige einer Nachricht
	 */
	public AblehnenActionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, Student student,
			Betreuer betreuer, JLabel lblPopUp) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.panelManager = panelManager;
		this.student = student;
		this.betreuer = betreuer;
		this.lblPopUp = lblPopUp;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn eine Aktion ausgeführt wird.
	 * @param e Das ausgelöste ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

		// Löscht die Daten aus der Datenbank
		datenbankabfrage.deleteDataArbeit(student.getMnr(), betreuer.getEmail());

		// Erstellt ein neues BetreuerAnfragenPanel und aktualisiert das Panel
		JPanel betreuerAnfragenPanel = new BetreuerAnfragenPanel(panelSwitcher, panelManager, betreuer);
		panelManager.updatePanels(betreuerAnfragenPanel, "Betreuer_Anfragen");

		// Zeigt eine Nachricht an, dass die Anfrage abgelehnt wurde
		lblPopUp.setText("Anfrage abgelehnt");
		lblPopUp.setVisible(true);
	}

}
