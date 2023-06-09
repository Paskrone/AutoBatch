package autobatch.gui.betreuer;

import java.awt.Dimension;

import java.awt.event.MouseEvent;
import java.util.ArrayList;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businesslogic.actionlistener.AbgabeHinzufuegenActionListener;
import autobatch.businesslogic.actionlistener.ChangeNoteActionListener;
import autobatch.businesslogic.itemlistener.NdaItemListener;
import autobatch.businesslogic.mouselistener.downloadFileMouseListener;
import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JButton;

public class BetreuerStudenten_1Panel extends JPanel {

	private PanelSwitcher panelSwitcher;
	private Betreuer betreuer;
	private final ButtonGroup buttonGroupNDA = new ButtonGroup();
    private DefaultListModel<String> listModel;
    private JList<String> fileList;
    private DefaultListModel<String> listModel_1;
    private JList<String> fileList_1;
    private Datenbankabfrage dbaccess;
	

	public BetreuerStudenten_1Panel(PanelManager panelManager, PanelSwitcher panelSwitcher, Betreuer betreuer) {

		this.panelSwitcher = panelSwitcher;
		setPreferredSize(new Dimension(1000, 500));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		Datenbankabfrage dbaccess = new Datenbankabfrage();

		int mnr = Integer.parseInt(panelSwitcher.getData("2") + "");
		Student student = dbaccess.getStudentByMNR(mnr);

		int idThema = Integer.parseInt(panelSwitcher.getData("3") + "");
		Arbeit arbeit = dbaccess.getArbeitByID(idThema);

		BetreuerNavigationBar betreuerNavigationBar = new BetreuerNavigationBar(panelSwitcher);

		JLabel lblName = new JLabel("Name:");

		JLabel lblStarttermin = new JLabel("Starttermin:");

		JLabel lblEmail = new JLabel("Email:");

		JLabel lblThema = new JLabel("Thema:");

		JLabel lblIPAnfang = new JLabel("IP-Anfang:");

		JLabel lblNameIn = new JLabel("New label");

		JLabel lblThemaIN = new JLabel("New label");

		JLabel lblNewLabel = new JLabel("Matrikelnummer:");

		JLabel lblNewLabel_1 = new JLabel("Abgabetermin:");

		JLabel lblNewLabel_2 = new JLabel("NDA benötigt:");

		JLabel lblNewLabel_3 = new JLabel("Semester:");

		JLabel lblNewLabel_4 = new JLabel("IP-Ende:");

		JLabel lblNewLabel_5 = new JLabel("Note Arbeit:");

		JLabel lblmnrIN = new JLabel("");

		JLabel lblAbgabeterminIN = new JLabel("New label");

		JCheckBox chckbxNDAJa = new JCheckBox("Ja");
		buttonGroupNDA.add(chckbxNDAJa);

		JCheckBox chckbxNDANein = new JCheckBox("Nein");
		buttonGroupNDA.add(chckbxNDANein);

		chckbxNDAJa.setSelected(arbeit.getNda_notwenidg());
		chckbxNDANein.setSelected(!arbeit.getNda_notwenidg());

		chckbxNDAJa.addItemListener(new NdaItemListener(arbeit, chckbxNDAJa, true));
		chckbxNDANein.addItemListener(new NdaItemListener(arbeit, chckbxNDANein, false));

		JLabel lblNewLabel_6 = new JLabel("New label");

		JLabel lblNEmailIN = new JLabel("");

		JLabel lblNewLabel_8 = new JLabel("New label");

		JLabel lblSemesterIN = new JLabel("New label");

		if (student != null) {
			lblNameIn.setText(student.getVorname() + " " + student.getNachname());
			lblThemaIN.setText(arbeit.getThema());
			lblmnrIN.setText(student.getMnr() + "");
			lblNEmailIN.setText(student.getEmail());
		}

		JLabel lblNoteVortragIN = new JLabel("New label");

		JLabel lblGesamtnoteIN = new JLabel("New label");

		JLabel lblNoteArbeitIN = new JLabel("");
		if (arbeit.getNoteArbeit() != 0) {
			lblNoteArbeitIN.setText(arbeit.getNoteArbeit() + "");
		}
		if (arbeit.getNoteVortrag()!=0) {
			lblNoteVortragIN.setText(arbeit.getNoteVortrag() + "");
		}
		if (arbeit.getGesamtnote()!=0) {
			lblGesamtnoteIN.setText(arbeit.getGesamtnote()+"");
		}

		JLabel lblNewLabel_11 = new JLabel("New label");
		
		JLabel lblBetreuerAbgaben = new JLabel("Bisherige Abgaben:");
		
		JLabel lblStudentAbgaben = new JLabel("Student:");
		
		JLabel lbl_success = new JLabel("Dokument erfolgreich hochgeladen!");
		lbl_success.setForeground(new Color(61, 255, 30));
		lbl_success.setVisible(false);
		
		JLabel lbl_error = new JLabel("Fehler beim Hochladen");
		lbl_error.setForeground(new Color(251, 44, 22));
		lbl_error.setVisible(false);
		
		listModel = new DefaultListModel<>();
        ArrayList<String> submissions = (ArrayList<String>) dbaccess.getSubmissions(betreuer.getBenutzername());
        for(String submission : submissions) {
            listModel.addElement(submission);
        }
        
        listModel_1 = new DefaultListModel<>();
        ArrayList<String> submissions_1 = (ArrayList<String>) dbaccess.getSubmissions(student.getBenutzername());
        System.out.println(student.getBenutzername());
        for(String submission : submissions_1) {
            listModel_1.addElement(submission);
        }
		
		fileList = new JList<>(listModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        fileList_1 = new JList<>(listModel_1);
        fileList_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane fileListScrollPane = new JScrollPane(fileList);
		
		JScrollPane fileListScrollPane_1 = new JScrollPane(fileList_1);
		
		fileList_1.addMouseListener(new downloadFileMouseListener() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) { // Double-click
                    int index = list.locationToIndex(evt.getPoint());
                    String filename = listModel.getElementAt(index);
                    downloadFile(filename);
                }
            }
        });
		
		fileList.addMouseListener(new downloadFileMouseListener() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) { // Double-click
                    int index = list.locationToIndex(evt.getPoint());
                    String filename = listModel.getElementAt(index);
                    downloadFile(filename);
                }
            }
        });
		
		JButton btn_uploadDocument = new JButton("Dokument Hochladen");
		btn_uploadDocument.addActionListener(new AbgabeHinzufuegenActionListener(lbl_success, lbl_error));
		
		
		
		// Hier den upload der PDF Dateien für den Betreuer ermöglichen
		
		
		
		// Hier das einsehen der Abagben des Studenten ermöglichen
		

		JButton btnNote = new JButton("Note Arbeit ändern");
		btnNote.addActionListener(new ChangeNoteActionListener(panelManager, panelSwitcher, betreuer, arbeit));

		JLabel lblNewLabel_7 = new JLabel("Note Vortrag:");

		JLabel lblNewLabel_9 = new JLabel("Gesamtnote:");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(betreuerNavigationBar, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEmail)
					.addContainerGap(946, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblThema)
						.addComponent(lblIPAnfang)
						.addComponent(lblNewLabel_5))
					.addGap(74)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_9)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_8)
								.addComponent(lblNameIn)
								.addComponent(lblThemaIN)
								.addComponent(lblNewLabel_6)
								.addComponent(lblNewLabel_7))
							.addGap(227)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_4))
							.addGap(79)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_11)
								
								.addGroup(groupLayout.createSequentialGroup()

									.addComponent(chckbxNDAJa)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckbxNDANein))
								.addComponent(lblAbgabeterminIN)
								.addComponent(lblmnrIN))))
					.addContainerGap(258, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(46)
							.addComponent(lblStarttermin))
						.addComponent(lblStudentAbgaben, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(864, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(fileListScrollPane, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_success)
						.addComponent(lbl_error))
					.addContainerGap(480, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(fileListScrollPane_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(724, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBetreuerAbgaben)
					.addContainerGap(864, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btn_uploadDocument)
					.addContainerGap(867, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(betreuerNavigationBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(lblNameIn)
						.addComponent(lblNewLabel)
						.addComponent(lblmnrIN))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStarttermin)
						.addComponent(lblNewLabel_1)
						.addComponent(lblAbgabeterminIN)
						.addComponent(lblNewLabel_6))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(lblNewLabel_2)
						.addComponent(chckbxNDAJa)
						.addComponent(chckbxNDANein)
						.addComponent(lblNewLabel_7))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblThema)
						.addComponent(lblThemaIN)
						.addComponent(lblNewLabel_3)
						)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIPAnfang)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_8)
						.addComponent(lblNewLabel_11))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_9))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblBetreuerAbgaben)
							.addGap(5)
							.addComponent(fileListScrollPane, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(btn_uploadDocument)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblStudentAbgaben)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fileListScrollPane_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addComponent(lbl_success)
							.addGap(18)
							.addComponent(lbl_error)))
					.addContainerGap(22, Short.MAX_VALUE))
		);

		setLayout(groupLayout);

	}
}
