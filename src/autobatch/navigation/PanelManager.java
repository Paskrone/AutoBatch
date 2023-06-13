package autobatch.navigation;

import javax.swing.JPanel;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Studiendekan;
import autobatch.gui.betreuer.BetreuerAnfragenPanel;
import autobatch.gui.betreuer.BetreuerDatenPanel;
import autobatch.gui.betreuer.BetreuerPanel;
import autobatch.gui.betreuer.BetreuerStudentenPanel;
import autobatch.gui.loginandregistration.LoginPanel;
import autobatch.gui.loginandregistration.RegistrationPanel;
import autobatch.gui.student.StudentenAbgabenPanel;
import autobatch.gui.student.StudentenBetreuerPanel;
import autobatch.gui.student.StudentenBetreuer_1Panel;
import autobatch.gui.student.StudentenDatenPanel;
import autobatch.gui.student.StudentenFormularePanel;
import autobatch.gui.student.StudentenIP_1Panel;
import autobatch.gui.student.StudentenIpPanel;
import autobatch.gui.student.StudentenPanel;
import autobatch.gui.studiendekan.StudiendekanIpPanel;
import autobatch.gui.studiendekan.StudiendekanPanel;
import autobatch.session.SessionManager;

/**
 * Verwaltet die unterschiedlichen Panels für die Benutzerschnittstelle.
 */
public class PanelManager {

	/**
	 * Hilft bei der Umschaltung zwischen den Panels.
	 */
	private PanelSwitcher panelSwitcher;

	/**
	 * Panel für die Anmeldung.
	 */
	private LoginPanel loginPanel;

	/**
	 * Panel für die Registrierung.
	 */
	private RegistrationPanel registrationPanel;

	/**
	 * Panel für die Studentenansicht.
	 */
	private StudentenPanel studentenPanel;

	/**
	 * Panel für die Ansicht des Studiendekans.
	 */
	private StudiendekanPanel studiendekanPanel;

	/**
	 * Panel für die Betreueransicht.
	 */
	private BetreuerPanel betreuerPanel;

	/**
	 * Container für alle Panels.
	 */
	private JPanel cards;


	/**
	 * Verwaltet die unterschiedlichen Panels für die Benutzerschnittstelle.
	 * 
	 * @param panelSwitcher Der PanelSwitcher zum Wechseln zwischen den Panels.
	 * @param cards Der JPanel-Container, der die Panels enthält.
	 */
	public PanelManager(PanelSwitcher panelSwitcher, JPanel cards) {
		this.panelSwitcher = panelSwitcher;
		this.cards = cards;
		initializeLoginAndRegistrationPanels();
	}

	/**
	 * Initialisiert die Login- und Registrierungs-Panels und fügt sie zum cards-Container hinzu.
	 */
	private void initializeLoginAndRegistrationPanels() {
		loginPanel = new LoginPanel(panelSwitcher, this);
		registrationPanel = new RegistrationPanel(panelSwitcher, this);
		cards.add(loginPanel, "Login");
		cards.add(registrationPanel, "Registrieren");
	}

	/**
	 * Initialisiert die Panels basierend auf der Art des aktuell angemeldeten Benutzers.
	 * Fügt das entsprechende Panel zum cards-Container hinzu und wechselt zu diesem.
	 */
	public void initializePanels() {
		Benutzer currentUser = SessionManager.getInstance().getAktuellerBenutzer();
		if (currentUser instanceof Student) {
			
			 Student currentStudent = (Student) currentUser;
			 studentenPanel = new StudentenPanel(this, panelSwitcher, currentStudent);
			 
					 
			 cards.add(studentenPanel, "Studenten");
					 
			 this.panelSwitcher.switchToPanel("Studenten");

		} else if (currentUser instanceof Studiendekan) {
			
			 Studiendekan currentStudiendekan = (Studiendekan) currentUser;
			 studiendekanPanel = new StudiendekanPanel(this, panelSwitcher,
			 currentStudiendekan); cards.add(studiendekanPanel, "Studiendekane");
			 
			 this.panelSwitcher.switchToPanel("Studiendekane");

		} else if (currentUser instanceof Betreuer) {
			
			 Betreuer currentBetreuer = (Betreuer) currentUser; betreuerPanel = new
			 BetreuerPanel(this, panelSwitcher, currentBetreuer);
					 
			 cards.add(betreuerPanel, "Betreuer");
					
			 this.panelSwitcher.switchToPanel("Betreuer");

		}
	}

	/**
	 * Aktualisiert die Panels. Fügt das gegebene Panel zum cards-Container hinzu.
	 *
	 * @param panel Das hinzuzufügende Panel.
	 * @param panelName Der Name des hinzuzufügenden Panels.
	 */
	public void updatePanels(JPanel panel, String panelName) {
		cards.add(panel, panelName);
	
		}
	}