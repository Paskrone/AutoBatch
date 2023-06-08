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

public class AblehnenActionListener implements ActionListener {

	private PanelSwitcher panelSwitcher;
	private PanelManager panelManager;

	private Student student;
	private Betreuer betreuer;

	private JLabel lblPopUp;

	public AblehnenActionListener(PanelSwitcher panelSwitcher, PanelManager panelManager, Student student,
			Betreuer betreuer, JLabel lblPopUp) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.panelManager = panelManager;
		this.student = student;
		this.betreuer = betreuer;
		this.lblPopUp = lblPopUp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

		datenbankabfrage.deleteDataArbeit(student.getMnr(), betreuer.getEmail());

		JPanel betreuerAnfragenPanel = new BetreuerAnfragenPanel(panelSwitcher, panelManager, betreuer);
		panelManager.updatePanels(betreuerAnfragenPanel, "Betreuer_Anfragen");

		lblPopUp.setText("Anfrage abgelehnt");
		lblPopUp.setVisible(true);
	}

}
