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
import autobatch.gui.student.StudentenIpPanel;
import autobatch.gui.student.StudentenPanel;
import autobatch.gui.studiendekan.StudiendekanPanel;
import autobatch.session.SessionManager;

public class PanelManager {

	private PanelSwitcher panelSwitcher;

	private LoginPanel loginPanel;
	private RegistrationPanel registrationPanel;

	private StudentenPanel studentenPanel;
	private StudentenDatenPanel studentenDatenPanel;
	private StudentenIpPanel studentenIpPanel;
	private StudentenBetreuerPanel studentenBetreuerPanel;
	private StudentenBetreuer_1Panel studentenBetreuer_1Panel;
	private StudentenAbgabenPanel studentenAbgabenPanel;
	private StudentenFormularePanel studentenFormularePanel;

	private StudiendekanPanel studiendekanPanel;

	private BetreuerPanel betreuerPanel;
	private BetreuerAnfragenPanel betreuerAnfragenPanel;
	private BetreuerDatenPanel betreuerDatenPanel;
	private BetreuerStudentenPanel betreuerStudentenPanel;

	private JPanel cards;

	public PanelManager(PanelSwitcher panelSwitcher, JPanel cards) {
		this.panelSwitcher = panelSwitcher;
		this.cards = cards;
		initializeLoginAndRegistrationPanels();
	}

	private void initializeLoginAndRegistrationPanels() {
		loginPanel = new LoginPanel(panelSwitcher, this);
		registrationPanel = new RegistrationPanel(panelSwitcher, this);
		cards.add(loginPanel, "Login");
		cards.add(registrationPanel, "Registrieren");
	}

	// Panels für den Student
	public void initializePanels() {
		Benutzer currentUser = SessionManager.getInstance().getAktuellerBenutzer();
		if (currentUser instanceof Student) {

			Student currentStudent = (Student) currentUser;
			studentenPanel = new StudentenPanel(panelSwitcher, currentStudent);
			studentenDatenPanel = new StudentenDatenPanel(panelSwitcher, currentStudent);
			studentenIpPanel = new StudentenIpPanel(panelSwitcher, currentStudent);
			studentenBetreuerPanel = new StudentenBetreuerPanel(panelSwitcher, this, currentStudent);
			studentenBetreuer_1Panel = new StudentenBetreuer_1Panel(panelSwitcher, currentStudent);
			studentenAbgabenPanel = new StudentenAbgabenPanel(this, panelSwitcher, currentStudent);
			studentenFormularePanel = new StudentenFormularePanel(panelSwitcher, currentStudent);

			cards.add(studentenPanel, "Studenten");
			cards.add(studentenDatenPanel, "Studenten_Daten");
			cards.add(studentenIpPanel, "Studenten_Ip");
			cards.add(studentenBetreuerPanel, "Studenten_Betreuer");
			cards.add(studentenBetreuer_1Panel, "Studenten_Betreuer_1");
			cards.add(studentenAbgabenPanel, "Studenten_Abgaben");
			cards.add(studentenFormularePanel, "Studenten_Formulare");

			this.panelSwitcher.switchToPanel("Studenten");

		} else if (currentUser instanceof Studiendekan) {

			Studiendekan currentStudiendekan = (Studiendekan) currentUser;
			studiendekanPanel = new StudiendekanPanel(panelSwitcher, currentStudiendekan);

			cards.add(studiendekanPanel, "Studiendekane");

			this.panelSwitcher.switchToPanel("Studiendekane");

		} else if (currentUser instanceof Betreuer) {

			Betreuer currentBetreuer = (Betreuer) currentUser;
			betreuerPanel = new BetreuerPanel(panelSwitcher, currentBetreuer);
			betreuerAnfragenPanel = new BetreuerAnfragenPanel(panelSwitcher, this, currentBetreuer);
			betreuerDatenPanel = new BetreuerDatenPanel(panelSwitcher, currentBetreuer);
			betreuerStudentenPanel = new BetreuerStudentenPanel(panelSwitcher, this, currentBetreuer);

			cards.add(betreuerPanel, "Betreuer");
			cards.add(betreuerAnfragenPanel, "Betreuer_Anfragen");
			cards.add(betreuerDatenPanel, "Betreuer_Daten");
			cards.add(betreuerStudentenPanel, "Betreuer_Studenten");

			this.panelSwitcher.switchToPanel("Betreuer");

		}
	}

	public void updatePanels(JPanel panel, String panelName) {

		cards.add(panel, panelName);

	}
	
}
	
	

