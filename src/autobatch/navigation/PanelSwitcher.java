package autobatch.navigation;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class PanelSwitcher {
    private JPanel cards;

    public PanelSwitcher(JPanel cards) {
        this.cards = cards;
    }

    public void switchToPanel(String panelName) {
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, panelName);
    }
}

