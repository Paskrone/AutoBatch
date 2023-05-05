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
        initializeLoginAndRegistrationPanels();
    }

    private void initializePanels() {
        
        registrationPanel = new RegistrationPanel(panelSwitcher);
        studentenDatenPanel = new StudentenDatenPanel(panelSwitcher);
    }
    
    private void initializeLoginAndRegistrationPanels() {
        loginPanel = new LoginPanel(panelSwitcher, this);
        registrationPanel = new RegistrationPanel(panelSwitcher);
        cards.add(loginPanel, "Login");
        cards.add(registrationPanel, "Registrieren");
    }
    
    //Panels für den Student
    public void initializeStudentPanels() {
        studentenPanel = new StudentenPanel(panelSwitcher);
        studentenDatenPanel = new StudentenDatenPanel(panelSwitcher);

        cards.add(studentenPanel, "Studenten");
        cards.add(studentenDatenPanel, "Studenten_Daten");
    }
    
    //Panels für den Studiendekan
    public void initializeStudiendekanPanels() {
        studentenPanel = new StudentenPanel(panelSwitcher);
        studentenDatenPanel = new StudentenDatenPanel(panelSwitcher);

        cards.add(studentenPanel, "Studenten");
        cards.add(studentenDatenPanel, "Studenten_Daten");
    }
    
    //Panels für den Betreuer
    public void initializeBetreuerPanels() {
        studentenPanel = new StudentenPanel(panelSwitcher);
        studentenDatenPanel = new StudentenDatenPanel(panelSwitcher);

        cards.add(studentenPanel, "Studenten");
        cards.add(studentenDatenPanel, "Studenten_Daten");
    }

    
}
