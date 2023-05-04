package autobatch.businesslogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import autobatch.navigation.PanelSwitcher;

public class RegistrationActionListener implements ActionListener{

	private PanelSwitcher panelSwitcher;
	
	public RegistrationActionListener(PanelSwitcher panelSwitcher) {
		this.panelSwitcher = panelSwitcher;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		panelSwitcher.switchToPanel("Registrieren");
		
	}

}
