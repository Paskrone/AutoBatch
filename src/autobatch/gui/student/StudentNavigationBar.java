package autobatch.gui.student;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import autobatch.businesslogic.mouselistener.NavigationBarMouseListener;
import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Student;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

public class StudentNavigationBar extends JPanel {

	Arbeit arbeit = null;

	public StudentNavigationBar(PanelManager panelmanager, PanelSwitcher panelSwitcher, Student student) {

		if (student.getArbeit() != 0) {
			Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
			arbeit = datenbankabfrage.getArbeitByID(student.getArbeit());
		}

		setPreferredSize(new Dimension(1000, 50));

		JLabel lbl_Ip = new JLabel("IP");
		lbl_Ip.addMouseListener(new NavigationBarMouseListener() {
			@Override
			public void labelClicked() {

				if (student.getBetreuer() != null) {
					JPanel panel = new StudentenIpPanel(panelmanager, panelSwitcher, student);
					panelmanager.updatePanels(panel, "Studenten_Ip");
					panelSwitcher.switchToPanel("Studenten_Ip");
				} else {
					JPanel panel = new StudentenIP_1Panel(panelmanager, panelSwitcher, student);
					panelmanager.updatePanels(panel, "Studenten_Ip_1");
					panelSwitcher.switchToPanel("Studenten_Ip_1");
				}
				if (arbeit!=null && arbeit.getIpStart()!=null) {
					JPanel panel= new StudentenIP_2Panel(panelmanager, panelSwitcher, student);
					panelmanager.updatePanels(panel, "Studenten_Ip_2");
					panelSwitcher.switchToPanel("Studenten_Ip_2");
				}

			}
		});

		JLabel lbl_betreuer = new JLabel("Betreuer");
		lbl_betreuer.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {
				if (student.getBetreuer() == null) {
					JPanel studentenBetreuerPanel = new StudentenBetreuerPanel(panelSwitcher, panelmanager, student);
					panelmanager.updatePanels(studentenBetreuerPanel, "Studenten_Betreuer");
					panelSwitcher.switchToPanel("Studenten_Betreuer");
				} else {
					JPanel studentenBetreuer_1Panel = new StudentenBetreuer_1Panel(panelmanager, panelSwitcher,
							student);
					panelmanager.updatePanels(studentenBetreuer_1Panel, "Studenten_Betreuer_1");
					panelSwitcher.switchToPanel("Studenten_Betreuer_1");
				}

			}
		});

		JLabel lbl_abgaben = new JLabel("Abgaben");
		lbl_abgaben.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {
				JPanel panel = new StudentenAbgabenPanel(panelmanager, panelSwitcher, student);
				panelmanager.updatePanels(panel, "Studenten_Abgaben");
				panelSwitcher.switchToPanel("Studenten_Abgaben");

			}
		});

		JLabel lbl_formulare = new JLabel("BA-Anmeldeformular");
		lbl_formulare.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {

				if (arbeit != null && arbeit.getBa_Anmeldung_Student()) {
					JPanel panel = new StudentenFormulare_1Panel(panelmanager, panelSwitcher, student);
					panelmanager.updatePanels(panel, "Studenten_Formulare_1");
					panelSwitcher.switchToPanel("Studenten_Formulare_1");
				} else {
					JPanel panel = new StudentenFormularePanel(panelmanager, panelSwitcher, student);
					panelmanager.updatePanels(panel, "Studenten_Formulare");
					panelSwitcher.switchToPanel("Studenten_Formulare");
				}

				if (arbeit==null) {
					JPanel panel = new StudentenFormulare_2Panel(panelmanager, panelSwitcher, student);
					panelmanager.updatePanels(panel, "StudentenFormulare_2Panel");
					panelSwitcher.switchToPanel("StudentenFormulare_2Panel");
				}

			}
		});

		JLabel lbl_daten = new JLabel("Daten");
		lbl_daten.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {
				JPanel panel = new StudentenDatenPanel(panelmanager, panelSwitcher, student);
				panelmanager.updatePanels(panel, "Studenten_Daten");
				panelSwitcher.switchToPanel("Studenten_Daten");

			}
		});

		JLabel lbl_abmelden = new JLabel("abmelden");
		lbl_abmelden.addMouseListener(new NavigationBarMouseListener() {

			@Override
			public void labelClicked() {

				panelSwitcher.switchToPanel("Login");

			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(42)
						.addComponent(lbl_betreuer, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(lbl_Ip, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGap(28).addComponent(lbl_formulare).addGap(42)
						.addComponent(lbl_abgaben, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addGap(38).addComponent(lbl_daten)
						.addPreferredGap(ComponentPlacement.RELATED, 457, Short.MAX_VALUE).addComponent(lbl_abmelden)
						.addGap(32)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(19, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lbl_abmelden)
								.addComponent(lbl_formulare).addComponent(lbl_abgaben).addComponent(lbl_betreuer)
								.addComponent(lbl_Ip).addComponent(lbl_daten))
						.addGap(15)));
		setLayout(groupLayout);

	}
}
