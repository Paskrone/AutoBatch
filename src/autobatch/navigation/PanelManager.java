package autobatch.navigation;

import javax.swing.JPanel;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Studiendekan;
import autobatch.gui.betreuer.BetreuerPanel;
import autobatch.gui.loginandregistration.LoginPanel;
import autobatch.gui.loginandregistration.RegistrationPanel;
import autobatch.gui.student.StudentenDatenPanel;
import autobatch.gui.student.StudentenPanel;
import autobatch.gui.studiendekan.StudiendekanPanel;
import autobatch.session.SessionManager;

public class PanelManager {
	private PanelSwitcher panelSwitcher;
    private LoginPanel loginPanel;
    private StudentenPanel studentenPanel;
    private RegistrationPanel registrationPanel;
    private StudentenDatenPanel studentenDatenPanel;
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
        registrationPanel = new RegistrationPanel(panelSwitcher);
        cards.add(loginPanel, "Login");
        cards.add(registrationPanel, "Registrieren");
    }
    
    //Panels für den Student
    public void initializeStudentPanels() {
    	Benutzer currentUser = SessionManager.getInstance().getAktuellerBenutzer();
        if (currentUser instanceof Student) {
        	
            Student currentStudent = (Student) currentUser;
            studentenPanel = new StudentenPanel(panelSwitcher);
            studentenDatenPanel = new StudentenDatenPanel(panelSwitcher, currentStudent);

            cards.add(studentenPanel, "Studenten");
            cards.add(studentenDatenPanel, "Studenten_Daten");
        }
    }
    
    //Panels für den Studiendekan
    public void initializeStudiendekanPanels() {
    	Benutzer currentUser = SessionManager.getInstance().getAktuellerBenutzer();
        if (currentUser instanceof Studiendekan) {
        	
            Studiendekan currentStudiendekan = (Studiendekan) currentUser;
            studiendekanPanel = new StudiendekanPanel(panelSwitcher, currentStudiendekan);

            cards.add(studiendekanPanel, "Studiendekane");
        }
    }
    
    //Panels für den Betreuer
    public void initializeBetreuerPanels() {
    	Benutzer currentUser = SessionManager.getInstance().getAktuellerBenutzer();
        if (currentUser instanceof Betreuer) {
        	
            Betreuer currentBetreuer = (Betreuer) currentUser;
            betreuerPanel = new BetreuerPanel(panelSwitcher, currentBetreuer);

            cards.add(betreuerPanel, "Betreuer");
        }
    }

    
}
