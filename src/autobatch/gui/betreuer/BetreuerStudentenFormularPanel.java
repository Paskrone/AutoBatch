package autobatch.gui.betreuer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businesslogic.actionlistener.FormularBetreuerActionListener;
import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Betreuer;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

/**
 * Das BetreuerStudentenFormularPanel dient als UI-Komponente zur bestätigung des zugehörigen Betreuer Formulars. 
 * Es enthält ein Formular zur Eingabe oder Änderung der Daten.
 */
public class BetreuerStudentenFormularPanel extends JPanel {
	/**
	 * Textfeld für das Thema.
	 */
	private JTextField tf_Thema;

	/**
	 * Textfeld für das Ausgabedatum.
	 */
	private JTextField tf_Ausgabetermin;

	/**
	 * Textfeld für das Abgabedatum.
	 */
	private JTextField tf_Abgabetermin;

	/**
	 * Prüfungsvariable für das bestandene Interdisziplinäre Projekt.
	 */
	private boolean ja = false;

	/**
	 * Prüfungsvariable, ob ein Button geklickt wurde.
	 */
	private boolean clicked = true;

	/**
	 * Erstellt ein neues BetreuerStudentenFormularPanel, das den angegebenen Betreuer und Arbeit 
	 * zur Bearbeitung anzeigt.
	 * 
	 * @param panelManager   Der PanelManager der Anwendung.
	 * @param panelSwitcher  Der PanelSwitcher der Anwendung.
	 * @param betreuer       Der zu bearbeitende Betreuer.
	 * @param arbeit         Die Arbeit, die dem Betreuer zugeordnet ist.
	 */
	public BetreuerStudentenFormularPanel(PanelManager panelManager, PanelSwitcher panelSwitcher, Betreuer betreuer,
			Arbeit arbeit) {

		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		BetreuerNavigationBar betreuerNavigationBar = new BetreuerNavigationBar(panelManager, panelSwitcher, betreuer);

		JLabel lblNewLabel = new JLabel("Als Thema der Bachelor-Arbeit wird vorgeschlagen:");

		tf_Thema = new JTextField();
		tf_Thema.setColumns(10);
		tf_Thema.setText(arbeit.getThema());

		JLabel lblNewLabel_1 = new JLabel("Ausgabetermin:");

		JLabel lblNewLabel_2 = new JLabel("Abgabetermin:");

		JLabel lblNewLabel_3 = new JLabel("Das Interdisziplinäre Projekt ist bestanden");

		JRadioButton rbJa = new JRadioButton("Ja");

		JRadioButton rbNein = new JRadioButton("Nein");

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rbJa);
		buttonGroup.add(rbNein);

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton selectedRadioButton = (JRadioButton) e.getSource();
				if (selectedRadioButton.isSelected() && selectedRadioButton.getText().equals("Ja")) {
					ja = true;
				} else if (selectedRadioButton.isSelected() && selectedRadioButton.getText().equals("Nein")) {
					ja = false;
				} else {
					clicked = false;
				}
			}
		};
		rbJa.addActionListener(actionListener);
		rbNein.addActionListener(actionListener);

		tf_Ausgabetermin = new JTextField();
		tf_Ausgabetermin.setColumns(10);

		tf_Abgabetermin = new JTextField();
		tf_Abgabetermin.setColumns(10);

		JLabel lblPopUp = new JLabel("Eingabe überprüfen!");
		lblPopUp.setVisible(false);

		JButton btnNewButton = new JButton("Bestätigen");
		btnNewButton.addActionListener(new FormularBetreuerActionListener(ja, clicked, tf_Thema, tf_Ausgabetermin,
				tf_Abgabetermin, lblPopUp, arbeit));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(betreuerNavigationBar, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(18)
						.addComponent(tf_Thema, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(513, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblNewLabel_3)
						.addContainerGap(717, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(50).addComponent(rbJa)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(rbNein).addContainerGap(830,
								Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(tf_Abgabetermin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(tf_Ausgabetermin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(737, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(lblPopUp))
								.addComponent(btnNewButton))
						.addContainerGap(875, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(
						tf_Thema, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1).addComponent(
						tf_Ausgabetermin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2).addComponent(
						tf_Abgabetermin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_3)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(rbJa).addComponent(rbNein))
				.addGap(18).addComponent(btnNewButton).addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lblPopUp).addGap(206)));
		setLayout(groupLayout);

	}
}
