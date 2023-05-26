package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.betreuer.BetreuerStudentenPanel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

public class AnnehmenActionListener implements ActionListener {

	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;
	
	private Student student;
	private Betreuer betreuer;

	private Arbeit arbeit;

	private JLabel lblPopUp;

	public AnnehmenActionListener(PanelSwitcher panelSwitcher,PanelManager panelManager, Student student, Betreuer betreuer, Arbeit arbeit, JLabel lblPopUp) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.panelManager = panelManager;
		
		this.student = student;
		this.betreuer = betreuer;
		this.arbeit = arbeit;
		this.lblPopUp = lblPopUp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		
		datenbankabfrage.updateDataStudentString(student, betreuer.getEmail(), "betreuer");

		datenbankabfrage.updateDataArbeitInt(arbeit, 1, "angenommen");

		datenbankabfrage.updateDataStudentInt(student, arbeit.getIdArbeit(), "arbeit");
		
		JPanel betreuerStudentenPanel = new BetreuerStudentenPanel(panelSwitcher, panelManager, betreuer);

		panelManager.updatePanels(betreuerStudentenPanel, "Betreuer_Studenten");

		lblPopUp.setVisible(true);

	}

}
