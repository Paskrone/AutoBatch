package autobatch.gui.loginandregistration;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import autobatch.businesslogic.actionlistener.CreateAccountActionListener;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RegistrationPanel extends JPanel {

	private JTextField tf_Mnr;
	private JTextField tf_vorname;
	private JTextField tf_nachname;
	private JTextField tf_Email;
	private JTextField tf_telefonnummer;
	private JTextField tf_benutzername;
	private JPasswordField tf_Passwort;
	private JTextField tf_Ort;
	private JTextField tf_Postleizahl;
	private JTextField tf_Strasse;
	private JTable table;

	public RegistrationPanel(PanelSwitcher panelSwitcher, PanelManager panelManager) {

		setPreferredSize(new Dimension(1000, 500));

		JLabel lblNewLabel = new JLabel("Studenten Account erstellen");
		lblNewLabel.setForeground(new Color(92, 160, 255));
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 19));

		JLabel lblNewLabel_1 = new JLabel("Matrikelnummer:");
		lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 13));

		tf_Mnr = new JTextField();
		tf_Mnr.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Vorname:");
		lblNewLabel_1_1.setFont(new Font("Poppins", Font.PLAIN, 13));

		tf_vorname = new JTextField();
		tf_vorname.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Nachname:");
		lblNewLabel_1_1_1.setFont(new Font("Poppins", Font.PLAIN, 13));

		tf_nachname = new JTextField();
		tf_nachname.setColumns(10);

		JLabel lblNewLabel_1_1_2 = new JLabel("E-Mail:");
		lblNewLabel_1_1_2.setFont(new Font("Poppins", Font.PLAIN, 13));

		tf_Email = new JTextField();
		tf_Email.setColumns(10);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Telefonnummer:");
		lblNewLabel_1_1_2_1.setFont(new Font("Poppins", Font.PLAIN, 13));

		tf_telefonnummer = new JTextField();
		tf_telefonnummer.setColumns(10);

		JLabel lblNewLabel_1_2 = new JLabel("Studiengang:");
		lblNewLabel_1_2.setFont(new Font("Poppins", Font.PLAIN, 13));

		JLabel lblNewLabel_1_1_2_2 = new JLabel("Benutzername:");
		lblNewLabel_1_1_2_2.setFont(new Font("Poppins", Font.PLAIN, 13));

		tf_benutzername = new JTextField();
		tf_benutzername.setColumns(10);

		JLabel lblNewLabel_1_1_2_2_1 = new JLabel("Passwort:");
		lblNewLabel_1_1_2_2_1.setFont(new Font("Poppins", Font.PLAIN, 13));

		tf_Passwort = new JPasswordField();

		JLabel lbl_registrationAnzeige = new JLabel("New label");
		lbl_registrationAnzeige.setVisible(false);

		JLabel lblPostleizahl = new JLabel("Postleizahl:");

		tf_Ort = new JTextField();
		tf_Ort.setColumns(10);

		tf_Postleizahl = new JTextField();
		tf_Postleizahl.setColumns(10);

		JLabel lblOrt = new JLabel("Wohnort:");

		JLabel lblStrasse = new JLabel("Straße:");

		tf_Strasse = new JTextField();
		tf_Strasse.setColumns(10);

		JLabel lblStudiengang = new JLabel("");

		String[] columnNames = { "" };

		List<String> list = new ArrayList<>();

		list.add("WI");
		list.add("BWL");

		Object[][] data = new Object[list.size()][1];

		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i);
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) { // Überprüfen Sie, ob die Selektion vollständig ist
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) { // Überprüfen Sie, ob eine Zeile ausgewählt wurde
						// Holen Sie sich die Daten aus der ausgewählten Zeile
						String studiengang = table.getValueAt(selectedRow, 0).toString();
						lblStudiengang.setText(studiengang);
					}
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);

		JButton btn_createAccount = new JButton("Account erstellen");
		btn_createAccount.setBackground(new Color(92, 160, 255));
		btn_createAccount.addActionListener(new CreateAccountActionListener(panelSwitcher, panelManager, tf_Mnr,
				tf_vorname, tf_nachname, tf_Email, tf_telefonnummer, lblStudiengang, tf_Ort, tf_Postleizahl, tf_Strasse,
				tf_benutzername, tf_Passwort, lbl_registrationAnzeige));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(52)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addComponent(lbl_registrationAnzeige)
								.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING).addComponent(btn_createAccount)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
										.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 109,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(tf_vorname, GroupLayout.DEFAULT_SIZE, 416,
														Short.MAX_VALUE)
												.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 109,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(tf_Email, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
												.addComponent(lblNewLabel_1)
												.addComponent(tf_Mnr, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
												.addComponent(lblNewLabel_1_1_2_2, GroupLayout.PREFERRED_SIZE, 109,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(tf_benutzername, GroupLayout.DEFAULT_SIZE, 416,
														Short.MAX_VALUE)
												.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
														.createParallelGroup(Alignment.LEADING)
														.addComponent(tf_Postleizahl, GroupLayout.PREFERRED_SIZE, 104,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblPostleizahl))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblOrt).addComponent(tf_Ort,
																		GroupLayout.PREFERRED_SIZE, 269,
																		GroupLayout.PREFERRED_SIZE))))
												.addGap(22)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblStrasse)
														.addComponent(lblNewLabel_1_1_2_2_1, GroupLayout.PREFERRED_SIZE,
																109, GroupLayout.PREFERRED_SIZE)
														.addComponent(tf_telefonnummer, GroupLayout.DEFAULT_SIZE, 377,
																Short.MAX_VALUE)
														.addComponent(lblNewLabel_1_1_2_1, GroupLayout.PREFERRED_SIZE,
																109, GroupLayout.PREFERRED_SIZE)
														.addComponent(tf_nachname, GroupLayout.DEFAULT_SIZE, 377,
																Short.MAX_VALUE)
														.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE,
																109, GroupLayout.PREFERRED_SIZE)
														.addComponent(tf_Passwort).addComponent(tf_Strasse)
														.addGroup(groupLayout.createSequentialGroup()
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblStudiengang)
																		.addComponent(lblNewLabel_1_2,
																				GroupLayout.PREFERRED_SIZE, 109,
																				GroupLayout.PREFERRED_SIZE))
																.addGap(18).addComponent(scrollPane,
																		GroupLayout.PREFERRED_SIZE, 102,
																		GroupLayout.PREFERRED_SIZE))))))
								.addGap(133)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(27).addComponent(lblNewLabel).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_1).addComponent(lblNewLabel_1_2,
												GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(tf_Mnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStudiengang)))
						.addComponent(scrollPane, 0, 0, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_vorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_nachname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_telefonnummer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1_2_2, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_2_2_1, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_benutzername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_Passwort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPostleizahl)
						.addComponent(lblOrt).addComponent(lblStrasse))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_Postleizahl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_Ort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_Strasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(26).addComponent(btn_createAccount).addGap(18).addComponent(lbl_registrationAnzeige)
				.addContainerGap(45, Short.MAX_VALUE)));

		setLayout(groupLayout);

	}
}
