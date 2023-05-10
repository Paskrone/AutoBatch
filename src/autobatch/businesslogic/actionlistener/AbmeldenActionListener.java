package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import autobatch.navigation.PanelSwitcher;

public class AbmeldenActionListener implements ActionListener {

	private PanelSwitcher panelSwitcher;

	
	public AbmeldenActionListener(PanelSwitcher panelSwitcher) {
		this.panelSwitcher=panelSwitcher;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panelSwitcher.switchToPanel("Login");
      
	}

}
