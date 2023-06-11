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

public class PanelManager {

	private PanelSwitcher panelSwitcher;

	private LoginPanel loginPanel;
	private RegistrationPanel registrationPanel;

	private StudentenPanel studentenPanel;

	private StudiendekanPanel studiendekanPanel;

	private BetreuerPanel betreuerPanel;

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
			studentenPanel = new StudentenPanel(this, panelSwitcher, currentStudent);

			cards.add(studentenPanel, "Studenten");

			this.panelSwitcher.switchToPanel("Studenten");

		} else if (currentUser instanceof Studiendekan) {

			Studiendekan currentStudiendekan = (Studiendekan) currentUser;
			studiendekanPanel = new StudiendekanPanel(this, panelSwitcher, currentStudiendekan);
			cards.add(studiendekanPanel, "Studiendekane");

			this.panelSwitcher.switchToPanel("Studiendekane");

		} else if (currentUser instanceof Betreuer) {

			Betreuer currentBetreuer = (Betreuer) currentUser;
			betreuerPanel = new BetreuerPanel(this, panelSwitcher, currentBetreuer);

			cards.add(betreuerPanel, "Betreuer");

			this.panelSwitcher.switchToPanel("Betreuer");

		}
	}

	public void updatePanels(JPanel panel, String panelName) {

		cards.add(panel, panelName);

	}

}
