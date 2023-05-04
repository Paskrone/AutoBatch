package autobatch.navigation;

import javax.swing.JPanel;

import autobatch.gui.LoginPanel;
import autobatch.gui.Main;
import autobatch.gui.RegistrationPanel;
import autobatch.gui.StudentenPanel;

public class PanelManager {
	private PanelSwitcher panelSwitcher;
    private LoginPanel loginPanel;
    private StudentenPanel studentenPanel;
    private RegistrationPanel registrationPanel;
    private JPanel cards;

    public PanelManager(PanelSwitcher panelSwitcher, JPanel cards) {
        this.panelSwitcher = panelSwitcher;
        this.cards = cards;
        initializePanels();
        addPanelsToCards();
    }

    private void initializePanels() {
        loginPanel = new LoginPanel(panelSwitcher);
        studentenPanel = new StudentenPanel(panelSwitcher);
        registrationPanel = new RegistrationPanel(panelSwitcher);
    }

    private void addPanelsToCards() {
        cards.add(loginPanel, "Login");
        cards.add(studentenPanel, "Studenten");
        cards.add(registrationPanel, "Registrieren");
    }
}
