package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.student.StudentenDatenPanel;
import autobatch.navigation.PanelSwitcher;

public class StudentenDatenActionListener implements ActionListener{

private PanelSwitcher panelSwitcher;

	
	public StudentenDatenActionListener(PanelSwitcher panelSwitcher) {
		this.panelSwitcher=panelSwitcher;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		panelSwitcher.switchToPanel("Studenten_Daten");
      
	}
	
}
