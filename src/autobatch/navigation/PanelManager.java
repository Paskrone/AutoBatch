package autobatch.navigation;

import javax.swing.JPanel;

import autobatch.gui.LoginPanel;
import autobatch.gui.RegistrationPanel;
import autobatch.gui.student.StudentenDatenPanel;
import autobatch.gui.student.StudentenPanel;

public class PanelManager {
	private PanelSwitcher panelSwitcher;
    private LoginPanel loginPanel;
    private StudentenPanel studentenPanel;
    private RegistrationPanel registrationPanel;
    private StudentenDatenPanel studentenDatenPanel;
    
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
        studentenDatenPanel = new StudentenDatenPanel(panelSwitcher);
    }

    private void addPanelsToCards() {
        cards.add(loginPanel, "Login");
        cards.add(studentenPanel, "Studenten");
        cards.add(registrationPanel, "Registrieren");
        cards.add(studentenDatenPanel, "Studenten_Daten");
    }
}
