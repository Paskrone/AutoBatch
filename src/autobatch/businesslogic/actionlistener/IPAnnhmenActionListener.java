package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.IPAnfragen;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Studiendekan;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.betreuer.BetreuerAnfragenPanel;
import autobatch.gui.betreuer.BetreuerStudentenPanel;
import autobatch.gui.studiendekan.StudiendekanIpAnfragenPanel;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

public class IPAnnhmenActionListener implements ActionListener {

	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;

	private Student student;
	private Betreuer betreuer;
	private Studiendekan dekan;

	private Arbeit arbeit;

	private JLabel lblPopUp;

	public IPAnnhmenActionListener(Studiendekan dekan, PanelSwitcher panelSwitcher, PanelManager panelManager,
			Student student, Betreuer betreuer, Arbeit arbeit, JLabel lblPopUp) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.panelManager = panelManager;

		this.student = student;
		this.betreuer = betreuer;
		this.dekan = dekan;
		this.arbeit = arbeit;
		this.lblPopUp = lblPopUp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

		datenbankabfrage.updateDataStudentString(student, student.getBetreuer(), "betreuer");

		datenbankabfrage.updateDataArbeitBoolean(arbeit, true, "ipAngenommen");

//		datenbankabfrage.updateDataStudentInt(student, anfrage.getIdArbeit(), "arbeit");

		JPanel studiendekanAnfragenPanl = new StudiendekanIpAnfragenPanel(panelManager, panelSwitcher, dekan);

		panelManager.updatePanels(studiendekanAnfragenPanl, "studiendekanIpAnfragen");

		lblPopUp.setVisible(true);

	}

}
