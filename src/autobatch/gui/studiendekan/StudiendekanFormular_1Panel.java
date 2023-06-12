package autobatch.gui.studiendekan;

import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.FormularStudiendekanActionListener;
import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Studiendekan;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * Das StudiendekanFormular_1Panel ist ein JPanel, das das erste Formular für den Studiendekan darstellt.
 * Es ermöglicht die Eingabe von Ausgabetermin und Abgabetermin einer Arbeit.
 */
public class StudiendekanFormular_1Panel extends JPanel {

    /**
     * Das Textfeld für den Ausgabetermin.
     */
    private JTextField tf_Ausgabetermin;

    /**
     * Das Textfeld für den Abgabetermin.
     */
    private JTextField tf_Abgabetermin;

    /**
     * Erstellt ein neues StudiendekanFormular_1Panel.
     *
     * @param panelmanager Der PanelManager, der für die Navigation zwischen den Panels zuständig ist.
     * @param panelSwitcher Der PanelSwitcher, der für das Umschalten zwischen den Panels verantwortlich ist.
     * @param studiendekan Der Studiendekan, für den das Formular angezeigt wird.
     */
	public StudiendekanFormular_1Panel(PanelManager panelmanager, PanelSwitcher panelSwitcher,
			Studiendekan studiendekan) {

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		StudiendekanNavigationBar studiendekanNavBar = new StudiendekanNavigationBar(panelmanager, panelSwitcher,
				studiendekan);
		
		
		int idArbeit =(int) panelSwitcher.getData("3");
		
		Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
		
		Arbeit arbeit = datenbankabfrage.getArbeitByID(idArbeit);
		
		JLabel lblNewLabel = new JLabel("Name:");
		
		JLabel lblNewLabel_1 = new JLabel("Ausgabetermin:");
		
		JLabel lblNewLabel_2 = new JLabel("Abgabetermin:");
		
		JLabel lblName = new JLabel(studiendekan.getVorname() + " " + studiendekan.getNachname());
		
		tf_Ausgabetermin = new JTextField();
		tf_Ausgabetermin.setColumns(10);
		
		tf_Abgabetermin = new JTextField();
		tf_Abgabetermin.setColumns(10);
		
		if (arbeit.getBaStart()!=null && arbeit.getBaAbgabetermin()!=null) {
			tf_Ausgabetermin.setText(arbeit.getBaStart());
			tf_Abgabetermin.setText(arbeit.getBaAbgabetermin());
		}
		
		JLabel lblPopUp = new JLabel("Eingabe überprüfen");
		lblPopUp.setVisible(false);

		
		JButton btnBestätigen = new JButton("bestätigen");
		btnBestätigen.addActionListener(new FormularStudiendekanActionListener(tf_Ausgabetermin, tf_Abgabetermin, lblPopUp, arbeit));
		
		
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(studiendekanNavBar, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_2))
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(tf_Ausgabetermin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_Abgabetermin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(705, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblPopUp))
						.addComponent(btnBestätigen))
					.addContainerGap(874, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(studiendekanNavBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(tf_Ausgabetermin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(tf_Abgabetermin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(btnBestätigen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPopUp)
					.addGap(257))
		);
		setLayout(groupLayout);

	}

}
